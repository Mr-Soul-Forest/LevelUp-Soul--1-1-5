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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.times
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import kotlinx.coroutines.delay
import kotlinx.datetime.DayOfWeek
import org.jetbrains.compose.resources.painterResource
import kotlin.math.abs
import kotlin.math.roundToInt

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
                            colorFilter = ColorFilter.tint(UIC, BlendMode.Modulate),
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
                        if (habits[habit_statistics_and_edit_x].changeLevel) {
                            HabitStatisticsStatusIcon(
                                habitStatisticsStatus = HabitStatisticsStatus.LEVEL,
                                statusNow = habitStatisticsStatus,
                                icon = painterResource(Res.drawable.habit_statistic__level),
                                contentDescription = ts_Level
                            ) { habitStatisticsStatus = HabitStatisticsStatus.LEVEL }
                        }
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
                        HabitStatisticsStatus.LEVEL -> LevelContent(progressPeriodSetting)
                        HabitStatisticsStatus.PROGRESS_GRAPH -> ProgressGraphContent(progressPeriodSetting)
                        HabitStatisticsStatus.BAR_CHART -> BarChartContent()
                        HabitStatisticsStatus.CALENDAR -> CalendarContent()
                        HabitStatisticsStatus.STREAKS -> StreaksContent()

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
    DISTRIBUTION_BY_DAY_OF_THE_WEEK,
    STREAKS
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
                colorFilter = ColorFilter.tint(UICT_no_see, BlendMode.Modulate),
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

    Column(
        verticalArrangement = Arrangement.spacedBy(24.89.dp),
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 33.2.dp)
            .padding(top = 27.2.dp)
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
        ValueSetVector(
            pps,
            habits[habit_statistics_and_edit_x].habitDay.size,
            "$ts_PPS (0 $ts_for_full_time):",
            ts_days
        ) {
            pps_for_habit_statistic = it.toIntOrNull() ?: habits[habit_statistics_and_edit_x].habitDay.size
            if (pps_for_habit_statistic <= 0) pps_for_habit_statistic =
                habits[habit_statistics_and_edit_x].habitDay.size
        }
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
        modifier = Modifier.fillMaxSize().padding(horizontal = 37.6.dp).padding(top = 8.8.dp)
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

