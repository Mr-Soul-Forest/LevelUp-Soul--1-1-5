package fireforestsoul.levelupsoul

import android.content.Context
import android.content.SharedPreferences
import kotlinx.datetime.LocalDate
import kotlin.text.toString
import androidx.compose.ui.graphics.Color

var context: Context? = null

fun initStorage(appContext: Context) {
    context = appContext
}

private val prefs: SharedPreferences
    get() = context!!.getSharedPreferences(save_file_name, Context.MODE_PRIVATE)

actual fun saveValue() {
    prefs.edit().apply {
        putString("app_version", app_version.toString())
        putString("habits-size", habits.size.toString())
        for (x in habits.indices) {
            putString("habits-$x-nameOfHabit", habits[x].nameOfHabit)
            putString("habits-$x-nameOfUnitsOfDimension", habits[x].nameOfUnitsOfDimension)
            putString("habits-$x-typeOfGoalHabits", habits[x].typeOfGoalHabits.toString())
            putString("habits-$x-needGoal", habits[x].needGoal.toString())
            putString("habits-$x-needDays", habits[x].needDays.toString())
            putString("habits-$x-typeOfColorHabits", habits[x].typeOfColorHabits.toString())
            putString("habits-$x-colorGood", habits[x].colorGood.value.toString(16))
            putString("habits-$x-startDate", habits[x].startDate.toString())
            putString("habits-$x-lastDate", habits[x].lastDate.toString())
            putString("habits-$x-habitDay-size", habits[x].habitDay.size.toString())
            for (y in habits[x].habitDay.indices) {
                putString("habits-$x-habitDay-$y-today", habits[x].habitDay[y].today.toString())
                putString(
                    "habits-$x-habitDay-$y-totalOfAPeriod",
                    habits[x].habitDay[y].totalOfAPeriod.toString()
                )
                putString(
                    "habits-$x-habitDay-$y-correctly",
                    habits[x].habitDay[y].correctly.toString()
                )
            }
        }
        putString("soul_color_type", soul_color_type.toString())
        putString("soul_color", soul_color.value.toString(16))
        putString("soul_name", soul_name)
        apply()
    }
}

actual fun loadValue() {

    /** [oldAppVersion]
     * Check save version
     *
     * V > 0 `Habits >`
     *
     * V >= 4.000.000 `Soul >`
     */
    val oldAppVersion = prefs.getString("app_version", null)?.toLongOrNull() ?: return
    if (oldAppVersion > 0) {

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
        val habitsSize = prefs.getString("habits-size", null)?.toIntOrNull() ?: 0
        habits = MutableList(habitsSize) { Habit() }
        for (x in 0 until habitsSize) {
            habits[x].nameOfHabit =
                prefs.getString("habits-$x-nameOfHabit", "New habit") ?: "New habit"
            habits[x].nameOfUnitsOfDimension =
                prefs.getString("habits-$x-nameOfUnitsOfDimension", "km") ?: "km"
            if (oldAppVersion >= 3000000) {
                habits[x].typeOfGoalHabits = enumValueOf<TypeOfGoalHabits>(
                    prefs.getString(
                        "habits-$x-typeOfGoalHabits",
                        "AT_LEAST"
                    )!!
                )
            } else {
                habits[x].typeOfGoalHabits = toTypeOfGoalHabits(
                    enumValueOf<Old3000000TypeOfGoalHabits>(
                        prefs.getString(
                            "habits-$x-typeOfGoalHabits",
                            "NOT_LITTLE"
                        )!!
                    )
                )
            }
            habits[x].needGoal =
                prefs.getString("habits-$x-needGoal", "1.0")?.toDoubleOrNull() ?: 1.0
            habits[x].needDays = prefs.getString("habits-$x-needDays", "1")?.toIntOrNull() ?: 1
            if (oldAppVersion >= 2000000) {
                habits[x].typeOfColorHabits =
                    enumValueOf<TypeOfColorHabits>(
                        prefs.getString(
                            "habits-$x-typeOfColorHabits",
                            "ADAPTIVE"
                        )!!
                    )
                habits[x].colorGood = Color(
                    prefs.getString("habits-$x-colorGood", "ff000000")?.toULongOrNull(16)
                        ?: 0xFF000000u
                )
            }
            habits[x].startDate =
                prefs.getString("habits-$x-startDate", "2025-01-01")?.let { LocalDate.parse(it) }
                    ?: LocalDate(
                        2025,
                        1,
                        1
                    )
            habits[x].lastDate =
                prefs.getString("habits-$x-lastDate", "2025-01-01")?.let { LocalDate.parse(it) }
                    ?: LocalDate(
                        2025,
                        1,
                        1
                    )

            /** [habitDaySize]
             *
             * Load habit day:
             *
             * `today` `totalOfAPeriod` `correctly`
             */
            val habitDaySize = prefs.getString("habits-$x-habitDay-size", null)?.toIntOrNull() ?: 0
            habits[x].habitDay = MutableList(habitDaySize) { HabitDay() }
            for (y in 0 until habitDaySize) {
                habits[x].habitDay[y].today =
                    prefs.getString("habits-$x-habitDay-$y-today", "0.0")?.toDoubleOrNull() ?: 0.0
                habits[x].habitDay[y].totalOfAPeriod =
                    prefs.getString("habits-$x-habitDay-$y-totalOfAPeriod", "0.0")?.toDoubleOrNull()
                        ?: 0.0
                habits[x].habitDay[y].correctly =
                    prefs.getString("habits-$x-habitDay-$y-correctly", "false") == "true"
            }
        }

        if (oldAppVersion >= 4000000) {

            /**
             * `soul_color_type` `soul_color` `soul_name`
             */
            soul_color_type = enumValueOf<TypeOfColorHabits>(prefs.getString("soul_color_type", "ADAPTIVE").toString())
            soul_color = Color(prefs.getString("soul_color", "ff000000").toString().toULong(16))
            soul_name = prefs.getString("soul_name", "Mr. Soul Forest").toString()
        }
    }
}