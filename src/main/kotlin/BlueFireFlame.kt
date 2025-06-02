import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun getBlueFireColor(): List<Color> {
    val colored = List(3) {
        Color(130, 177, 255)
        Color(68, 138, 255)
        Color(41, 121, 255)
    }
    return colored
}

@Composable
fun BlueFireFlame() {
    val infiniteTransition = rememberInfiniteTransition()

    val scale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        )
    )

    Canvas(modifier = Modifier.size(60.dp)) {
        val center = Offset(size.width / 2, size.height / 2)

        drawCircle(
            color = getBlueFireColor()[0].copy(alpha = alpha * 0.3f),
            radius = size.minDimension / 2 * scale * 1.5f,
            center = center
        )

        drawCircle(
            color = getBlueFireColor()[1].copy(alpha = alpha),
            radius = size.minDimension / 2 * scale,
            center = center
        )

        drawCircle(
            color = getBlueFireColor()[2],
            radius = size.minDimension / 4 * scale,
            center = center
        )
    }
}
