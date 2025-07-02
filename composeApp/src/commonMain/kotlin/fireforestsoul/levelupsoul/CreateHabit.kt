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

@Composable
fun CreateHabit(viewModel: AppViewModel) {
    val verticalScroll = rememberScrollState()
    val horizontalScroll = rememberScrollState()
    var expanded0 by remember { mutableStateOf(false) }
    var expanded1 by remember { mutableStateOf(false) }

    val habit = Habit()
    var nameOfHabit by remember { mutableStateOf("") }
    var typeOfColorHabits by remember { mutableStateOf(TypeOfColorHabits.ADAPTIVE) }
    var colorGood by remember { mutableStateOf(habit.colorGood) }
    var typeOfGoalHabits by remember { mutableStateOf(habit.typeOfGoalHabits) }
    var needGoal by remember { mutableStateOf("") }
    var nameOfUnitsOfDimension by remember { mutableStateOf("") }
    var needDays by remember { mutableStateOf("") }
    var changeLevel by remember { mutableStateOf(habit.changeLevel) }
    var changeNeedGoalWithLevel by remember { mutableStateOf(habit.changeNeedGoalWithLevel) }
    var changeNeedDaysWithLevel by remember { mutableStateOf(habit.changeNeedDaysWithLevel) }

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
                        text = "Create habit",
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
                        text = "❌ Cancel",
                        fontSize = 16.sp,
                        color = Color(200, 150, 150),
                        modifier = Modifier.clickable {
                            viewModel.setStatus(AppStatus.TABLE)
                        }
                    )
                    Text(
                        text = "✅ Confirm",
                        fontSize = 16.sp,
                        color = Color(150, 200, 150),
                        modifier = Modifier.clickable {
                            habit.nameOfHabit = nameOfHabit
                            habit.typeOfColorHabits = typeOfColorHabits
                            habit.colorGood = colorGood
                            habit.typeOfGoalHabits = typeOfGoalHabits
                            habit.needGoal =
                                (if (needGoal.toDoubleOrNull() != null) needGoal.toDoubleOrNull() else habit.needGoal)!!
                            habit.nameOfUnitsOfDimension = nameOfUnitsOfDimension
                            habit.needDays =
                                (if (needDays.toIntOrNull() != null) needDays.toIntOrNull() else habit.needDays)!!
                            habit.changeLevel = changeLevel
                            habit.changeNeedGoalWithLevel = changeNeedGoalWithLevel
                            habit.changeNeedDaysWithLevel = changeNeedDaysWithLevel
                            habit.update()
                            habits.add(habit)
                            viewModel.setStatus(AppStatus.TABLE)
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
                            text = "Title: ",
                            fontSize = 16.sp,
                            color = textSeeUiColor
                        )
                        OutlinedTextField(
                            value = nameOfHabit,
                            onValueChange = { nameOfHabit = it },
                            label = {
                                Text(
                                    "Example: ${habit.nameOfHabit}",
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
                                focusedContainerColor = Color.Black,
                                unfocusedContainerColor = Color.Black,
                                disabledContainerColor = Color.Black,
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
                            text = "Color: ",
                            fontSize = 16.sp,
                            color = textSeeUiColor,
                        )
                        Column {
                            Button(
                                onClick = { expanded0 = true },
                                colors = ButtonColors(
                                    containerColor = Color.Black,
                                    contentColor = textSeeUiColor,
                                    disabledContainerColor = Color.Black,
                                    disabledContentColor = textNoSeeColor
                                )
                            ) {
                                Text(
                                    "type: ${typeOfColorHabits.name}",
                                    fontSize = 16.sp,
                                    color = textSeeUiColor,
                                )
                            }
                            DropdownMenu(
                                expanded = expanded0,
                                onDismissRequest = { expanded0 = false },
                                modifier = Modifier.background(Color.Black)
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
                            text = "Goal",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
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
                                text = "Type: ",
                                fontSize = 16.sp,
                                color = textSeeUiColor,
                            )
                            Column {
                                Button(
                                    onClick = { expanded1 = true },
                                    colors = ButtonColors(
                                        containerColor = Color.Black,
                                        contentColor = textSeeUiColor,
                                        disabledContainerColor = Color.Black,
                                        disabledContentColor = textNoSeeColor
                                    )
                                ) {
                                    Text(
                                        "type: ${typeOfGoalHabits.name}",
                                        fontSize = 16.sp,
                                        color = textSeeUiColor,
                                    )
                                }
                                DropdownMenu(
                                    expanded = expanded1,
                                    onDismissRequest = { expanded1 = false },
                                    modifier = Modifier.background(Color.Black)
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
                                text = "Count: ",
                                fontSize = 16.sp,
                                color = textSeeUiColor,
                            )
                            OutlinedTextField(
                                value = needGoal,
                                onValueChange = { needGoal = it },
                                label = {
                                    Text(
                                        "Example: ${habit.needGoal}",
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
                                    focusedContainerColor = Color.Black,
                                    unfocusedContainerColor = Color.Black,
                                    disabledContainerColor = Color.Black,
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
                                        "Example: ${habit.nameOfUnitsOfDimension}",
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
                                    focusedContainerColor = Color.Black,
                                    unfocusedContainerColor = Color.Black,
                                    disabledContainerColor = Color.Black,
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
                                text = "Period: ",
                                fontSize = 16.sp,
                                color = textSeeUiColor,
                            )
                            OutlinedTextField(
                                value = needDays,
                                onValueChange = { needDays = it },
                                label = {
                                    Text(
                                        "Example: ${habit.needDays}",
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
                                    focusedContainerColor = Color.Black,
                                    unfocusedContainerColor = Color.Black,
                                    disabledContainerColor = Color.Black,
                                    cursorColor = textSeeUiColor,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent
                                ),
                            )
                            Text(
                                text = " days",
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
                            text = "Level",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = textSeeUiColor,
                        )
                    }
                    Column(
                        modifier = Modifier
                            .horizontalScroll(horizontalScroll),
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
                                text = if (changeLevel) "Change level" else "No change level",
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
                                text = if (changeNeedGoalWithLevel) "Change goal with level" else "No change goal with level",
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
                                text = if (changeNeedDaysWithLevel) "Change period for goal with level" else "No change period for goal with level",
                                fontSize = 16.sp,
                                color = textSeeUiColor
                            )
                        }
                    }
                }
            }
        }
    }
}