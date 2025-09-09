package com.example.cakedeliveryapp.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cakedeliveryapp.domain.model.Cake
import com.example.cakedeliveryapp.presentation.Dimens.CardImageSize
import com.example.cakedeliveryapp.presentation.Dimens.ExtraSmallPadding
import com.example.cakedeliveryapp.presentation.Dimens.ExtraSmallPadding2
import com.example.cakedeliveryapp.presentation.Dimens.smallPadding
import com.example.cakedeliveryapp.presentation.WindowInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    onClick: (String) -> Unit,
    windowInfo : WindowInfo

) {

    val expanded = windowInfo.screenWidthInfo is WindowInfo.WindowType.Expanded

    val cakes by homeViewModel.state.collectAsState()
    val isLoading = homeViewModel.isLoading

    if (isLoading) {
        ShimmerGrid(if(!expanded)
            8 else 6,windowInfo)
    } else {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Cakes",style = MaterialTheme.typography.titleMedium.copy(fontSize = if(expanded) 30.sp else 24.sp),
                        modifier = Modifier.padding(start = 5.dp)) },

                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            },

            ) { paddingValues ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(if(!expanded)ExtraSmallPadding2 else 15.dp),
                contentPadding = paddingValues,
                verticalArrangement = Arrangement.spacedBy(ExtraSmallPadding),
                horizontalArrangement = Arrangement.spacedBy(ExtraSmallPadding)

            ) {
                items(cakes.size) { index ->
                    val cake = cakes[index]
                    HomeGridItem(cake, onCakeClick = { onClick(cake.id) },windowInfo)


                }
            }
        }
    }
}

@Composable
fun HomeGridItem(cake: Cake, onCakeClick: () -> Unit,windowInfo: WindowInfo) {


    val expanded = windowInfo.screenWidthInfo is WindowInfo.WindowType.Expanded
        val context = LocalContext.current
        Column(
            modifier = Modifier.padding(if(!expanded)ExtraSmallPadding2 else 13.dp),
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(if(!expanded)CardImageSize else 410.dp )
                    .clip(RoundedCornerShape(smallPadding))
                    .clickable {
                        onCakeClick()
                    },
                model = ImageRequest.Builder(context)
                    .data(cake.imageUrl)
                    .build(),

                contentDescription = null,
                contentScale = if(!expanded)  ContentScale.Crop else ContentScale.None
            )
            Spacer(modifier = Modifier.height(smallPadding))
            Text(
                text = cake.name,
                style = MaterialTheme.typography.headlineSmall.copy(fontSize = if(expanded) 26.sp else 20.sp),
                modifier = Modifier.wrapContentHeight().padding(start = 4.dp)

            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Price : ${cake.price}",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = if(expanded) 24.sp else 18.sp),
                modifier = Modifier.padding(start = 4.dp)
            )
               Spacer(modifier = Modifier.weight(0.5f))


    }


}

