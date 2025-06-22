package fireforestsoul.levelupsoul

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