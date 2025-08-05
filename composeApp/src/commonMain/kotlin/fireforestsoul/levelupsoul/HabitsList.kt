package fireforestsoul.levelupsoul

import androidx.compose.foundation.ScrollState
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HabitsListContent(verticalScrollState: ScrollState, viewModel: AppViewModel) {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(UI_dark_color)
            .verticalScroll(verticalScrollState)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(13.4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.51.dp)
        ) {
            for (x in 0 until habits.size) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .height(101.15.dp)
                        .background(UI_color, RoundedCornerShape(13.03.dp))
                        .clickable {
                            habit_statistics_and_edit_x = x
                            viewModel.setStatus(AppStatus.HABIT_STATISTICS)
                        }
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize()
                            .padding(16.48.dp, 15.71.dp, 14.94.dp, 11.49.dp),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(12.26.dp)
                    ) {
                        Text(
                            text = habits[x].iconChar,
                            textAlign = TextAlign.Center,
                            color = seeColorByIndex(x),
                            modifier = Modifier.size(57.47.dp),
                            maxLines = 1,
                            fontSize = 45.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Black
                        )
                        Column(
                            verticalArrangement = Arrangement.spacedBy(7.66.dp),
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier.fillMaxSize()
                                .padding(top = 7.28.dp)
                        ) {
                            Text(
                                text = habits[x].nameOfHabit,
                                fontSize = 16.5.sp,
                                fontWeight = FontWeight.W700,
                                color = textSeeUiColor,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    }
}