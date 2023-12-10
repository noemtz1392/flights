package mx.com.test.android.presentation.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mx.com.test.android.domain.models.Flight
import mx.com.test.android.presentation.OutlinedCardExample
import mx.com.test.android.presentation.screens.FlightViewModel
import mx.com.test.android.presentation.theme.FlightsTheme
import mx.com.test.android.presentation.theme.garnettFontFamily


@Composable
fun FlightListScreen(
    viewModel: FlightViewModel = hiltViewModel(),
    openFlightDetail: () -> Unit
) {

    //val state by viewModel.uiState.collectAsState()

    viewModel.trackFlightByNumberFlight("35")
    val scrollState = rememberLazyListState()

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBarFlights {
            openFlightDetail()
        }


        viewModel.flights.collectAsStateWithLifecycle().let {
            FlightCardList(
                it.value,
                scrollState = scrollState
            )
        }
    }
}

@Composable
fun FlightCardList(
    flightList: List<Flight>,
    scrollState: LazyListState,
) {
    LazyColumn(
        state = scrollState,
        modifier = Modifier
            .testTag(FlightCardsTestTag)
            .fillMaxSize(),
        contentPadding = WindowInsets.systemBars
            .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom)
            .asPaddingValues()
    ) {
        items(
            count = flightList.count(),
            key = { flightList[it].id },
            itemContent = {
                OutlinedCardExample(flight = flightList[it])
            }
        )
    }
}

const val FlightCardsTestTag = "FlightCardsTestTag"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarFlights(onBackPressed: () -> Unit) {
    TopAppBar(

        modifier = Modifier,
        title = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "AM 500",
                    fontSize = 30.sp,
                    color = Color.Black,
                    fontFamily = garnettFontFamily,
                    fontWeight = FontWeight.SemiBold
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Tuesday, Nov 21",
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontFamily = garnettFontFamily,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = " | ",
                        fontSize = 16.sp,
                        color = Color(0x4D000000),
                        fontFamily = garnettFontFamily,
                        fontWeight = FontWeight.Normal
                    )
                    TextButton(
                        onClick = onBackPressed,
                        contentPadding = ButtonDefaults.TextButtonWithIconContentPadding
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.CalendarMonth,
                            contentDescription = "",
                            modifier = Modifier.size(ButtonDefaults.IconSize),
                            tint = Color.Black
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            text = "Change",
                            fontSize = 16.sp,
                            color = Color.Black,
                            fontFamily = garnettFontFamily,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent,
            navigationIconContentColor = Color.White,
            titleContentColor = Color.Transparent,
            actionIconContentColor = Color.Transparent
        ),
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "")
            }
        }
    )
}

@Preview(device = Devices.PHONE, showBackground = true)
@Composable
fun preview() {
    FlightsTheme {
        FlightListScreen() {
        }
    }
}