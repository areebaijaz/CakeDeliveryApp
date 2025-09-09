package com.example.cakedeliveryapp.presentation.detail


import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cakedeliveryapp.presentation.Dimens.cardDesSize
import com.example.cakedeliveryapp.presentation.Dimens.cardTextSize
import com.example.cakedeliveryapp.presentation.Dimens.imageInDetail
import com.example.cakedeliveryapp.presentation.Dimens.paddingInDetail
import com.example.cakedeliveryapp.presentation.Dimens.smallPadding
import com.example.cakedeliveryapp.presentation.Dimens.spaceInDetail


fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition()
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000)
        )
    ).value
    background(color = Color(0xFFC3C3C3).copy(alpha = alpha))

}

@Composable
fun Shimmer(
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingInDetail),
        verticalArrangement = Arrangement.spacedBy(spaceInDetail)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = smallPadding)
                .height(imageInDetail)
                .clip(RoundedCornerShape(12.dp))
                .shimmerEffect(),
        )
        Spacer(modifier = Modifier.height(7.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(cardTextSize)
                .shimmerEffect()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(cardTextSize)
                .shimmerEffect()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(cardDesSize)
                .shimmerEffect()
        )

        Spacer(modifier = Modifier.height(7.dp))
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .shimmerEffect(),
            shape = RoundedCornerShape(50.dp)
        ) {}


    }
}