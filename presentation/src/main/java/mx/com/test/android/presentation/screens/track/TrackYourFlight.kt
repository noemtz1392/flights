package mx.com.test.android.presentation.screens.track

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mx.com.test.android.presentation.screens.FlightViewModel
import mx.com.test.android.presentation.theme.garnettFontFamily
import mx.com.test.android.presentation.theme.shapeDeparture
import mx.com.test.android.presentation.utils.scrim

@Composable
fun TrackYourFlightScreen(
    navController: NavController = rememberNavController(),
    viewModel: FlightViewModel = hiltViewModel(),
    actionSearchFlight: () -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
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
            SegmentedButtonExample(
                modifier = Modifier
                    .height(40.dp)
                    .align(Alignment.BottomCenter)
                    .offset(y = 20.dp)
            ) {
                when (it) {
                    SearchFlight.Destination -> {}
                    SearchFlight.FlightNumber -> {

                    }
                }
            }
        }

        SearchFlightsByDestination {
            viewModel.trackFlightByNumberFlight("35")
            //viewModel.trackFlightByDestination("MEX", "CUN")
            actionSearchFlight()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SegmentedButtonExample(modifier: Modifier = Modifier, action: (SearchFlight) -> Unit) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val options = listOf("Flight Number", "Destination")


    SingleChoiceSegmentedButtonRow(modifier = modifier) {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                onClick = {
                    selectedIndex = index
                    when (selectedIndex) {
                        0 -> action(SearchFlight.FlightNumber)
                        1 -> action(SearchFlight.Destination)
                    }
                },
                selected = index == selectedIndex,
                icon = {}
            ) {
                Text(
                    text = label,
                    color = Color.White,
                    fontFamily = garnettFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp
                )
            }
        }
    }
}

/*

colors = SegmentedButtonDefaults.colors(
                    activeContainerColor = Color.Black,
                    activeBorderColor = Color.White
                ),


                border = SegmentedButtonDefaults.borderStroke(Color(0XFFD6D6D6), 1.dp),
* */


@Composable
private fun SearchFlightByNumber() {

}

@Composable
private fun BoxWithConstraintsScope.SearchFlightsByDestination(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
            .align(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                modifier = Modifier.weight(0.5f),
                value = "",
                onValueChange = {}
            )
            Spacer(modifier = Modifier.width(16.dp))
            TextField(
                modifier = Modifier.weight(1f),
                value = "",
                onValueChange = {}
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            onValueChange = {}
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = shapeDeparture,
            elevation = ButtonDefaults.elevatedButtonElevation(),
            onClick = onClick
        ) {
            Text(
                text = "Search Flights",
                fontSize = 15.sp,
                fontFamily = garnettFontFamily,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

sealed class SearchFlight {
    data object FlightNumber : SearchFlight()
    data object Destination : SearchFlight()
}

@Preview(name = "Track flight", device = Devices.PHONE)
@Composable
fun PreviewTrackingFlight() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        TrackYourFlightScreen {
        }
    }
}