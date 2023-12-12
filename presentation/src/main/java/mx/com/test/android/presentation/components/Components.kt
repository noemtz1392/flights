package mx.com.test.android.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.com.test.android.presentation.theme.ColorDivider
import mx.com.test.android.presentation.theme.garnettFontFamily

@Composable
fun Divider(modifier: Modifier) {
    HorizontalDivider(
        modifier = modifier
            .background(ColorDivider)
            .height(0.6.dp)
    )
}

@Composable
fun LinkButton(text: String) {
    Text(
        text = text,
        color = Color.Black,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = garnettFontFamily,
        textDecoration = TextDecoration.Underline
    )
}

@Composable
fun LinkButton(
    modifier: Modifier = Modifier,
    text: String,
    underlineText: String,
    onClick: () -> Unit
) {
    val string = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color(0xB3000000),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = garnettFontFamily,
            )
        ) {
            append(text)
        }
        append(" ")
        withStyle(
            style = SpanStyle(
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = garnettFontFamily,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append(underlineText)
        }
    }
    ClickableText(
        text = string,
        modifier = modifier.fillMaxWidth(),
        style = TextStyle(textAlign = TextAlign.Center)
    ) { offset ->
        string.getStringAnnotations(offset, offset).firstOrNull()?.let { span ->
            println("Clicked on ${span.item}")
        }
        onClick()
    }
}