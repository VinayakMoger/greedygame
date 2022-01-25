package com.greedygame.testapp.ui

import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel(myApplication: BaseApplication):AndroidViewModel(myApplication) {
    var isSuccess = MutableStateFlow(false)
    var isLoading = MutableStateFlow(false)
}