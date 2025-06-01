import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(25, 25, 25, 255)) // тут цвет фона окна
    ) {
        // содержимое окна
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "LevelUp Soul"
    ) {
        App()
    }
}
