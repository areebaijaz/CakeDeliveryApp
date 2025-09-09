package com.example.cakedeliveryapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.cakedeliveryapp.presentation.navgraph.NavGraph
import com.example.compose.CakeDeliveryAppTheme
import com.loc.newsapp.presentation.onboarding.OnBoardingScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel by viewModels<MainViewModel> ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        installSplashScreen().apply {
            setKeepOnScreenCondition{
                Log.d("SplashCheck", "Condition: ${viewModel.splashCondition}")
                viewModel.splashCondition
        }
        }
        enableEdgeToEdge()
        setContent {
            CakeDeliveryAppTheme {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val startDestination = viewModel.startDestination
                    NavGraph(startDestination)
                }

            }
        }
    }
}
