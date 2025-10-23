/**Copyright 2025 Forge-of-Ovorldule (https://github.com/Forge-of-Ovorldule) and Mr-Soul-Forest (https://github.com/Mr-Soul-Forest)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

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

fun reversColor(color: Color): Color {
    return Color(1f - color.red, 1f - color.green, 1f - color.blue, color.alpha)
}

fun reversNoBiggerColor(color: Color): Color {
    val maxColor = maxOf(color.red, color.green, color.blue)
    return Color(maxColor - color.red, maxColor - color.green, maxColor - color.blue)
}

fun checkBackgroundBright(background: Color, ifDark: Color, ifLight: Color = reversColor(ifDark)): Color {
    return if ((background.red + background.green + background.blue) * 255 < 382.5) ifDark else ifLight
}

fun Color.multiply(
    r: Float = 1f,
    g: Float = 1f,
    b: Float = 1f,
    a: Float = 1f
): Color {
    return Color(
        if (this.red * r > 1f) 1f else this.red * r,
        if (this.green * g > 1f) 1f else this.green * g,
        if (this.blue * b > 1f) 1f else this.blue * b,
        if (this.alpha * a > 1f) 1f else this.alpha * a
    )
}