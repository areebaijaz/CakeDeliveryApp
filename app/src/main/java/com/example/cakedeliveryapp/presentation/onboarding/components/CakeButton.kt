package com.example.cakedeliveryapp.presentation.onboarding.components

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cakedeliveryapp.presentation.WindowInfo

@Composable
fun CakeButton(
    text: String,
    onClick: () -> Unit,
    windowInfo: WindowInfo
) {

    val expanded = windowInfo.screenWidthInfo is WindowInfo.WindowType.Expanded


    Button(
        onClick = onClick,
        shape = RoundedCornerShape(6.dp),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)

        )
    }

}

@Composable
fun CakeTextButton(
    text: String,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick
    ) {
        Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
        )
    }


}