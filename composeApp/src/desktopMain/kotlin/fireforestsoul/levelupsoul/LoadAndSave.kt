package fireforestsoul.levelupsoul

import androidx.compose.ui.graphics.Color
import kotlinx.datetime.LocalDate
import java.io.File

actual fun saveValue() {
    File(save_file_name).printWriter().use { out ->
        out.println(app_version.toString())
        out.println(habits.size.toString())
        for (x in 0 until habits.size) {
            out.println(habits[x].nameOfHabit)
            out.println(habits[x].nameOfUnitsOfDimension)
            out.println(habits[x].typeOfGoalHabits.toString())
            out.println(habits[x].needGoal.toString())
            out.println(habits[x].needDays.toString())
            out.println(habits[x].typeOfColorHabits.toString())
            out.println(habits[x].colorGood.value.toString(16))
            out.println(habits[x].startDate.toString())
            out.println(habits[x].lastDate.toString())
            out.println(habits[x].habitDay.size.toString())
            for (y in 0 until habits[x].habitDay.size) {
                out.println(habits[x].habitDay[y].today.toString())
                out.println(habits[x].habitDay[y].totalOfAPeriod.toString())
                out.println(habits[x].habitDay[y].correctly.toString())
            }
        }
    }
}

actual fun loadValue() {
    val file = File(save_file_name)
    if (file.exists()) {
        val input = file.readLines()

        val oldAppVersion = input.getOrNull(0)?.toLong()
        if (oldAppVersion != null) {
            if (oldAppVersion > 0) {
                val habitsSize = input.getOrNull(1)?.toInt()!!
                habits = mutableListOf(Habit())
                var plusIndexX = 0
                for (x in 0 until habitsSize) {
                    if (x > 0) habits.add(Habit())
                    habits[x].nameOfHabit = input.getOrNull(2 + plusIndexX).toString()
                    habits[x].nameOfUnitsOfDimension = input.getOrNull(3 + plusIndexX).toString()
                    if (oldAppVersion > 2001000)
                        habits[x].typeOfGoalHabits =
                            enumValueOf<TypeOfGoalHabits>(input.getOrNull(4 + plusIndexX).toString())
                    else habits[x].typeOfGoalHabits = toTypeOfGoalHabits(
                        enumValueOf<Old2001000TypeOfGoalHabits>(
                            input.getOrNull(4 + plusIndexX).toString()
                        )
                    )
                    habits[x].needGoal = input.getOrNull(5 + plusIndexX)?.toDouble()!!
                    habits[x].needDays = input.getOrNull(6 + plusIndexX)?.toInt()!!
                    var plusIndex1000000 = 0
                    if (oldAppVersion > 1000000) {
                        plusIndex1000000 = 2
                        habits[x].typeOfColorHabits =
                            enumValueOf<TypeOfColorHabits>(input.getOrNull(7 + plusIndexX).toString())
                        habits[x].colorGood = Color(input.getOrNull(8 + plusIndexX).toString().toULong(16))
                    }
                    habits[x].startDate =
                        input.getOrNull(plusIndex1000000 + 7 + plusIndexX)?.let { LocalDate.parse(it) }!!
                    habits[x].lastDate =
                        input.getOrNull(plusIndex1000000 + 8 + plusIndexX)?.let { LocalDate.parse(it) }!!
                    val habitDaySize = input.getOrNull(plusIndex1000000 + 9 + plusIndexX)?.toInt()!!
                    habits[x].habitDay = mutableListOf(HabitDay())
                    var plusIndexY = 0
                    for (y in 0 until habitDaySize) {
                        if (y > 0) habits[x].habitDay.add(HabitDay())
                        habits[x].habitDay[y].today =
                            input.getOrNull(plusIndex1000000 + 10 + plusIndexX + plusIndexY)?.toDouble()!!
                        habits[x].habitDay[y].totalOfAPeriod =
                            input.getOrNull(plusIndex1000000 + 11 + plusIndexX + plusIndexY)?.toDouble()!!
                        habits[x].habitDay[y].correctly =
                            input.getOrNull(plusIndex1000000 + 12 + plusIndexX + plusIndexY).toBoolean()
                        plusIndexY += 3
                    }
                    plusIndexX += 8 + plusIndexY + plusIndex1000000
                }
            }
        }
    }
}
