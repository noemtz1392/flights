package mx.com.test.android.presentation.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun Modifier.scrim(colors: List<Color>): Modifier = drawWithContent {
    drawRect(Brush.horizontalGradient(colors))
    drawContent()
}
