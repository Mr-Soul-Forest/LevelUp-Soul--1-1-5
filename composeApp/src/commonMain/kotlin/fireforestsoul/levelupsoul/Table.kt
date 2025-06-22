package fireforestsoul.levelupsoul

import androidx.compose.foundation.Image
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontFamily
import org.jetbrains.compose.resources.painterResource
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.Dp
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime
import kotlin.math.max
import kotlin.math.min

var UI_color = Color(40, 40, 40, 255)
val textNoSeeColor = Color(100, 100, 100, 255)
val textSeeUiColor = Color(200, 200, 200, 255)
val UI_dark_color = Color(25, 25, 25)

expect fun export()
expect fun import(onImported: () -> Unit)

@Composable
fun TableContent(viewModel: AppViewModel, blur: Dp = 0.dp) {
    val appStatus by viewModel.appStatus.collectAsState()
    var countdownDate by remember {
        mutableStateOf(
            Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        )
    }

    Box(
        modifier = Modifier
            .background(UI_color)
            .fillMaxSize()
    )
    Scaffold(
        modifier = Modifier
            .padding(WindowInsets.systemBars.asPaddingValues())
            .blur(blur),
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(UI_color)
            ) {
                Row(
                    modifier = Modifier
                        .height(48.dp)
                        .fillMaxWidth()
                        .padding(10.dp, 0.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Привычки",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Default,
                        color = textSeeUiColor,
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    if ("Android" !in getPlatform().name) {
                        IconButton(onClick = {
                            if (appStatus == AppStatus.TABLE) {
                                saveValue()
                                export()
                            }
                        }) {
                            Image(
                                painter = painterResource(Res.drawable.export),
                                contentDescription = "Export habits",
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .size(28.dp),
                            )
                        }
                        IconButton(onClick = {
                            if (appStatus == AppStatus.TABLE) {
                                saveValue()
                                import {
                                    countFilesLoad = 0
                                    viewModel.setStatus(AppStatus.LOADING)
                                }
                            }
                        }) {
                            Image(
                                painter = painterResource(Res.drawable.import_icon),
                                contentDescription = "Import habits",
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .size(28.dp),
                            )
                        }
                    }
                    IconButton(onClick = {
                        if (appStatus == AppStatus.TABLE) {
                            viewModel.setStatus(AppStatus.CREATE_HABIT)
                        }
                    }) {
                        Image(
                            painter = painterResource(Res.drawable.add_habit),
                            contentDescription = "Create habits",
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .size(28.dp),
                        )
                    }
                    DatePickerDialog(countdownDate, viewModel) {
                        countdownDate = it
                    }
                }
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(UI_color)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = {
                        if (appStatus == AppStatus.TABLE) {
                            println("Habits")
                        }
                    }) {
                        Image(
                            painter = painterResource(Res.drawable.habits),
                            contentDescription = "Habits",
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .size(28.dp),
                        )
                    }
                    IconButton(onClick = {
                        if (appStatus == AppStatus.TABLE) {
                            println("Groups")
                        }
                    }) {
                        Image(
                            painter = painterResource(Res.drawable.groups_of_habits),
                            contentDescription = "Groups of habits",
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .size(28.dp),
                        )
                    }
                    IconButton(onClick = {
                        if (appStatus == AppStatus.TABLE) {
                            println("Soul")
                        }
                    }) {
                        Image(
                            painter = painterResource(Res.drawable.soul_stat),
                            contentDescription = "Soul Stats",
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .size(28.dp),
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        //main content
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
        val verticalScroll = rememberScrollState()
        val horizontalScroll = rememberScrollState()
        var maxDays = 0
        for (habit in habits) {
            maxDays = max(habit.habitDay.size, maxDays)
        }
        var maxSeria = 0
        for (x in 0 until habits.size) {
            maxSeria = max(
                if (habitSeria(x).isNotEmpty()) habitSeria(x)[0] else 0,
                maxSeria
            )
        }

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
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
                        val seeColor = seeColorByIndex(y, maxDays, maxSeria)
                        val noSeeColor = noSeeColorByIndex(y, maxDays, maxSeria)

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
                            Row (
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
                            val seeColor = seeColorByIndex(y, maxDays, maxSeria)
                            val noSeeColor = noSeeColorByIndex(y, maxDays, maxSeria)

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
                                        maxDays + countdownDate.toEpochDays() - Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.toEpochDays(),
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
                                                    Text(
                                                        text = habits[y].habitDay[xIndex].today.toString(),
                                                        color = if (habits[y].habitDay[xIndex].correctly
                                                        ) seeColor else noSeeColor,
                                                        fontWeight = FontWeight.Normal,
                                                        fontSize = firstSellFontSize,
                                                        modifier = Modifier.clickable {
                                                            if (appStatus == AppStatus.TABLE) {
                                                                set_habit_day_today_x = xIndex
                                                                set_habit_day_today_y = y
                                                                viewModel.setStatus(AppStatus.SET_HABIT_DAY_TODAY)
                                                            }
                                                        }
                                                    )
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
}