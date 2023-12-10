package mx.com.test.android.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mx.com.test.android.presentation.theme.ColorDivider


@Composable
fun Divider(modifier: Modifier) {
    HorizontalDivider(
        modifier = modifier
            .background(ColorDivider)
            .height(0.6.dp)
    )
}