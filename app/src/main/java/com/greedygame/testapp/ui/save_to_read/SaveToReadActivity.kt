package com.greedygame.testapp.ui.save_to_read

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SaveToReadActivity : ComponentActivity() {
    private val viewModel: SaveToReadViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold {
                SaveToReadPage(viewModel.listOfSaveToReadNews,this@SaveToReadActivity)

            }
        }
    }
}
