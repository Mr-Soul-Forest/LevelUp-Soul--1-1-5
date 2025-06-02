import androidx.compose.animation.core.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun LoadingTextAnimation() {
    val infiniteTransition = rememberInfiniteTransition()

    val ellipsis by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = 6.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Text(
        text =
            if (ellipsis < 4.0f) "Fire Forest Souls представляет" + ".".repeat(ellipsis.toInt())
            else "Fire Forest Souls представляет" + " ".repeat(6 - ellipsis.toInt()),
        fontSize = 10.sp,
        fontWeight = FontWeight.Thin,
        fontFamily = FontFamily.Monospace,
        color = averageColor(getBlueFireColor())
    )
}
