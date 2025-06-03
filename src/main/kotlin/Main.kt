import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

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
