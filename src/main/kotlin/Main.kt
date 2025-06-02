import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

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
        color = Color(255, 255, 255)
    )
}

@Composable
fun Loading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(25, 25, 25, 255)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            BlueFireFlame()
            Spacer(modifier = Modifier.height(40.dp)) // отступ
            LoadingTextAnimation()
        }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "LevelUp Soul"
    ) {
        if (app_status == AppStatus.LOADING) {
            Loading()
        }
    }
}
