package fireforestsoul.levelupsoul

import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalDensity
import kotlinx.coroutines.yield

import levelup_soul.composeapp.generated.resources.Res
import levelup_soul.composeapp.generated.resources.compose_multiplatform

@Composable
fun MyScreen() {
    LaunchedEffect(Unit) {
        // Подождать первый кадр
        yield() // даём Compose отрисовать первый кадр

        // Здесь уже можно делать что-то "после кадра"
        println("Первый кадр нарисован!")

        // Например, запуск анимации, логика и т.п.
    }

    // Всё остальное UI
}

@Composable
@Preview
fun App() {
    if (app_status == AppStatus.LOADING) {
        Loading()
    }
}