package ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import data.Strings

val LocalTheme = staticCompositionLocalOf {
    Theme(
        strings = Strings(),
        colors = ThemeColors()
    )
}

data class Theme(
    val strings: Strings,
    val colors: ThemeColors
)

