package fireforestsoul.levelupsoul

import android.content.Context
import android.content.SharedPreferences
import kotlinx.datetime.LocalDate

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
            putString("habits-$x-startDate", habits[x].startDate.toString())
            putString("habits-$x-lastDate", habits[x].lastDate.toString())
            putString("habits-$x-habitDay-size", habits[x].habitDay.size.toString())
            for (y in habits[x].habitDay.indices) {
                putString("habits-$x-habitDay-$y-today", habits[x].habitDay[y].today.toString())
                putString("habits-$x-habitDay-$y-totalOfAPeriod", habits[x].habitDay[y].totalOfAPeriod.toString())
                putString("habits-$x-habitDay-$y-correctly", habits[x].habitDay[y].correctly.toString())
            }
        }
        apply()
    }
}

actual fun loadValue() {
    val oldAppVersion = prefs.getString("app_version", null)?.toLongOrNull() ?: return
    if (oldAppVersion > 0) {
        val habitsSize = prefs.getString("habits-size", null)?.toIntOrNull() ?: 0
        habits = MutableList(habitsSize) { Habit() }
        for (x in 0 until habitsSize) {
            habits[x].nameOfHabit = prefs.getString("habits-$x-nameOfHabit", "") ?: ""
            habits[x].nameOfUnitsOfDimension = prefs.getString("habits-$x-nameOfUnitsOfDimension", "") ?: ""
            habits[x].typeOfGoalHabits =
                enumValueOf<TypeOfGoalHabits>(prefs.getString("habits-$x-typeOfGoalHabits", "NOT_LITTLE")!!)
            habits[x].needGoal = prefs.getString("habits-$x-needGoal", "0.0")?.toDoubleOrNull() ?: 0.0
            habits[x].needDays = prefs.getString("habits-$x-needDays", "0")?.toIntOrNull() ?: 0
            habits[x].startDate =
                prefs.getString("habits-$x-startDate", "2025-01-01")?.let { LocalDate.parse(it) } ?: LocalDate(
                    2025,
                    1,
                    1
                )
            habits[x].lastDate =
                prefs.getString("habits-$x-lastDate", "2025-01-01")?.let { LocalDate.parse(it) } ?: LocalDate(
                    2025,
                    1,
                    1
                )

            val habitDaySize = prefs.getString("habits-$x-habitDay-size", null)?.toIntOrNull() ?: 0
            habits[x].habitDay = MutableList(habitDaySize) { HabitDay() }
            for (y in 0 until habitDaySize) {
                habits[x].habitDay[y].today =
                    prefs.getString("habits-$x-habitDay-$y-today", "0.0")?.toDoubleOrNull() ?: 0.0
                habits[x].habitDay[y].totalOfAPeriod =
                    prefs.getString("habits-$x-habitDay-$y-totalOfAPeriod", "0.0")?.toDoubleOrNull() ?: 0.0
                habits[x].habitDay[y].correctly =
                    prefs.getString("habits-$x-habitDay-$y-correctly", "false") == "true"
            }
        }
    }
}