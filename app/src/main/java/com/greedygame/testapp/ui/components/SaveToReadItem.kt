package com.greedygame.testapp.ui.components

/**
 * Save to read item layout design (Compose)
 */
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.greedygame.testapp.data.response.Article
import com.greedygame.testapp.ui.news_dashboard.NewsPageViewModel
import com.greedygame.testapp.ui.news_description.NewsDescriptionActivity
import com.greedygame.testapp.ui.theme.*


@Composable
fun SaveToReadItem(data: Article, context: Context) {
    Column(
        modifier = Modifier
            .padding(start = 30.dp, end = 30.dp)
            .fillMaxWidth()
    ) {
        Spacer(Modifier.height(10.dp))
        Image(
            painter = rememberImagePainter(data.urlToImage),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(shape = RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(10.dp))
        Text(
            text = data.title!!,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = black,
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = data.description!!,
            maxLines = 3,

            overflow = TextOverflow.Ellipsis,
            fontSize = 16.sp,
            color = black,
            modifier = Modifier.padding(5.dp)
        )
        Spacer(Modifier.height(10.dp))

        Button(
            onClick = {
                val intent = Intent(context, NewsDescriptionActivity::class.java)
                intent.putExtra(NEWS_KEY,data.id)
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = newsButtonColor),
            modifier = Modifier
                .padding(horizontal = 12.dp),
            shape = RoundedCornerShape(6.dp),
        ) {
            Text(
                text = "Read",
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }
        Spacer(Modifier.height(10.dp))
        Divider(
            color = deviderColor,
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
        )
    }
}