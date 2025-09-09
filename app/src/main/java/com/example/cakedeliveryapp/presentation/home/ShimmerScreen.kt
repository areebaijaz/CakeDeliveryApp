package com.example.cakedeliveryapp.presentation.home

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cakedeliveryapp.presentation.Dimens.CardImageSize
import com.example.cakedeliveryapp.presentation.Dimens.ExtraSmallPadding
import com.example.cakedeliveryapp.presentation.Dimens.ExtraSmallPadding2
import com.example.cakedeliveryapp.presentation.Dimens.cardTextSize
import com.example.cakedeliveryapp.presentation.Dimens.paddingInDetail
import com.example.cakedeliveryapp.presentation.Dimens.smallPadding
import com.example.cakedeliveryapp.presentation.Dimens.topBarHeight
import com.example.cakedeliveryapp.presentation.WindowInfo

@Composable
fun  ShimmerGrid(
    itemCount: Int,
    windowInfo: WindowInfo
) {
    val compact = windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact
    val medium = windowInfo.screenWidthInfo is WindowInfo.WindowType.Medium
    val expanded = windowInfo.screenWidthInfo is WindowInfo.WindowType.Expanded
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(topBarHeight).shimmer()
        )
            Spacer(modifier = Modifier.height(paddingInDetail))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(if(!expanded)ExtraSmallPadding2 else 15.dp),
                contentPadding = PaddingValues(ExtraSmallPadding2),
                verticalArrangement = Arrangement.spacedBy(ExtraSmallPadding),
                horizontalArrangement = Arrangement.spacedBy(ExtraSmallPadding)

            ) {
                items(itemCount) {
                    ShimmerGridItem(windowInfo)
                }

        }
    }
}



fun Modifier.shimmer() = composed {
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
fun ShimmerGridItem(
    windowInfo: WindowInfo
) {
    val compact = windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact
    val medium = windowInfo.screenWidthInfo is WindowInfo.WindowType.Medium
    val expanded = windowInfo.screenWidthInfo is WindowInfo.WindowType.Expanded

        Column(
            modifier = Modifier.padding(if(!expanded)ExtraSmallPadding2 else 13.dp),
            verticalArrangement = Arrangement.spacedBy(ExtraSmallPadding)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .height(if(!expanded)CardImageSize else 410.dp )
                    .clip(RoundedCornerShape(smallPadding))
                    .shimmer()
            )
            Spacer(modifier = Modifier.height(smallPadding))
           Box(
               modifier = Modifier.fillMaxWidth().height(cardTextSize).shimmer()
           )
            Spacer(modifier = Modifier.height(2.dp))
            Box(
                modifier = Modifier.fillMaxWidth(0.5f).height(cardTextSize).shimmer()
            )
            Spacer(modifier = Modifier.weight(0.5f))


    }


}