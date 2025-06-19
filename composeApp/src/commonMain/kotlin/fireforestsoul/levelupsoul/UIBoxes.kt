package fireforestsoul.levelupsoul

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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