package com.greedygame.testapp.ui.save_to_read

/**
 * get save to read list from Base application class
 */
import com.greedygame.testapp.ui.BaseApplication
import com.greedygame.testapp.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SaveToReadViewModel@Inject constructor(private val baseApplication: BaseApplication) : BaseViewModel(baseApplication){
    var listOfSaveToReadNews= baseApplication.saveToReadList
}