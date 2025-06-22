package fireforestsoul.levelupsoul

import androidx.compose.ui.graphics.Color

fun seeColorByIndex(index: Int, maxDays: Int, maxSeria: Int): Color {
    return if (habits[index].typeOfColorHabits == TypeOfColorHabits.SELECTED)
        habits[index].colorGood
    else Color(
        (habits[index].habitDay.size.toDouble() / maxDays.toDouble() * 255.0).toInt(),
        (progress(index) * 255.0).toInt(),
        ((if (habitSeria(index).isNotEmpty()) habitSeria(index)[0] else 0) / maxSeria * 255.0).toInt()
    )
}

fun noSeeColorByIndex(index: Int, maxDays: Int, maxSeria: Int): Color {
    return if (habits[index].typeOfColorHabits == TypeOfColorHabits.SELECTED)
        Color(
            habits[index].colorGood.red * 0.5F,
            habits[index].colorGood.green * 0.5F,
            habits[index].colorGood.blue * 0.5F
        )
    else Color(
        (habits[index].habitDay.size.toDouble() / maxDays.toDouble() * 127.5).toInt(),
        (progress(index) * 127.5).toInt(),
        ((if (habitSeria(index).isNotEmpty()) habitSeria(index)[0] else 0) / maxSeria * 127.5).toInt()
    )
}