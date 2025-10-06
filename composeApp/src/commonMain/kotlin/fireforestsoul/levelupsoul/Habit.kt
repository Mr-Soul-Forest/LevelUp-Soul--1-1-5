/**Copyright 2025 Forge-of-Ovorldule (https://github.com/Forge-of-Ovorldule) and Mr-Soul-Forest (https://github.com/Mr-Soul-Forest)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package fireforestsoul.levelupsoul

import androidx.compose.ui.graphics.Color
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.toBigDecimal

class Habit(
    var nameOfHabit: String = ts_New_habit,
    var nameOfUnitsOfDimension: String = ts_km,
    var typeOfGoalHabits: TypeOfGoalHabits = TypeOfGoalHabits.AT_LEAST,
    var needGoal: BigDecimal = 1.toBigDecimal(),
    var needDays: Int = 1,
    var typeOfColorHabits: TypeOfColorHabits = TypeOfColorHabits.ADAPTIVE,
    var colorGood: Color = UICT_see,
    var changeLevel: Boolean = true,
    var changeNeedGoalWithLevel: Boolean = false,
    var changeNeedDaysWithLevel: Boolean = false,
    var iconChar: String = "🆕"
) {

    var startDate: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    var lastLevelChangeDate: LocalDate = startDate
    var level: Int = 0
    var habitDay: MutableList<HabitDay> = MutableList(1) { HabitDay(0.toBigDecimal()) }
    var phantomNeedDays = needDays.toBigDecimal()

    fun updateDate(sort: Boolean = true) {
        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        val addDays: Int = (today.toEpochDays() - startDate.toEpochDays() - habitDay.size + 1)

        if (addDays > 0) {
            habitDay.addAll(List(addDays) { HabitDay(0.toBigDecimal()) })
        }

        update(sort)
    }

    fun updateHabitDay(index: Int) {
        habitDay[index].totalOfAPeriod = 0.toBigDecimal()
        for (i in (index - needDays + 1)..index) {
            if (i >= 0)
                habitDay[index].totalOfAPeriod += habitDay[i].today
        }
        when (typeOfGoalHabits) {
            TypeOfGoalHabits.NO_MORE -> habitDay[index].correctly = (habitDay[index].totalOfAPeriod <= needGoal)
            TypeOfGoalHabits.AT_LEAST -> habitDay[index].correctly = (habitDay[index].totalOfAPeriod >= needGoal)
        }
    }

    fun update(sort: Boolean = true) {
        for (i in 0..<habitDay.size) {
            updateHabitDay(i)
        }

        if (sort) {
            habits.sortByDescending { if (habitSeria(it).isNotEmpty()) habitSeria(it)[0] else 0 }
            habits.sortByDescending { it.level }
            habits.sortByDescending { progress(it) }
        }

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
                            when (typeOfGoalHabits) {
                                TypeOfGoalHabits.AT_LEAST -> phantomNeedDays *= "0.8".toBigDecimal()
                                TypeOfGoalHabits.NO_MORE -> phantomNeedDays /= "0.8".toBigDecimal()
                            }
                            needDays = if (phantomNeedDays % 1 != BigDecimal.ZERO) phantomNeedDays.intValue(false) + 1 else phantomNeedDays.intValue(false)
                        }
                        if (changeNeedGoalWithLevel) {
                            when (typeOfGoalHabits) {
                                TypeOfGoalHabits.AT_LEAST -> needGoal /= "0.8".toBigDecimal()
                                TypeOfGoalHabits.NO_MORE -> needGoal *= "0.8".toBigDecimal()
                            }
                        }
                    }
                } else if (progress(this) <= 0.2) {
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
                            when (typeOfGoalHabits) {
                                TypeOfGoalHabits.AT_LEAST -> phantomNeedDays /= 0.8
                                TypeOfGoalHabits.NO_MORE -> phantomNeedDays *= "0.8".toBigDecimal()
                            }
                            needDays = if (phantomNeedDays % 1 != BigDecimal.ZERO) phantomNeedDays.intValue(false) + 1 else phantomNeedDays.intValue(false)
                        }
                        if (changeNeedGoalWithLevel) {
                            when (typeOfGoalHabits) {
                                TypeOfGoalHabits.AT_LEAST -> needGoal *= "0.8".toBigDecimal()
                                TypeOfGoalHabits.NO_MORE -> needGoal /= "0.8".toBigDecimal()
                            }
                        }
                    }
                }
            }
        }
    }
}