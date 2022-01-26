package com.greedygame.testapp.ui.news_dashboard


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
import javax.inject.Inject

@HiltViewModel
class NewsPageViewModel @Inject constructor(private val baseApplication: BaseApplication, private val repository: NewRepository) : BaseViewModel(baseApplication){
    var newsResponse = mutableListOf<Article>()
    var tempList = mutableListOf<Article>()

    fun getNews() {
        viewModelScope.launch{
            isSuccess.value = false
            val params: MutableMap<String, String> = HashMap()
            params["q"] = "tesla"
            params["from"] = "2021-12-26"
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
    fun saveToReadItem(article: Article,index:Int) {
        article.isSaveToRead = true
        newsResponse[index] = article
        tempList[index] = article
        baseApplication.addItem(article)
    }

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
    fun isSaveToReadAvailable()= baseApplication.saveToReadList.isNotEmpty()
}
