package fireforestsoul.levelupsoul

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ionspin.kotlin.bignum.decimal.toBigDecimal
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.painterResource

var habit_statistics_and_edit_x = 0

@Composable
fun HabitStatistics(viewModel: AppViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(UIC_dark, UIC_black)))
    ) {

        var habitStatisticsStatus by remember { mutableStateOf(HabitStatisticsStatus.GOAL) }

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
                            fontFamily = FontFamily.Monospace,
                            fontSize = 28.33.sp
                        )
                        Text(
                            text = ts_statistic,
                            color = UICT_no_see,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 12.sp
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
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 16.sp
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
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 16.sp
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
                            fontSize = 50.sp,
                            fontWeight = FontWeight.Black,
                            modifier = Modifier.rotate(-28.79f)
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
                            fontSize = 42.67.sp,
                            fontWeight = FontWeight.Black,
                            modifier = Modifier.rotate(33.94f)
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
                            fontSize = 26.67.sp,
                            fontWeight = FontWeight.Black,
                            modifier = Modifier.rotate(-17.23f)
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
                    .height(paddingValues.calculateTopPadding() + 66.4.dp)
            )

            val verticalScroll = rememberScrollState()
            val spaceCell = 8.dp

            val seeColor = seeColorByIndex(habit_statistics_and_edit_x)
            val noSeeColor = noSeeColorByIndex(habit_statistics_and_edit_x)

            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(UIC_dark_x2, RoundedCornerShape(topStart = 66.4.dp, topEnd = 66.4.dp))
                    .verticalScroll(verticalScroll)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = spaceCell, horizontal = spaceCell / 2),
                    verticalArrangement = Arrangement.spacedBy(spaceCell),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var progressPeriod by remember { mutableStateOf(habits[habit_statistics_and_edit_x].habitDay.size.toString()) }
                    var progressPeriodSetting by remember { mutableStateOf(habits[habit_statistics_and_edit_x].habitDay.size) }

                    //Goal
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(UIC_dark_x05, RoundedCornerShape(20.dp))
                            .height(48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = ts_Goal,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = UICT_see,
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "$ts_You_need " + habits[habit_statistics_and_edit_x].typeOfGoalHabits.toString()
                                .lowercase() + " ${habits[habit_statistics_and_edit_x].needGoal.toBestString()} ${habits[habit_statistics_and_edit_x].nameOfUnitsOfDimension} $ts_in ${habits[habit_statistics_and_edit_x].needDays} $ts_days",
                            fontSize = 16.sp,
                            color = UICT_see
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            PPSInfoDialog()
                            OutlinedTextField(
                                value = progressPeriod,
                                onValueChange = { progressPeriod = it },
                                label = {
                                    Text(
                                        "$ts_For_all_time 0",
                                        fontSize = 12.sp,
                                        color = UICT_no_see
                                    )
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                singleLine = true,
                                textStyle = TextStyle(
                                    fontSize = 14.sp,
                                    color = UICT_see
                                ),
                                shape = RoundedCornerShape(15.dp),
                                colors = TextFieldDefaults.colors(
                                    focusedTextColor = UICT_see,
                                    unfocusedTextColor = UICT_no_see,
                                    disabledTextColor = UICT_no_see,
                                    focusedContainerColor = UIC_dark_x2,
                                    unfocusedContainerColor = UIC_dark_x2,
                                    disabledContainerColor = UIC_dark_x2,
                                    cursorColor = UICT_see,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent
                                ),
                                modifier = Modifier.size(125.dp, 55.dp)
                            )
                            Text(
                                text = ts_Set,
                                fontSize = 16.sp,
                                color = UICT_see,
                                modifier = Modifier
                                    .background(Color(25, 50, 25), RoundedCornerShape(15.dp))
                                    .clickable {
                                        if (progressPeriod.toIntOrNull() != null) {
                                            progressPeriodSetting = if (progressPeriod.toInt() == 0)
                                                habits[habit_statistics_and_edit_x].habitDay.size
                                            else
                                                progressPeriod.toInt()
                                        }
                                    }
                                    .padding(12.5.dp)
                            )
                        }
                    }

                    //Progress
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(UIC, RoundedCornerShape(20.dp))
                            .height(48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = ts_Progress,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = UICT_see,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.padding(vertical = 15.dp),
                            horizontalArrangement = Arrangement.spacedBy(30.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(15.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    val progressDay =
                                        plusProgress(habit_statistics_and_edit_x, 1, progressPeriodSetting)
                                    DonutChart(
                                        modifier = Modifier.size(60.dp),
                                        values = listOf(progressDay, 1 - progressDay),
                                        colors = listOf(seeColor, noSeeColor),
                                        strokeWidth = 5.dp
                                    )
                                    Text(
                                        text = (if (progressDay > 0) "+" else "") + "${(progressDay * 100).toInt()}%",
                                        fontSize = 12.sp,
                                        color = seeColor
                                    )
                                }
                                Text(
                                    text = ts_day,
                                    fontSize = 12.sp,
                                    color = UICT_see
                                )
                                Box(contentAlignment = Alignment.Center) {
                                    val progressWeek =
                                        plusProgress(habit_statistics_and_edit_x, 7, progressPeriodSetting)
                                    DonutChart(
                                        modifier = Modifier.size(60.dp),
                                        values = listOf(progressWeek, 1 - progressWeek),
                                        colors = listOf(seeColor, noSeeColor),
                                        strokeWidth = 5.dp
                                    )
                                    Text(
                                        text = (if (progressWeek > 0) "+" else "") + "${(progressWeek * 100).toInt()}%",
                                        fontSize = 12.sp,
                                        color = seeColor
                                    )
                                }
                                Text(
                                    text = ts_week,
                                    fontSize = 12.sp,
                                    color = UICT_see
                                )
                            }
                            Column(
                                verticalArrangement = Arrangement.spacedBy(15.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    val progress = progress(habit_statistics_and_edit_x, progressPeriodSetting)
                                    DonutChart(
                                        modifier = Modifier.size(125.dp),
                                        values = listOf(progress, 1 - progress),
                                        colors = listOf(seeColor, noSeeColor),
                                        strokeWidth = 10.dp
                                    )
                                    Text(
                                        text = "${(progress * 100).toInt()}%",
                                        fontSize = 16.sp,
                                        color = seeColor
                                    )
                                }
                            }
                            Column(
                                verticalArrangement = Arrangement.spacedBy(15.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    val progressMonth =
                                        plusProgress(habit_statistics_and_edit_x, 30, progressPeriodSetting)
                                    DonutChart(
                                        modifier = Modifier.size(60.dp),
                                        values = listOf(progressMonth, 1 - progressMonth),
                                        colors = listOf(seeColor, noSeeColor),
                                        strokeWidth = 5.dp
                                    )
                                    Text(
                                        text = (if (progressMonth > 0) "+" else "") + "${(progressMonth * 100).toInt()}%",
                                        fontSize = 12.sp,
                                        color = seeColor
                                    )
                                }
                                Text(
                                    text = ts_month,
                                    fontSize = 12.sp,
                                    color = UICT_see
                                )
                                Box(contentAlignment = Alignment.Center) {
                                    val progressYear =
                                        plusProgress(habit_statistics_and_edit_x, 365, progressPeriodSetting)
                                    DonutChart(
                                        modifier = Modifier.size(60.dp),
                                        values = listOf(progressYear, 1 - progressYear),
                                        colors = listOf(seeColor, noSeeColor),
                                        strokeWidth = 5.dp
                                    )
                                    Text(
                                        text = (if (progressYear > 0) "+" else "") + "${(progressYear * 100).toInt()}%",
                                        fontSize = 12.sp,
                                        color = seeColor
                                    )
                                }
                                Text(
                                    text = ts_year,
                                    fontSize = 12.sp,
                                    color = UICT_see
                                )
                            }
                        }
                    }

                    //Level
                    if (habits[habit_statistics_and_edit_x].changeLevel) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(UIC, RoundedCornerShape(20.dp))
                                .height(48.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = ts_Level,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = UICT_see,
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = Modifier.padding(vertical = 30.dp),
                                horizontalArrangement = Arrangement.spacedBy(30.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                var goodProgress by remember { mutableStateOf(0f) }
                                var progressUp by remember { mutableStateOf(true) }
                                var needDaysChanged by remember { mutableStateOf(habits[habit_statistics_and_edit_x].needDays) }
                                var needGoalChanged by remember { mutableStateOf(habits[habit_statistics_and_edit_x].needGoal) }

                                goodProgress = 0f
                                if (progress(habit_statistics_and_edit_x) >= 0.8) {
                                    for (x in (habits[habit_statistics_and_edit_x].habitDay.size - (Clock.System.now()
                                        .toLocalDateTime(TimeZone.currentSystemDefault()).date.toEpochDays() - habits[habit_statistics_and_edit_x].lastLevelChangeDate.toEpochDays()))
                                            until habits[habit_statistics_and_edit_x].habitDay.size) {
                                        if (x >= 0) {
                                            if (progress(habit_statistics_and_edit_x, startIndex = x) >= 0.8) {
                                                goodProgress++
                                            } else {
                                                goodProgress = 0f
                                            }
                                        }
                                    }
                                    if (habits[habit_statistics_and_edit_x].changeNeedDaysWithLevel) {
                                        if (habits[habit_statistics_and_edit_x].typeOfGoalHabits == TypeOfGoalHabits.AT_LEAST) needDaysChanged =
                                            if ((habits[habit_statistics_and_edit_x].needDays * 0.8).toInt() > 0) (habits[habit_statistics_and_edit_x].needDays * 0.8).toInt() else 1
                                        else if (habits[habit_statistics_and_edit_x].typeOfGoalHabits == TypeOfGoalHabits.NO_MORE) needDaysChanged =
                                            (habits[habit_statistics_and_edit_x].needDays / 0.8).toInt()
                                    }
                                    if (habits[habit_statistics_and_edit_x].changeNeedGoalWithLevel) {
                                        if (habits[habit_statistics_and_edit_x].typeOfGoalHabits == TypeOfGoalHabits.AT_LEAST) needGoalChanged =
                                            habits[habit_statistics_and_edit_x].needGoal / "0.8".toBigDecimal()
                                        else if (habits[habit_statistics_and_edit_x].typeOfGoalHabits == TypeOfGoalHabits.NO_MORE) needGoalChanged =
                                            habits[habit_statistics_and_edit_x].needGoal * "0.8".toBigDecimal()
                                    }
                                    progressUp = true
                                } else if (progress(habit_statistics_and_edit_x) <= 0.2) {
                                    for (x in (habits[habit_statistics_and_edit_x].habitDay.size - (Clock.System.now()
                                        .toLocalDateTime(TimeZone.currentSystemDefault()).date.toEpochDays() - habits[habit_statistics_and_edit_x].lastLevelChangeDate.toEpochDays())) until habits[habit_statistics_and_edit_x].habitDay.size) {
                                        if (x >= 0) {
                                            if (progress(habit_statistics_and_edit_x, startIndex = x) <= 0.2) {
                                                goodProgress++
                                            } else {
                                                goodProgress = 0f
                                            }
                                        }
                                    }
                                    if (habits[habit_statistics_and_edit_x].changeNeedDaysWithLevel) {
                                        if (habits[habit_statistics_and_edit_x].typeOfGoalHabits == TypeOfGoalHabits.AT_LEAST) needDaysChanged =
                                            (habits[habit_statistics_and_edit_x].needDays / 0.8).toInt()
                                        else if (habits[habit_statistics_and_edit_x].typeOfGoalHabits == TypeOfGoalHabits.NO_MORE) needDaysChanged =
                                            if ((habits[habit_statistics_and_edit_x].needDays * 0.8).toInt() > 0) (habits[habit_statistics_and_edit_x].needDays * 0.8).toInt() else 1
                                    }
                                    if (habits[habit_statistics_and_edit_x].changeNeedGoalWithLevel) {
                                        if (habits[habit_statistics_and_edit_x].typeOfGoalHabits == TypeOfGoalHabits.AT_LEAST) needGoalChanged =
                                            habits[habit_statistics_and_edit_x].needGoal * "0.8".toBigDecimal()
                                        else if (habits[habit_statistics_and_edit_x].typeOfGoalHabits == TypeOfGoalHabits.NO_MORE) needGoalChanged =
                                            habits[habit_statistics_and_edit_x].needGoal / "0.8".toBigDecimal()
                                    }
                                    progressUp = false
                                }

                                Box {
                                    Box(
                                        modifier = Modifier
                                            .padding(top = 18.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .border(
                                                    width = 1.dp,
                                                    color = UICT_no_see,
                                                    shape = RoundedCornerShape(8.dp)
                                                )
                                                .background(UIC, RoundedCornerShape(8.dp)),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = "$ts_Period:",
                                                color = Color.Transparent,
                                                fontSize = 14.sp,
                                                modifier = Modifier.padding(start = 7.dp, end = 7.dp)
                                            )
                                            Column(
                                                modifier = Modifier.padding(7.dp),
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.spacedBy(2.dp)
                                            ) {
                                                Text(
                                                    text = "${habits[habit_statistics_and_edit_x].needDays}",
                                                    fontSize = 16.sp,
                                                    color = UICT_see
                                                )
                                                Text(
                                                    text = "⬇",
                                                    fontSize = 16.sp,
                                                    color = if (progressUp) UIC_green else UIC_red,
                                                    fontWeight = FontWeight.Black
                                                )
                                                Text(
                                                    text = "$needDaysChanged",
                                                    fontSize = 16.sp,
                                                    color = if (progressUp) UIC_green else UIC_red
                                                )
                                            }
                                        }
                                    }
                                    Text(
                                        text = "$ts_Period:",
                                        color = UICT_no_see,
                                        style = MaterialTheme.typography.bodySmall,
                                        fontSize = 14.sp,
                                        modifier = Modifier
                                            .padding(start = 7.dp)
                                    )
                                }

                                Column(
                                    verticalArrangement = Arrangement.spacedBy(15.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center
                                    ) {
                                        DonutChart(
                                            modifier = Modifier.size(125.dp),
                                            values = if (progressUp) listOf(
                                                goodProgress / 20f,
                                                1f - goodProgress / 20f
                                            ) else listOf(1f - goodProgress / 20f, goodProgress / 20f),
                                            colors = if (progressUp) listOf(seeColor, noSeeColor) else listOf(
                                                noSeeColor,
                                                seeColor
                                            ),
                                            strokeWidth = 10.dp
                                        )
                                        Text(
                                            text = if (goodProgress == 0f) "${habits[habit_statistics_and_edit_x].level}" else (if (progressUp) "${habits[habit_statistics_and_edit_x].level} ⬆" else "${habits[habit_statistics_and_edit_x].level} ⬇"),
                                            fontSize = 16.sp,
                                            fontWeight = if (goodProgress == 0f) FontWeight.Normal else FontWeight.Bold,
                                            color = if (goodProgress == 0f) UICT_see else (if (progressUp) UIC_green else UIC_red)
                                        )
                                    }
                                }

                                Box {
                                    Box(
                                        modifier = Modifier
                                            .padding(top = 18.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .border(
                                                    width = 1.dp,
                                                    color = UICT_no_see,
                                                    shape = RoundedCornerShape(8.dp)
                                                )
                                                .background(UIC, RoundedCornerShape(8.dp)),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = " $ts_Goal: ",
                                                color = Color.Transparent,
                                                fontSize = 14.sp,
                                                modifier = Modifier.padding(start = 7.dp, end = 7.dp)
                                            )
                                            Column(
                                                modifier = Modifier.padding(7.dp),
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.spacedBy(2.dp)
                                            ) {
                                                Text(
                                                    text = habits[habit_statistics_and_edit_x].needGoal.toBestString(),
                                                    fontSize = 16.sp,
                                                    color = UICT_see
                                                )
                                                Text(
                                                    text = "⬇",
                                                    fontSize = 16.sp,
                                                    color = if (progressUp) UIC_green else UIC_red,
                                                    fontWeight = FontWeight.Black
                                                )
                                                Text(
                                                    text = needGoalChanged.toBestString(),
                                                    fontSize = 16.sp,
                                                    color = if (progressUp) UIC_green else UIC_red
                                                )
                                            }
                                        }
                                    }
                                    Text(
                                        text = " $ts_Goal: ",
                                        color = UICT_no_see,
                                        style = MaterialTheme.typography.bodySmall,
                                        fontSize = 14.sp,
                                        modifier = Modifier
                                            .padding(start = 7.dp)
                                    )
                                }
                            }
                        }
                    }

                    //Progress graph
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(UIC, RoundedCornerShape(20.dp))
                            .height(48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = ts_Progress_graph,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = UICT_see,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(15.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            var period by remember { mutableStateOf(habits[habit_statistics_and_edit_x].habitDay.size.toString()) }
                            var step by remember { mutableStateOf(1.toString()) }
                            var periodSetting by remember { mutableStateOf(habits[habit_statistics_and_edit_x].habitDay.size) }
                            var stepSetting by remember { mutableStateOf(1) }

                            AnimatedLineChart(
                                data = listProgress(
                                    habit_statistics_and_edit_x,
                                    periodSetting,
                                    stepSetting,
                                    progressPeriodSetting
                                ),
                                yMax = 1f,
                                lineAndDotColor = seeColor,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(220.dp)
                                    .padding(16.dp)
                            )
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                OutlinedTextField(
                                    value = period,
                                    onValueChange = { period = it },
                                    label = {
                                        Text(
                                            "$ts_Period:",
                                            fontSize = 12.sp,
                                            color = UICT_no_see
                                        )
                                    },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    singleLine = true,
                                    textStyle = TextStyle(
                                        fontSize = 14.sp,
                                        color = UICT_see
                                    ),
                                    shape = RoundedCornerShape(15.dp),
                                    colors = TextFieldDefaults.colors(
                                        focusedTextColor = UICT_see,
                                        unfocusedTextColor = UICT_no_see,
                                        disabledTextColor = UICT_no_see,
                                        focusedContainerColor = UIC_dark_x2,
                                        unfocusedContainerColor = UIC_dark_x2,
                                        disabledContainerColor = UIC_dark_x2,
                                        cursorColor = UICT_see,
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                        disabledIndicatorColor = Color.Transparent
                                    ),
                                    modifier = Modifier.size(125.dp, 55.dp)
                                )
                                OutlinedTextField(
                                    value = step,
                                    onValueChange = { step = it },
                                    label = {
                                        Text(
                                            "$ts_Step:",
                                            fontSize = 12.sp,
                                            color = UICT_no_see
                                        )
                                    },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    singleLine = true,
                                    textStyle = TextStyle(
                                        fontSize = 14.sp,
                                        color = UICT_see
                                    ),
                                    shape = RoundedCornerShape(15.dp),
                                    colors = TextFieldDefaults.colors(
                                        focusedTextColor = UICT_see,
                                        unfocusedTextColor = UICT_no_see,
                                        disabledTextColor = UICT_no_see,
                                        focusedContainerColor = UIC_dark_x2,
                                        unfocusedContainerColor = UIC_dark_x2,
                                        disabledContainerColor = UIC_dark_x2,
                                        cursorColor = UICT_see,
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                        disabledIndicatorColor = Color.Transparent
                                    ),
                                    modifier = Modifier.size(125.dp, 55.dp)
                                )
                                Text(
                                    text = ts_Set,
                                    fontSize = 16.sp,
                                    color = UICT_see,
                                    modifier = Modifier
                                        .background(Color(25, 50, 25), RoundedCornerShape(15.dp))
                                        .clickable {
                                            if (period.toIntOrNull() != null) {
                                                periodSetting = if (period.toInt() == 0)
                                                    habits[habit_statistics_and_edit_x].habitDay.size
                                                else
                                                    period.toInt()
                                            }
                                            if (step.toIntOrNull() != null) {
                                                stepSetting = if (step.toInt() <= 0)
                                                    1
                                                else
                                                    step.toInt()
                                            }
                                        }
                                        .padding(12.5.dp)
                                )
                            }
                        }
                    }

                    //Results
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(UIC, RoundedCornerShape(20.dp))
                            .height(48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = ts_Results,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = UICT_see,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(15.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            var step by remember { mutableStateOf(1.toString()) }
                            var stepSetting by remember { mutableStateOf(1) }

                            AnimatedBarChart(
                                data = listToday(habit_statistics_and_edit_x, stepSetting),
                                labels = listTodayDates(habit_statistics_and_edit_x, stepSetting),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(220.dp)
                                    .padding(16.dp),
                                barColor = seeColor
                            )
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                OutlinedTextField(
                                    value = step,
                                    onValueChange = { step = it },
                                    label = {
                                        Text(
                                            "$ts_Step:",
                                            fontSize = 12.sp,
                                            color = UICT_no_see
                                        )
                                    },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    singleLine = true,
                                    textStyle = TextStyle(
                                        fontSize = 14.sp,
                                        color = UICT_see
                                    ),
                                    shape = RoundedCornerShape(15.dp),
                                    colors = TextFieldDefaults.colors(
                                        focusedTextColor = UICT_see,
                                        unfocusedTextColor = UICT_no_see,
                                        disabledTextColor = UICT_no_see,
                                        focusedContainerColor = UIC_dark_x2,
                                        unfocusedContainerColor = UIC_dark_x2,
                                        disabledContainerColor = UIC_dark_x2,
                                        cursorColor = UICT_see,
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                        disabledIndicatorColor = Color.Transparent
                                    ),
                                    modifier = Modifier.size(125.dp, 55.dp)
                                )
                                Text(
                                    text = ts_Set,
                                    fontSize = 16.sp,
                                    color = UICT_see,
                                    modifier = Modifier
                                        .background(Color(25, 50, 25), RoundedCornerShape(15.dp))
                                        .clickable {
                                            if (step.toIntOrNull() != null) {
                                                stepSetting = if (step.toInt() <= 0)
                                                    1
                                                else
                                                    step.toInt()
                                            }
                                        }
                                        .padding(12.5.dp)
                                )
                            }
                            HabitGrid(
                                values = listDaysNumbers(habit_statistics_and_edit_x),
                                states = listDaysBoolean(habit_statistics_and_edit_x),
                                trueColor = seeColor,
                                falseColor = noSeeColor,
                                startDate = habits[habit_statistics_and_edit_x].startDate,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(220.dp)
                                    .padding(16.dp),
                            )
                        }
                    }

                    //Seria
                    if (habitSeria(habit_statistics_and_edit_x).isNotEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(UIC, RoundedCornerShape(20.dp))
                                .height(48.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = ts_Seria,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = UICT_see,
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            var columnWidth by remember { mutableStateOf(0) }

                            Column(
                                verticalArrangement = Arrangement.spacedBy(4.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .onGloballyPositioned { layoutCoordinates ->
                                        columnWidth = layoutCoordinates.size.width
                                    }
                            ) {
                                val maxValue = habitSeria(habit_statistics_and_edit_x)[0]
                                for (value in habitSeria(habit_statistics_and_edit_x)) {
                                    Box(
                                        modifier = Modifier
                                            .width(with(LocalDensity.current) { (columnWidth / (if (maxValue != 0) maxValue else 1) * value).toDp() })
                                            .background(
                                                seeColor,
                                                RoundedCornerShape(15.dp)
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "$value",
                                            fontSize = 15.sp,
                                            color = if (seeColor.red * 255 + seeColor.green * 255 + seeColor.blue * 255 < 383) UIC_white else UIC_black,
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
}

enum class HabitStatisticsStatus {
    GOAL,
    PROGRESS,
    LEVEL,
    PROGRESS_GRAPH,
    BAR_CHART,
    CALENDAR,
    STRIKES,
    DISTRIBUTION_BY_DAY_OF_THE_WEEK
}