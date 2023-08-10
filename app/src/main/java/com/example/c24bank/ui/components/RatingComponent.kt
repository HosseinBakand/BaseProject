package com.example.c24bank.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.c24bank.R
import kotlin.math.floor

@Composable
fun RatingComponent(rating: Float){//todo setMax
    val count = floor(rating / 0.5f)

    val fullStarCount = floor(count / 2).toInt()
    val iaHalfExist = count.toInt() % 2 == 1
    val borderStarCount = 5 - fullStarCount - count.toInt() % 2


    val borderStarPainter = painterResource(id = R.drawable.ic_star)
    val fullStarPainter = painterResource(id = R.drawable.ic_star_full)
    val halfStarPainter = painterResource(id = R.drawable.ic_star_half)

    Row(modifier = Modifier) {
        for (i in 0 until fullStarCount) {
            Image(painter = fullStarPainter, contentDescription = null)
        }
        if (iaHalfExist) {
            Image(painter = halfStarPainter, contentDescription = null)
        }

        for (i in 0 until borderStarCount) {
            Image(painter = borderStarPainter, contentDescription = null)
        }
    }
}