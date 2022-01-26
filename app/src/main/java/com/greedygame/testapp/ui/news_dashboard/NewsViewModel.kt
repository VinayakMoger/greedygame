package com.greedygame.testapp.ui.news_dashboard

/**
 * Business Logic implementation for Dashboard page
 */

import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.greedygame.testapp.BuildConfig.API_KEY
import com.greedygame.testapp.data.remote.Status
import com.greedygame.testapp.data.repository.NewRepository
import com.greedygame.testapp.data.response.Article
import com.greedygame.testapp.ui.BaseApplication
import com.greedygame.testapp.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

@HiltViewModel
class NewsPageViewModel @Inject constructor(private val baseApplication: BaseApplication, private val repository: NewRepository) : BaseViewModel(baseApplication){
    var newsResponse = mutableListOf<Article>()
    var tempList = mutableListOf<Article>()

    /**
     * API_KEY is added as Build Config field
     * call news API
     */
    fun getNews() {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val currentDate = sdf.format(Date())
        viewModelScope.launch{
            isSuccess.value = false
            val params: MutableMap<String, String> = HashMap()
            params["q"] = "tesla"
            params["from"] = currentDate
            params["sortBy"] = "publishedAt"
            params["apiKey"] = API_KEY

            repository.getNews(params).collect {
                isLoading.value = it.status == Status.LOADING
                if (it.status == Status.ERROR)  Toast.makeText(baseApplication,it.message!!, Toast.LENGTH_LONG).show()
                else if (it.status == Status.SUCCESS) {
                    newsResponse = (it.data as MutableList<Article>?)!!
                    tempList = newsResponse
                    isSuccess.value = true

                }
            }
        }
    }

    /**
     * add the save to read items
     */
    fun saveToReadItem(article: Article,index:Int) {
        isSuccess.value = false
        article.isSaveToRead = true
        newsResponse[index] = article
        tempList[index] = article
        baseApplication.addItem(article)
        isSuccess.value = true
    }

    /**
     * Search by title
     */
    fun filter(title:String) {
        isSuccess.value = false
        newsResponse = if(title.isNotEmpty()) {
            val temp = newsResponse.filter {
                it.title!!.contains(title,true)
            }
            temp as MutableList<Article>
        } else {
            tempList
        }
        isSuccess.value = true
    }

    /**
     * Check the list to navigate save to read
     */
    fun isSaveToReadAvailable()= baseApplication.saveToReadList.isNotEmpty()

    fun convertDate(inputDate:String):String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",Locale.getDefault())
        var convertedDate: Date? = null
        var formattedDate=inputDate
        try {
            convertedDate = sdf.parse(inputDate)
            formattedDate = SimpleDateFormat("dd-MM-yyyy",Locale.getDefault()).format(convertedDate)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return formattedDate
    }
}
