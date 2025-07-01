package fireforestsoul.levelupsoul

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.max

@Composable
fun SoulStatisticsContent() {
    val verticalScroll = rememberScrollState()
    val spaceCell = 8.dp
    val seeColor = seeColorByIndex(habit_statistics_and_edit_x)
    val noSeeColor = noSeeColorByIndex(habit_statistics_and_edit_x)
    var maxDays = 0
    for (habit in habits) {
        maxDays = max(habit.habitDay.size, maxDays)
    }

    Box(
        modifier = Modifier
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
            var progressPeriod by remember { mutableStateOf(maxDays.toString()) }
            var progressPeriodSetting by remember { mutableStateOf(maxDays) }

            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                var soulName by remember { mutableStateOf(soul_name) }

                Text(
                    text = "Soul:",
                    fontSize = 16.sp,
                    color = textSeeUiColor
                )
                OutlinedTextField(
                    value = soulName,
                    onValueChange = {
                        soulName = it
                        soul_name = soulName
                    },
                    label = {
                        Text(
                            "Mr(s)",
                            fontSize = 12.sp,
                            color = textNoSeeColor
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
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
                        focusedContainerColor = Color(20, 20, 20),
                        unfocusedContainerColor = Color(20, 20, 20),
                        disabledContainerColor = Color(20, 20, 20),
                        cursorColor = textSeeUiColor,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.height(55.dp)
                )
            }
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
                        focusedContainerColor = Color(20, 20, 20),
                        unfocusedContainerColor = Color(20, 20, 20),
                        disabledContainerColor = Color(20, 20, 20),
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
    }
}