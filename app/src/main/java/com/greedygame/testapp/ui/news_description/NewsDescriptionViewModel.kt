package com.greedygame.testapp.ui.news_description

/**
 * Business Logic implementation for news description page
 */
import androidx.lifecycle.viewModelScope
import com.greedygame.testapp.data.repository.NewRepository
import com.greedygame.testapp.data.response.Article
import com.greedygame.testapp.ui.BaseApplication
import com.greedygame.testapp.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDescriptionViewModel@Inject constructor(private val baseApplication: BaseApplication, private val repository: NewRepository) : BaseViewModel(baseApplication){
    lateinit var article: Article

    /**
     * Get news item from room table
     */
    fun getNewsItem(id:Int) {
        viewModelScope.launch {
            isSuccess.value = false
            repository.getNewsItem(id).collect {
                article = it
                isSuccess.value = true
            }
        }
    }
}
