package fireforestsoul.levelupsoul

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class HabitX(
    val name: String,
    val level: Int,
    val progress: Int,
    val max: Int,
    val color: Color
)

@Composable
fun HabitTrackerScreen() {
    val habits = listOf(
        HabitX(name = "Exercise", level = 6, progress = 106, max = 200, color = Color(0xFFFF9800)),
        HabitX(name = "Read", level = 3, progress = 40, max = 100, color = Color(0xFFBC9B7D)),
        HabitX(name = "Meditate", level = 2, progress = 75, max = 150, color = Color(0xFF7EAF8F))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFF1C1C1E)),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        habits.forEach { HabitCard(it) }
    }
}

@Composable
fun HabitCard(habit: HabitX) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF2C2C2E), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(habit.name, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Level ${habit.level}", color = Color.LightGray, fontSize = 16.sp)
        }

        Spacer(Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(Color.DarkGray, RoundedCornerShape(4.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(habit.progress.toFloat() / habit.max)
                    .height(8.dp)
                    .background(habit.color, RoundedCornerShape(4.dp))
            )
        }

        Spacer(Modifier.height(4.dp))

        Text(
            "${habit.progress} / ${habit.max} XP",
            color = Color.Gray,
            fontSize = 14.sp
        )
    }
}