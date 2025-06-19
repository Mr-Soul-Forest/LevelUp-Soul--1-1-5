package fireforestsoul.levelupsoul

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus

var set_habit_day_today_x = 0
var set_habit_day_today_y = 0

@Composable
fun SetHabitDayToday(viewModel: AppViewModel) {
    var inputText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .background(color = UI_color, shape = RoundedCornerShape(20.dp))
                .padding(10.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val dateToSet = habits[set_habit_day_today_y].startDate.plus(
                    set_habit_day_today_x, DateTimeUnit.DAY
                )
                Text(
                    text = "Do you want to set a value for ${dateToSet.month} ${dateToSet.dayOfMonth}, ${dateToSet.year} for habit ${habits[set_habit_day_today_y].nameOfHabit}?",
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = textSeeUiColor
                )
                TextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    label = { Text(
                        "Old: ${habits[set_habit_day_today_y].habitDay[set_habit_day_today_x].today}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = textNoSeeColor
                    ) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = textSeeUiColor
                    ),
                    shape = RoundedCornerShape(15.dp),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = textSeeUiColor,
                        unfocusedTextColor = textNoSeeColor,
                        disabledTextColor = textNoSeeColor,
                        focusedContainerColor = UI_dark_color,
                        unfocusedContainerColor = UI_dark_color,
                        disabledContainerColor = UI_dark_color,
                        cursorColor = textSeeUiColor,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "❌ Cancel",
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = Color(200,150,150),
                        modifier = Modifier.clickable {
                            viewModel.setStatus(AppStatus.TABLE)
                        }
                    )
                    Spacer(Modifier.width(50.dp))
                    Text(
                        text = "✅ Confirm",
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = Color(150,200,150),
                        modifier = Modifier.clickable {
                            val value = inputText.toDoubleOrNull()
                            if (value != null) {
                                habits[set_habit_day_today_y].habitDay[set_habit_day_today_x].today = value
                                habits[set_habit_day_today_y].update()
                            }
                            viewModel.setStatus(AppStatus.TABLE)
                        }
                    )
                }
            }
        }
    }
}