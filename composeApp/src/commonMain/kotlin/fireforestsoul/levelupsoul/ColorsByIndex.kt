package fireforestsoul.levelupsoul

import androidx.compose.ui.graphics.Color
import kotlin.math.max
import kotlin.math.min

fun seeColorByIndex(index: Int): Color {
    var maxDays = 0
    for (habit in habits) {
        maxDays = max(habit.habitDay.size, maxDays)
    }
    var maxSeria = 0
    for (x in 0 until habits.size) {
        maxSeria = max(
            if (habitSeria(x).isNotEmpty()) habitSeria(x)[0] else 0,
            maxSeria
        )
    }
    var maxLevel = 0
    var minLevel = 0
    for (habit in habits) {
        maxLevel = max(maxLevel, habit.level)
        minLevel = min(minLevel, habit.level)
    }
    val levels = maxLevel - minLevel

    /**
     * red= 2 green= 1 blue= 1
     */
    return if (habits[index].typeOfColorHabits == TypeOfColorHabits.SELECTED)
        habits[index].colorGood
    else Color(
        (habits[index].habitDay.size.toDouble() / (if (maxDays != 0) maxDays else 1).toDouble() * 127.5).toInt()
        + ((habits[index].level - minLevel) / (if (levels == 0) 1 else levels) * 127.5).toInt(),
        (progress(index) * 255.0).toInt(),
        ((if (habitSeria(index).isNotEmpty()) habitSeria(index)[0] else 0) / (if (maxSeria != 0) maxSeria else 1) * 255.0).toInt()
    )
}

fun noSeeColorByIndex(index: Int): Color {
    val seeColor = seeColorByIndex(index)
    return Color(
        seeColor.red * 0.5f,
        seeColor.green * 0.5f,
        seeColor.blue * 0.5f
    )
}