package fireforestsoul.levelupsoul

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import kotlin.math.max
import kotlin.math.min

fun progress(
    index: Int,
    days: Int = habits[index].habitDay.size,
    startIndex: Int = habits[index].habitDay.size - 1
): Float {
    var correctly = 0f
    var correctlyDays = 0
    for (indexY in (startIndex - days + 1)..(startIndex)) {
        if (indexY in 0..(habits[index].habitDay.size - 1)) {
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
    days: Int = habit.habitDay.size,
    startIndex: Int = habit.habitDay.size - 1
): Float {
    var correctly = 0f
    var correctlyDays = 0
    for (indexY in (startIndex - days + 1)..(startIndex)) {
        if (indexY in 0..(habit.habitDay.size - 1)) {
            if (habit.habitDay[indexY].correctly)
                correctly++
            correctlyDays++
        }
    }
    return correctly / (if (correctlyDays != 0) correctlyDays else 1)
}

fun progressAll(
    maxDays: Int,
    days: Int = maxDays,
    startIndex: Int = maxDays - 1
): Float {
    var correctly = 0f
    for (x in 0 until habits.size) {
        correctly += progress(
            x,
            if (days >= maxDays) habits[x].habitDay.size else days,
            habits[x].habitDay.size - maxDays + startIndex
        )
    }
    return correctly / (if (habits.isNotEmpty()) habits.size else 1)
}

fun plusProgress(
    index: Int,
    period: Int,
    days: Int = habits[index].habitDay.size,
    startIndex: Int = habits[index].habitDay.size - 1
): Float {
    return progress(index, days, startIndex) -
            progress(
                index,
                days,
                startIndex - period
            )
}

fun plusProgressAll(
    maxDays: Int,
    period: Int,
    days: Int = maxDays,
    startIndex: Int = maxDays - 1
): Float {
    return progressAll(days, startIndex) -
            progressAll(
                days,
                startIndex - period
            )
}

fun listProgress(
    index: Int,
    period: Int,
    step: Int,
    days: Int = habits[index].habitDay.size
): List<Float> {
    var y = habits[index].habitDay.size - period
    val list = mutableListOf(progress(index, days, y))
    y += step
    while (y < habits[index].habitDay.size) {
        if (y >= 0)
            list.add(progress(index, days, y))
        y += step
    }
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
    index: Int,
    step: Int
): List<Float> {
    var add0 = 0f
    for (z in 0 until step) {
        if (z < habits[index].habitDay.size)
            add0 += habits[index].habitDay[z].today.toFloat()
    }
    val list = mutableListOf(add0)
    var y = step
    while (y < habits[index].habitDay.size) {
        var add = 0f
        for (z in y until (y + step)) {
            if (z < habits[index].habitDay.size)
                add += habits[index].habitDay[z].today.toFloat()
        }
        list.add(add)
        y += step
    }

    return list
}

fun listTodayAll(
    maxDays: Int,
    step: Int
): List<Float> {
    var add0 = 0f
    for (z in 0 until step) {
        for (index in 0 until habits.size) {
            if (z < habits[index].habitDay.size)
                add0 += if (habits[index].habitDay[z].correctly) 1 else 0
        }
    }
    val list = mutableListOf(add0)
    var y = step
    while (y < maxDays) {
        var add = 0f
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
        oldestStartDate = LocalDate(0, 1, 1).plus(
            min(oldestStartDate.toEpochDays(), habit.startDate.toEpochDays()) - 2,
            DateTimeUnit.DAY
        )
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
