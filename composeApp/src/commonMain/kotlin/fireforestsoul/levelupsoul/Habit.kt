package fireforestsoul.levelupsoul

import androidx.compose.ui.graphics.Color
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class Habit(
    var nameOfHabit: String = ts_New_habit,
    var nameOfUnitsOfDimension: String = ts_km,
    var typeOfGoalHabits: TypeOfGoalHabits = TypeOfGoalHabits.AT_LEAST,
    var needGoal: Double = 1.0,
    var needDays: Int = 1,
    var typeOfColorHabits: TypeOfColorHabits = TypeOfColorHabits.ADAPTIVE,
    var colorGood: Color = textSeeUiColor,
    var changeLevel: Boolean = true,
    var changeNeedGoalWithLevel: Boolean = false,
    var changeNeedDaysWithLevel: Boolean = false
) {

    var startDate: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    var lastLevelChangeDate: LocalDate = startDate
    var level: Int = 0
    var habitDay: MutableList<HabitDay> = MutableList(1) { HabitDay(0.0) }

    fun updateDate() {
        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        val addDays: Int = (today.toEpochDays() - startDate.toEpochDays() - habitDay.size + 1)

        if (addDays > 0) {
            habitDay.addAll(List(addDays) { HabitDay(0.0) })
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
        habits.sortByDescending { it.level }
        habits.sortByDescending { progress(it) }

        if (changeLevel) {
            if (Clock.System.now()
                    .toLocalDateTime(TimeZone.currentSystemDefault()).date.toEpochDays() - lastLevelChangeDate.toEpochDays() >= 20
            ) {
                var goodProgress = 0
                if (progress(this) >= 0.8) {
                    for (x in (habitDay.size - 20) until habitDay.size) {
                        if (x >= 0) {
                            if (progress(this, startIndex = x) >= 0.8) {
                                goodProgress++
                            }
                        }
                    }
                    if (goodProgress == 20) {
                        level++
                        lastLevelChangeDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
                        if (changeNeedDaysWithLevel) {
                            if (typeOfGoalHabits == TypeOfGoalHabits.AT_LEAST) needDays =
                                if ((needDays * 0.8).toInt() > 0) (needDays * 0.8).toInt() else 1
                            else if (typeOfGoalHabits == TypeOfGoalHabits.NO_MORE) needDays = (needDays / 0.8).toInt()
                        }
                        if (changeNeedGoalWithLevel) {
                            if (typeOfGoalHabits == TypeOfGoalHabits.AT_LEAST) needGoal /= 0.8
                            else if (typeOfGoalHabits == TypeOfGoalHabits.NO_MORE) needGoal *= 0.8
                        }
                    }
                }
                else if (progress(this) <= 0.2) {
                    for (x in (habitDay.size - 20) until habitDay.size) {
                        if (x >= 0) {
                            if (progress(this, startIndex = x) <= 0.2) {
                                goodProgress++
                            }
                        }
                    }
                    if (goodProgress == 20) {
                        level--
                        lastLevelChangeDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
                        if (changeNeedDaysWithLevel) {
                            if (typeOfGoalHabits == TypeOfGoalHabits.AT_LEAST) needDays = (needDays / 0.8).toInt()
                            else if (typeOfGoalHabits == TypeOfGoalHabits.NO_MORE) needDays = if ((needDays * 0.8).toInt() > 0) (needDays * 0.8).toInt() else 1
                        }
                        if (changeNeedGoalWithLevel) {
                            if (typeOfGoalHabits == TypeOfGoalHabits.AT_LEAST) needGoal *= 0.8
                            else if (typeOfGoalHabits == TypeOfGoalHabits.NO_MORE) needGoal /= 0.8
                        }
                    }
                }
            }
        }
    }
}