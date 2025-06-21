package fireforestsoul.levelupsoul

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import kotlin.math.max

var habit_statistics_and_edit_x = 0

@Composable
fun HabitStatistics(viewModel: AppViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(UI_dark_color)
    ) {
        Scaffold(
            modifier = Modifier.padding(WindowInsets.systemBars.asPaddingValues()),
            topBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(UI_color)
                        .height(48.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Habit <${habits[habit_statistics_and_edit_x].nameOfHabit}> statistic",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = textSeeUiColor,
                            modifier = Modifier.padding(start = 15.dp)
                        )
                        IconButton(onClick = {
                            viewModel.setStatus(AppStatus.EDIT_HABIT)
                        }) {
                            Image(
                                painter = painterResource(Res.drawable.edit_a_habit),
                                contentDescription = "Edit a habit",
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .size(28.dp),
                            )
                        }
                    }
                }
            },
            bottomBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .background(UI_color),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "⟵",
                        fontSize = 25.sp,
                        color = textSeeUiColor,
                        modifier = Modifier.clickable {
                            viewModel.setStatus(AppStatus.TABLE)
                        }
                    )
                }
            }
        ) { paddingValues ->
            val verticalScroll = rememberScrollState()
            val spaceCell = 8.dp

            var maxDays = 0
            for (habit in habits) {
                maxDays = max(habit.habitDay.size, maxDays)
            }
            val seeColor =
                if (habits[habit_statistics_and_edit_x].typeOfColorHabits == TypeOfColorHabits.SELECTED)
                    habits[habit_statistics_and_edit_x].colorGood
                else Color(
                    (habits[habit_statistics_and_edit_x].habitDay.size.toDouble() / maxDays.toDouble() * 255.0).toInt(),
                    (progress(habit_statistics_and_edit_x) * 255.0).toInt(),
                    255
                )
            val noSeeColor = if (habits[habit_statistics_and_edit_x].typeOfColorHabits == TypeOfColorHabits.SELECTED)
                Color(
                    habits[habit_statistics_and_edit_x].colorGood.red * 0.5F,
                    habits[habit_statistics_and_edit_x].colorGood.green * 0.5F,
                    habits[habit_statistics_and_edit_x].colorGood.blue * 0.5F
                )
            else Color(
                (habits[habit_statistics_and_edit_x].habitDay.size.toDouble() / maxDays.toDouble() * 127.5).toInt(),
                (progress(habit_statistics_and_edit_x) * 127.5).toInt(),
                127
            )

            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(UI_dark_color)
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
                            .background(UI_color, RoundedCornerShape(20.dp))
                            .height(48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Goal",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = textSeeUiColor,
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "You need " + habits[habit_statistics_and_edit_x].typeOfGoalHabits.toString()
                                .lowercase() + " ${habits[habit_statistics_and_edit_x].needGoal} ${habits[habit_statistics_and_edit_x].nameOfUnitsOfDimension} in ${habits[habit_statistics_and_edit_x].needDays} days",
                            fontSize = 16.sp,
                            color = textSeeUiColor
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
                                        "For all time 0",
                                        fontSize = 12.sp,
                                        color = textNoSeeColor
                                    )
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                singleLine = true,
                                textStyle = TextStyle(
                                    fontSize = 14.sp,
                                    color = textSeeUiColor
                                ),
                                shape = RoundedCornerShape(15.dp),
                                colors = TextFieldDefaults.colors(
                                    focusedTextColor = textSeeUiColor,
                                    unfocusedTextColor = textNoSeeColor,
                                    disabledTextColor = textNoSeeColor,
                                    focusedContainerColor = Color(20,20,20),
                                    unfocusedContainerColor = Color(20,20,20),
                                    disabledContainerColor = Color(20,20,20),
                                    cursorColor = textSeeUiColor,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent
                                ),
                                modifier = Modifier.size(125.dp, 55.dp)
                            )
                            Text(
                                text = "Set",
                                fontSize = 16.sp,
                                color = textSeeUiColor,
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
                            .background(UI_color, RoundedCornerShape(20.dp))
                            .height(48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Progress",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = textSeeUiColor,
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
                                        strokeWidth = 10.dp
                                    )
                                    Text(
                                        text = (if (progressDay > 0) "+" else "") + "${(progressDay * 100).toInt()}%",
                                        fontSize = 12.sp,
                                        color = seeColor
                                    )
                                }
                                Text(
                                    text = "day",
                                    fontSize = 12.sp,
                                    color = textSeeUiColor
                                )
                                Box(contentAlignment = Alignment.Center) {
                                    val progressWeek =
                                        plusProgress(habit_statistics_and_edit_x, 7, progressPeriodSetting)
                                    DonutChart(
                                        modifier = Modifier.size(60.dp),
                                        values = listOf(progressWeek, 1 - progressWeek),
                                        colors = listOf(seeColor, noSeeColor),
                                        strokeWidth = 10.dp
                                    )
                                    Text(
                                        text = (if (progressWeek > 0) "+" else "") + "${(progressWeek * 100).toInt()}%",
                                        fontSize = 12.sp,
                                        color = seeColor
                                    )
                                }
                                Text(
                                    text = "week",
                                    fontSize = 12.sp,
                                    color = textSeeUiColor
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
                                        strokeWidth = 20.dp
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
                                        strokeWidth = 10.dp
                                    )
                                    Text(
                                        text = (if (progressMonth > 0) "+" else "") + "${(progressMonth * 100).toInt()}%",
                                        fontSize = 12.sp,
                                        color = seeColor
                                    )
                                }
                                Text(
                                    text = "month",
                                    fontSize = 12.sp,
                                    color = textSeeUiColor
                                )
                                Box(contentAlignment = Alignment.Center) {
                                    val progressYear =
                                        plusProgress(habit_statistics_and_edit_x, 365, progressPeriodSetting)
                                    DonutChart(
                                        modifier = Modifier.size(60.dp),
                                        values = listOf(progressYear, 1 - progressYear),
                                        colors = listOf(seeColor, noSeeColor),
                                        strokeWidth = 10.dp
                                    )
                                    Text(
                                        text = (if (progressYear > 0) "+" else "") + "${(progressYear * 100).toInt()}%",
                                        fontSize = 12.sp,
                                        color = seeColor
                                    )
                                }
                                Text(
                                    text = "year",
                                    fontSize = 12.sp,
                                    color = textSeeUiColor
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}