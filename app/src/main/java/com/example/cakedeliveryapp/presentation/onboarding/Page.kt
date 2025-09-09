package com.loc.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.cakedeliveryapp.R

data class Page(
    val title : String,
    @DrawableRes val compactImage : Int,
//    @DrawableRes val mediumImage : Int,
    @DrawableRes val expandedImage : Int,
)

val pages = listOf(
    Page(
        title = "Welcome to the SweetBite",
        compactImage = R.drawable.cake_pink,
        expandedImage = R.drawable.pink_glitter_cake





    ),
    Page(
        title = "Celebrate every moment with SweetBite",
        compactImage = R.drawable.cute_family_cake,
        expandedImage = R.drawable.expanded_cake_with_family

    ),
    Page(
        title = "Get your cake fresh and fast anytime,anywhere",
        compactImage = R.drawable.cake_in_box,
        expandedImage = R.drawable.pink_expanded_cake_delivery
    ),


    )