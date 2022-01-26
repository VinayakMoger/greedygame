package com.greedygame.testapp.ui.news_dashboard

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.greedygame.testapp.R
import com.greedygame.testapp.ui.components.NewsItem
import com.greedygame.testapp.ui.save_to_read.SaveToReadActivity
import com.greedygame.testapp.ui.theme.BgColor

@Composable
fun DashBoardPage(viewModel: NewsPageViewModel, context: Context) {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    if (viewModel.isSuccess.collectAsState().value) {
        Surface(
            color = BgColor,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    OutlinedTextField(
                        value = textState.value, onValueChange = {
                            textState.value = it
                            viewModel.filter(it.text)
                        },
                        textStyle = TextStyle(fontSize = 17.sp),
                        modifier = Modifier
                            .padding(10.dp)
                            .weight(1F)
                            .background(Color(0xFFE7F1F1), RoundedCornerShape(16.dp)),
                        leadingIcon = {
                            Icon(Icons.Filled.Search, null, tint = Color.Gray)
                        },
                        placeholder = {
                            Text(text = "Title")
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            backgroundColor = Color.Transparent,
                            cursorColor = Color.DarkGray
                        )

                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    IconButton(onClick = {
                        if(viewModel.isSaveToReadAvailable()) {
                            val intent = Intent(context,SaveToReadActivity::class.java)
                            context.startActivity(intent)
                        }
                    }) {
                        Icon(
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp),
                            painter = painterResource(R.drawable.ic_bookmark_black_24dp),
                            contentDescription = null
                        )
                    }
                }
                Spacer(Modifier.height(10.dp))
                LazyColumn {
                    items(viewModel.newsResponse.size) { index ->
                        NewsItem(data = viewModel.newsResponse[index], index, viewModel, context)
                    }
                }
            }

        }

    }

}