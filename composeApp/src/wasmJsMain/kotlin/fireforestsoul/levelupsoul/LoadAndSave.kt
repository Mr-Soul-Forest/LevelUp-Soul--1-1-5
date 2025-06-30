package fireforestsoul.levelupsoul

import androidx.compose.ui.graphics.Color
import kotlinx.browser.localStorage
import kotlinx.datetime.LocalDate

actual fun saveValue() {
    localStorage.setItem("app_version", app_version.toString())
    localStorage.setItem("habits-size", habits.size.toString())
    for (x in 0 until habits.size) {
        localStorage.setItem("habits-$x-nameOfHabit", habits[x].nameOfHabit)
        localStorage.setItem("habits-$x-nameOfUnitsOfDimension", habits[x].nameOfUnitsOfDimension)
        localStorage.setItem("habits-$x-typeOfGoalHabits", habits[x].typeOfGoalHabits.toString())
        localStorage.setItem("habits-$x-needGoal", habits[x].needGoal.toString())
        localStorage.setItem("habits-$x-needDays", habits[x].needDays.toString())
        localStorage.setItem("habits-$x-typeOfColorHabits", habits[x].typeOfColorHabits.toString())
        localStorage.setItem("habits-$x-colorGood", habits[x].colorGood.value.toString(16))
        localStorage.setItem("habits-$x-startDate", habits[x].startDate.toString())
        localStorage.setItem("habits-$x-lastDate", habits[x].lastDate.toString())
        localStorage.setItem("habits-$x-habitDay-size", habits[x].habitDay.size.toString())
        for (y in 0 until habits[x].habitDay.size) {
            localStorage.setItem("habits-$x-habitDay-$y-today", habits[x].habitDay[y].today.toString())
            localStorage.setItem(
                "habits-$x-habitDay-$y-totalOfAPeriod",
                habits[x].habitDay[y].totalOfAPeriod.toString()
            )
            localStorage.setItem("habits-$x-habitDay-$y-correctly", habits[x].habitDay[y].correctly.toString())
        }
    }
    localStorage.setItem("soul_color_type", soul_color_type.toString())
    localStorage.setItem("soul_color", soul_color.value.toString(16))
    localStorage.setItem("soul_name", soul_name)
}

actual fun loadValue() {

    /** [oldAppVersion]
     * Check save version
     *
     * V > 0 `Habits >`
     *
     * V > 3.000.000 `Soul >`
     */
    val oldAppVersion = localStorage.getItem("app_version")?.toLong()
    if (oldAppVersion != null) {
        if (oldAppVersion > 0) {

            /** [habitsSize]
             *
             * Load Habits:
             *
             * `nameOfHabit` `nameOfUnitsOfDimension` `typeOfGoalHabits` `needGoal` `needDays`
             *
             * V > 1.000.000 `typeOfColorHabits` `colorGood`
             *
             * `startDate` `lastDate` `habitDays >`
             */
            val habitsSize = localStorage.getItem("habits-size")?.toInt()!!
            habits = mutableListOf(Habit())
            for (x in 0 until habitsSize) {
                if (x > 0) habits.add(Habit())
                habits[x].nameOfHabit = localStorage.getItem("habits-$x-nameOfHabit").toString()
                habits[x].nameOfUnitsOfDimension = localStorage.getItem("habits-$x-nameOfUnitsOfDimension").toString()
                if (oldAppVersion > 2001000)
                    habits[x].typeOfGoalHabits =
                        enumValueOf<TypeOfGoalHabits>(localStorage.getItem("habits-$x-typeOfGoalHabits").toString())
                else habits[x].typeOfGoalHabits = toTypeOfGoalHabits(
                    enumValueOf<Old3000000TypeOfGoalHabits>(
                        localStorage.getItem("habits-$x-typeOfGoalHabits").toString()
                    )
                )
                habits[x].needGoal = localStorage.getItem("habits-$x-needGoal")?.toDouble()!!
                habits[x].needDays = localStorage.getItem("habits-$x-needDays")?.toInt()!!
                if (oldAppVersion > 1000000) {
                    habits[x].typeOfColorHabits =
                        enumValueOf<TypeOfColorHabits>(localStorage.getItem("habits-$x-typeOfColorHabits").toString())
                    habits[x].colorGood = Color(localStorage.getItem("habits-$x-colorGood").toString().toULong(16))
                }
                habits[x].startDate = localStorage.getItem("habits-$x-startDate")?.let { LocalDate.parse(it) }!!
                habits[x].lastDate = localStorage.getItem("habits-$x-lastDate")?.let { LocalDate.parse(it) }!!

                /** [habitDaySize]
                 *
                 * Load habit day:
                 *
                 * `today` `totalOfAPeriod` `correctly`
                 */
                val habitDaySize = localStorage.getItem("habits-$x-habitDay-size")?.toInt()!!
                habits[x].habitDay = mutableListOf(HabitDay())
                for (y in 0 until habitDaySize) {
                    if (y > 0) habits[x].habitDay.add(HabitDay())
                    habits[x].habitDay[y].today = localStorage.getItem("habits-$x-habitDay-$y-today")?.toDouble()!!
                    habits[x].habitDay[y].totalOfAPeriod =
                        localStorage.getItem("habits-$x-habitDay-$y-totalOfAPeriod")?.toDouble()!!
                    habits[x].habitDay[y].correctly =
                        localStorage.getItem("habits-$x-habitDay-$y-correctly").toBoolean()
                }
            }

            if (oldAppVersion > 3000000) {

                /**
                 * `soul_color_type` `soul_color` `soul_name`
                 */
                soul_color_type = enumValueOf<TypeOfColorHabits>(localStorage.getItem("soul_color_type").toString())
                soul_color = Color(localStorage.getItem("soul_color").toString().toULong(16))
                soul_name = localStorage.getItem("soul_name").toString()
            }
        }
    }
}