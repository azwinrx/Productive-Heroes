package com.azwin.dotask.View.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp // <-- Import yang diperlukan
import com.azwin.dotask.R
import com.azwin.dotask.ui.theme.jersey25

@Composable
fun StatisticBar(progress: Float, color: Color, text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .height(24.dp)
            .background(color = Color.Black.copy(alpha = 0.68f))
            .border(width = 1.5.dp, color = Color.Black, shape = RoundedCornerShape(5.dp)),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(fraction = progress)
                .fillMaxHeight()
                .background(color = color, shape = RoundedCornerShape(5.dp))
        )
        Box(

            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(
                fontFamily = jersey25,
                text = text,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(apiLevel = 35)
@Composable
fun StatBarPreview() {
    StatisticBar(
        progress = 0.7f,
        color = colorResource(R.color.Red),
        text = "70%"
    )
}
