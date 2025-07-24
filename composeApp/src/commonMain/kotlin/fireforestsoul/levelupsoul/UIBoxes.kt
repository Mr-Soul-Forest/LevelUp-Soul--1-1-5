package fireforestsoul.levelupsoul

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.times
import com.ionspin.kotlin.bignum.decimal.toBigDecimal
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.isoDayNumber
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
                    ts_Color_picker,
                    fontSize = 16.sp,
                    color = textSeeUiColor
                )
            },
            text = {
                Column {
                    Text(
                        "$ts_Red: $red",
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
                        "$ts_Green: $green",
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
                        "$ts_Blue: $blue",
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
                        ts_OK,
                        fontSize = 16.sp,
                        color = Color(150, 200, 150)
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(
                        ts_Cancel,
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
            .height(48.dp)
            .clickable { showDialog = true },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "\uD83D\uDDD1 $ts_Delete",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = textSeeUiColor
        )
    }

    if (showDialog) {
        AlertDialog(
            containerColor = Color.Black,
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    text = "\uD83D\uDDD1 $ts_Delete_habit_confirm",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = textSeeUiColor,
                )
            },
            text = {
                Text(
                    text = "$ts_Are_you_sure_you_want_to_remove_the <${habits[index].nameOfHabit}>",
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
                        "\uD83D\uDDD1 $ts_Delete",
                        fontSize = 16.sp,
                        color = Color(200, 150, 150)
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(
                        "❌ $ts_Cancel",
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
            contentDescription = ts_Open_calendar,
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
                    ts_Date_selection,
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
                                    ts_Day,
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
                                    ts_Month,
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
                                    ts_Year,
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
                        text = "✅ $ts_Apply",
                        fontSize = 16.sp,
                        color = Color(150, 200, 150),
                    )
                }
            },
            dismissButton = {
                Row(
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
                            "❌ $ts_Cancel",
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
                            "\uD83D\uDCC5 $ts_Today",
                            fontSize = 16.sp,
                            color = Color(200, 150, 150),
                        )
                    }
                }
            }
        )
    }
}

@Composable
fun DonutChart(
    values: List<Float>,
    colors: List<Color>,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 40.dp // ширина "кольца"
) {
    val total = values.sum()
    var startAngle = -90f // начать сверху

    Canvas(modifier = modifier) {
        values.forEachIndexed { i, value ->
            val sweepAngle = (value / total) * 360f
            drawArc(
                color = colors.getOrElse(i) { Color.Gray },
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx())
            )
            startAngle += sweepAngle
        }
    }
}

