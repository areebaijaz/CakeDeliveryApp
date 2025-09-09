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

    val buttonWidth = when(windowInfo.screenWidthInfo){
        is WindowInfo.WindowType.Compact -> 80.dp
        is WindowInfo.WindowType.Medium -> 100.dp
        is WindowInfo.WindowType.Expanded -> 140.dp

    }

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier.width(buttonWidth)
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