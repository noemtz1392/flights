package mx.com.test.android.presentation.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mx.com.test.android.presentation.components.LinkButton
import mx.com.test.android.presentation.components.LoadingDialog
import mx.com.test.android.presentation.screens.FlightViewModel
import mx.com.test.android.presentation.theme.FlightsTheme
import mx.com.test.android.presentation.theme.departureShape
import mx.com.test.android.presentation.theme.garnettFontFamily
import mx.com.test.android.presentation.utils.scrim
import mx.com.test.android.presentation.utils.toFormatDate
import java.util.Date

const val TRACK_BY_FLIGHT_NUMBER = 1
const val TRACK_BY_ROUTE = 2

@Composable
fun TrackYourFlightScreen(
    viewModel: FlightViewModel,
    navigateToFlightScreen: () -> Unit
) {
    val option = rememberSaveable { mutableIntStateOf(TRACK_BY_FLIGHT_NUMBER) }

    val uiState by viewModel.searchUiState.collectAsStateWithLifecycle()
    if (uiState.isSearching) {
        LoadingDialog()
    }
    if (uiState.foundResults && uiState.message.isNotBlank()) {
        navigateToFlightScreen()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .scrim(colors = listOf(Color(0xFFF7F7F7), Color(0xFFF7F7F7)))
                .aspectRatio(16f / 9f)

        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Track your flight",
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontFamily = garnettFontFamily,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Keep you informed in real time!",
                    color = Color.Black,
                    fontFamily = garnettFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                )
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0XFFCCCCCC))
                    .align(Alignment.BottomCenter),
            )
            SegmentOptions(
                modifier = Modifier
                    .height(48.dp)
                    .align(Alignment.BottomCenter)
                    .offset(y = 24.dp),
                selectedOption = option.value
            ) { optionSelected ->
                option.value = optionSelected
            }
        }

        if (option.value == TRACK_BY_FLIGHT_NUMBER) {
            SearchFlightsByDestination {
                when (it) {
                    ActionClick.SearchFlight -> viewModel.searchFlightByFlightNumber()
                    ActionClick.SearchFlights -> viewModel.searchFlightByFlightNumber()
                    ActionClick.SearchingByDestination -> option.value = TRACK_BY_ROUTE
                    ActionClick.SearchingByFlightNumber -> option.value = TRACK_BY_ROUTE
                }
            }
        } else if (option.value == TRACK_BY_ROUTE) {
            SearchFlightByNumber {
                when (it) {
                    ActionClick.SearchFlight -> viewModel.searchFlightByRoute()
                    ActionClick.SearchFlights -> viewModel.searchFlightByRoute()
                    ActionClick.SearchingByDestination -> option.value = TRACK_BY_FLIGHT_NUMBER
                    ActionClick.SearchingByFlightNumber -> option.value = TRACK_BY_FLIGHT_NUMBER
                }
            }
        }
    }
}

@Composable
fun SegmentOptions(
    modifier: Modifier = Modifier,
    selectedOption: Int,
    optionSelected: (Int) -> Unit = {}
) {
    val flightNumberTextColor = when (selectedOption) {
        TRACK_BY_FLIGHT_NUMBER -> Color.White
        TRACK_BY_ROUTE -> Color.Black
        else -> Color.Black
    }

    val destinationTextColor = when (selectedOption) {
        TRACK_BY_FLIGHT_NUMBER -> Color.Black
        TRACK_BY_ROUTE -> Color.White
        else -> Color.Black
    }

    Row(
        modifier = modifier
            .wrapContentWidth()
            .fillMaxHeight()
            .background(Color.White)
            .border(1.dp, Color(0XFFD6D6D6), RoundedCornerShape(size = 4.dp))
            .padding(all = FlightsTheme.padding.paddingExtraSmall),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .clickable { optionSelected(TRACK_BY_FLIGHT_NUMBER) }
                .background(destinationTextColor, RoundedCornerShape(size = 4.dp))
                .padding(
                    horizontal = FlightsTheme.padding.paddingExtraLarge,
                    vertical = FlightsTheme.padding.paddingSmall
                ),
            text = "Flight Number",
            color = flightNumberTextColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = garnettFontFamily,
        )

        Text(
            modifier = Modifier
                .clickable { optionSelected(TRACK_BY_ROUTE) }
                .background(flightNumberTextColor, RoundedCornerShape(size = 4.dp))
                .padding(
                    horizontal = FlightsTheme.padding.paddingExtraLarge,
                    vertical = FlightsTheme.padding.paddingSmall
                ),
            text = "Destination",
            color = destinationTextColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = garnettFontFamily,
        )
    }
}


