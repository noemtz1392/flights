package mx.com.test.android.presentation.screens.list

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBackIos
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mx.com.test.android.domain.models.Flight
import mx.com.test.android.presentation.OutlinedCardExample
import mx.com.test.android.presentation.R
import mx.com.test.android.presentation.screens.FlightViewModel
import mx.com.test.android.presentation.theme.FlightsTheme
import mx.com.test.android.presentation.theme.garnettFontFamily


@ExperimentalMaterial3Api
@Composable
fun FlightListScreen(
    modifier: Modifier = Modifier,
    viewModel: FlightViewModel = hiltViewModel(),
    onBackPress: () -> Unit,
    navigateToDetail: (Flight) -> Unit,
) {
    BackHandler(onBack = onBackPress)

    val scrollState = rememberLazyListState()
    val topBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topBarState)

    val uiState by viewModel.flightUiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AppBar(
                modifier = Modifier.padding(end = FlightsTheme.padding.paddingNormal),
                actions = {
                    DateContent(
                        flightNumber = uiState.flightNumber.orEmpty(),
                        departureDate = uiState.dateOfDeparture
                    )
                }, onNavIconPressed = onBackPress
            )
        },
        contentWindowInsets = ScaffoldDefaults
            .contentWindowInsets
            .exclude(WindowInsets.navigationBars)
            .exclude(WindowInsets.ime),
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            FlightCards(
                flightList = uiState.flights,
                scrollState = scrollState,
                navigateToDetail = navigateToDetail
            )
        }
    }
}

@Composable
fun FlightCards(
    flightList: List<Flight>,
    scrollState: LazyListState,
    navigateToDetail: (Flight) -> Unit
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
        items(flightList, key = { it.id }) { item ->
            OutlinedCardExample(
                flight = item,
                navigateToDetail = navigateToDetail
            )
        }
    }
}

const val FlightCardsTestTag = "FlightCardsTestTag"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    onNavIconPressed: () -> Unit = { },
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    TopAppBar(
        modifier = modifier,
        title = title,
        actions = actions,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            NavigationBackIcon(
                contentDescription = stringResource(id = R.string.action_navigation_back),
                onBackPress = onNavIconPressed
            )

        }
    )
}

@Composable
fun DateContent(
    flightNumber: String,
    departureDate: String
) {
    Column(
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = flightNumber,
            fontSize = 21.sp,
            color = Color.Black,
            fontFamily = garnettFontFamily,
            fontWeight = FontWeight.SemiBold
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = departureDate,
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = garnettFontFamily,
            )
            Box(
                Modifier
                    .padding(FlightsTheme.padding.paddingExtraSmall)
                    .width(0.6.dp)
                    .height(16.dp)
                    .background(Color(0x4D000000))
            )
            Icon(
                imageVector = Icons.Rounded.CalendarMonth,
                contentDescription = "",
                modifier = Modifier.size(ButtonDefaults.IconSize),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.size(FlightsTheme.spacing.spaceExtraSmall))
            Text(
                text = stringResource(id = R.string.action_change_date),
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = garnettFontFamily,
                textDecoration = TextDecoration.Underline
            )
        }

    }
}

@Composable
fun NavigationBackIcon(
    contentDescription: String?,
    onBackPress: () -> Unit,
) {
    IconButton(onClick = onBackPress) {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.ArrowBackIos,
            contentDescription = contentDescription,
            tint = Color.Black
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(device = Devices.PHONE, showBackground = true)
@Composable
fun preview() {
    FlightsTheme {
        AppBar(actions = {
            DateContent(
                "AM 600",
                "Domingo, 10 Diciembre"
            )
        })
    }
}