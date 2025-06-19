package fireforestsoul.levelupsoul

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CreateHabit(viewModel: AppViewModel) {
    val verticalScroll = rememberScrollState()
    var habit = Habit()
    var nameOfHabit by remember { mutableStateOf("") }

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
                        fontFamily = FontFamily.Default,
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
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = Color(200, 150, 150),
                        modifier = Modifier.clickable {
                            viewModel.setStatus(AppStatus.TABLE)
                        }
                    )
                    Text(
                        text = "✅ Confirm",
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = Color(150, 200, 150),
                        modifier = Modifier.clickable {
                            habit.nameOfHabit = nameOfHabit
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
                    .verticalScroll(verticalScroll)
                    .background(UI_dark_color)
            ) {
                //NameOfHabit
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Title: ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.Default,
                        color = textSeeUiColor,
                        modifier = Modifier.padding(start = 15.dp)
                    )
                    TextField(
                        value = nameOfHabit,
                        onValueChange = { nameOfHabit = it },
                        label = { Text(
                            "Example: ${habit.nameOfHabit}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = textNoSeeColor
                        ) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
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
                }
            }
        }
    }
}