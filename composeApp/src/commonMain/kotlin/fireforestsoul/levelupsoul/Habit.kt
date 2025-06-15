package fireforestsoul.levelupsoul

import kotlinx.datetime.LocalDate
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class Habit(
    var nameOfHabit: String = "New habit",
    var nameOfUnitsOfDimension: String = "km",
    var typeOfGoalHabits: TypeOfGoalHabits = TypeOfGoalHabits.NOT_LITTLE,
    var needGoal: Double = 1.0,
    var needDays: Int = 1
) {

    var startDate: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    var habitDay: MutableList<HabitDay> = MutableList(1) { HabitDay(0.0) }
    var lastDate: LocalDate = startDate

    fun updateDate() {
        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        val addDays: Int = (today.toEpochDays() - lastDate.toEpochDays())

        if (addDays > 0) {
            habitDay.addAll(List(addDays) { HabitDay(0.0) })
            lastDate = today
        }

        update()
    }

    fun setHabitDay(date: LocalDate, value: Double) {
        val index: Int = (date.toEpochDays() - startDate.toEpochDays())
        habitDay[index].today = value
    }

    fun updateHabitDay(index: Int) {
        habitDay[index].totalOfAPeriod = 0.0
        for (i in (index - needDays)..index) {
            if (i >= 0)
                habitDay[index].totalOfAPeriod += habitDay[i].today
        }
        when (typeOfGoalHabits) {
            TypeOfGoalHabits.NO_MORE -> habitDay[index].correctly = (habitDay[index].totalOfAPeriod <= needGoal)
            TypeOfGoalHabits.NOT_LITTLE -> habitDay[index].correctly = (habitDay[index].totalOfAPeriod >= needGoal)
            else -> habitDay[index].correctly = true
        }
    }

    fun update() {
        for (i in 0..(habitDay.size - 1)) {
            updateHabitDay(i)
        }
    }

}