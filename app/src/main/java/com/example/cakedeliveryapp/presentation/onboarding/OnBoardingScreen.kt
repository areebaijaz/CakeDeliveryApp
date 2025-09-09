package com.loc.newsapp.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cakedeliveryapp.presentation.onboarding.components.CakeButton
import com.example.cakedeliveryapp.presentation.onboarding.components.CakeTextButton
import com.example.cakedeliveryapp.presentation.Dimens.mediumPadding2
import com.example.cakedeliveryapp.presentation.Dimens.pageIndicatorWidth
import com.example.cakedeliveryapp.presentation.WindowInfo
import com.example.cakedeliveryapp.presentation.onboarding.OnBoardingViewModel
import com.loc.newsapp.presentation.onboarding.components.OnBoardingPage
import com.loc.newsapp.presentation.onboarding.components.PageIndicator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingViewModel = hiltViewModel(),
    windowInfo: WindowInfo
) {
Column(
    modifier = Modifier.fillMaxSize()
) {
    val pagerState = rememberPagerState(initialPage = 0) {
        pages.size
    }

    val buttonState = remember {
        derivedStateOf {
            when(pagerState.currentPage){
                0 -> listOf("","Next")
                1 -> listOf("Back","Next")
                2 -> listOf("Back","Get Started")
                else -> listOf("","")
            }
        }
    }

    HorizontalPager(state = pagerState) { index ->
        OnBoardingPage(page = pages[index], pagerState = pagerState, buttonState = buttonState.value, viewModel = viewModel, windowInfo = windowInfo)

    }



}
}