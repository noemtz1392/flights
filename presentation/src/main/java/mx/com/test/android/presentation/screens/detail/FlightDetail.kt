@file:OptIn(ExperimentalMaterial3Api::class)

package mx.com.test.android.presentation.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBackIos
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import mx.com.test.android.domain.models.Flight
import mx.com.test.android.presentation.R
import mx.com.test.android.presentation.background
import mx.com.test.android.presentation.screens.FlightViewModel
import mx.com.test.android.presentation.theme.BottomCardShape
import mx.com.test.android.presentation.theme.FlightStatusShape
import mx.com.test.android.presentation.theme.FlightsTheme
import mx.com.test.android.presentation.theme.arrivalShape
import mx.com.test.android.presentation.theme.colorArrivalBackground
import mx.com.test.android.presentation.theme.garnettFontFamily
import mx.com.test.android.presentation.utils.dateToHoursAndMinutes
import mx.com.test.android.presentation.utils.hoursToHoursAndMinutes

@Composable
fun FlightDetailScreen(
    navBack: NavBackStackEntry,
    viewModel: FlightViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    viewModel.flightUiState.collectAsStateWithLifecycle().let { state ->
        val flightDetail = state.value.flights.find { flight ->
            flight.id == navBack.arguments?.getString("id")
        }
        Box(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
        ) {
            HeaderImage()
            AppBar(onNavIconPressed = onBackPressed)
            if (flightDetail != null) {
                BottomSheetContent(flightDetail, state.value.dateOfDeparture)
            } else {
                Text(
                    text = "Flight not found...", modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)
                )
            }
        }
    }
}

@Composable
private fun HeaderImage() {
    Image(
        painter = painterResource(id = R.drawable.flight_detail),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth(),
        alignment = Alignment.TopCenter,
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun BoxScope.BottomSheetContent(flight: Flight, dateOfDeparture: String) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(0.98f)
            .align(Alignment.BottomStart),
        shape = BottomCardShape,
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Spacer(modifier = Modifier.height(FlightsTheme.spacing.spaceMedium))
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.ic_resize_indicator),
            contentDescription = "Resize flight detail",
            alignment = Alignment.Center
        )

        FlightCode(flight, dateOfDeparture)
        Spacer(modifier = Modifier.width(24.dp))
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0XFFE9E9E9))
                .height(0.5.dp)
        )
        ItineraryDetail(flight)
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(Color(0XFFE9E9E9))
                .height(0.6.dp)
        )
        Text(
            modifier = Modifier.padding(
                start = FlightsTheme.padding.paddingNormal,
                top = FlightsTheme.padding.paddingNormal,
                end = FlightsTheme.padding.paddingNormal
            ),
            text = stringResource(id = R.string.flight_details_label),
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = garnettFontFamily,
        )
        Departure(flight)
        Arrival(flight)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.16f)
        )
    }
}

@Composable
private fun ItineraryDetail(flight: Flight) {
    Column(
        modifier = Modifier.padding(
            start = FlightsTheme.padding.paddingNormal,
            top = FlightsTheme.padding.paddingNormal,
            end = FlightsTheme.padding.paddingNormal,
            bottom = FlightsTheme.padding.paddingSmall
        ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = flight.estimatedDepartureTime.dateToHoursAndMinutes(),
                color = Color.Black,
                fontSize = 20.sp,
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
                    modifier = Modifier.padding(FlightsTheme.padding.paddingExtraSmall),
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
                    modifier = Modifier.padding(FlightsTheme.padding.paddingExtraSmall),
                    painter = painterResource(id = R.drawable.ic_dot_indicator),
                    contentDescription = "Dot indicator arrival",
                    tint = Color.Black
                )
            }

            Text(
                text = flight.estimatedArrivalTime.dateToHoursAndMinutes(),
                color = Color.Black,
                fontSize = 20.sp,
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
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = garnettFontFamily,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = flight.segment.arrivalAirport,
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = garnettFontFamily,
            )
        }
    }
}
@Composable
fun FlightCode(flight: Flight, dateOfDeparture: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = FlightsTheme.padding.paddingNormal,
                vertical = FlightsTheme.padding.paddingSmall
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Row {
                Text(
                    text = flight.segment.operatingCarrier,
                    color = Color(0x4D000000),
                    fontFamily = garnettFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp
                )
                Spacer(modifier = Modifier.width(FlightsTheme.spacing.spaceExtraSmall))
                Text(
                    text = flight.segment.operatingFlightCode,
                    color = Color.Black,
                    fontFamily = garnettFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp
                )
            }
            Text(
                text = dateOfDeparture,
                color = Color.Black,
                fontFamily = garnettFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.weight(1.0f))
        Text(
            text = flight.status.name,
            modifier = Modifier
                .background(flight.status.background(), FlightStatusShape.copy(CornerSize(4.dp)))
                .padding(
                    horizontal = FlightsTheme.padding.paddingSmall,
                    vertical = FlightsTheme.padding.paddingExtraSmall
                ),
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = garnettFontFamily,
        )
    }
}

