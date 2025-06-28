package fireforestsoul.levelupsoul

import androidx.compose.ui.graphics.Color
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.math.max

class Habit(
    var nameOfHabit: String = "New habit",
    var nameOfUnitsOfDimension: String = "km",
    var typeOfGoalHabits: TypeOfGoalHabits = TypeOfGoalHabits.AT_LEAST,
    var needGoal: Double = 1.0,
    var needDays: Int = 1,
    var typeOfColorHabits: TypeOfColorHabits = TypeOfColorHabits.ADAPTIVE,
    var colorGood: Color = textSeeUiColor
) {

    var startDate: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    var lastDate: LocalDate = startDate
    var habitDay: MutableList<HabitDay> = MutableList(1) { HabitDay(0.0) }

    fun updateDate() {
        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        val addDays: Int = (today.toEpochDays() - lastDate.toEpochDays())

        if (addDays > 0) {
            habitDay.addAll(List(addDays) { HabitDay(0.0) })
            lastDate = today
        }

        update()
    }

    fun updateHabitDay(index: Int) {
        habitDay[index].totalOfAPeriod = 0.0
        for (i in (index - needDays + 1)..index) {
            if (i >= 0)
                habitDay[index].totalOfAPeriod += habitDay[i].today
        }
        when (typeOfGoalHabits) {
            TypeOfGoalHabits.NO_MORE -> habitDay[index].correctly = (habitDay[index].totalOfAPeriod <= needGoal)
            TypeOfGoalHabits.AT_LEAST -> habitDay[index].correctly = (habitDay[index].totalOfAPeriod >= needGoal)
        }
    }

    fun update() {
        for (i in 0..(habitDay.size - 1)) {
            updateHabitDay(i)
        }

        habits.sortByDescending { if (habitSeria(it).isNotEmpty()) habitSeria(it)[0] else 0 }
        habits.sortByDescending { progress(it) }
    }

}