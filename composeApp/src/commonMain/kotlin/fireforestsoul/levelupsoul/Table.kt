package fireforestsoul.levelupsoul

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontFamily
import org.jetbrains.compose.resources.painterResource
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.*
import kotlin.math.min

var UI_color = Color(40, 40, 40, 255)

@Composable
fun TableContent() {
    Box(
        modifier = Modifier
            .background(UI_color)
            .fillMaxSize()
    )
    Scaffold(
        modifier = Modifier
            .padding(WindowInsets.systemBars.asPaddingValues()),
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(UI_color)
            ) {
                Row(
                    modifier = Modifier
                        .height(48.dp)
                        .fillMaxWidth()
                        .padding(10.dp, 0.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Привычки",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Default,
                        color = Color(200, 200, 200),
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = { println("Export") }) {
                        Image(
                            painter = painterResource(Res.drawable.export),
                            contentDescription = "Export habits",
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .size(28.dp),
                        )
                    }
                    IconButton(onClick = { println("Import") }) {
                        Image(
                            painter = painterResource(Res.drawable.import),
                            contentDescription = "Import habits",
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .size(28.dp),
                        )
                    }
                    IconButton(onClick = { println("Add habit") }) {
                        Image(
                            painter = painterResource(Res.drawable.add_habit),
                            contentDescription = "Create habits",
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .size(28.dp),
                        )
                    }
                    IconButton(onClick = { println("Calendar") }) {
                        Image(
                            painter = painterResource(Res.drawable.calendar),
                            contentDescription = "Open calendar",
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
                    .background(UI_color)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { println("Habits") }) {
                        Image(
                            painter = painterResource(Res.drawable.habits),
                            contentDescription = "Habits",
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .size(28.dp),
                        )
                    }
                    IconButton(onClick = { println("Groups") }) {
                        Image(
                            painter = painterResource(Res.drawable.groups_of_habits),
                            contentDescription = "Groups of habits",
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .size(28.dp),
                        )
                    }
                    IconButton(onClick = { println("Soul") }) {
                        Image(
                            painter = painterResource(Res.drawable.soul_stat),
                            contentDescription = "Soul Stats",
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .size(28.dp),
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        val firstCellSizeX = 200.dp
        val firstCellSizeY = 40.dp
        val nextCellSizeX = firstCellSizeY
        val nextCellSizeY = firstCellSizeY
        val textNoSeeColor = Color(100, 100, 100, 255)
        val textSeeUiColor = Color(200, 200, 200, 255)
        val spacedCell = 3.dp
        val sizeOfBorder = 1.dp
        val roundedBorder = 7.5.dp
        val firstSellFontSize = 17.sp
        val firstSellSmallFontSize = 9.sp
        val verticalScroll = rememberScrollState()
        val horizontalScroll = rememberScrollState()
        val startSellY = verticalScroll.value / nextCellSizeY.value.toInt()
        val startSellX = verticalScroll.value / nextCellSizeY.value.toInt()

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(25, 25, 25))
                .verticalScroll(verticalScroll)
        ) {
            val maxCellX = (maxWidth / nextCellSizeX).toInt()
            val maxCellY = (maxHeight / nextCellSizeY).toInt()

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(spacedCell),
                modifier = Modifier.width(firstCellSizeX)
            ) {
                Box(
                    modifier = Modifier.size(firstCellSizeX, firstCellSizeY),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Habits",
                        color = textNoSeeColor,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = firstSellFontSize
                    )
                }
                for (y in startSellY until min(habits.size, maxCellY)) {
                    Box(
                        modifier = Modifier
                            .size(firstCellSizeX, firstCellSizeY)
                            .border(sizeOfBorder, color = textNoSeeColor, shape = RoundedCornerShape(roundedBorder)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = habits[y].nameOfHabit,
                                color = textSeeUiColor,
                                fontWeight = FontWeight.Normal,
                                fontSize = firstSellFontSize,
                            )
                            val needOrCanMore = habits[y].needGoal - habits[y].habitDay[habits[y].habitDay.size - 1].totalOfAPeriod
                            Text(
                                text = if (habits[y].typeOfGoalHabits == TypeOfGoalHabits.NOT_LITTLE)
                                    "Need $needOrCanMore more"
                                else "You can have $needOrCanMore more",
                                color = textNoSeeColor,
                                fontWeight = FontWeight.Normal,
                                fontSize = firstSellSmallFontSize,
                            )
                        }
                    }
                }
            }
        }
    }
}