@Composable
fun Departure(flight: Flight) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_flight_departure),
                contentDescription = null,
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(FlightsTheme.spacing.spaceExtraSmall))
            Text(
                text = stringResource(id = R.string.departure_label),
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontFamily = garnettFontFamily,
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(id = R.string.terminal_number_label, flight.boardingTerminal),
                modifier = Modifier.alpha(0.5f),
                color = Color.Black,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = garnettFontFamily,
            )
        }

        Spacer(modifier = Modifier.height(FlightsTheme.spacing.spaceSmall))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorArrivalBackground,
                    shape = arrivalShape
                )
                .padding(
                    horizontal = FlightsTheme.spacing.spaceMedium,
                    vertical = FlightsTheme.spacing.spaceSmall
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.terminal_label),
                    color = Color.Black,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = garnettFontFamily,
                )
                Text(
                    text = flight.boardingTerminal,
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = garnettFontFamily,
                )
            }

            Spacer(modifier = Modifier.width(FlightsTheme.spacing.spaceLarge))

            Column {
                Text(
                    text = stringResource(id = R.string.departure_gate_label),
                    color = Color.Black,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = garnettFontFamily,
                )
                Text(
                    text = flight.boardingGate,
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = garnettFontFamily,
                )
            }

            Spacer(modifier = Modifier.width(FlightsTheme.spacing.spaceLarge))

            Column {
                Column {
                    Text(
                        text = stringResource(id = R.string.boarding_time_label),
                        color = Color.Black,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = garnettFontFamily,
                    )
                    Text(
                        text = flight.boardingTime.hoursToHoursAndMinutes(),
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = garnettFontFamily,
                    )
                }
            }
        }
    }
}

@Composable
private fun Arrival(flight: Flight) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_flight_arrival),
                contentDescription = null,
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(id = R.string.arrival_label),
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontFamily = garnettFontFamily,
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(id = R.string.terminal_number_label, flight.arrivalTerminal),
                modifier = Modifier.alpha(0.5f),
                color = Color.Black,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = garnettFontFamily,
            )
        }

        Spacer(modifier = Modifier.height(FlightsTheme.spacing.spaceSmall))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorArrivalBackground,
                    shape = arrivalShape
                )
                .padding(
                    horizontal = FlightsTheme.spacing.spaceMedium,
                    vertical = FlightsTheme.spacing.spaceSmall
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.terminal_label),
                    color = Color.Black,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = garnettFontFamily,
                )
                Text(
                    text = flight.arrivalTerminal,
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = garnettFontFamily,
                )
            }

            Spacer(modifier = Modifier.width(FlightsTheme.spacing.spaceLarge))

            Column {
                Text(
                    text = stringResource(id = R.string.estimated_landing_label),
                    color = Color.Black,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = garnettFontFamily,
                )
                Text(
                    text = flight.estimatedArrivalTime.dateToHoursAndMinutes(),
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = garnettFontFamily,
                )
            }
        }
    }
}


@Composable
private fun AppBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = {},
    onNavIconPressed: () -> Unit = { },
) {
    TopAppBar(
        modifier = modifier.padding(horizontal = FlightsTheme.padding.paddingSmall),
        title = title,
        navigationIcon = {
            NavigationBackIcon(
                contentDescription = stringResource(id = R.string.action_navigation_back),
                onBackPressed = onNavIconPressed
            )

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}

@Composable
private fun NavigationBackIcon(
    modifier: Modifier = Modifier,
    contentDescription: String? = "On back",
    onBackPressed: () -> Unit,
) {
    IconButton(
        onClick = onBackPressed,
        modifier = modifier,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Color.White,
            contentColor = Color.White,
        )
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.ArrowBackIos,
            contentDescription = contentDescription,
            tint = Color.Black
        )
    }
}

@Preview(device = Devices.PHONE)
@Composable
fun PreviewBottomSheet() {
    FlightsTheme {
        AppBar {

        }
    }
}