@Composable
private fun LevelContent(
    pps: Int
) {
    @Composable
    fun CircleImage(
        isGood: Boolean,
        kX: Float,
        paddingY: Dp,
        size: Dp
    ) {
        Box(
            modifier = Modifier.padding(top = paddingY)
                .fillMaxWidth(kX),
            contentAlignment = Alignment.TopEnd
        ) {
            Box(
                modifier = Modifier.size(size)
                    .background(
                        if (isGood) seeColorByIndex(habit_statistics_and_edit_x)
                        else reversNoBiggerColor(seeColorByIndex(habit_statistics_and_edit_x)),
                        RoundedCornerShape(size / 2)
                    )
                    .clip(RoundedCornerShape(size / 2)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(if (isGood) Res.drawable.habit_statistic__level__up else Res.drawable.habit_statistic__level__down),
                    contentDescription = ts_Level,
                    colorFilter = ColorFilter.tint(
                        if (isGood) noSeeColorByIndex(habit_statistics_and_edit_x)
                        else reversNoBiggerColor(noSeeColorByIndex(habit_statistics_and_edit_x)),
                        BlendMode.Modulate
                    ),
                    modifier = Modifier.size(size / 2)
                )
            }
        }
    }

    @Composable
    fun DonutChart(isGood: Boolean) {
        Box(
            modifier = Modifier.size(180.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val stroke = Stroke(width = 20.dp.toPx(), cap = StrokeCap.Round)

                drawArc(
                    color = if (isGood) noSeeColorByIndex(habit_statistics_and_edit_x)
                    else reversNoBiggerColor(noSeeColorByIndex(habit_statistics_and_edit_x)),
                    startAngle = -90f,
                    sweepAngle = 360f,
                    useCenter = false,
                    style = stroke,
                    size = Size(size.width, size.height)
                )

                drawArc(
                    color = if (isGood) seeColorByIndex(habit_statistics_and_edit_x)
                    else reversNoBiggerColor(seeColorByIndex(habit_statistics_and_edit_x)),
                    startAngle = -90f,
                    sweepAngle = 360f * habits[habit_statistics_and_edit_x].getToLevelUp(pps),
                    useCenter = false,
                    style = stroke,
                    size = Size(size.width, size.height)
                )
            }
            Text(
                text = habits[habit_statistics_and_edit_x].level.toString(),
                fontSize = 25.6.sp,
                color = UICT_see,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontFamily = JetBrainsFont(),
                fontWeight = FontWeight.ExtraBold
            )
        }
    }

    @Composable
    fun paramElement(
        subtitle: String,
        isGood: Boolean,
        bottomFun: @Composable () -> Unit
    ) {
        val background =
            if (!habits[habit_statistics_and_edit_x].changeLevel || isGood) UIC_dark.multiply(g = 2f)
            else UIC_dark.multiply(r = 2f)
        Column(
            modifier = Modifier.fillMaxWidth()
                .height(45.2.dp)
                .background(background, RoundedCornerShape(22.6.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = subtitle,
                fontSize = 12.8.sp,
                color = UICT_no_see,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontFamily = JetBrainsFont(),
                fontWeight = FontWeight.Thin
            )
            bottomFun()
        }
    }

    Column(
        modifier = Modifier.padding(top = 22.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(42.8.dp)
    ) {
        val isGood = if (progress(habit_statistics_and_edit_x, pps) <= 0.2f) false else true
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            CircleImage(isGood, 0.1666f, 20.dp, 49.2.dp)
            CircleImage(isGood, 0.237f, 89.6.dp, 16.4.dp)
            CircleImage(isGood, 0.1296f, 147.2.dp, 33.2.dp)
            CircleImage(isGood, 0.8055f, 29.2.dp, 25.2.dp)
            CircleImage(isGood, 0.9462f, 44.dp, 14.8.dp)
            CircleImage(isGood, 0.8981f, 114.4.dp, 41.6.dp)
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                DonutChart(isGood)
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 29.2.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            paramElement(ts_Goal, isGood) {
                if (habits[habit_statistics_and_edit_x].changeNeedGoalWithLevel) {
                    Row {
                        Text(
                            text = habits[habit_statistics_and_edit_x].needGoal.toBestString() + " " + habits[habit_statistics_and_edit_x].nameOfUnitsOfDimension + " ",
                            fontSize = 16.sp,
                            color = UICT_see,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontFamily = JetBrainsFont(),
                            fontWeight = FontWeight.Normal
                        )
                        Text(
                            text = "-> " + habits[habit_statistics_and_edit_x].getNeedGoalWhenNewLevel(pps)
                                .toBestString() + " " + habits[habit_statistics_and_edit_x].nameOfUnitsOfDimension,
                            fontSize = 16.sp,
                            color = if (isGood) UIC_green else UIC_red,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontFamily = JetBrainsFont(),
                            fontWeight = FontWeight.Bold
                        )
                    }
                } else {
                    Text(
                        text = habits[habit_statistics_and_edit_x].needGoal.toBestString() + " " + habits[habit_statistics_and_edit_x].nameOfUnitsOfDimension,
                        fontSize = 16.sp,
                        color = UICT_see,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = JetBrainsFont(),
                        fontWeight = FontWeight.Normal
                    )
                }
            }
            paramElement(ts_Period, isGood) {
                if (habits[habit_statistics_and_edit_x].changeNeedDaysWithLevel) {
                    Row {
                        Text(
                            text = habits[habit_statistics_and_edit_x].needDays.toString(),
                            fontSize = 16.sp,
                            color = UICT_see,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontFamily = JetBrainsFont(),
                            fontWeight = FontWeight.Normal
                        )
                        var textToSubString =
                            (habits[habit_statistics_and_edit_x].phantomNeedDays - habits[habit_statistics_and_edit_x].phantomNeedDays.intValue(
                                false
                            )).toBestString()
                        if (textToSubString.length >= 3)
                            Text(
                                text = " (${habits[habit_statistics_and_edit_x].phantomNeedDays.toBestString()})",
                                fontSize = 16.sp,
                                color = UICT_no_see,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontFamily = JetBrainsFont(),
                                fontWeight = FontWeight.Thin
                            )
                        Text(
                            text = " $ts_days ",
                            fontSize = 16.sp,
                            color = UICT_see,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontFamily = JetBrainsFont(),
                            fontWeight = FontWeight.Normal
                        )
                        Text(
                            text = "-> " + habits[habit_statistics_and_edit_x].getNeedDaysWhenNewLevel(pps).toString(),
                            fontSize = 16.sp,
                            color = if (isGood) UIC_green else UIC_red,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontFamily = JetBrainsFont(),
                            fontWeight = FontWeight.Bold
                        )
                        textToSubString =
                            (habits[habit_statistics_and_edit_x].getPhantomNeedDaysWhenNewLevel(pps) - habits[habit_statistics_and_edit_x].getPhantomNeedDaysWhenNewLevel(
                                pps
                            ).intValue(false)).toBestString()
                        if (textToSubString.length >= 3)
                            Text(
                                text = " (${
                                    habits[habit_statistics_and_edit_x].getPhantomNeedDaysWhenNewLevel(pps)
                                        .toBestString()
                                })",
                                fontSize = 16.sp,
                                color = UICT_no_see,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontFamily = JetBrainsFont(),
                                fontWeight = FontWeight.Thin
                            )
                        Text(
                            text = " $ts_days",
                            fontSize = 16.sp,
                            color = if (isGood) UIC_green else UIC_red,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontFamily = JetBrainsFont(),
                            fontWeight = FontWeight.Bold
                        )
                    }
                } else {
                    Text(
                        text = habits[habit_statistics_and_edit_x].needDays.toString() + " " + ts_days,
                        fontSize = 16.sp,
                        color = UICT_see,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = JetBrainsFont(),
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
}

@Composable
private fun ProgressGraphContent(
    pps: Int
) {
    var isSmooth by remember { mutableStateOf(true) }

    @Composable
    fun SelectSmooth() {
        @Composable
        fun SelectedElement(smooth: Boolean = isSmooth, isSecond: Boolean = false) {
            Box(
                modifier = Modifier.fillMaxWidth(if (isSecond) 1f else 0.5f)
                    .height(35.6.dp)
                    .background(
                        Brush.linearGradient(
                            listOf(
                                noSeeColorByIndex(habit_statistics_and_edit_x),
                                noSeeColorByIndex(habit_statistics_and_edit_x).multiply(0.2f, 0.2f, 0.2f)
                            ), Offset(0f, 0f), Offset.Infinite
                        ),
                        RoundedCornerShape(17.8.dp)
                    )
                    .border(0.4.dp, seeColorByIndex(habit_statistics_and_edit_x), RoundedCornerShape(17.8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (smooth) ts_Smooth else ts_Linear,
                    fontSize = 16.sp,
                    color = UICT_see,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = JetBrainsFont(),
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }

        @Composable
        fun UnselectedElement(smooth: Boolean = isSmooth, isSecond: Boolean = false) {
            Box(
                modifier = Modifier.fillMaxWidth(if (isSecond) 1f else 0.5f)
                    .height(35.6.dp)
                    .clip(RoundedCornerShape(17.8.dp))
                    .clickable { isSmooth = !smooth },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (isSmooth) ts_Linear else ts_Smooth,
                    fontSize = 16.sp,
                    color = UICT_no_see,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = JetBrainsFont(),
                    fontWeight = FontWeight.Thin
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth()
                .height(45.2.dp)
                .background(
                    noSeeColorByIndex(habit_statistics_and_edit_x).multiply(0.4f, 0.4f, 0.4f, 0.4f),
                    RoundedCornerShape(22.7.dp)
                )
                .padding(horizontal = 6.4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isSmooth) {
                SelectedElement()
                UnselectedElement(isSecond = true)
            } else {
                UnselectedElement()
                SelectedElement(isSecond = true)
            }
        }
    }

    @Composable
    fun SmoothLineChart(
        data: List<Float>,
        modifier: Modifier = Modifier.fillMaxWidth().height(180.8.dp),
        lineColor: Color = seeColorByIndex(habit_statistics_and_edit_x),
        gradientStart: Color = noSeeColorByIndex(habit_statistics_and_edit_x),
        gradientEnd: Color = UIC_dark,
        gridColor: Color = UIC_light,
        strokeWidth: Dp = 2.dp,
        gridLines: Int = 6
    ) {
        if (data.isEmpty()) return

        Canvas(modifier = modifier) {
            val path = Path()
            val gradientPath = Path()

            val maxY = 1f
            val minY = 0f
            val range = (maxY - minY).takeIf { it != 0f } ?: 1f

            val chartWidth = size.width
            val chartHeight = size.height
            val stepX = chartWidth / (data.size - 1)

            val gridSpacing = chartHeight / (gridLines - 1)
            repeat(gridLines) { i ->
                val y = chartHeight - i * gridSpacing
                drawLine(
                    color = gridColor,
                    start = Offset(0f, y),
                    end = Offset(chartWidth, y),
                    strokeWidth = 2f,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(3f, 3f))
                )
            }

            val points = data.mapIndexed { i, y ->
                Offset(i * stepX, chartHeight - (y - minY) / range * chartHeight)
            }

            path.moveTo(points.first().x, points.first().y)
            for (i in 1 until points.size) {
                val prev = points[i - 1]
                val cur = points[i]
                if (isSmooth) {
                    val midX = (prev.x + cur.x) / 2f
                    path.quadraticTo(prev.x, prev.y, midX, (prev.y + cur.y) / 2f)
                } else {
                    path.lineTo(cur.x, cur.y)
                }
            }
            path.lineTo(points.last().x, points.last().y)

            gradientPath.addPath(path)
            gradientPath.lineTo(points.last().x, chartHeight)
            gradientPath.lineTo(points.first().x, chartHeight)
            gradientPath.close()

            val brush = Brush.verticalGradient(
                colors = listOf(gradientStart, gradientEnd),
                startY = 0f,
                endY = chartHeight
            )

            drawPath(
                path = gradientPath,
                brush = brush
            )

            drawPath(
                path = path,
                color = lineColor,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
            )
        }
    }

    var period by remember { mutableStateOf(habits[habit_statistics_and_edit_x].habitDay.size) }
    var step by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 29.2.dp)
            .padding(top = 36.8.dp),
        verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        SmoothLineChart(listProgress(habit_statistics_and_edit_x, period, step, pps))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SelectSmooth()
            ValueSetVector(
                period,
                habits[habit_statistics_and_edit_x].habitDay.size,
                "$ts_Period (0 $ts_For_all_time)",
                ts_days
            ) {
                period = it.toIntOrNull() ?: habits[habit_statistics_and_edit_x].habitDay.size
                if (period < 2) period = 2
            }
            ValueSetVector(
                step,
                habits[habit_statistics_and_edit_x].habitDay.size,
                ts_Step,
                ts_days
            ) {
                step = it.toIntOrNull() ?: 1
                if (step < 1) step = 1
            }
        }
    }
}

@Composable
private fun BarChartContent() {
    @Composable
    fun BarChart(
        values: List<BigDecimal>,
        dates: List<String>,
        positiveGradient: List<Color> = listOf(
            seeColorByIndex(habit_statistics_and_edit_x),
            seeColorByIndex(habit_statistics_and_edit_x).multiply(0.59f, 0.59f, 0.59f)
        ),
        negativeGradient: List<Color> = listOf(
            reversNoBiggerColor(seeColorByIndex(habit_statistics_and_edit_x)),
            reversNoBiggerColor(seeColorByIndex(habit_statistics_and_edit_x).multiply(0.59f, 0.59f, 0.59f))
        ),
        modifier: Modifier = Modifier.height(200.dp).fillMaxWidth(),
        barWidth: Dp = 18.dp,
        barSpacing: Dp = 10.dp,
        cornerRadius: Dp = 6.8.dp,
        axisColor: Color = UIC_light,
    ) {
        if (values.isEmpty()) return

        val scrollState = rememberScrollState()
        val density = LocalDensity.current
        val textMeasurer = rememberTextMeasurer()

        val barWidthPx = with(density) { barWidth.toPx() }
        val spacingPx = with(density) { barSpacing.toPx() }
        val cornerRadiusPx = with(density) { cornerRadius.toPx() }
        val labelOffsetPx = with(density) { 0.8.dp.toPx() }

        LaunchedEffect(values) {
            snapshotFlow { scrollState.maxValue }.collect { max ->
                scrollState.scrollTo(max)
            }
        }

        val totalWidthDp = (values.size * (barWidth + barSpacing)).coerceAtLeast(1.dp)

        Box(
            modifier = modifier.horizontalScroll(scrollState),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .width(totalWidthDp)
                    .fillMaxHeight()
            ) {
                val minValue = values.minOf { it.doubleValue(false) }
                val maxValue = values.maxOf { it.doubleValue(false) }
                val range = maxValue - minValue
                val chartHeight = size.height
                val chartWidth = size.width

                val zeroY = if (range == 0.0) chartHeight / 2f
                else (chartHeight * (maxValue / range)).toFloat()

                drawLine(
                    color = axisColor,
                    start = Offset(0f, zeroY),
                    end = Offset(chartWidth, zeroY),
                    strokeWidth = 2f,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(3f, 3f))
                )

                values.forEachIndexed { i, value ->
                    val v = value.doubleValue(false).toFloat()
                    val x = i * (barWidthPx + spacingPx)
                    val barHeight = (chartHeight * abs(v / range.toFloat()))

                    val top = if (v >= 0f) zeroY - barHeight else zeroY
                    val bottom = if (v >= 0f) zeroY else zeroY + barHeight

                    val brush = Brush.verticalGradient(
                        colors = if (v >= 0f) positiveGradient else negativeGradient,
                        startY = top,
                        endY = bottom
                    )

                    drawRoundRect(
                        brush = brush,
                        topLeft = Offset(x, top),
                        size = Size(barWidthPx, bottom - top),
                        cornerRadius = CornerRadius(cornerRadiusPx, cornerRadiusPx)
                    )
                }
            }

            BoxWithConstraints(
                modifier = Modifier
                    .width(totalWidthDp)
                    .fillMaxHeight()
            ) {
                val boxHeightPx = with(density) { maxHeight.toPx() }
                val minValue = values.minOf { it.doubleValue(false) }
                val maxValue = values.maxOf { it.doubleValue(false) }
                val range = maxValue - minValue
                val zeroY = if (range == 0.0) boxHeightPx / 2f
                else (boxHeightPx * (maxValue / range)).toFloat()

                values.forEachIndexed { i, value ->
                    val v = value.doubleValue(false).toFloat()
                    if (v == 0f) return@forEachIndexed

                    val xCenter = i * (barWidthPx + spacingPx) + barWidthPx / 2f
                    val barHeight = (boxHeightPx * abs(v / range.toFloat()))

                    val top = if (v >= 0f) zeroY - barHeight else zeroY
                    val bottom = if (v >= 0f) zeroY else zeroY + barHeight

                    val label = value.toBestString()

                    val textLayout = textMeasurer.measure(
                        text = AnnotatedString(label),
                        style = androidx.compose.ui.text.TextStyle(
                            fontSize = 11.2.sp,
                            fontFamily = JetBrainsFont(),
                            fontWeight = FontWeight.Thin,
                            color = Color.Unspecified,
                        ),
                        constraints = androidx.compose.ui.unit.Constraints()
                    )
                    val textWidth = textLayout.size.width.toFloat()
                    val textHeight = textLayout.size.height.toFloat()

                    val labelX = (xCenter - textWidth / 2f).roundToInt()
                    val labelY = if (v >= 0f) {
                        (top - labelOffsetPx - textHeight).roundToInt()
                    } else {
                        (bottom + labelOffsetPx).roundToInt()
                    }

                    Box(
                        modifier = Modifier.offset { IntOffset(labelX, labelY) }
                    ) {
                        Text(
                            text = label,
                            textAlign = TextAlign.Center,
                            color = UICT_see,
                            fontSize = 11.2.sp,
                            fontFamily = JetBrainsFont(),
                            fontWeight = FontWeight.Thin,
                            maxLines = 1
                        )
                    }

                    val dateLabel = if (i < dates.size) dates[i] else ""
                    if (dateLabel.isNotEmpty()) {
                        val dateLayout = textMeasurer.measure(
                            text = AnnotatedString(dateLabel),
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 11.2.sp,
                                fontFamily = JetBrainsFont(),
                                fontWeight = FontWeight.Light,
                                color = UICT_see
                            ),
                            constraints = androidx.compose.ui.unit.Constraints()
                        )
                        val dateWidth = dateLayout.size.width.toFloat()

                        val dateX = (xCenter - dateWidth / 2f).roundToInt()
                        val dateY = (zeroY + labelOffsetPx + with(density) { 2.dp.toPx() }).roundToInt()

                        Box(
                            modifier = Modifier.offset { IntOffset(dateX, dateY) }
                        ) {
                            Text(
                                text = dateLabel,
                                textAlign = TextAlign.Center,
                                color = UICT_see,
                                fontSize = 10.sp,
                                fontFamily = JetBrainsFont(),
                                fontWeight = FontWeight.Light,
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }
    }


    var step by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 29.2.dp)
            .padding(top = 36.8.dp),
        verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            BarChart(
                listToday(habit_statistics_and_edit_x, step),
                listTodayDates(habit_statistics_and_edit_x, step)
            )
        }
        ValueSetVector(
            step,
            habits[habit_statistics_and_edit_x].habitDay.size - 1,
            ts_Step,
            ts_days
        ) {
            step = it.toIntOrNull() ?: 1
            if (step < 1) step = 1
            if (step > habits[habit_statistics_and_edit_x].habitDay.size - 1) step =
                habits[habit_statistics_and_edit_x].habitDay.size - 1
        }
    }
}

@Composable
fun CalendarContent() {
    @Composable
    fun HabitGrid(
        startDay: Int = when (habits[habit_statistics_and_edit_x].startDate.dayOfWeek) {
            DayOfWeek.MONDAY -> 0
            DayOfWeek.TUESDAY -> 1
            DayOfWeek.WEDNESDAY -> 2
            DayOfWeek.THURSDAY -> 3
            DayOfWeek.FRIDAY -> 4
            DayOfWeek.SATURDAY -> 5
            DayOfWeek.SUNDAY -> 6
            else -> 0
        },
        labels: List<Int> = listDaysNumbers(habit_statistics_and_edit_x),
        goods: List<Boolean> = listDaysBoolean(habit_statistics_and_edit_x),
        goodColor: Color = seeColorByIndex(habit_statistics_and_edit_x),
        badColor: Color = noSeeColorByIndex(habit_statistics_and_edit_x),
        modifier: Modifier = Modifier
    ) {
        val scrollState = rememberScrollState()

        LaunchedEffect(goods) {
            snapshotFlow { scrollState.maxValue }.collect { max ->
                scrollState.scrollTo(max)
            }
        }

        Box(modifier = modifier.horizontalScroll(scrollState)) {
            var index = 0
            Row(horizontalArrangement = Arrangement.spacedBy(9.2.dp)) {
                while (index < goods.size + startDay) {
                    Column(verticalArrangement = Arrangement.spacedBy(9.2.dp)) {
                        if (index < startDay) {
                            Box(modifier = Modifier.size(18.dp))
                        } else {
                            Box(
                                modifier = Modifier.size(18.dp)
                                    .background(
                                        if (goods[index - startDay]) goodColor else badColor,
                                        RoundedCornerShape(3.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = labels[index - startDay].toString(),
                                    fontSize = 10.8.sp,
                                    color = checkBackgroundBright(
                                        if (goods[index - startDay]) goodColor else badColor,
                                        UICT_see
                                    ),
                                    maxLines = 1,
                                    fontFamily = JetBrainsFont(),
                                    fontWeight = FontWeight.ExtraLight
                                )
                            }
                        }
                        index++
                        while ((index) % 7 != 0 && index < goods.size + startDay) {
                            if (index < startDay) {
                                Box(modifier = Modifier.size(18.dp))
                            } else {
                                Box(
                                    modifier = Modifier.size(18.dp)
                                        .background(
                                            if (goods[index - startDay]) goodColor else badColor,
                                            RoundedCornerShape(3.dp)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = labels[index - startDay].toString(),
                                        fontSize = 10.8.sp,
                                        color = checkBackgroundBright(
                                            if (goods[index - startDay]) goodColor else badColor,
                                            UICT_see
                                        ),
                                        maxLines = 1,
                                        fontFamily = JetBrainsFont(),
                                        fontWeight = FontWeight.ExtraLight
                                    )
                                }
                            }
                            index++
                        }
                    }
                }
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 29.2.dp)
            .padding(top = 32.8.dp),
        contentAlignment = Alignment.Center
    ) {
        HabitGrid()
    }
}

@Composable
fun StreaksContent() {
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 29.2.dp)
            .padding(top = 32.8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(11.2.dp)
    ) {
        val streaks = habitStreaks(habit_statistics_and_edit_x)
        val maxStreak = streaks.max()

        for (streak in streaks) {
            val k = streak.toFloat() / maxStreak.toFloat()
            Box(
                modifier = Modifier.fillMaxWidth(k)
                    .height(24.4.dp)
                    .background(
                        seeColorByIndex(habit_statistics_and_edit_x).multiply(k, k, k),
                        RoundedCornerShape(8.8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$streak $ts_days",
                    color = checkBackgroundBright(
                        seeColorByIndex(habit_statistics_and_edit_x).multiply(k, k, k),
                        UICT_see
                    ),
                    fontSize = 12.8.sp,
                    fontFamily = JetBrainsFont(),
                    fontWeight = FontWeight.Thin,
                    maxLines = 1
                )
            }
        }
    }
}