/**Copyright 2025 Forge-of-Ovorldule (https://github.com/Forge-of-Ovorldule) and Mr-Soul-Forest (https://github.com/Mr-Soul-Forest)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package fireforestsoul.levelupsoul

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.toBigDecimal
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus

fun progress(
    index: Int,
    pps: Int = habits[index].habitDay.size,
    endIndex: Int = habits[index].habitDay.size - 1
): Float {
    var correctly = 0f
    var correctlyDays = 0
    for (indexY in (endIndex - pps + 1)..(endIndex)) {
        if (indexY in 0..<habits[index].habitDay.size) {
            if (habits[index].habitDay[indexY].correctly)
                correctly++
            correctlyDays++
        }
    }
    return correctly / (if (correctlyDays != 0) correctlyDays else 1)
}

fun progress(
    habit: Habit,
): Float {
    var correctly = 0f
    for (habitDay in habit.habitDay) {
        if (habitDay.correctly)
            correctly++
    }
    return correctly / (if (habit.habitDay.isNotEmpty()) habit.habitDay.size else 1)
}

fun progress(
    habit: Habit,
    pps: Int = habit.habitDay.size,
    startIndex: Int = habit.habitDay.size - 1
): Float {
    var correctly = 0f
    var correctlyDays = 0
    for (indexY in (startIndex - pps + 1)..(startIndex)) {
        if (indexY in 0..<habit.habitDay.size) {
            if (habit.habitDay[indexY].correctly)
                correctly++
            correctlyDays++
        }
    }
    return correctly / (if (correctlyDays != 0) correctlyDays else 1)
}

fun progressAll(
    maxDays: Int,
    pps: Int = maxDays,
    endIndex: Int = maxDays - 1
): Float {
    var correctly = 0f
    for (x in 0 until habits.size) {
        correctly += progress(
            x,
            if (pps >= maxDays) habits[x].habitDay.size else pps,
            habits[x].habitDay.size - maxDays + endIndex
        )
    }
    return correctly / (if (habits.isNotEmpty()) habits.size else 1)
}

fun plusProgress(
    index: Int,
    period: Int,
    pps: Int = habits[index].habitDay.size,
    startIndex: Int = habits[index].habitDay.size - 1
): Float {
    return progress(index, pps, startIndex) -
            progress(
                index,
                pps,
                startIndex - period
            )
}

fun plusProgressAll(
    maxDays: Int,
    period: Int,
    days: Int = maxDays,
    startIndex: Int = maxDays - 1
): Float {
    return progressAll(maxDays, days, startIndex) -
            progressAll(
                maxDays,
                days,
                startIndex - period
            )
}

fun listProgress(
    habitIndex: Int,
    period: Int,
    step: Int,
    pps: Int = habits[habitIndex].habitDay.size
): List<Float> {
    var index = habits[habitIndex].habitDay.size - 1
    val list = mutableListOf(progress(habitIndex, pps, index))
    index -= step

    while (index + step >= habits[habitIndex].habitDay.size - period) {
        if (index > 0)
            list.add(progress(habitIndex, pps, index))
        else {
            list.add(progress(habitIndex, pps, 0))
            break
        }
        index -= step
    }

    list.reverse()
    return list
}

fun listProgressAll(
    maxDays: Int,
    period: Int,
    step: Int,
    days: Int = maxDays
): List<Float> {
    var y = maxDays - period
    val list = mutableListOf(progressAll(maxDays, days, y))
    y += step
    while (y < maxDays) {
        if (y >= 0)
            list.add(progressAll(maxDays, days, y))
        y += step
    }
    return list
}

fun listToday(
    habitIndex: Int,
    step: Int
): List<BigDecimal> {
    var index = habits[habitIndex].habitDay.size - 1

    var sum0 = BigDecimal.ZERO
    for (i in (index - step + 1)..index) {
        sum0 += habits[habitIndex].habitDay[i].today
    }
    index -= step

    val list = mutableListOf(sum0)

    while (index - step >= 0) {
        var sum = BigDecimal.ZERO
        for (i in (index - step + 1)..index) {
            sum += habits[habitIndex].habitDay[i].today
        }
        list.add(sum)
        index -= step
    }

    list.reverse()
    return list
}

fun listTodayAll(
    maxDays: Int,
    step: Int
): List<BigDecimal> {
    var add0 = 0.toBigDecimal()
    for (z in 0 until step) {
        for (index in 0 until habits.size) {
            if (z < habits[index].habitDay.size)
                add0 += if (habits[index].habitDay[z].correctly) 1 else 0
        }
    }
    val list = mutableListOf(add0)
    var y = step
    while (y < maxDays) {
        var add = 0.toBigDecimal()
        for (z in y until (y + step)) {
            for (index in 0 until habits.size) {
                val z0 = habits[index].habitDay.size - maxDays + z
                if (z0 < habits[index].habitDay.size && z0 >= 0)
                    add += if (habits[index].habitDay[z0].correctly) 1 else 0
            }
        }
        list.add(add)
        y += step
    }

    return list
}

fun listTodayDates(
    index: Int,
    step: Int
): List<String> {
    fun formatter(localDate: LocalDate): String {
        return if (step < 7) localDate.dayOfWeek.toString().take(3)
        else if (step < 30) localDate.dayOfMonth.toString()
        else if (step < 365) localDate.month.toString().take(3)
        else localDate.year.toString()
    }

    val list = mutableListOf(formatter(habits[index].startDate))
    var y = step
    while (y < habits[index].habitDay.size) {
        list.add(formatter(habits[index].startDate.plus(y, DateTimeUnit.DAY)))
        y += step
    }
    return list
}

fun listTodayDatesAll(
    maxDays: Int,
    step: Int
): List<String> {
    fun formatter(localDate: LocalDate): String {
        return if (step < 7) localDate.dayOfWeek.toString().take(3)
        else if (step < 30) localDate.dayOfMonth.toString()
        else if (step < 365) localDate.month.toString().take(3)
        else localDate.year.toString()
    }

    var oldestStartDate = LocalDate(9999, 1, 1)
    for (habit in habits) {
        oldestStartDate = if (oldestStartDate < habit.startDate) oldestStartDate else habit.startDate
    }

    val list = mutableListOf(formatter(oldestStartDate))
    var y = step
    while (y < maxDays) {
        list.add(formatter(oldestStartDate.plus(y, DateTimeUnit.DAY)))
        y += step
    }
    return list
}

fun listDaysNumbers(
    index: Int
): List<Int> {
    val list = mutableListOf(habits[index].startDate.dayOfMonth)
    for (y in 1 until habits[index].habitDay.size) {
        list.add(habits[index].startDate.plus(y, DateTimeUnit.DAY).dayOfMonth)
    }
    return list
}

fun listDaysNumbers(
    habit: Habit
): List<Int> {
    val list = mutableListOf(habit.startDate.dayOfMonth)
    for (y in 1 until habit.habitDay.size) {
        list.add(habit.startDate.plus(y, DateTimeUnit.DAY).dayOfMonth)
    }
    return list
}

fun listDaysBoolean(
    index: Int
): List<Boolean> {
    val list = mutableListOf(habits[index].habitDay[0].correctly)
    for (y in 1 until habits[index].habitDay.size) {
        list.add(habits[index].habitDay[y].correctly)
    }
    return list
}

fun habitSeria(
    index: Int
): List<Int> {
    val list = mutableListOf(0)
    var add = 0
    for (habitDay in habits[index].habitDay) {
        if (habitDay.correctly) add++
        else {
            list.add(add)
            add = 0
        }
    }
    list.add(add)

    list.removeAll { it == 0 }
    list.sortDescending()
    return list
}

fun habitSeria(
    habit: Habit
): List<Int> {
    val list = mutableListOf(0)
    var add = 0
    for (habitDay in habit.habitDay) {
        if (habitDay.correctly) add++
        else {
            list.add(add)
            add = 0
        }
    }
    list.add(add)

    list.removeAll { it == 0 }
    list.sortDescending()
    return list
}
