package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.model.ThemePalette

@Composable
fun PresentationAppTheme(
    palette: ThemePalette = ThemePalette.CORPORATE,
    content: @Composable () -> Unit
) {
    val colorScheme = when (palette) {
        ThemePalette.CORPORATE -> lightColorScheme(
            primary = palette.primaryColor,
            onPrimary = Color.White,
            secondary = palette.accentColor,
            background = palette.backgroundColor,
            surface = palette.surfaceColor,
            onBackground = palette.textColorPrimary,
            onSurface = palette.textColorPrimary,
            outline = palette.cardBorderColor
        )
        ThemePalette.CREATIVE -> lightColorScheme(
            primary = palette.primaryColor,
            onPrimary = Color.White,
            secondary = palette.accentColor,
            background = palette.backgroundColor,
            surface = palette.surfaceColor,
            onBackground = palette.textColorPrimary,
            onSurface = palette.textColorPrimary,
            outline = palette.cardBorderColor
        )
        ThemePalette.CYBER -> darkColorScheme(
            primary = palette.primaryColor,
            onPrimary = Color.Black,
            secondary = palette.accentColor,
            background = palette.backgroundColor,
            surface = palette.surfaceColor,
            onBackground = palette.textColorPrimary,
            onSurface = palette.textColorPrimary,
            outline = palette.cardBorderColor
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
