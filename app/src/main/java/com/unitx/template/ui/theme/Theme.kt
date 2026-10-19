package com.unitx.template.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryColorDark,
    onPrimary = OnPrimaryColorDark,
    secondary = SecondaryColorDark,
    onSecondary = OnSecondaryColorDark,
    tertiary = TertiaryColorDark,
    onTertiary = OnTertiaryColorDark,
    background = BackgroundColorDark,
    onBackground = OnBackgroundColorDark,
    surface = SurfaceColorDark,
    onSurface = OnSurfaceColorDark,
    surfaceContainerLowest = SurfaceLowestColorDark,
    error = ErrorColorDark,
    onError = OnErrorColorDark
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    onPrimary = OnPrimaryColor,
    secondary = SecondaryColor,
    onSecondary = OnSecondaryColor,
    tertiary = TertiaryColor,
    onTertiary = OnTertiaryColor,
    background = BackgroundColor,
    onBackground = OnBackgroundColor,
    surface = SurfaceColor,
    onSurface = OnSurfaceColor,
    surfaceContainerLowest = SurfaceLowestColor,
    error = ErrorColor,
    onError = OnErrorColor
)

@Composable
fun extendedColor (light: Color, dark : Color): Color{
    return if (isSystemInDarkTheme()) dark else light
}

val ColorScheme.extraColor1 @Composable
get() = extendedColor(
        light = Color(0xFFD0BCFF),
        dark = Color(0xFFD0BCFF)
    )

private val Shapes = Shapes(
    extraSmall = RoundedCornerShape(6.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(18.dp),
    extraLarge = RoundedCornerShape(24.dp)
)

@Composable
fun AndroeggTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}