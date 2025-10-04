/**Copyright 2025 Forge-of-Ovorldule (https://github.com/Forge-of-Ovorldule) and Mr-Soul-Forest (https://github.com/Mr-Soul-Forest)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package fireforestsoul.levelupsoul

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource

var habit_statistics_and_edit_x = 0
private var pps_for_habit_statistic = 0

@Composable
fun HabitStatistics(viewModel: AppViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(UIC_dark, UIC_black)))
    ) {

        var habitStatisticsStatus by remember { mutableStateOf(HabitStatisticsStatus.GOAL) }
        var progressPeriodSetting by remember { mutableStateOf(habits[habit_statistics_and_edit_x].habitDay.size) }
        pps_for_habit_statistic = progressPeriodSetting

        Scaffold(
            modifier = Modifier
                .padding(WindowInsets.systemBars.asPaddingValues())
                .background(Brush.verticalGradient(listOf(UIC_dark, UIC_black))),
            topBar = {
                var maxHeightBox by remember { mutableStateOf(0.dp) }

                BoxWithConstraints(
                    modifier = Modifier.fillMaxWidth()
                        .layout { measurable, constraints ->
                            val placeable = measurable.measure(constraints)
                            maxHeightBox = placeable.height.toDp()
                            layout(placeable.width, placeable.height) {
                                placeable.place(0, 0)
                            }
                        }
                ) {
                    val maxWidthBox = maxWidth

                    IconButton(
                        { viewModel.setStatus(backStatus) },
                        modifier = Modifier.padding(14.dp, 12.dp)
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.back),
                            contentDescription = ts_Go_back,
                            colorFilter = ColorFilter.tint(UIC),
                            modifier = Modifier.size(30.8.dp)
                                .clip(RoundedCornerShape(15.4.dp))
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .padding(top = 52.dp, bottom = 10.8.dp),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "«${habits[habit_statistics_and_edit_x].nameOfHabit}»",
                            color = UICT_see,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = JetBrainsFont(),
                            fontSize = 34.sp
                        )
                        Text(
                            text = ts_statistic,
                            color = UICT_no_see,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontFamily = JetBrainsFont(),
                            fontSize = 14.4.sp
                        )
                        Spacer(modifier = Modifier.height(57.6.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .padding(horizontal = 66.4.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Box(
                                modifier = Modifier.size(maxWidthBox * 0.33f, 40.dp)
                                    .background(
                                        seeColorByIndex(habit_statistics_and_edit_x),
                                        RoundedCornerShape(27.2.dp)
                                    )
                                    .border(
                                        0.8.dp,
                                        seeColorByIndex(habit_statistics_and_edit_x),
                                        RoundedCornerShape(27.2.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = ts_Statistic,
                                    color = checkBackgroundBright(
                                        seeColorByIndex(habit_statistics_and_edit_x),
                                        UICT_see
                                    ),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    fontFamily = JetBrainsFont(),
                                    fontSize = 19.2.sp
                                )
                            }
                            Box(
                                modifier = Modifier.size(maxWidthBox * 0.33f, 40.dp)
                                    .background(Color.Transparent)
                                    .border(
                                        0.8.dp,
                                        seeColorByIndex(habit_statistics_and_edit_x),
                                        RoundedCornerShape(27.2.dp)
                                    )
                                    .clickable { viewModel.setStatus(AppStatus.EDIT_HABIT) },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = ts_Edit,
                                    color = checkBackgroundBright(
                                        seeColorByIndex(habit_statistics_and_edit_x),
                                        UICT_see
                                    ),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    fontFamily = JetBrainsFont(),
                                    fontSize = 19.2.sp
                                )
                            }
                        }
                    }

                    Box(
                        modifier = Modifier.padding(
                            start = maxWidthBox / 1080 * 801.79f,
                            top = maxHeightBox / 536 * 54.07f
                        )
                    ) {
                        Text(
                            text = habits[habit_statistics_and_edit_x].iconChar,
                            color = seeColorByIndex(habit_statistics_and_edit_x),
                            fontSize = 60.sp,
                            fontWeight = FontWeight.Black,
                            modifier = Modifier.rotate(-28.79f),
                            fontFamily = JetBrainsFont()
                        )
                    }
                    Box(
                        modifier = Modifier.padding(
                            start = maxWidthBox / 1080 * 51.32f,
                            top = maxHeightBox / 536 * 181.29f
                        )
                    ) {
                        Text(
                            text = habits[habit_statistics_and_edit_x].iconChar,
                            color = seeColorByIndex(habit_statistics_and_edit_x),
                            fontSize = 51.2.sp,
                            fontWeight = FontWeight.Black,
                            modifier = Modifier.rotate(33.94f),
                            fontFamily = JetBrainsFont()
                        )
                    }
                    Box(
                        modifier = Modifier.padding(
                            start = maxWidthBox / 1080 * 677.25f,
                            top = maxHeightBox / 536 * 288.99f
                        )
                    ) {
                        Text(
                            text = habits[habit_statistics_and_edit_x].iconChar,
                            color = seeColorByIndex(habit_statistics_and_edit_x),
                            fontSize = 34.sp,
                            fontWeight = FontWeight.Black,
                            modifier = Modifier.rotate(-17.23f),
                            fontFamily = JetBrainsFont()
                        )
                    }
                }
            },
            bottomBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(UIC_dark)
                        .padding(horizontal = 15.56.dp)
                        .padding(bottom = 13.78.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .background(UIC_light, RoundedCornerShape(20.89.dp))
                            .padding(horizontal = 20.89.dp)
                            .height(48.44.dp)
                            .horizontalScroll(rememberScrollState()),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        HabitStatisticsStatusIcon(
                            habitStatisticsStatus = HabitStatisticsStatus.GOAL,
                            statusNow = habitStatisticsStatus,
                            icon = painterResource(Res.drawable.habit_statistic__goal),
                            contentDescription = ts_Goal
                        ) { habitStatisticsStatus = HabitStatisticsStatus.GOAL }
                        HabitStatisticsStatusIcon(
                            habitStatisticsStatus = HabitStatisticsStatus.PROGRESS,
                            statusNow = habitStatisticsStatus,
                            icon = painterResource(Res.drawable.habit_statistic__progress),
                            contentDescription = ts_Progress
                        ) { habitStatisticsStatus = HabitStatisticsStatus.PROGRESS }
                        HabitStatisticsStatusIcon(
                            habitStatisticsStatus = HabitStatisticsStatus.LEVEL,
                            statusNow = habitStatisticsStatus,
                            icon = painterResource(Res.drawable.habit_statistic__level),
                            contentDescription = ts_Level
                        ) { habitStatisticsStatus = HabitStatisticsStatus.LEVEL }
                        HabitStatisticsStatusIcon(
                            habitStatisticsStatus = HabitStatisticsStatus.PROGRESS_GRAPH,
                            statusNow = habitStatisticsStatus,
                            icon = painterResource(Res.drawable.habit_statistic__progress_graph),
                            contentDescription = ts_Progress_graph
                        ) { habitStatisticsStatus = HabitStatisticsStatus.PROGRESS_GRAPH }
                        HabitStatisticsStatusIcon(
                            habitStatisticsStatus = HabitStatisticsStatus.BAR_CHART,
                            statusNow = habitStatisticsStatus,
                            icon = painterResource(Res.drawable.habit_statistic__bar_chart),
                            contentDescription = ts_Bar_chart
                        ) { habitStatisticsStatus = HabitStatisticsStatus.BAR_CHART }
                        HabitStatisticsStatusIcon(
                            habitStatisticsStatus = HabitStatisticsStatus.CALENDAR,
                            statusNow = habitStatisticsStatus,
                            icon = painterResource(Res.drawable.habit_statistic__calendar),
                            contentDescription = ts_Calendar
                        ) { habitStatisticsStatus = HabitStatisticsStatus.CALENDAR }
                        HabitStatisticsStatusIcon(
                            habitStatisticsStatus = HabitStatisticsStatus.STREAKS,
                            statusNow = habitStatisticsStatus,
                            icon = painterResource(Res.drawable.habit_statistic__streaks),
                            contentDescription = ts_Streaks
                        ) { habitStatisticsStatus = HabitStatisticsStatus.STREAKS }
                        HabitStatisticsStatusIcon(
                            habitStatisticsStatus = HabitStatisticsStatus.DISTRIBUTION_BY_DAY_OF_THE_WEEK,
                            statusNow = habitStatisticsStatus,
                            icon = painterResource(Res.drawable.habit_statistic__distribution_by_day_of_the_week),
                            contentDescription = ts_Distribution_by_day_of_the_week
                        ) { habitStatisticsStatus = HabitStatisticsStatus.DISTRIBUTION_BY_DAY_OF_THE_WEEK }
                    }
                }
            }
        ) { paddingValues ->

            Box(
                modifier = Modifier.fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                UIC_black,
                                noSeeColorByIndex(habit_statistics_and_edit_x)
                            )
                        )
                    )
                    .height(paddingValues.calculateTopPadding() + 66.4.dp),
            )

            val verticalScroll = rememberScrollState()

            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(UIC_dark, RoundedCornerShape(topStart = 66.4.dp, topEnd = 66.4.dp))
                    .verticalScroll(verticalScroll)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth().height(66.4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = when (habitStatisticsStatus) {
                                HabitStatisticsStatus.GOAL -> ts_Goal
                                HabitStatisticsStatus.LEVEL -> ts_Level
                                HabitStatisticsStatus.STREAKS -> ts_Streaks
                                HabitStatisticsStatus.CALENDAR -> ts_Calendar
                                HabitStatisticsStatus.PROGRESS -> ts_Progress
                                HabitStatisticsStatus.BAR_CHART -> ts_Results
                                HabitStatisticsStatus.PROGRESS_GRAPH -> ts_Progress_graph
                                HabitStatisticsStatus.DISTRIBUTION_BY_DAY_OF_THE_WEEK -> ts_Distribution_by_day_of_the_week
                            },
                            fontFamily = JetBrainsFont(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.Medium,
                            fontSize = 19.2.sp,
                            color = UICT_see
                        )
                    }
                    when (habitStatisticsStatus) {
                        HabitStatisticsStatus.GOAL -> {
                            GoalContent(progressPeriodSetting)
                            LaunchedEffect(Unit) {
                                while (true) {
                                    progressPeriodSetting = pps_for_habit_statistic
                                    delay(50)
                                }
                            }
                        }

                        HabitStatisticsStatus.PROGRESS -> ProgressContent(progressPeriodSetting)

                        else -> {}
                    }
                }
            }
        }
    }
}

private enum class HabitStatisticsStatus {
    GOAL,
    PROGRESS,
    LEVEL,
    PROGRESS_GRAPH,
    BAR_CHART,
    CALENDAR,
    STREAKS,
    DISTRIBUTION_BY_DAY_OF_THE_WEEK
}

@Composable
private fun HabitStatisticsStatusIcon(
    habitStatisticsStatus: HabitStatisticsStatus = HabitStatisticsStatus.GOAL,
    statusNow: HabitStatisticsStatus = HabitStatisticsStatus.GOAL,
    icon: Painter,
    contentDescription: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.size(35.56.dp)
            .clickable(onClick = onClick)
            .background(
                if (habitStatisticsStatus == statusNow) seeColorByIndex(habit_statistics_and_edit_x)
                else UIC_light_x2,
                RoundedCornerShape(8.89.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(26.67.dp),
            colorFilter = if (habitStatisticsStatus != statusNow) ColorFilter.tint(UIC_light_x05, BlendMode.Modulate)
            else ColorFilter.tint(
                checkBackgroundBright(
                    seeColorByIndex(habit_statistics_and_edit_x),
                    reversColor(UIC_light_x05),
                    UIC_light_x05
                ), BlendMode.Modulate
            )
        )
    }
}

@Composable
private fun GoalContent(
    pps: Int
) {
    @Composable
    fun PPSInfoDialog(smallText: String) {
        var showDialog by remember { mutableStateOf(false) }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { showDialog = true }
        )
        {
            Image(
                painter = painterResource(Res.drawable.info),
                contentDescription = ts_Info,
                colorFilter = ColorFilter.tint(UICT_no_see),
                modifier = Modifier.size(12.8.dp)
            )
            Text(
                text = smallText,
                fontFamily = JetBrainsFont(),
                fontWeight = FontWeight.ExtraLight,
                fontSize = 12.8.sp,
                color = UICT_no_see,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        if (showDialog) {
            AlertDialog(
                containerColor = UIC_dark,
                onDismissRequest = { showDialog = false },
                title = {
                    Text(
                        "$ts_PPS $ts_Info",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = UICT_see
                    )
                },
                text = {
                    Text(
                        ts_PPS_means_Progress_Period_Settings_By_default_progress_is_the_,
                        fontSize = 16.sp,
                        color = UICT_see
                    )
                },
                confirmButton = {
                    Button(
                        onClick = { showDialog = false },
                        colors = ButtonColors(
                            containerColor = UIC,
                            contentColor = UICT_see,
                            disabledContainerColor = Color.Transparent,
                            disabledContentColor = Color.Transparent
                        )
                    ) {
                        Text(
                            text = ts_Close,
                            fontSize = 16.sp,
                            color = Color(150, 150, 200),
                        )
                    }
                }
            )
        }
    }

    @Composable
    fun GoalParamItem(
        res: Painter,
        contentDescription: String,
        text: String,
        smallText: String,
        isPPS: Boolean = false
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(22.67.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.size(44.44.dp)
                    .background(UIC_extra_light, RoundedCornerShape(22.22.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = res,
                    contentDescription = contentDescription,
                    modifier = Modifier.size(35.56.dp),
                    colorFilter = ColorFilter.tint(UIC_light, BlendMode.Modulate)
                )
            }
            Column {
                Text(
                    text = text,
                    fontFamily = JetBrainsFont(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 19.2.sp,
                    color = UICT_see,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (isPPS) PPSInfoDialog(smallText)
                else
                    Text(
                        text = smallText,
                        fontFamily = JetBrainsFont(),
                        fontWeight = FontWeight.ExtraLight,
                        fontSize = 12.8.sp,
                        color = UICT_no_see,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
            }
        }
    }

    @Composable
    fun PPSSetVector(
        pps: Int
    ) {
        var pssForHabitStatistic by remember { mutableStateOf(pps.toString()) }

        Box(
            modifier = Modifier.fillMaxWidth().height(50.22.dp)
                .background(UIC_x2green, RoundedCornerShape(88.89.dp))
                .clip(RoundedCornerShape(88.89.dp))
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(pps.toFloat() / habits[habit_statistics_and_edit_x].habitDay.size.toFloat())
                    .height(50.22.dp)
                    .background(
                        Brush.horizontalGradient(listOf(UIC_x2green, UIC_x2green_x1o5white)),
                        RoundedCornerShape(88.89.dp)
                    )
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "$ts_PPS (0 $ts_for_full_time):",
                    fontFamily = JetBrainsFont(),
                    fontWeight = FontWeight.Thin,
                    fontSize = 12.8.sp,
                    color = UICT_no_see,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.size(4.dp))
                BasicTextField(
                    value = pssForHabitStatistic,
                    onValueChange = {
                        pssForHabitStatistic = it
                        pps_for_habit_statistic = it.toIntOrNull() ?: habits[habit_statistics_and_edit_x].habitDay.size
                        if (pps_for_habit_statistic == 0) pps_for_habit_statistic =
                            habits[habit_statistics_and_edit_x].habitDay.size
                    },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(
                        fontFamily = JetBrainsFont(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = UIC_green
                    ),
                    singleLine = true,
                    decorationBox = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                verticalAlignment = Alignment.Bottom
                            ) {
                                it()
                                Text(
                                    text = "/" + habits[habit_statistics_and_edit_x].habitDay.size.toString(),
                                    fontFamily = JetBrainsFont(),
                                    fontWeight = FontWeight.Thin,
                                    fontSize = 9.4.sp,
                                    color = UICT_no_see,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = " $ts_days",
                                    fontFamily = JetBrainsFont(),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = UIC_green,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(24.89.dp),
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 33.2.dp)
    ) {
        GoalParamItem(
            painterResource(Res.drawable.habit_statistic__goal__type_of_goal),
            ts_Type_of_goal,
            if (habits[habit_statistics_and_edit_x].typeOfGoalHabits == TypeOfGoalHabits.AT_LEAST) ts_At_least
            else ts_No_more,
            ts_type
        )
        GoalParamItem(
            painterResource(Res.drawable.habit_statistic__goal__need_goal),
            ts_Needed_for_the_goal,
            habits[habit_statistics_and_edit_x].needGoal.toBestString() + " " + habits[habit_statistics_and_edit_x].nameOfUnitsOfDimension,
            ts_goal
        )
        GoalParamItem(
            painterResource(Res.drawable.habit_statistic__goal__period),
            ts_Period,
            habits[habit_statistics_and_edit_x].needDays.toString() + " " + ts_days,
            ts_period
        )
        GoalParamItem(
            painterResource(Res.drawable.habit_statistic__goal__PPS),
            ts_PPS,
            "$pps $ts_days",
            " $ts_PPS $ts_for_statistic",
            true
        )
        PPSSetVector(pps)
    }
}

@Composable
private fun ProgressContent(
    pps: Int
) {
    @Composable
    fun DonutChart(
        progress: Float,
        modifier: Modifier = Modifier,
        strokeWidth: Dp = 20.dp,
        trackColor: Color = noSeeColorByIndex(habit_statistics_and_edit_x),
        progressColor: Color = seeColorByIndex(habit_statistics_and_edit_x),
        label: String = ts_all,
        withLabel: Boolean = false,
        bottomLabel: String = "",
        withBottomLabel: Boolean = !withLabel,
        isPlusProgress: Boolean = !withLabel,
        isBottonLabel: Boolean = true
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.8.dp)
        ) {
            if (withBottomLabel && !isBottonLabel) {
                Text(
                    text = bottomLabel,
                    fontSize = 12.8.sp,
                    color = UICT_no_see,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = JetBrainsFont()
                )
            }
            Box(
                modifier = modifier,
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val stroke = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)

                    drawArc(
                        color = trackColor,
                        startAngle = -90f,
                        sweepAngle = 360f,
                        useCenter = false,
                        style = stroke,
                        size = Size(size.width, size.height)
                    )

                    drawArc(
                        color = progressColor,
                        startAngle = -90f,
                        sweepAngle = 360f * progress,
                        useCenter = false,
                        style = stroke,
                        size = Size(size.width, size.height)
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    if (withLabel) {
                        Text(
                            text = label,
                            fontSize = 12.8.sp,
                            color = UICT_no_see,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontFamily = JetBrainsFont()
                        )
                    }
                    if (isPlusProgress) {
                        Text(
                            text = (if (progress >= 0) "+" else "") + "${(progress * 100).toInt()}%",
                            fontSize = 14.4.sp,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = JetBrainsFont(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = if (progress >= 0) UIC_green else UIC_red
                        )
                    } else {
                        Text(
                            text = "${(progress * 100).toInt()}%",
                            fontSize = 25.6.sp,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = JetBrainsFont(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = UICT_see
                        )
                    }
                    if (withLabel) {
                        Text(
                            text = label,
                            fontSize = 12.8.sp,
                            color = Color.Transparent,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontFamily = JetBrainsFont()
                        )
                    }
                }
            }
            if (withBottomLabel && isBottonLabel) {
                Text(
                    text = bottomLabel,
                    fontSize = 12.8.sp,
                    color = UICT_no_see,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = JetBrainsFont()
                )
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().padding(horizontal = 33.2.dp)
    ) {
        DonutChart(
            progress(habit_statistics_and_edit_x, pps),
            Modifier.size(180.dp),
            20.dp,
            withLabel = true
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(66.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                var progress = plusProgress(habit_statistics_and_edit_x, 1, pps)
                DonutChart(
                    progress = progress,
                    strokeWidth = 10.dp,
                    isBottonLabel = true,
                    trackColor = if (progress >= 0f) noSeeColorByIndex(habit_statistics_and_edit_x) else reversNoBiggerColor(
                        noSeeColorByIndex(habit_statistics_and_edit_x)
                    ),
                    progressColor = if (progress >= 0f) seeColorByIndex(habit_statistics_and_edit_x) else reversNoBiggerColor(
                        seeColorByIndex(habit_statistics_and_edit_x)
                    ),
                    modifier = Modifier.size(90.dp),
                    bottomLabel = ts_day
                )
                progress = plusProgress(habit_statistics_and_edit_x, 7, pps)
                DonutChart(
                    progress = progress,
                    strokeWidth = 10.dp,
                    isBottonLabel = true,
                    trackColor = if (progress >= 0f) noSeeColorByIndex(habit_statistics_and_edit_x) else reversNoBiggerColor(
                        noSeeColorByIndex(habit_statistics_and_edit_x)
                    ),
                    progressColor = if (progress >= 0f) seeColorByIndex(habit_statistics_and_edit_x) else reversNoBiggerColor(
                        seeColorByIndex(habit_statistics_and_edit_x)
                    ),
                    modifier = Modifier.size(90.dp),
                    bottomLabel = ts_week
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                var progress = plusProgress(habit_statistics_and_edit_x, 30, pps)
                DonutChart(
                    progress = progress,
                    strokeWidth = 10.dp,
                    isBottonLabel = false,
                    trackColor = if (progress >= 0f) noSeeColorByIndex(habit_statistics_and_edit_x) else reversNoBiggerColor(
                        noSeeColorByIndex(habit_statistics_and_edit_x)
                    ),
                    progressColor = if (progress >= 0f) seeColorByIndex(habit_statistics_and_edit_x) else reversNoBiggerColor(
                        seeColorByIndex(habit_statistics_and_edit_x)
                    ),
                    modifier = Modifier.size(90.dp),
                    bottomLabel = ts_month
                )
                progress = plusProgress(habit_statistics_and_edit_x, 365, pps)
                DonutChart(
                    progress = progress,
                    strokeWidth = 10.dp,
                    isBottonLabel = false,
                    trackColor = if (progress >= 0f) noSeeColorByIndex(habit_statistics_and_edit_x) else reversNoBiggerColor(
                        noSeeColorByIndex(habit_statistics_and_edit_x)
                    ),
                    progressColor = if (progress >= 0f) seeColorByIndex(habit_statistics_and_edit_x) else reversNoBiggerColor(
                        seeColorByIndex(habit_statistics_and_edit_x)
                    ),
                    modifier = Modifier.size(90.dp),
                    bottomLabel = ts_year
                )
            }
        }
    }
}