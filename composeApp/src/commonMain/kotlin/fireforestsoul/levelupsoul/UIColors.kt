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

val UICT_no_see = Color(106, 118, 127)
val UICT_see = Color(255, 255, 255)

val UIC_light = Color(37, 40, 44)
val UIC = Color(26, 28, 32)
val UIC_dark = Color(18, 20, 24)
val UIC_black = Color(0, 0, 0)
val UIC_white = Color(255, 255, 255)
val UIC_green = Color(0, 255, 0)
val UIC_red = Color(255, 0, 0)

val UIC_dark_x05 = averageColor(listOf(UIC, UIC_dark)) //22 24 28
val UIC_extra_dark = averageColor(listOf(UIC_dark, UIC_black)) //9 10 12
val UIC_dark_x2 = averageColor(listOf(UIC_dark, UIC_extra_dark)) //13(.5) 15 18
val UIC_extra_light = averageColor(listOf(UIC_light, UIC_white)) //146 147(.5) 149(.5)
val UIC_light_x2 = averageColor(listOf(UIC_light, UIC_extra_light)) //91(.5) 93(.75) 96(.75)
val UIC_light_x05 = averageColor(listOf(UIC, UIC_light)) //31(.5) 34 38

val UIC_x2green = UIC_dark.copy(green = if (UIC_dark.green * 2f <= 1f) UIC_dark.green * 2f else 1f)
val UIC_x2green_x1o5white = Color(
    if (UIC_x2green.red * 1.5f <= 1f) UIC_x2green.red * 1.5f else 1f,
    if (UIC_x2green.green * 1.5f <= 1f) UIC_x2green.green * 1.5f else 1f,
    if (UIC_x2green.blue * 1.5f <= 1f) UIC_x2green.blue * 1.5f else 1f
)