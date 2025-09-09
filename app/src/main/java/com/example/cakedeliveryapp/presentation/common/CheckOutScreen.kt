package com.example.cakedeliveryapp.presentation.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cakedeliveryapp.presentation.Dimens.ExtraSmallPadding
import com.example.cakedeliveryapp.presentation.Dimens.imageInCheckOut
import com.example.cakedeliveryapp.presentation.Dimens.paddingInDetail
import com.example.cakedeliveryapp.presentation.Dimens.smallPadding
import com.example.cakedeliveryapp.presentation.Dimens.spaceInCheckout
import com.example.cakedeliveryapp.presentation.WindowInfo

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun CheckoutScreen(
    checkoutViewModel: CartAndCheckoutViewModel = hiltViewModel(),
    navController: NavController,
    onClick: () -> Unit,
    windowInfo : WindowInfo
) {
    val compact = windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact
    val medium = windowInfo.screenWidthInfo is WindowInfo.WindowType.Medium
    val expanded = windowInfo.screenWidthInfo is WindowInfo.WindowType.Expanded

    val cake = checkoutViewModel.cake.collectAsState()
    val context = LocalContext.current
    val subTotal = (cake.value?.price ?: 0) * (cake.value?.quantity ?: 0)
    val total = 200 + subTotal
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Checkout",
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = if(expanded) 30.sp else 24.sp),
                        modifier = Modifier.padding(start = 5.dp)
                ) },
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
                .padding(if(!expanded) paddingInDetail else 24.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(spaceInCheckout)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(if(!expanded) ExtraSmallPadding else  8.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(if(!expanded) imageInCheckOut else 150.dp)
                            .clip(RoundedCornerShape(smallPadding)),
                        model = ImageRequest.Builder(context)
                            .data(cake.value?.imageUrl ?: "")
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(if(!expanded) 15.dp else 30.dp))
                    Column {
                        cake.value?.name?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.titleMedium.copy(fontSize = if (
                                    expanded) 30.sp else 24.sp),
                                modifier = Modifier.wrapContentHeight()

                            )
                        }
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = "Price: ${cake.value?.price}",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodyLarge.copy(fontSize = if(expanded) 25.sp else 18.sp),
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Quantity: ${cake.value?.quantity}",
                            style = MaterialTheme.typography.bodyLarge.copy(fontSize = if(expanded) 24.sp else 18.sp),
                        )
                        Spacer(modifier = Modifier.height(1.dp))
                    }

                }

            }
            Spacer(modifier = Modifier.height(if(!expanded)10.dp else 8.dp))
            OrderConformation(subTotal,total,onClick,windowInfo)

    }

}

}
@Composable
fun RowScope.BoxContent(text: String, weight : Float,windowInfo: WindowInfo){
    val expanded = windowInfo.screenWidthInfo is WindowInfo.WindowType.Expanded
    Card(
        modifier = Modifier
            .weight(weight)
            .height(76.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = if(expanded) 24.sp else 18.sp)
            )
        }
}
}
@Composable
fun OrderConformation(subTotal : Int,total : Int,onClick: () -> Unit,windowInfo: WindowInfo) {
    val expanded = windowInfo.screenWidthInfo is WindowInfo.WindowType.Expanded
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BoxContent("SubTotal",1f,windowInfo)

        Spacer(modifier = Modifier.width(if(!expanded)20.dp else 25.dp))
        BoxContent("$subTotal",1f,windowInfo)
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BoxContent("DeliveryCharges",1f,windowInfo)
        Spacer(modifier = Modifier.width(if(!expanded)20.dp else 25.dp))
        BoxContent("200",1f,windowInfo)
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BoxContent("Total",1f,windowInfo)
        Spacer(modifier = Modifier.width(if(!expanded)20.dp else 25.dp))
        BoxContent("$total",1f,windowInfo)
    }
    Spacer(modifier = Modifier.height(10.dp))
    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier.fillMaxWidth().height(if(expanded) 65.dp else 50.dp).padding(if(expanded)5.dp else 0.dp)
    ) {
        Text("Conform Order", style = MaterialTheme.typography.bodyLarge.copy(fontSize = if(expanded) 24.sp else 18.sp),

        )
    }


}