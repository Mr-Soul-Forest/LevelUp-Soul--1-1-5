package fireforestsoul.levelupsoul

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    Window(
        onCloseRequest = {
            saveValue()
            exitApplication()
        },
        title = "LevelUp-Soul",
        icon = painterResource("app_icon.png")
    ) {
        App()
    }
}