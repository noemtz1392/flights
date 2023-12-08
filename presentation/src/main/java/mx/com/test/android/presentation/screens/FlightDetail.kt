package mx.com.test.android.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mx.com.test.android.presentation.Itinerary
import mx.com.test.android.presentation.R
import mx.com.test.android.presentation.theme.garnettFontFamily
import mx.com.test.android.presentation.theme.shapeDeparture
import mx.com.test.android.presentation.theme.shapeFlightDetail

@Composable
fun FlightDetailScreen(
    navController: NavController = rememberNavController(),
    nestedScrollInteropConnection: NestedScrollConnection = rememberNestedScrollInteropConnection()
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {

        FlightImage(scrollState = scrollState)
        BottomSheetContent()
    }
}

@Composable
private fun FlightImage(
    scrollState: ScrollState
) {
    val offset = (scrollState.value / 2)
    val offsetDp = with(LocalDensity.current) { offset.toDp() }
    Image(
        painter = painterResource(id = R.drawable.flight_detail),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = offsetDp),
        alignment = Alignment.TopCenter,
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun BoxScope.BottomSheetContent() {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(0.98f)
            .align(Alignment.BottomStart),
        shape = shapeFlightDetail,
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.ic_resize_indicator),
            contentDescription = "Resize flight detail",
            alignment = Alignment.Center
        )
        FlightStatus()

        Spacer(modifier = Modifier.width(24.dp))
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0XFFE9E9E9))
                .height(0.6.dp)
        )
        Itinerary()
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(Color(0XFFE9E9E9))
                .height(0.6.dp)
        )
        Departure()
        Arrival()
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.24f)
        )
    }
}

@Composable
fun FlightStatus() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column() {
            Text(
                text = "AM 500",
                color = Color.Black,
                fontFamily = garnettFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp
            )
            Text(
                text = "Tuesday, Nov 21",
                color = Color.Black,
                fontFamily = garnettFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.weight(1.0f))
        ElevatedCard(
            shape = ShapeDefaults.ExtraSmall,
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                text = "Arrived",
                color = Color.White,
                fontFamily = garnettFontFamily,
                fontWeight = FontWeight.SemiBold, fontSize = 12.sp
            )
        }
    }
}

@Composable
fun Departure() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_flight_departure),
                contentDescription = "",
                tint = Color.Black
            )

            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Departure",
                fontFamily = garnettFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Ciudad de México - AICM",
                fontFamily = garnettFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 11.sp,
                color = Color(0x80000000),
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier =
            Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0XFFF7F7F7),
                    shape = shapeDeparture
                )
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Terminal",
                    fontFamily = garnettFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 11.sp,
                    color = Color.Black,
                )
                Text(
                    text = "2",
                    fontFamily = garnettFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color.Black,
                )
            }

            Spacer(modifier = Modifier.width(36.dp))
            Column {
                Text(
                    text = "Gate",
                    fontFamily = garnettFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 11.sp,
                    color = Color.Black,
                )
                Text(
                    text = "62",
                    fontFamily = garnettFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color.Black,
                )
            }

            Spacer(modifier = Modifier.width(36.dp))
            Column {
                Text(
                    text = "Boarding",
                    fontFamily = garnettFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 11.sp,
                    color = Color.Black,
                )
                Text(
                    text = "06:00",
                    fontFamily = garnettFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color.Black,
                )
            }
        }
    }
}

@Composable
private fun Arrival() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_flight_arrival),
                contentDescription = "",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Arrival",
                fontFamily = garnettFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Cancún  - Terminal 4",
                fontFamily = garnettFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 11.sp,
                color = Color(0x80000000),
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier =
            Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0XFFF7F7F7),
                    shape = shapeDeparture
                )
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Terminal",
                    fontFamily = garnettFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 11.sp,
                    color = Color.Black,
                )
                Text(
                    text = "2",
                    fontFamily = garnettFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color.Black,
                )
            }
            Spacer(modifier = Modifier.width(36.dp))
            Column {
                Text(
                    text = "Est. Landing",
                    fontFamily = garnettFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 11.sp,
                    color = Color.Black,
                )
                Text(
                    text = "09:21",
                    fontFamily = garnettFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color.Black,
                )
            }

        }
    }
}