package fireforestsoul.levelupsoul

import androidx.compose.ui.graphics.Color

fun seeColorByIndex(index: Int, maxDays: Int, maxSeria: Int): Color {
    return if (habits[index].typeOfColorHabits == TypeOfColorHabits.SELECTED)
        habits[index].colorGood
    else Color(
        (habits[index].habitDay.size.toDouble() / (if (maxDays != 0) maxDays else 1).toDouble() * 255.0).toInt(),
        (progress(index) * 255.0).toInt(),
        ((if (habitSeria(index).isNotEmpty()) habitSeria(index)[0] else 0) / (if (maxSeria != 0) maxSeria else 1) * 255.0).toInt()
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
        (habits[index].habitDay.size.toDouble() / (if (maxDays != 0) maxDays else 1).toDouble() * 127.5).toInt(),
        (progress(index) * 127.5).toInt(),
        ((if (habitSeria(index).isNotEmpty()) habitSeria(index)[0] else 0) / (if (maxSeria != 0) maxSeria else 1) * 127.5).toInt()
    )
}