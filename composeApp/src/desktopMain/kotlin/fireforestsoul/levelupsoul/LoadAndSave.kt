package fireforestsoul.levelupsoul

import androidx.compose.ui.graphics.Color
import kotlinx.datetime.LocalDate
import java.io.File
import com.ionspin.kotlin.bignum.decimal.toBigDecimal

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
            out.println(habits[x].changeLevel.toString())
            out.println(habits[x].changeNeedGoalWithLevel.toString())
            out.println(habits[x].changeNeedDaysWithLevel.toString())
            out.println(habits[x].startDate.toString())
            out.println(habits[x].lastLevelChangeDate.toString())
            out.println(habits[x].level.toString())
            out.println(habits[x].habitDay.size.toString())
            for (y in 0 until habits[x].habitDay.size) {
                out.println(habits[x].habitDay[y].today.toString())
                out.println(habits[x].habitDay[y].totalOfAPeriod.toString())
                out.println(habits[x].habitDay[y].correctly.toString())
            }
        }
        out.println(soul_color_type.toString())
        out.println(soul_color.value.toString(16))
        out.println(soul_name)
        out.println(soul_level.toString())
        out.println(soul_last_level_change_date.toString())
        out.println(language.toString())
        out.println(withExponent).toString()
    }
}

actual fun loadValue() {
    val file = File(save_file_name)
    if (file.exists()) {
        val input = file.readLines()

        val oldAppVersion = input.getOrNull(0)?.toLong()
        if (oldAppVersion != null) {
            if (oldAppVersion > 0) {
                var index = 1

                val habitsSize = input.getOrNull(index)?.toInt()!!
                index++
                habits = mutableListOf(Habit())
                for (x in 0 until habitsSize) {
                    if (x > 0) habits.add(Habit())
                    habits[x].nameOfHabit = input.getOrNull(index).toString()
                    index++
                    habits[x].nameOfUnitsOfDimension = input.getOrNull(index).toString()
                    index++
                    if (oldAppVersion > 2001000) {
                        habits[x].typeOfGoalHabits =
                            enumValueOf<TypeOfGoalHabits>(input.getOrNull(index).toString())
                        index++
                    } else {
                        habits[x].typeOfGoalHabits = toTypeOfGoalHabits(
                            enumValueOf<Old3000000TypeOfGoalHabits>(
                                input.getOrNull(index).toString()
                            )
                        )
                        index++
                    }
                    habits[x].needGoal = input.getOrNull(index)?.toBigDecimal()!!
                    index++
                    habits[x].needDays = input.getOrNull(index)?.toInt()!!
                    index++
                    if (oldAppVersion >= 2000000) {
                        habits[x].typeOfColorHabits =
                            enumValueOf<TypeOfColorHabits>(input.getOrNull(index).toString())
                        index++
                        habits[x].colorGood = Color(input.getOrNull(index).toString().toULong(16))
                        index++

                        if (oldAppVersion >= 1000000000) {
                            habits[x].changeLevel = input.getOrNull(index).toBoolean()
                            index++
                            habits[x].changeNeedGoalWithLevel = input.getOrNull(index).toBoolean()
                            index++
                            habits[x].changeNeedDaysWithLevel = input.getOrNull(index).toBoolean()
                            index++
                        }
                    }
                    habits[x].startDate =
                        input.getOrNull(index)?.let { LocalDate.parse(it) }!!
                    index++
                    if (oldAppVersion < 1000000000) {
                        /** lastDate */
                        index++
                    }

                    if (oldAppVersion >= 1000000000) {
                        habits[x].lastLevelChangeDate = input.getOrNull(index)?.let { LocalDate.parse(it) }!!
                        index++
                        habits[x].level = input.getOrNull(index)?.toInt()!!
                        index++
                    }

                    val habitDaySize = input.getOrNull(index)?.toInt()!!
                    index++
                    habits[x].habitDay = mutableListOf(HabitDay())
                    for (y in 0 until habitDaySize) {
                        if (y > 0) habits[x].habitDay.add(HabitDay())

                        habits[x].habitDay[y].today =
                            input.getOrNull(index)?.toBigDecimal()!!
                        index++
                        habits[x].habitDay[y].totalOfAPeriod =
                            input.getOrNull(index)?.toBigDecimal()!!
                        index++
                        habits[x].habitDay[y].correctly =
                            input.getOrNull(index).toBoolean()
                        index++
                    }
                }
                if (oldAppVersion > 3000000) {

                    soul_color_type = enumValueOf<TypeOfColorHabits>(input.getOrNull(index).toString())
                    index++
                    soul_color = Color(input.getOrNull(index).toString().toULong(16))
                    index++
                    soul_name = input.getOrNull(index).toString()
                    index++

                    if (oldAppVersion >= 1000000000) {
                        soul_level = input.getOrNull(index)?.toInt()!!
                        index++
                        soul_last_level_change_date = input.getOrNull(index)?.let { LocalDate.parse(it) }!!
                        index++

                        if (oldAppVersion >= 1000001000) {
                            language = enumValueOf<Languages>(input.getOrNull(index).toString())
                            index++

                            if (oldAppVersion >= 1001000000) {
                                withExponent = input.getOrNull(index).toBoolean()
                                index++
                            }
                        }
                    }
                }
            }
        }
    }
}
