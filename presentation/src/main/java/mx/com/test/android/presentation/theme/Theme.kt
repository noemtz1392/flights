package mx.com.test.android.presentation.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import mx.com.test.android.presentation.R

private val flightsLightColorScheme = lightColorScheme(
    primary = color_light_primary,
    onPrimary = color_light_onPrimary,
    primaryContainer = Color.White,
    onPrimaryContainer = Color.White,
    secondary = Color.White,
    onSecondary = Color.White,
    secondaryContainer = Color.White,
    onSecondaryContainer = Color.White,
    tertiary = Color.White,
    onTertiary = Color.White,
    tertiaryContainer = Color.White,
    onTertiaryContainer = Color.White,
    error = Color.White,
    errorContainer = Color.White,
    onError = Color.White,
    onErrorContainer = Color.White,
    background = Color.White,
    onBackground = Color.White,
    surface = Color.White,
    onSurface = Color.White,
    surfaceVariant = Color.White,
    onSurfaceVariant = Color.White,
    outline = Color.White,
    inverseOnSurface = Color.White,
    inverseSurface = Color.White,
    inversePrimary = Color.White,
    surfaceTint = Color.White,
    outlineVariant = Color.White,
    scrim = Color.White,
)

private val flightsDarkColorScheme = darkColorScheme(
    primary = color_dark_primary,
    onPrimary = color_dark_onPrimary,
    primaryContainer = Color.White,
    onPrimaryContainer = Color.White,
    secondary = Color.White,
    onSecondary = Color.White,
    secondaryContainer = Color.White,
    onSecondaryContainer = Color.White,
    tertiary = Color.White,
    onTertiary = Color.White,
    tertiaryContainer = Color.White,
    onTertiaryContainer = Color.White,
    error = Color.White,
    errorContainer = Color.White,
    onError = Color.White,
    onErrorContainer = Color.White,
    background = Color.White,
    onBackground = Color.White,
    surface = Color.White,
    onSurface = Color.White,
    surfaceVariant = Color.White,
    onSurfaceVariant = Color.White,
    outline = Color.White,
    inverseOnSurface = Color.White,
    inverseSurface = Color.White,
    inversePrimary = Color.White,
    surfaceTint = Color.White,
    outlineVariant = Color.White,
    scrim = Color.White,
)

@Composable
fun FlightsTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    useDynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val isDynamicColor = useDynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    val colorScheme = when {
        isDynamicColor && useDarkTheme -> dynamicDarkColorScheme(context)
        isDynamicColor && !useDarkTheme -> dynamicLightColorScheme(context)
        useDarkTheme -> flightsDarkColorScheme
        else -> flightsLightColorScheme
    }


    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = context.getColor(R.color.color_status_bar)
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                useDarkTheme
        }
    }

    CompositionLocalProvider(
        LocalSpacing provides Spacing(),
        LocalPadding provides Padding()
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}

object FlightsTheme {
    /**
     * Proxy to [MaterialTheme]
     */
    val colorScheme: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme

    /**
     * Proxy to [MaterialTheme]
     */
    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.typography

    /**
     * Proxy to [MaterialTheme]
     */
    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.shapes

    /**
     * Retrieves the current [Spacing] at the call site's position in the hierarchy.
     */
    val spacing: Spacing
        @Composable
        @ReadOnlyComposable
        get() = LocalSpacing.current

    /**
     * Retrieves the current [Padding] at the call site's position in the hierarchy.
     */
    val padding: Padding
        @Composable
        @ReadOnlyComposable
        get() = LocalPadding.current
}