package com.example.codelabstudy.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = primaryDark,
    primaryVariant = primaryDarkVariant,
    onPrimary = White2,
    secondary = darkSecondary,
    secondaryVariant = darkSecondaryVariant,
    onSecondary = White2,
    error = RedErrorLight,
    onError = RedErrorLight,
)

private val LightColorPalette = lightColors(
    primary = primaryLight,
    primaryVariant = primaryLightVariant,
    onPrimary = Black2,
    secondary = lightSecondary,
    secondaryVariant = lightSecondaryVariant,
    onSecondary = Black2,
    error = RedErrorDark,
    onError = RedErrorLight,
)

@Composable
fun CodeLabStudyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController() //@composable 밑에다가 써야함
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = true
        )
    }

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}