@Composable
fun PPSInfoDialog() {
    var showDialog by remember { mutableStateOf(false) }

    Text(
        text = "\uD83D\uDEC8 $ts_PPS: ",
        fontSize = 16.sp,
        color = textSeeUiColor,
        modifier = Modifier.clickable { showDialog = true }
    )

    if (showDialog) {
        AlertDialog(
            containerColor = UI_dark_color,
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    "$ts_PPS $ts_Info",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = textSeeUiColor
                )
            },
            text = {
                Text(
                    ts_PPS_means_Progress_Period_Settings_By_default_progress_is_the_,
                    fontSize = 16.sp,
                    color = textSeeUiColor
                )
            },
            confirmButton = {
                Button(
                    onClick = { showDialog = false },
                    colors = ButtonColors(
                        containerColor = UI_color,
                        contentColor = textSeeUiColor,
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
fun AnimatedLineChart(
    data: List<Float>,
    yMax: Float,
    ySteps: Int = 5,
    lineAndDotColor: Color = Color(0xFF3F51B5),
    modifier: Modifier = Modifier
) {
    val animatedProgress = remember { Animatable(0f) }

    LaunchedEffect(data) {
        animatedProgress.snapTo(0f)
        animatedProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing)
        )
    }

    val backgroundColor = Color(30, 30, 30)
    val gridColor = textSeeUiColor.copy(alpha = 0.15f)

    BoxWithConstraints(
        modifier = modifier
            .background(backgroundColor, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        val chartWidth = constraints.maxWidth.toFloat()
        val chartHeight = constraints.maxHeight.toFloat()

        val spacing = chartWidth / (data.size).coerceAtLeast(1)

        val points = data.mapIndexed { index, value ->
            Offset(
                x = index * spacing + spacing / 2,
                y = chartHeight * (1 - (value / yMax))
            )
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                for (i in 0..ySteps) {
                    val y = chartHeight * (i.toFloat() / ySteps)
                    drawLine(
                        color = gridColor,
                        start = Offset(0f, y),
                        end = Offset(chartWidth, y),
                        strokeWidth = 1f,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(4f, 4f))
                    )
                }

                if (points.size >= 2) {
                    for (i in 0 until points.size - 1) {
                        val start = points[i]
                        val end = points[i + 1]
                        drawLine(
                            color = lineAndDotColor,
                            start = start,
                            end = Offset(
                                x = start.x + (end.x - start.x) * animatedProgress.value,
                                y = start.y + (end.y - start.y) * animatedProgress.value
                            ),
                            strokeWidth = 2.dp.toPx()
                        )
                    }
                }

                points.forEach {
                    drawCircle(
                        color = lineAndDotColor,
                        center = it,
                        radius = 4.dp.toPx()
                    )
                }
            }
        }
    }
}

@Composable
fun AnimatedBarChart(
    data: List<BigDecimal>,
    labels: List<String>,
    modifier: Modifier = Modifier,
    maxY: BigDecimal = data.maxOrNull() ?: 1.toBigDecimal(),
    barColor: Color = Color(0xFF4CAF50)
) {
    val scrollState = rememberScrollState()
    val barWidth = 16.dp
    val spacing = 12.dp

    BoxWithConstraints(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color(30, 30, 30))
            .horizontalScroll(scrollState)
    ) {
        val totalHeight = maxHeight
        val barMaxHeight = totalHeight - 40.dp

        LaunchedEffect(Unit) {
            scrollState.scrollTo(scrollState.maxValue)
        }

        Row(verticalAlignment = Alignment.Bottom) {
            data.forEachIndexed { index, value ->
                val animatedHeight by animateDpAsState(
                    targetValue = (barMaxHeight * value.toString().toFloat() / maxY.toString().toFloat()).coerceAtLeast(1.dp),
                    animationSpec = tween(durationMillis = 600),
                    label = "barHeight"
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier
                        .padding(horizontal = spacing / 2)
                        .height(totalHeight)
                ) {
                    Text(
                        text = "$value",
                        fontSize = 11.sp,
                        color = textSeeUiColor,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Box(
                        modifier = Modifier
                            .height(animatedHeight)
                            .width(barWidth)
                            .clip(RoundedCornerShape(4.dp))
                            .background(barColor)
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = labels.getOrNull(index) ?: "",
                        fontSize = 11.sp,
                        color = textNoSeeColor,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Composable
fun HabitGrid(
    values: List<Int>,
    states: List<Boolean>,
    trueColor: Color,
    falseColor: Color,
    startDate: LocalDate,
    modifier: Modifier = Modifier
) {
    val backgroundColor = Color(30, 30, 30)
    val boxSize = 20.dp
    val space = 4.dp

    val horizontalScroll = rememberScrollState()

    val dayOfWeekOffset = (startDate.dayOfWeek.isoDayNumber + 6) % 7

    val paddedValues = List(dayOfWeekOffset) { null } + values.mapIndexed { i, v -> v to states[i] }

    val weeks = paddedValues.chunked(7)

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .padding(8.dp)
            .horizontalScroll(horizontalScroll)
    ) {
        LaunchedEffect(Unit) {
            horizontalScroll.scrollTo(horizontalScroll.maxValue)
        }

        Row(horizontalArrangement = Arrangement.spacedBy(space)) {
            for (col in weeks) {
                Column(verticalArrangement = Arrangement.spacedBy(space)) {
                    for (cell in col) {
                        if (cell == null) {
                            Spacer(modifier = Modifier.size(boxSize))
                        } else {
                            val (value, state) = cell
                            val color = if (state) trueColor else falseColor
                            Box(
                                modifier = Modifier
                                    .size(boxSize)
                                    .clip(RoundedCornerShape(5.dp))
                                    .background(color),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = value.toString(),
                                    color = if (trueColor.red * 255 + trueColor.green * 255 + trueColor.blue * 255 < 383) Color.White else Color.Black,
                                    fontSize = 10.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SoulGrid(
    maxDays: Int,
    states: List<BigDecimal> = listTodayAll(maxDays, 1),
    colorBest: Color,
    modifier: Modifier = Modifier
) {
    var oldestHabit = Habit()
    for (habit in habits) {
        if (habit.startDate.toEpochDays() < oldestHabit.startDate.toEpochDays()) oldestHabit = habit
    }
    val values: List<Int> = listDaysNumbers(oldestHabit)
    val startDate = oldestHabit.startDate

    val backgroundColor = Color(30, 30, 30)
    val boxSize = 20.dp
    val space = 4.dp
    val horizontalScroll = rememberScrollState()
    val dayOfWeekOffset = (startDate.dayOfWeek.isoDayNumber + 6) % 7
    val paddedValues = List(dayOfWeekOffset) { null } + values.mapIndexed { i, v -> v to states[i] }
    val weeks = paddedValues.chunked(7)

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .padding(8.dp)
            .horizontalScroll(horizontalScroll)
    ) {
        LaunchedEffect(Unit) {
            horizontalScroll.scrollTo(horizontalScroll.maxValue)
        }

        Row(horizontalArrangement = Arrangement.spacedBy(space)) {
            for (col in weeks) {
                Column(verticalArrangement = Arrangement.spacedBy(space)) {
                    for (cell in col) {
                        if (cell == null) {
                            Spacer(modifier = Modifier.size(boxSize))
                        } else {
                            val (value, state) = cell
                            val color = Color(
                                ((colorBest.red * state).saveDiv(if (habits.isNotEmpty()) habits.size else 1)).toString().toFloat(),
                                ((colorBest.green * state).saveDiv(if (habits.isNotEmpty()) habits.size else 1)).toString().toFloat(),
                                ((colorBest.blue * state).saveDiv(if (habits.isNotEmpty()) habits.size else 1)).toString().toFloat()
                            )
                            Box(
                                modifier = Modifier
                                    .size(boxSize)
                                    .clip(RoundedCornerShape(5.dp))
                                    .background(color),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = value.toString(),
                                    color = if (color.red * 255 + color.green * 255 + color.blue * 255 < 383) Color.White else Color.Black,
                                    fontSize = 10.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}