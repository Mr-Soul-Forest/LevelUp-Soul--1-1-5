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

val UIC_dark_x05 = averageColor(listOf(UIC, UIC_dark))
val UIC_extra_dark = averageColor(listOf(UIC_dark, UIC_black))
val UIC_dark_x2 = averageColor(listOf(UIC_dark, UIC_extra_dark))
val UIC_extra_light = averageColor(listOf(UIC_light, UIC_white))
val UIC_light_x2 = averageColor(listOf(UIC_light, UIC_extra_light))
val UIC_light_x05 = averageColor(listOf(UIC, UIC_light))