@Composable
private fun ColumnScope.SearchFlightByNumber(onClick: (ActionClick) -> Unit) {
    val origin by rememberSaveable { mutableStateOf("Mexico City") }
    val originKey by rememberSaveable { mutableStateOf("MEX") }
    val destination by rememberSaveable { mutableStateOf("Cancún") }
    val destinationKey by rememberSaveable { mutableStateOf("CUN") }

    Column(
        modifier = Modifier
            .weight(0.8f)
            .padding(all = FlightsTheme.padding.paddingNormal),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .border(1.5.dp, Color.Black, RoundedCornerShape(size = 12.dp))
                    .padding(
                        horizontal = FlightsTheme.padding.paddingMedium,
                        vertical = FlightsTheme.padding.paddingSmall
                    )
            ) {
                Text(
                    text = "Origin",
                    color = Color.Black,
                    fontSize = 10.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = garnettFontFamily,
                )
                Row {
                    Text(
                        text = origin, color = Color.Black,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = garnettFontFamily,
                    )
                    Spacer(modifier = Modifier.width(FlightsTheme.spacing.spaceExtraSmall))
                    Text(
                        text = originKey,
                        color = Color(0x4D000000),
                        fontSize = 15.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = garnettFontFamily,
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .border(1.5.dp, Color.Black, RoundedCornerShape(size = 12.dp))
                    .padding(
                        horizontal = FlightsTheme.padding.paddingMedium,
                        vertical = FlightsTheme.padding.paddingSmall
                    )
            ) {
                Text(
                    text = "Destination",
                    color = Color.Black,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = garnettFontFamily,
                )
                Row {
                    Text(
                        text = destination,
                        color = Color.Black,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = garnettFontFamily,
                    )
                    Spacer(modifier = Modifier.width(FlightsTheme.spacing.spaceExtraSmall))
                    Text(
                        text = destinationKey,
                        color = Color(0x4D000000),
                        fontSize = 15.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = garnettFontFamily,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.5.dp, Color.Black, RoundedCornerShape(size = 12.dp))
                .padding(
                    horizontal = FlightsTheme.padding.paddingMedium,
                    vertical = FlightsTheme.padding.paddingSmall
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                Text(
                    text = "Date of departure",
                    color = Color.Black,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = garnettFontFamily,
                )
                Text(
                    text = Date(System.currentTimeMillis()).toString().toFormatDate(),
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = garnettFontFamily,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Rounded.CalendarMonth,
                contentDescription = "",
                modifier = Modifier.size(ButtonDefaults.IconSize),
                tint = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = departureShape,
            elevation = ButtonDefaults.elevatedButtonElevation(),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Black
            ),
            onClick = { onClick(ActionClick.SearchFlight) }
        ) {
            Text(
                text = "Search Flights",
                fontSize = 15.sp,
                fontFamily = garnettFontFamily,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(FlightsTheme.spacing.spaceNormal))
        Text(
            text = "Looking for a specific flight?",
            fontSize = 12.sp,
            color = Color(0xB3000000),
            fontFamily = garnettFontFamily,
            fontWeight = FontWeight.Normal
        )
        LinkButton(text = "Try searching by", underlineText = "flight number") {
            onClick(ActionClick.SearchingByFlightNumber)
        }
        Spacer(modifier = Modifier.height(FlightsTheme.spacing.spaceNormal))
    }
    Spacer(modifier = Modifier.weight(0.20f))
}

@Composable
private fun ColumnScope.SearchFlightsByDestination(
    onClick: (ActionClick) -> Unit,
) {
    val flightNumber by rememberSaveable { mutableStateOf("500") }
    val flightNumberKey by rememberSaveable { mutableStateOf("AM") }

    Column(
        modifier = Modifier
            .weight(0.8f)
            .padding(all = FlightsTheme.padding.paddingNormal),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .border(1.5.dp, Color.Black, RoundedCornerShape(size = 12.dp))
                    .padding(
                        horizontal = FlightsTheme.padding.paddingMedium,
                        vertical = FlightsTheme.padding.paddingSmall
                    )
            ) {
                Text(
                    text = "Flight number",
                    color = Color.Black,
                    fontSize = 10.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = garnettFontFamily,
                )
                Row {
                    Text(
                        text = flightNumberKey,
                        color = Color(0x4D000000),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = garnettFontFamily,
                    )
                    Spacer(modifier = Modifier.width(FlightsTheme.spacing.spaceExtraSmall))
                    Text(
                        text = flightNumber,
                        color = Color.Black,
                        fontSize = 15.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = garnettFontFamily,
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Row(
                modifier = Modifier
                    .weight(2f)
                    .border(1.5.dp, Color.Black, RoundedCornerShape(size = 12.dp))
                    .padding(
                        horizontal = FlightsTheme.padding.paddingMedium,
                        vertical = FlightsTheme.padding.paddingSmall
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column {
                    Text(
                        text = "Date of departure",
                        color = Color.Black,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = garnettFontFamily,
                    )
                    Text(
                        text = Date(System.currentTimeMillis()).toString().toFormatDate(),
                        color = Color.Black,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = garnettFontFamily,
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Rounded.CalendarMonth,
                    contentDescription = "",
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                    tint = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = departureShape,
            elevation = ButtonDefaults.elevatedButtonElevation(),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Black
            ),
            onClick = {
                onClick(ActionClick.SearchFlights)
            }
        ) {
            Text(
                text = "Search Flight",
                fontSize = 15.sp,
                fontFamily = garnettFontFamily,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(FlightsTheme.spacing.spaceNormal))
        Text(
            text = "Can’t find your flight number?",
            fontSize = 12.sp,
            color = Color(0xB3000000),
            fontFamily = garnettFontFamily,
            fontWeight = FontWeight.Normal
        )
        LinkButton(text = "Try searching by", underlineText = "destination") {
            onClick(ActionClick.SearchingByDestination)
        }
        Spacer(modifier = Modifier.height(FlightsTheme.spacing.spaceNormal))
    }
    Spacer(modifier = Modifier.weight(0.20f))
}

sealed class ActionClick {
    data object SearchFlight : ActionClick()
    data object SearchFlights : ActionClick()

    data object SearchingByDestination : ActionClick()
    data object SearchingByFlightNumber : ActionClick()
}

@Preview(name = "Track flight", device = Devices.PHONE)
@Composable
fun PreviewTrackingFlight() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        SegmentOptions(modifier = Modifier, selectedOption = TRACK_BY_ROUTE)
    }
}