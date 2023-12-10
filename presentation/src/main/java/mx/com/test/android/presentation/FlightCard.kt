package mx.com.test.android.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.com.test.android.domain.models.Flight
import mx.com.test.android.domain.models.FlightStatus
import mx.com.test.android.presentation.components.Divider
import mx.com.test.android.presentation.theme.Arrived
import mx.com.test.android.presentation.theme.Delayed
import mx.com.test.android.presentation.theme.FlightStatusShape
import mx.com.test.android.presentation.theme.FlightsTheme
import mx.com.test.android.presentation.theme.FlightsTheme.padding
import mx.com.test.android.presentation.theme.InTheAir
import mx.com.test.android.presentation.theme.OnTime
import mx.com.test.android.presentation.theme.garnettFontFamily
import mx.com.test.android.presentation.utils.dateToHoursAndMinutes
import mx.com.test.android.presentation.utils.minutesToHoursAndMinutes

@Composable
fun OutlinedCardExample(flight: Flight) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = padding.paddingNormal,
                vertical = padding.paddingSmall
            ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = Color.White,
            contentColor = Color.White
        ),
        border = BorderStroke(1.5.dp, Color.Black),
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            FlightStatus(flight.status)
            Itinerary(flight)
            Divider(modifier = Modifier.fillMaxWidth())
            FlightDetail(flight)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightStatus(status: FlightStatus) {
    var switchState by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.padding(end = padding.paddingSmall),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = status.name,
            modifier = Modifier
                .background(status.background(), FlightStatusShape)
                .padding(
                    horizontal = padding.paddingNormal,
                    vertical = padding.paddingSmall
                ),
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = garnettFontFamily,
        )

        Spacer(modifier = Modifier.weight(1f))
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.action_toggle_favorite),
                color = Color.Black,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = garnettFontFamily,
            )

            CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
                Switch(
                    checked = true,
                    onCheckedChange = { newState -> switchState = newState },
                    modifier = Modifier.scale(.72f)
                )
            }
        }
    }
}


@Composable
fun Itinerary(flight: Flight) {
    Column(
        modifier = Modifier.padding(
            start = padding.paddingNormal,
            top = padding.paddingNormal,
            end = padding.paddingNormal,
            bottom = padding.paddingSmall
        ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = flight.estimatedDepartureTime.dateToHoursAndMinutes(),
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = garnettFontFamily,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier.padding(padding.paddingExtraSmall),
                    painter = painterResource(id = R.drawable.ic_dot_indicator),
                    contentDescription = "Dot indicator departure",
                    tint = Color.Black
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(2.dp)
                        .background(Color.Black)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_flight),
                    contentDescription = "Flight indicator"
                )
                Icon(
                    modifier = Modifier.padding(padding.paddingExtraSmall),
                    painter = painterResource(id = R.drawable.ic_dot_indicator),
                    contentDescription = "Dot indicator arrival",
                    tint = Color.Black
                )
            }

            Text(
                text = flight.estimatedArrivalTime.dateToHoursAndMinutes(),
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = garnettFontFamily,
            )
        }
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = flight.segment.departureAirport,
                color = Color.Black,
                fontFamily = garnettFontFamily,
                fontWeight = FontWeight.Light,
                fontSize = 16.sp,
            )
            Text(
                modifier = Modifier.weight(1f),
                text = flight.segment.flightDurationInMinutes.minutesToHoursAndMinutes(),
                color = Color(0x66000000),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = garnettFontFamily,
                textAlign = TextAlign.Center,
            )
            Text(
                text = flight.segment.arrivalAirport,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                fontFamily = garnettFontFamily,
            )
        }
    }
}

@Composable
fun FlightDetail(flight: Flight) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = flight.flightNumber, fontSize = 15.sp,
            fontFamily = garnettFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.weight(1f))

        ClickableText(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = 12.0.sp,
                        fontFamily = garnettFontFamily,
                        fontWeight = FontWeight.Normal,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append("Details")
                }
            },
        ) {

        }
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null,
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )

    }
}

fun FlightStatus.background(): Color {
    return when (this) {
        FlightStatus.IN_THE_AIR -> InTheAir
        FlightStatus.ON_TIME -> OnTime
        FlightStatus.DELAYED -> Delayed
        FlightStatus.ARRIVED -> Arrived
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOutlinedCardExample() {
    FlightsTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
                Spacer(modifier = Modifier.weight(1f))
                FlightStatus(status = FlightStatus.ARRIVED)
                Spacer(modifier = Modifier.height(16.dp))
                FlightStatus(status = FlightStatus.ON_TIME)
                Spacer(modifier = Modifier.height(16.dp))
                FlightStatus(status = FlightStatus.IN_THE_AIR)
                Spacer(modifier = Modifier.height(16.dp))
                FlightStatus(status = FlightStatus.DELAYED)
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}