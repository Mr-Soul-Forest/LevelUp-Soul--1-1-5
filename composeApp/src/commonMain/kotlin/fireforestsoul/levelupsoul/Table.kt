package fireforestsoul.levelupsoul

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlin.math.max
import kotlin.math.min

expect fun export()
expect fun import(onImported: () -> Unit)

@Composable
fun TableContent(viewModel: AppViewModel, verticalScroll: ScrollState, horizontalScroll: ScrollState, countdownDate: LocalDate) {
    val firstCellSizeX = 200.dp
    val firstCellSizeY = 40.dp
    val nextCellSizeX = 45.dp
    val nextCellSizeY = firstCellSizeY
    val spacedCell = 3.dp
    val sizeOfBorder = 1.dp
    val roundedBorder = 7.5.dp
    val firstSellFontSize = 16.sp
    val firstSellSmallFontSize = 9.sp
    val dataSellFontSize = 11.sp
    var maxDays = 0
    for (habit in habits) {
        maxDays = max(habit.habitDay.size, maxDays)
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(UI_dark_color)
    ) {
        val maxCellX = ((maxWidth - firstCellSizeX) / (nextCellSizeX + spacedCell)).toInt()

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(spacedCell),
            modifier = Modifier.verticalScroll(verticalScroll)
        ) {
            //first column
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(spacedCell),
                modifier = Modifier.width(firstCellSizeX)
            ) {
                Box(
                    modifier = Modifier.size(firstCellSizeX, firstCellSizeY),
                    contentAlignment = Alignment.Center
                ) {}
                for (y in 0 until habits.size) {
                    val seeColor = seeColorByIndex(y)
                    val noSeeColor = noSeeColorByIndex(y)

                    Box(
                        modifier = Modifier
                            .size(firstCellSizeX, firstCellSizeY)
                            .border(
                                sizeOfBorder,
                                color = noSeeColor,
                                shape = RoundedCornerShape(roundedBorder)
                            )
                            .clickable {
                                habit_statistics_and_edit_x = y
                                viewModel.setStatus(AppStatus.HABIT_STATISTICS)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            DonutChart(
                                values = listOf(progress(y), 1f - progress(y)),
                                colors = listOf(seeColor, noSeeColor),
                                modifier = Modifier
                                    .size(30.5.dp, 22.5.dp)
                                    .padding(start = 8.dp),
                                strokeWidth = 3.5.dp
                            )
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = habits[y].nameOfHabit,
                                    color = seeColor,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = firstSellFontSize
                                )
                                val needOrCanMore =
                                    habits[y].needGoal - habits[y].habitDay[habits[y].habitDay.size - 1].totalOfAPeriod
                                Text(
                                    text = if (habits[y].typeOfGoalHabits == TypeOfGoalHabits.AT_LEAST)
                                        "Need $needOrCanMore more"
                                    else "You can have $needOrCanMore more",
                                    color = noSeeColor,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = firstSellSmallFontSize,
                                )
                            }
                        }
                    }
                }
            }

            //main table body
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .horizontalScroll(horizontalScroll)
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(spacedCell),
                ) {
                    //dates
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(spacedCell),
                        modifier = Modifier.height(nextCellSizeY)
                    ) {
                        for (x in 0 until max(maxCellX, 10)) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.size(nextCellSizeX, nextCellSizeY)
                            ) {
                                Text(
                                    text = (countdownDate.minus(
                                        x,
                                        DateTimeUnit.DAY
                                    )).dayOfMonth.toString(),
                                    color = textNoSeeColor,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = dataSellFontSize
                                )
                                Text(
                                    text = (countdownDate.minus(
                                        x,
                                        DateTimeUnit.DAY
                                    )).dayOfWeek.toString().take(3),
                                    color = textNoSeeColor,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = dataSellFontSize
                                )
                            }
                        }
                    }
                    //results
                    for (y in 0 until habits.size) {
                        val seeColor = seeColorByIndex(y)
                        val noSeeColor = noSeeColorByIndex(y)

                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier
                                .height(nextCellSizeY)
                                .border(
                                    sizeOfBorder,
                                    color = noSeeColor,
                                    shape = RoundedCornerShape(roundedBorder)
                                )
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(spacedCell),
                            ) {
                                for (x in 0 until min(
                                    maxDays + countdownDate.toEpochDays() - Clock.System.now()
                                        .toLocalDateTime(TimeZone.currentSystemDefault()).date.toEpochDays(),
                                    max(maxCellX, 10)
                                )) {
                                    val xIndex =
                                        habits[y].habitDay.size - 1 - x + (countdownDate.toEpochDays() - Clock.System.now()
                                            .toLocalDateTime(TimeZone.currentSystemDefault()).date.toEpochDays())
                                    if (xIndex >= 0) {
                                        Box(
                                            modifier = Modifier
                                                .width(nextCellSizeX)
                                                .height(nextCellSizeY * 7 / 16),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            if (xIndex < habits[y].habitDay.size) {
                                                var inputText by remember { mutableStateOf("") }
                                                var showDialog by remember { mutableStateOf(false) }

                                                Text(
                                                    text = habits[y].habitDay[xIndex].today.toString(),
                                                    color = if (habits[y].habitDay[xIndex].correctly
                                                    ) seeColor else noSeeColor,
                                                    fontWeight = FontWeight.Normal,
                                                    fontSize = firstSellFontSize,
                                                    modifier = Modifier.clickable {
                                                        showDialog = true
                                                    }
                                                )

                                                if (showDialog) {
                                                    AlertDialog(
                                                        containerColor = UI_color,
                                                        onDismissRequest = { showDialog = false },
                                                        title = {
                                                            val dateToSet = habits[y].startDate.plus(
                                                                xIndex, DateTimeUnit.DAY
                                                            )
                                                            Text(
                                                                text = "Do you want to set a value for ${dateToSet.month} ${dateToSet.dayOfMonth}, ${dateToSet.year} for habit ${habits[y].nameOfHabit}?",
                                                                fontWeight = FontWeight.Normal,
                                                                fontSize = 16.sp,
                                                                color = textSeeUiColor
                                                            )
                                                        },
                                                        text = {
                                                            OutlinedTextField(
                                                                value = inputText,
                                                                onValueChange = { inputText = it },
                                                                label = {
                                                                    Text(
                                                                        "Old: ${habits[y].habitDay[xIndex].today}",
                                                                        fontSize = 12.sp,
                                                                        fontWeight = FontWeight.Normal,
                                                                        color = textNoSeeColor
                                                                    )
                                                                },
                                                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                                                singleLine = true,
                                                                textStyle = TextStyle(
                                                                    fontSize = 16.sp,
                                                                    fontWeight = FontWeight.Normal,
                                                                    color = textSeeUiColor
                                                                ),
                                                                shape = RoundedCornerShape(15.dp),
                                                                colors = TextFieldDefaults.colors(
                                                                    focusedTextColor = textSeeUiColor,
                                                                    unfocusedTextColor = textNoSeeColor,
                                                                    disabledTextColor = textNoSeeColor,
                                                                    focusedContainerColor = UI_dark_color,
                                                                    unfocusedContainerColor = UI_dark_color,
                                                                    disabledContainerColor = UI_dark_color,
                                                                    cursorColor = textSeeUiColor,
                                                                    focusedIndicatorColor = Color.Transparent,
                                                                    unfocusedIndicatorColor = Color.Transparent,
                                                                    disabledIndicatorColor = Color.Transparent
                                                                )
                                                            )
                                                        },
                                                        dismissButton = {
                                                            Text(
                                                                text = "❌ Cancel",
                                                                fontWeight = FontWeight.Normal,
                                                                fontSize = 16.sp,
                                                                color = Color(200, 150, 150),
                                                                modifier = Modifier.clickable {
                                                                    showDialog = false
                                                                    viewModel.setStatus(AppStatus.TABLE_UPDATER)
                                                                }
                                                            )
                                                        },
                                                        confirmButton = {
                                                            Text(
                                                                text = "✅ Confirm",
                                                                fontWeight = FontWeight.Normal,
                                                                fontSize = 16.sp,
                                                                color = Color(150, 200, 150),
                                                                modifier = Modifier.clickable {
                                                                    val value = inputText.toDoubleOrNull()
                                                                    if (value != null) {
                                                                        habits[y].habitDay[xIndex].today = value
                                                                        habits[y].update()
                                                                    }
                                                                    showDialog = false
                                                                    viewModel.setStatus(AppStatus.TABLE_UPDATER)
                                                                }
                                                            )
                                                        }
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            Box(
                                modifier = Modifier.padding(spacedCell * 2)
                            ) {
                                Text(
                                    text = habits[y].nameOfUnitsOfDimension,
                                    color = noSeeColor,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = firstSellSmallFontSize
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}