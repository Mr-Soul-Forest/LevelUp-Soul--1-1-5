package fireforestsoul.levelupsoul

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.painterResource

@Composable
fun ColorPickerBox(
    initialColor: Color = Color.Red,
    onColorSelected: (Color) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var currentColor by remember { mutableStateOf(initialColor) }

    Box(
        modifier = Modifier
            .size(40.dp)
            .background(currentColor, RoundedCornerShape(20.dp))
            .clickable { showDialog = true }
    )

    if (showDialog) {
        var red by remember { mutableStateOf((currentColor.red * 255).toInt()) }
        var green by remember { mutableStateOf((currentColor.green * 255).toInt()) }
        var blue by remember { mutableStateOf((currentColor.blue * 255).toInt()) }

        AlertDialog(
            containerColor = Color.Black,
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    "Color picker",
                    fontSize = 16.sp,
                    color = textSeeUiColor
                )
            },
            text = {
                Column {
                    Text(
                        "Red: $red",
                        fontSize = 16.sp,
                        color = textSeeUiColor
                    )
                    Slider(
                        value = red.toFloat(),
                        onValueChange = {
                            red = it.toInt()
                            currentColor = Color(red, green, blue)
                        },
                        valueRange = 0f..255f,
                        colors = SliderColors(
                            thumbColor = Color(red, green, blue),
                            activeTrackColor = Color(red, green, blue),
                            activeTickColor = Color(red, green, blue),
                            inactiveTrackColor = Color(red, green, blue),
                            inactiveTickColor = Color(red, green, blue),
                            disabledThumbColor = Color.Transparent,
                            disabledActiveTrackColor = Color.Transparent,
                            disabledActiveTickColor = Color.Transparent,
                            disabledInactiveTrackColor = Color.Transparent,
                            disabledInactiveTickColor = Color.Transparent
                        )
                    )
                    Text(
                        "Green: $green",
                        fontSize = 16.sp,
                        color = textSeeUiColor
                    )
                    Slider(
                        value = green.toFloat(),
                        onValueChange = {
                            green = it.toInt()
                            currentColor = Color(red, green, blue)
                        },
                        valueRange = 0f..255f,
                        colors = SliderColors(
                            thumbColor = Color(red, green, blue),
                            activeTrackColor = Color(red, green, blue),
                            activeTickColor = Color(red, green, blue),
                            inactiveTrackColor = Color(red, green, blue),
                            inactiveTickColor = Color(red, green, blue),
                            disabledThumbColor = Color.Transparent,
                            disabledActiveTrackColor = Color.Transparent,
                            disabledActiveTickColor = Color.Transparent,
                            disabledInactiveTrackColor = Color.Transparent,
                            disabledInactiveTickColor = Color.Transparent
                        )
                    )
                    Text(
                        "Blue: $blue",
                        fontSize = 16.sp,
                        color = textSeeUiColor
                    )
                    Slider(
                        value = blue.toFloat(),
                        onValueChange = {
                            blue = it.toInt()
                            currentColor = Color(red, green, blue)
                        },
                        valueRange = 0f..255f,
                        colors = SliderColors(
                            thumbColor = Color(red, green, blue),
                            activeTrackColor = Color(red, green, blue),
                            activeTickColor = Color(red, green, blue),
                            inactiveTrackColor = Color(red, green, blue),
                            inactiveTickColor = Color(red, green, blue),
                            disabledThumbColor = Color.Transparent,
                            disabledActiveTrackColor = Color.Transparent,
                            disabledActiveTickColor = Color.Transparent,
                            disabledInactiveTrackColor = Color.Transparent,
                            disabledInactiveTickColor = Color.Transparent
                        )
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    onColorSelected(currentColor)
                }) {
                    Text(
                        "OK",
                        fontSize = 16.sp,
                        color = Color(150, 200, 150)
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(
                        "Отмена",
                        fontSize = 16.sp,
                        color = Color(200, 150, 150)
                    )
                }
            }
        )
    }
}

