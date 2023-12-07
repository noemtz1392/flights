package mx.com.test.android.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.com.test.android.presentation.theme.garnettFontFamily
import mx.com.test.android.presentation.theme.shapeFlightStatus

@Composable
fun OutlinedCardExample(modifier: Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.outlinedCardColors(),
        border = BorderStroke(1.5.dp, Color.Black),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier) {
            FlightStatus("On time")
            Itinerary()
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0X66000000))
                    .height(0.6.dp)
            )
            FlightDetail()
        }
    }
}

@Composable
fun FlightStatus(flightStatus: String) {
    var switchState by remember { mutableStateOf(false) }
    Row(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .background(Color(0XFF2E9509), shapeFlightStatus),
        ) {
            Text(
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 4.dp,
                    end = 16.dp,
                    bottom = 4.dp
                ),
                text = flightStatus,
                fontSize = 14.sp,
                fontFamily = garnettFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Favorite",
                color = Color.Black,
                fontFamily = garnettFontFamily,
                fontWeight = FontWeight.SemiBold,
            )
            Switch(
                checked = true,
                onCheckedChange = { newState -> switchState = newState },
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun Switch1(
    scale: Float = 2f,
    width: Dp = 36.dp,
    height: Dp = 20.dp,
    checkedTrackColor: Color = Color(0xFF35898F),
    uncheckedTrackColor: Color = Color(0xFFe0e0e0),
    thumbColor: Color = Color.White,
    gapBetweenThumbAndTrackEdge: Dp = 4.dp
) {

    val switchON = remember {
        mutableStateOf(true)
    }

    val thumbRadius = (height / 2) - gapBetweenThumbAndTrackEdge

    // To move the thumb, we need to calculate the position (along x axis)
    val animatePosition = animateFloatAsState(
        targetValue = if (switchON.value)
            with(LocalDensity.current) { (width - thumbRadius - gapBetweenThumbAndTrackEdge).toPx() }
        else
            with(LocalDensity.current) { (thumbRadius + gapBetweenThumbAndTrackEdge).toPx() }
    )

    Canvas(
        modifier = Modifier
            .size(width = width, height = height)
            .scale(scale = scale)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        // This is called when the user taps on the canvas
                        switchON.value = !switchON.value
                    }
                )
            }
    ) {

        // Track
        drawRoundRect(
            color = if (switchON.value) checkedTrackColor else uncheckedTrackColor,
            cornerRadius = CornerRadius(x = 10.dp.toPx(), y = 10.dp.toPx())
        )

        // Thumb
        drawCircle(
            color = thumbColor,
            radius = thumbRadius.toPx(),
            center = Offset(
                x = animatePosition.value,
                y = size.height / 2
            )
        )

    }

    Spacer(modifier = Modifier.height(18.dp))

    Text(text = if (switchON.value) "ON" else "OFF")
}

@Composable
fun CustomSwitch(
    width: Dp = 64.dp,
    height: Dp = 40.dp,
    checkedTrackColor: Color = Color(0xFF000000),
    uncheckedTrackColor: Color = Color(0xFFD6D6D6),
    gapBetweenThumbAndTrackEdge: Dp = 8.dp,
    borderWidth: Dp = 1.dp,
    cornerSize: Int = 50,
    iconInnerPadding: Dp = 4.dp,
    thumbSize: Dp = 24.dp
) {

    // this is to disable the ripple effect
    val interactionSource = remember {
        MutableInteractionSource()
    }

    // state of the switch
    var switchOn by remember {
        mutableStateOf(true)
    }

    // for moving the thumb
    val alignment by animateAlignmentAsState(if (switchOn) 1f else -1f)

    // outer rectangle with border
    Box(
        modifier = Modifier
            .size(width = width, height = height)
            .border(
                width = borderWidth,
                color = if (switchOn) checkedTrackColor else uncheckedTrackColor,
                shape = RoundedCornerShape(percent = cornerSize)
            )
            .clickable(
                indication = null,
                interactionSource = interactionSource
            ) {
                switchOn = !switchOn
            },
        contentAlignment = Alignment.Center
    ) {

        // this is to add padding at the each horizontal side
        Box(
            modifier = Modifier
                .padding(
                    start = gapBetweenThumbAndTrackEdge,
                    end = gapBetweenThumbAndTrackEdge
                )
                .fillMaxSize(),
            contentAlignment = alignment
        ) {

            // thumb with icon
            Icon(
                imageVector = if (switchOn) Icons.Filled.Done else Icons.Filled.Close,
                contentDescription = if (switchOn) "Enabled" else "Disabled",
                modifier = Modifier
                    .size(size = thumbSize)
                    .background(
                        color = if (switchOn) checkedTrackColor else uncheckedTrackColor,
                        shape = CircleShape
                    )
                    .padding(all = iconInnerPadding),
                tint = Color.White
            )
        }
    }
}

@Composable
private fun animateAlignmentAsState(
    targetBiasValue: Float
): State<BiasAlignment> {
    val bias by animateFloatAsState(targetBiasValue, label = "")
    return derivedStateOf { BiasAlignment(horizontalBias = bias, verticalBias = 0f) }
}


@Composable
fun Itinerary() {
    Column(
        modifier = Modifier.padding(16.dp),
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "06:24",
                color = Color.Black,
                fontFamily = garnettFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_dot_indicator),
                    contentDescription = "Dot indicator origin", tint = Color.Black
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_flight_indicator),
                    contentDescription = "Flight indicator",

                    )
                Icon(
                    painter = painterResource(id = R.drawable.ic_dot_indicator),
                    contentDescription = "Dot indicator destination",
                    tint = Color.Black
                )
            }

            Text(
                text = "09:21", color = Color.Black,
                fontFamily = garnettFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp
            )
        }
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "MEX",
                color = Color.Black,
                fontFamily = garnettFontFamily,
                fontWeight = FontWeight.Light,
                fontSize = 18.sp,
            )
            Text(

                modifier = Modifier.weight(1f),
                text = "2h 28m",
                textAlign = TextAlign.Center,
                color = Color(0x66000000),
                fontFamily = garnettFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp,
            )
            Text(
                text = "CUN",
                color = Color.Black,
                fontFamily = garnettFontFamily,
                fontWeight = FontWeight.Light,
                fontSize = 18.sp,
            )
        }
    }
}

@Composable
fun FlightDetail() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "AM 504", fontSize = 15.sp,
            fontFamily = garnettFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Details",
            fontSize = 13.sp,
            fontFamily = garnettFontFamily,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )
        Icon(
            imageVector = Icons.Rounded.KeyboardArrowRight,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null,
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOutlinedCardExample() {
    OutlinedCardExample(Modifier)

}