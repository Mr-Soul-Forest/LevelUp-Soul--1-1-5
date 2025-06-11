package fireforestsoul.levelupsoul

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
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

@Composable
fun TableContent() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(40, 40, 40, 255))
    ) {
        Row(
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth()
                .padding(10.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
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
}