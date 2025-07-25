package fireforestsoul.levelupsoul

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ionspin.kotlin.bignum.decimal.toBigDecimal

@Composable
fun EditHabit(viewModel: AppViewModel) {
    val verticalScroll = rememberScrollState()
    val horizontalScroll = rememberScrollState()
    var expanded0 by remember { mutableStateOf(false) }
    var expanded1 by remember { mutableStateOf(false) }

    var nameOfHabit by remember { mutableStateOf(habits[habit_statistics_and_edit_x].nameOfHabit) }
    var typeOfColorHabits by remember { mutableStateOf(habits[habit_statistics_and_edit_x].typeOfColorHabits) }
    var colorGood by remember { mutableStateOf(habits[habit_statistics_and_edit_x].colorGood) }
    var typeOfGoalHabits by remember { mutableStateOf(habits[habit_statistics_and_edit_x].typeOfGoalHabits) }
    var needGoal by remember { mutableStateOf(habits[habit_statistics_and_edit_x].needGoal.toString()) }
    var nameOfUnitsOfDimension by remember { mutableStateOf(habits[habit_statistics_and_edit_x].nameOfUnitsOfDimension) }
    var needDays by remember { mutableStateOf(habits[habit_statistics_and_edit_x].needDays.toString()) }
    var changeLevel by remember { mutableStateOf(habits[habit_statistics_and_edit_x].changeLevel) }
    var changeNeedGoalWithLevel by remember { mutableStateOf(habits[habit_statistics_and_edit_x].changeNeedGoalWithLevel) }
    var changeNeedDaysWithLevel by remember { mutableStateOf(habits[habit_statistics_and_edit_x].changeNeedDaysWithLevel) }

    val spaceX = 4.dp
    val spaceY = 4.dp
    val spaceXY = 15.dp

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
                    Text(
                        text = ts_Edit + " \"${habits[habit_statistics_and_edit_x].nameOfHabit}\"",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = textSeeUiColor,
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }
            },
            bottomBar = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .background(UI_color)
                        .padding(horizontal = 50.dp)
                ) {
                    Text(
                        text = "❌ $ts_Cancel",
                        fontSize = 16.sp,
                        color = Color(200, 150, 150),
                        modifier = Modifier.clickable {
                            viewModel.setStatus(AppStatus.TABLE)
                        }
                    )
                    Text(
                        text = "✅ $ts_Confirm",
                        fontSize = 16.sp,
                        color = Color(150, 200, 150),
                        modifier = Modifier.clickable {
                            habits[habit_statistics_and_edit_x].nameOfHabit = nameOfHabit
                            habits[habit_statistics_and_edit_x].typeOfColorHabits = typeOfColorHabits
                            habits[habit_statistics_and_edit_x].colorGood = colorGood
                            habits[habit_statistics_and_edit_x].typeOfGoalHabits = typeOfGoalHabits
                            habits[habit_statistics_and_edit_x].needGoal =
                                if (needGoal.toDoubleOrNull() != null) needGoal.toBigDecimal() else habits[habit_statistics_and_edit_x].needGoal
                            habits[habit_statistics_and_edit_x].nameOfUnitsOfDimension = nameOfUnitsOfDimension
                            habits[habit_statistics_and_edit_x].needDays =
                                (if (needDays.toIntOrNull() != null) needDays.toIntOrNull() else habits[habit_statistics_and_edit_x].needDays)!!
                            habits[habit_statistics_and_edit_x].changeLevel = changeLevel
                            habits[habit_statistics_and_edit_x].changeNeedGoalWithLevel = changeNeedGoalWithLevel
                            habits[habit_statistics_and_edit_x].changeNeedDaysWithLevel = changeNeedDaysWithLevel
                            habits[habit_statistics_and_edit_x].update()
                            viewModel.setStatus(AppStatus.HABIT_STATISTICS)
                        }
                    )
                }
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(UI_dark_color)
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(verticalScroll)
                        .padding(spaceXY),
                    verticalArrangement = Arrangement.spacedBy(spaceY)
                ) {
                    //nameOfHabit
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(spaceX),
                    ) {
                        Text(
                            text = "$ts_Title: ",
                            fontSize = 16.sp,
                            color = textSeeUiColor
                        )
                        OutlinedTextField(
                            value = nameOfHabit,
                            onValueChange = { nameOfHabit = it },
                            label = {
                                Text(
                                    "$ts_Old: ${habits[habit_statistics_and_edit_x].nameOfHabit}",
                                    fontSize = 12.sp,
                                    color = textNoSeeColor
                                )
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            singleLine = true,
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                color = textSeeUiColor
                            ),
                            shape = RoundedCornerShape(15.dp),
                            colors = TextFieldDefaults.colors(
                                focusedTextColor = textSeeUiColor,
                                unfocusedTextColor = textNoSeeColor,
                                disabledTextColor = textNoSeeColor,
                                focusedContainerColor = UI_extra_dark_color,
                                unfocusedContainerColor = UI_extra_dark_color,
                                disabledContainerColor = UI_extra_dark_color,
                                cursorColor = textSeeUiColor,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent
                            ),
                        )
                    }
                    //colors
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(spaceX),
                    ) {
                        Text(
                            text = "$ts_Color: ",
                            fontSize = 16.sp,
                            color = textSeeUiColor,
                        )
                        Column {
                            Button(
                                onClick = { expanded0 = true },
                                colors = ButtonColors(
                                    containerColor = UI_extra_dark_color,
                                    contentColor = textSeeUiColor,
                                    disabledContainerColor = UI_extra_dark_color,
                                    disabledContentColor = textNoSeeColor
                                )
                            ) {
                                Text(
                                    "$ts_type: ${typeOfColorHabits.name}",
                                    fontSize = 16.sp,
                                    color = textSeeUiColor,
                                )
                            }
                            DropdownMenu(
                                expanded = expanded0,
                                onDismissRequest = { expanded0 = false },
                                modifier = Modifier.background(UI_extra_dark_color)
                            ) {
                                TypeOfColorHabits.entries.forEach { mode ->
                                    DropdownMenuItem(
                                        onClick = {
                                            typeOfColorHabits = mode
                                            expanded0 = false
                                        },
                                        text = {
                                            Text(
                                                text = mode.name,
                                                fontSize = 16.sp,
                                                color = textNoSeeColor
                                            )
                                        }
                                    )
                                }
                            }
                        }
                        ColorPickerBox(
                            initialColor = colorGood,
                            onColorSelected = { colorGood = it }
                        )
                    }
                    //Goal
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(UI_color, RoundedCornerShape(20.dp))
                            .height(48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = ts_Goal,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = textSeeUiColor,
                        )
                    }
                    Column(
                        modifier = Modifier
                            .horizontalScroll(horizontalScroll),
                        verticalArrangement = Arrangement.spacedBy(spaceY)
                    ) {
                        //type
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(spaceX),
                        ) {
                            Text(
                                text = "$ts_Type: ",
                                fontSize = 16.sp,
                                color = textSeeUiColor,
                            )
                            Column {
                                Button(
                                    onClick = { expanded1 = true },
                                    colors = ButtonColors(
                                        containerColor = UI_extra_dark_color,
                                        contentColor = textSeeUiColor,
                                        disabledContainerColor = UI_extra_dark_color,
                                        disabledContentColor = textNoSeeColor
                                    )
                                ) {
                                    Text(
                                        "$ts_type: ${typeOfGoalHabits.name}",
                                        fontSize = 16.sp,
                                        color = textSeeUiColor,
                                    )
                                }
                                DropdownMenu(
                                    expanded = expanded1,
                                    onDismissRequest = { expanded1 = false },
                                    modifier = Modifier.background(UI_extra_dark_color)
                                ) {
                                    TypeOfGoalHabits.entries.forEach { mode ->
                                        DropdownMenuItem(
                                            onClick = {
                                                typeOfGoalHabits = mode
                                                expanded1 = false
                                            },
                                            text = {
                                                Text(
                                                    text = mode.name,
                                                    fontSize = 16.sp,
                                                    color = textNoSeeColor
                                                )
                                            }
                                        )
                                    }
                                }
                            }
                        }
                        //count
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(spaceX),
                        ) {
                            Text(
                                text = "$ts_Count: ",
                                fontSize = 16.sp,
                                color = textSeeUiColor,
                            )
                            OutlinedTextField(
                                value = needGoal,
                                onValueChange = { needGoal = it },
                                label = {
                                    Text(
                                        "$ts_Old: ${habits[habit_statistics_and_edit_x].needGoal}",
                                        fontSize = 12.sp,
                                        color = textNoSeeColor
                                    )
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                singleLine = true,
                                textStyle = TextStyle(
                                    fontSize = 16.sp,
                                    color = textSeeUiColor
                                ),
                                shape = RoundedCornerShape(15.dp),
                                colors = TextFieldDefaults.colors(
                                    focusedTextColor = textSeeUiColor,
                                    unfocusedTextColor = textNoSeeColor,
                                    disabledTextColor = textNoSeeColor,
                                    focusedContainerColor = UI_extra_dark_color,
                                    unfocusedContainerColor = UI_extra_dark_color,
                                    disabledContainerColor = UI_extra_dark_color,
                                    cursorColor = textSeeUiColor,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent
                                ),
                            )
                            OutlinedTextField(
                                value = nameOfUnitsOfDimension,
                                onValueChange = { nameOfUnitsOfDimension = it },
                                label = {
                                    Text(
                                        "$ts_Old: ${habits[habit_statistics_and_edit_x].nameOfUnitsOfDimension}",
                                        fontSize = 12.sp,
                                        color = textNoSeeColor
                                    )
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                singleLine = true,
                                textStyle = TextStyle(
                                    fontSize = 16.sp,
                                    color = textSeeUiColor
                                ),
                                shape = RoundedCornerShape(15.dp),
                                colors = TextFieldDefaults.colors(
                                    focusedTextColor = textSeeUiColor,
                                    unfocusedTextColor = textNoSeeColor,
                                    disabledTextColor = textNoSeeColor,
                                    focusedContainerColor = UI_extra_dark_color,
                                    unfocusedContainerColor = UI_extra_dark_color,
                                    disabledContainerColor = UI_extra_dark_color,
                                    cursorColor = textSeeUiColor,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent
                                ),
                            )
                        }
                        //period
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(spaceX),
                        ) {
                            Text(
                                text = "$ts_Period: ",
                                fontSize = 16.sp,
                                color = textSeeUiColor,
                            )
                            OutlinedTextField(
                                value = needDays,
                                onValueChange = { needDays = it },
                                label = {
                                    Text(
                                        "$ts_Old: ${habits[habit_statistics_and_edit_x].needDays}",
                                        fontSize = 12.sp,
                                        color = textNoSeeColor
                                    )
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                singleLine = true,
                                textStyle = TextStyle(
                                    fontSize = 16.sp,
                                    color = textSeeUiColor
                                ),
                                shape = RoundedCornerShape(15.dp),
                                colors = TextFieldDefaults.colors(
                                    focusedTextColor = textSeeUiColor,
                                    unfocusedTextColor = textNoSeeColor,
                                    disabledTextColor = textNoSeeColor,
                                    focusedContainerColor = UI_extra_dark_color,
                                    unfocusedContainerColor = UI_extra_dark_color,
                                    disabledContainerColor = UI_extra_dark_color,
                                    cursorColor = textSeeUiColor,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent
                                ),
                            )
                            Text(
                                text = " $ts_days",
                                fontSize = 16.sp,
                                color = textSeeUiColor,
                            )
                        }
                    }

                    //Level
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(UI_color, RoundedCornerShape(20.dp))
                            .height(48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = ts_Level,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = textSeeUiColor,
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(spaceY)
                    ) {

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = changeLevel,
                                onCheckedChange = { changeLevel = it },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = textNoSeeColor,
                                    uncheckedColor = textNoSeeColor,
                                    checkmarkColor = textSeeUiColor
                                )
                            )
                            Text(
                                text = if (changeLevel) ts_Change_level else ts_No_change_level,
                                fontSize = 16.sp,
                                color = textSeeUiColor
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = changeNeedGoalWithLevel,
                                onCheckedChange = { changeNeedGoalWithLevel = it },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = textNoSeeColor,
                                    uncheckedColor = textNoSeeColor,
                                    checkmarkColor = textSeeUiColor
                                )
                            )
                            Text(
                                text = if (changeNeedGoalWithLevel) ts_Change_goal_with_level else ts_No_change_goal_with_level,
                                fontSize = 16.sp,
                                color = textSeeUiColor
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = changeNeedDaysWithLevel,
                                onCheckedChange = { changeNeedDaysWithLevel = it },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = textNoSeeColor,
                                    uncheckedColor = textNoSeeColor,
                                    checkmarkColor = textSeeUiColor
                                )
                            )
                            Text(
                                text = if (changeNeedDaysWithLevel) ts_Change_period_for_goal_with_level else ts_No_change_period_for_goal_with_level,
                                fontSize = 16.sp,
                                color = textSeeUiColor
                            )
                        }
                    }

                    //Delete
                    DeleteHabitConfirm(habit_statistics_and_edit_x) {
                        habits.removeAt(habit_statistics_and_edit_x)
                        habit_statistics_and_edit_x = 0
                        viewModel.setStatus(AppStatus.TABLE)
                    }
                }
            }
        }
    }
}