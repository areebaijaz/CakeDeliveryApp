package com.example.cakedeliveryapp.presentation.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cakedeliveryapp.data.local.cart.entity.CartEntity
import com.example.cakedeliveryapp.presentation.Dimens.cardImageInCart
import com.example.cakedeliveryapp.presentation.Dimens.paddingInDetail
import com.example.cakedeliveryapp.presentation.Dimens.smallPadding
import com.example.cakedeliveryapp.presentation.Dimens.spaceInDetail
import com.example.cakedeliveryapp.presentation.WindowInfo
import com.example.cakedeliveryapp.presentation.common.CartAndCheckoutViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToCartScreen(
    navController: NavController,
    cartViewModel: CartAndCheckoutViewModel = hiltViewModel(),
    onClick: (String) -> Unit,
    windowInfo : WindowInfo,
) {
    val compact = windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact
    val medium = windowInfo.screenWidthInfo is WindowInfo.WindowType.Medium
    val expanded = windowInfo.screenWidthInfo is WindowInfo.WindowType.Expanded

    val cake = cartViewModel.cake.collectAsState()
    var quantity by remember { mutableStateOf(1) }
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Select Quantity",style = MaterialTheme.typography.titleMedium.copy(fontSize = if(expanded) 30.sp else 24.sp),
                    modifier = Modifier.padding(start = 5.dp)) },
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
        bottomBar = {
                if(!expanded) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val total = (cake.value?.price )?.times((quantity ))
                    Text(
                        "Total: Rs $total",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                    )
                    Button(
                        onClick = {
                            val cartItem = cake.value?.let {
                                CartEntity(
                                    id = it.id,
                                    name = it.name,
                                    imageUrl = it.imageUrl,
                                    price = it.price,
                                    quantity = quantity
                                )
                            }
                            if (cartItem != null) {
                                cartViewModel.addCartItem(cartItem)
                            }
                            onClick(cake.value?.id ?: "")
                        }
                    ) {
                        Text(
                            "Checkout",
                            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                        )
                    }
                }
            }
        }
    ) { paddingValues ->


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues).verticalScroll(rememberScrollState())
                .padding(if(!expanded)paddingInDetail else 23.dp),
            verticalArrangement = Arrangement.spacedBy(if(!expanded) spaceInDetail else 7.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (!expanded) cardImageInCart else 390.dp)
                    .clip(RoundedCornerShape(smallPadding)),
                model = ImageRequest.Builder(context)
                    .data(cake.value?.imageUrl ?: "")
                    .build(),

                contentDescription = null,
                contentScale = if (!expanded) ContentScale.Crop else ContentScale.None
            )
            Spacer(modifier = Modifier.height(8.dp))
            cake.value?.name?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = if (expanded) 30.sp else 24.sp),
                    modifier = Modifier.wrapContentHeight()

                )
            }
            Text(
                text = "₨ ${cake.value?.price}",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = if (expanded) 24.sp else 18.sp),// Pink

            )
            Spacer(modifier = Modifier.height(if (expanded) 4.dp else 6.dp))
            cake.value?.description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.labelLarge.copy(fontSize = if (expanded) 24.sp else 18.sp),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(if (expanded) 7.dp else 7.dp))

            val buttonWidth = when(windowInfo.screenWidthInfo){
                is WindowInfo.WindowType.Compact -> 70.dp
                is WindowInfo.WindowType.Medium -> 90.dp
                is WindowInfo.WindowType.Expanded -> 130.dp

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        quantity = (quantity - 1).coerceAtLeast(1)

                    },
                    modifier = Modifier.width(buttonWidth)
                ) {
                    Text(
                        text = "-",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = if (expanded) 24.sp else 18.sp),
                    )

                }
                Text(
                    text = quantity.toString(),
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.bodyLarge,
                )
                Button(
                    onClick = {
                        quantity = (quantity + 1)

                    },
                    modifier = Modifier.width(buttonWidth)
                ) {
                    Text(
                        text = "+",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = if (expanded) 24.sp else 18.sp),
                    )

                }

            }
            if(expanded) {
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val total = (cake.value?.price ?: 0) * (quantity)
                    Text(
                        "Total: ₨ $total",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 24.sp),
                    )
                    Button(
                        onClick = {
                            val cartItem = cake.value?.let {
                                CartEntity(
                                    id = it.id,
                                    name = it.name,
                                    imageUrl = it.imageUrl,
                                    price = it.price,
                                    quantity = quantity
                                )
                            }
                            if (cartItem != null) {
                                cartViewModel.addCartItem(cartItem)
                            }
                            onClick(cake.value?.id ?: "")
                        }
                    ) {
                        Text(
                            "Checkout",
                            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 24.sp),
                        )
                    }
                }
            }
        }

    }
}
