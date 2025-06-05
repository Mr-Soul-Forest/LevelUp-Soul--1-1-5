package fireforestsoul.levelupsoul

import androidx.compose.ui.graphics.Color

fun averageColor(colors: List<Color>): Color {
    if (colors.isEmpty()) return Color.Black

    val size = colors.size
    var sumRed = 0.0f
    var sumGreen = 0.0f
    var sumBlue = 0.0f
    var sumAlpha = 0.0f
    for (c in colors) {
        sumRed += c.red
        sumGreen += c.green
        sumBlue += c.blue
        sumAlpha += c.alpha
    }

    return Color(
        red = sumRed / size,
        green = sumGreen / size,
        blue = sumBlue / size,
        alpha = sumAlpha / size
    )
}