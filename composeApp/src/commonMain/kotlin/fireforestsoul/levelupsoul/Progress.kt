package fireforestsoul.levelupsoul

import kotlinx.datetime.DatePeriod

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