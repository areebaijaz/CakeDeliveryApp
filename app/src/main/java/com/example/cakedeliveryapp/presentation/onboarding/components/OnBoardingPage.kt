package com.loc.newsapp.presentation.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.example.cakedeliveryapp.R
import com.example.cakedeliveryapp.presentation.Dimens.mediumPadding2
import com.example.cakedeliveryapp.presentation.Dimens.pageIndicatorWidth
import com.example.cakedeliveryapp.presentation.WindowInfo
import com.example.cakedeliveryapp.presentation.onboarding.OnBoardingViewModel
import com.example.cakedeliveryapp.presentation.onboarding.components.CakeButton
import com.example.cakedeliveryapp.presentation.onboarding.components.CakeTextButton
import com.loc.newsapp.presentation.onboarding.Page
import com.loc.newsapp.presentation.onboarding.pages
import kotlinx.coroutines.launch


@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page,
    pagerState: PagerState,
    buttonState: List<String>,
    viewModel: OnBoardingViewModel,
    windowInfo: WindowInfo
) {
    val context = LocalContext.current
    val isExpand = windowInfo.screenWidthInfo is WindowInfo.WindowType.Expanded
    val isCompact = windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact
    val isMedium = windowInfo.screenWidthInfo is WindowInfo.WindowType.Medium
    val boxModifier = if (!isCompact) {
        Modifier
            .fillMaxSize()
            .navigationBarsPadding()
    } else {
        Modifier.fillMaxSize()
    }

    Box(modifier = boxModifier) {
        val painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context)
                .data(
                    if (isExpand)
                        page.expandedImage else page.compactImage
                ).scale(Scale.FIT).crossfade(true).build()
        )


        Image(
            modifier = Modifier
                .fillMaxSize()
                .alpha(if (isExpand) 0.6f else 0.7f),
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop

        )


        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Expanded) 0.2f else 0.9f),
                            Color.Transparent
                        )
                    )
                )
                .padding(40.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            val customModifier = if (isCompact) {
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)


            } else {
                Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(horizontal = 20.dp)
            }
            Text(
                text = page.title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = if (isExpand) 50.sp else 30.sp
                ),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                modifier = customModifier
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(25.dp)
        ) {
            IndicatorAndButton(viewModel, pagerState, buttonState,windowInfo)

        }


    }

}

@Composable
fun IndicatorAndButton(
    viewModel: OnBoardingViewModel,
    pagerState: PagerState,
    buttonState: List<String>,
    windowInfo: WindowInfo
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(mediumPadding2),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PageIndicator(
            modifier = Modifier.width(pageIndicatorWidth),
            pageSize = pages.size,
            selectedPage = pagerState.currentPage
        )


        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            val scope = rememberCoroutineScope()
            if (buttonState[0].isNotEmpty()) {
                CakeTextButton(
                    text = buttonState[0],
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage > 0) {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }

                    },
                )
            }

            CakeButton(
                text = buttonState[1],
                onClick = {
                    scope.launch {
                        if (pagerState.currentPage == pages.lastIndex) {
                            viewModel.saveEntry()

                        } else {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                },
                windowInfo = windowInfo
            )


        }
    }
}
