package com.example.cakedeliveryapp.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.cakedeliveryapp.presentation.Dimens.imageInDetail
import com.example.cakedeliveryapp.presentation.Dimens.paddingInDetail
import com.example.cakedeliveryapp.presentation.Dimens.smallPadding
import com.example.cakedeliveryapp.presentation.Dimens.spaceInDetail
import com.example.cakedeliveryapp.presentation.WindowInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    detailViewModel: DetailViewModel = hiltViewModel(),
    navController: NavController,
    onClick: (String) -> Unit,
    windowInfo: WindowInfo
) {
    val compact = windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact
    val medium = windowInfo.screenWidthInfo is WindowInfo.WindowType.Medium
    val expanded = windowInfo.screenWidthInfo is WindowInfo.WindowType.Expanded

    val isLoading = detailViewModel.isLoading
    val cake = detailViewModel.cake.collectAsState()

    if (isLoading) {
        Shimmer()
    } else {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Card Details",
                            style = MaterialTheme.typography.titleMedium.copy(fontSize = if(expanded) 30.sp else 24.sp),
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    },
                    navigationIcon =
                        {
                            IconButton(
                                onClick = {
                                    navController.popBackStack()
                                },
                                colors = IconButtonDefaults.iconButtonColors(
                                    contentColor = MaterialTheme.colorScheme.onPrimary
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = null,

                                    )

                            }
                        },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            },

            ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(if(!expanded)paddingInDetail else 23.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(spaceInDetail)
            ) {
                AsyncImage(
                    model = cake.value?.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = smallPadding)
                        .height(if(!expanded) imageInDetail else 660.dp )
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(7.dp))
                cake.value?.name?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleMedium.copy(fontSize = if(expanded)30.sp else 24.sp),
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = "₨ ${cake.value?.price}",
                    color = MaterialTheme.colorScheme.primary, // Pink
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = if(expanded) 24.sp else 18.sp),
                )
                cake.value?.description?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.labelLarge.copy(fontSize = if(expanded) 24.sp else 18.sp),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(7.dp))
                Button(
                    onClick = {
                        onClick(cake.value?.id ?: "")
                    },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(50.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = null
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "Add to Cart",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = if(expanded) 24.sp else 18.sp)
                    )
                }


            }

        }
    }
}