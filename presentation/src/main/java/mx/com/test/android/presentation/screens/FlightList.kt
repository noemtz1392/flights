package mx.com.test.android.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mx.com.test.android.domain.models.Flight
import mx.com.test.android.presentation.OutlinedCardExample
import mx.com.test.android.presentation.theme.FlightsTheme
import mx.com.test.android.presentation.theme.garnettFontFamily
import java.util.UUID


@Composable
fun FlightListScreen(
    navController: NavController = rememberNavController(),
    flights: List<Flight>,
    openFlightDetail: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBarFlights {
            openFlightDetail()
        }
        LazyColumn(
            contentPadding = PaddingValues(0.dp),
            verticalArrangement = Arrangement.Center
        ) {
            items(flights, key = { item: Flight -> item.id }) {
                OutlinedCardExample(Modifier)
            }
        }
    }
}

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
        colors = TopAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent,
            navigationIconContentColor = Color.White,
            titleContentColor = Color.Transparent,
            actionIconContentColor = Color.Transparent
        ),
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(imageVector = Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "")
            }
        }
    )
}

@Preview(device = Devices.PHONE, showBackground = true)
@Composable
fun preview() {
    FlightsTheme {
        val items = listOf(
            Flight(
                UUID.randomUUID().toString(),
                status = mx.com.test.android.domain.models.FlightStatus.IN_THE_AIR
            ),

            Flight(
                UUID.randomUUID().toString(),
                status = mx.com.test.android.domain.models.FlightStatus.IN_THE_AIR
            ),
            Flight(
                UUID.randomUUID().toString(),
                status = mx.com.test.android.domain.models.FlightStatus.IN_THE_AIR
            ),

            Flight(
                UUID.randomUUID().toString(),
                status = mx.com.test.android.domain.models.FlightStatus.IN_THE_AIR
            )
        )
        FlightListScreen(flights = items) {

        }
    }
}