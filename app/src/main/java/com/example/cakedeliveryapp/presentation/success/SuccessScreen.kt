package com.example.cakedeliveryapp.presentation.success

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cakedeliveryapp.presentation.Dimens.ExtraSmallPadding
import com.example.cakedeliveryapp.presentation.Dimens.ExtraSmallPadding2
import com.example.cakedeliveryapp.presentation.Dimens.paddingInDetail
import com.example.cakedeliveryapp.presentation.WindowInfo

@Composable
fun SuccessScreen(
    onClick:() -> Unit,
    windowInfo: WindowInfo
) {

    val expanded = windowInfo.screenWidthInfo is WindowInfo.WindowType.Expanded

    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize().padding(it).padding(paddingInDetail),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                modifier = Modifier.size(230.dp),
                tint = MaterialTheme.colorScheme.primary
                )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Order Placed Successfully!",
                style = MaterialTheme.typography.headlineSmall.copy(fontSize = if(expanded) 26.sp else 20.sp),
                modifier = Modifier.padding(horizontal = ExtraSmallPadding)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Your order will be delivered within 30–40 minutes.",
                style = MaterialTheme.typography.labelLarge.copy(fontSize = if(expanded) 24.sp else 18.sp),
                modifier = Modifier.padding(horizontal = ExtraSmallPadding2),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { onClick() },
                modifier = Modifier.fillMaxWidth().padding(15.dp).height(50.dp)
            ) {
                Text(
                    text = "Go to Home",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = if(expanded) 24.sp else 18.sp)
                )
            }


        }

    }
}
