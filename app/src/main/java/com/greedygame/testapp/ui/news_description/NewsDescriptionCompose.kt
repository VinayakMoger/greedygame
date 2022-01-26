package com.greedygame.testapp.ui.news_description

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.greedygame.testapp.data.response.Article
import com.greedygame.testapp.ui.theme.BgColor


/**
 * Description page design implementation (Compose)
 */

@Composable
fun DescriptionCompose(viewModel: NewsDescriptionViewModel) {
    if(viewModel.isSuccess.collectAsState().value) {
        val article = viewModel.article
        Scaffold {
            ImageCard(article)
            Surface(
                color = BgColor,
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = 200.dp),
                shape = RoundedCornerShape(40.dp).copy(
                    bottomStart = ZeroCornerSize,
                    bottomEnd = ZeroCornerSize
                )
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(start = 20.dp,top = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    item{
                        Text("Author : ${article.author}",color = Color.Red)
                    }
                    item{
                        Spacer(Modifier.height(20.dp))
                    }
                    item {
                        Text(article.description!!,fontSize = 18.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun ImageCard(article: Article) {
    Surface(modifier = Modifier.height(240.dp), color = Color.Black) {
        Image(painter = rememberImagePainter(article.urlToImage),contentScale = ContentScale.FillBounds,contentDescription = "",modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth())
        Text(article.title!!,color = Color.White,fontSize = 20.sp,fontWeight = FontWeight.Bold,modifier = Modifier.padding(20.dp ))
    }
}