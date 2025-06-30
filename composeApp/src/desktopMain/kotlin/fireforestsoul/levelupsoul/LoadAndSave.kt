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
        out.println(soul_color_type.toString())
        out.println(soul_color.value.toString(16))
        out.println(soul_name)
    }
}

actual fun loadValue() {
    val file = File(save_file_name)
    if (file.exists()) {
        val input = file.readLines()

        /** [oldAppVersion]
         * Check save version
         *
         * V > 0 `Habits >`
         *
         * V >= 4.000.000 `Soul >`
         */
        val oldAppVersion = input.getOrNull(0)?.toLong()
        if (oldAppVersion != null) {
            if (oldAppVersion > 0) {
                var index = 1

                /** [habitsSize]
                 *
                 * Load Habits:
                 *
                 * `nameOfHabit` `nameOfUnitsOfDimension` `typeOfGoalHabits` `needGoal` `needDays`
                 *
                 * V >= 2.000.000 `typeOfColorHabits` `colorGood`
                 *
                 * `startDate` `lastDate` `habitDays >`
                 */
                val habitsSize = input.getOrNull(index)?.toInt()!!
                index++
                habits = mutableListOf(Habit())
                for (x in 0 until habitsSize) { /** Habits load */
                    if (x > 0) habits.add(Habit())
                    habits[x].nameOfHabit = input.getOrNull(index).toString()
                    index++
                    habits[x].nameOfUnitsOfDimension = input.getOrNull(index).toString()
                    index++
                    if (oldAppVersion > 2001000) {
                        habits[x].typeOfGoalHabits =
                            enumValueOf<TypeOfGoalHabits>(input.getOrNull(index).toString())
                        index++
                    }
                    else {
                        habits[x].typeOfGoalHabits = toTypeOfGoalHabits(
                            enumValueOf<Old3000000TypeOfGoalHabits>(
                                input.getOrNull(index).toString()
                            )
                        )
                        index++
                    }
                    habits[x].needGoal = input.getOrNull(index)?.toDouble()!!
                    index++
                    habits[x].needDays = input.getOrNull(index)?.toInt()!!
                    index++
                    if (oldAppVersion > 1000000) {
                        habits[x].typeOfColorHabits =
                            enumValueOf<TypeOfColorHabits>(input.getOrNull(index).toString())
                        index++
                        habits[x].colorGood = Color(input.getOrNull(index).toString().toULong(16))
                        index++
                    }
                    habits[x].startDate =
                        input.getOrNull(index)?.let { LocalDate.parse(it) }!!
                    index++
                    habits[x].lastDate =
                        input.getOrNull(index)?.let { LocalDate.parse(it) }!!
                    index++

                    /** [habitDaySize]
                     *
                     * Load habit day:
                     *
                     * `today` `totalOfAPeriod` `correctly`
                     */
                    val habitDaySize = input.getOrNull(index)?.toInt()!!
                    index++
                    habits[x].habitDay = mutableListOf(HabitDay())
                    for (y in 0 until habitDaySize) {
                        if (y > 0) habits[x].habitDay.add(HabitDay())
                        habits[x].habitDay[y].today =
                            input.getOrNull(index)?.toDouble()!!
                        index++
                        habits[x].habitDay[y].totalOfAPeriod =
                            input.getOrNull(index)?.toDouble()!!
                        index++
                        habits[x].habitDay[y].correctly =
                            input.getOrNull(index).toBoolean()
                        index++
                    }
                }
                if (oldAppVersion > 3000000) {

                    /**
                     * `soul_color_type` `soul_color` `soul_name`
                     */
                    soul_color_type = enumValueOf<TypeOfColorHabits>(input.getOrNull(index).toString())
                    index++
                    soul_color = Color(input.getOrNull(index).toString().toULong(16))
                    index++
                    soul_name = input.getOrNull(index).toString()
                    index++
                }
            }
        }
    }
}