@Composable
fun DeleteHabitConfirm(index: Int, onDeleteConfirmed: () -> Unit) {
    var showDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(200, 40, 40), RoundedCornerShape(20.dp))
            .height(48.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "\uD83D\uDDD1 Delete",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = textSeeUiColor,
            modifier = Modifier
                .clickable { showDialog = true }
        )
    }

    if (showDialog) {
        AlertDialog(
            containerColor = Color.Black,
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    text = "\uD83D\uDDD1 Delete habit confirm",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = textSeeUiColor,
                )
            },
            text = {
                Text(
                    text = "Are you sure you want to remove the <${habits[index].nameOfHabit}>",
                    fontSize = 16.sp,
                    color = textSeeUiColor,
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    onDeleteConfirmed()
                    showDialog = false
                }) {
                    Text(
                        "\uD83D\uDDD1 Delete",
                        fontSize = 16.sp,
                        color = Color(200, 150, 150)
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(
                        "❌ Cancel",
                        fontSize = 16.sp,
                        color = Color(150, 200, 150),
                    )
                }
            }
        )
    }
}

@Composable
fun DatePickerDialog(
    initialDate: LocalDate,
    viewModel: AppViewModel,
    onConfirm: (LocalDate) -> Unit
) {
    val appStatus by viewModel.appStatus.collectAsState()

    var showDialog by remember { mutableStateOf(false) }
    var day by remember { mutableStateOf(initialDate.dayOfMonth.toString()) }
    var month by remember { mutableStateOf(initialDate.monthNumber.toString()) }
    var year by remember { mutableStateOf(initialDate.year.toString()) }

    IconButton(onClick = {
        if (appStatus == AppStatus.TABLE) {
            showDialog = true
        }
    }) {
        Image(
            painter = painterResource(Res.drawable.calendar),
            contentDescription = "Open calendar",
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .size(28.dp)
        )
    }

    if (showDialog) {
        AlertDialog(
            containerColor = UI_dark_color,
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    "Date selection",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = textSeeUiColor
                )
            },
            text = {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        OutlinedTextField(
                            value = day,
                            onValueChange = { day = it },
                            label = {
                                Text(
                                    "Day",
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
                                focusedContainerColor = UI_color,
                                unfocusedContainerColor = UI_color,
                                disabledContainerColor = UI_color,
                                cursorColor = textSeeUiColor,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent
                            ),
                            modifier = Modifier.width(80.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        OutlinedTextField(
                            value = month,
                            onValueChange = { month = it },
                            label = {
                                Text(
                                    "Month",
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
                                focusedContainerColor = UI_color,
                                unfocusedContainerColor = UI_color,
                                disabledContainerColor = UI_color,
                                cursorColor = textSeeUiColor,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent
                            ),
                            modifier = Modifier.width(80.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        OutlinedTextField(
                            value = year,
                            onValueChange = { year = it },
                            label = {
                                Text(
                                    "Year",
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
                                focusedContainerColor = UI_color,
                                unfocusedContainerColor = UI_color,
                                disabledContainerColor = UI_color,
                                cursorColor = textSeeUiColor,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent
                            ),
                            modifier = Modifier.width(100.dp)
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        val d = day.toIntOrNull()
                        val m = month.toIntOrNull()
                        val y = year.toIntOrNull()
                        if (d != null && m != null && y != null) {
                            try {
                                onConfirm(LocalDate(y, m, d))
                            } catch (_: Exception) {
                                // можно добавить сообщение об ошибке
                            }
                        }
                    },
                    colors = ButtonColors(
                        containerColor = UI_color,
                        contentColor = textSeeUiColor,
                        disabledContainerColor = Color.Transparent,
                        disabledContentColor = Color.Transparent
                    )
                ) {
                    Text(
                        text = "✅ Apply",
                        fontSize = 16.sp,
                        color = Color(150, 200, 150),
                    )
                }
            },
            dismissButton = {
                Row (
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextButton(
                        onClick = { showDialog = false },
                        colors = ButtonColors(
                            containerColor = UI_color,
                            contentColor = textSeeUiColor,
                            disabledContainerColor = Color.Transparent,
                            disabledContentColor = Color.Transparent
                        )
                    ) {
                        Text(
                            "❌ Cancel",
                            fontSize = 16.sp,
                            color = Color(200, 150, 150),
                        )
                    }
                    TextButton(
                        onClick = {
                            showDialog = false
                            onConfirm(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date)
                        },
                        colors = ButtonColors(
                            containerColor = UI_color,
                            contentColor = textSeeUiColor,
                            disabledContainerColor = Color.Transparent,
                            disabledContentColor = Color.Transparent
                        )
                    ) {
                        Text(
                            "\uD83D\uDCC5 Today",
                            fontSize = 16.sp,
                            color = Color(200, 150, 150),
                        )
                    }
                }
            }
        )
    }
}