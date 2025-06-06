package fireforestsoul.levelupsoul

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = {
            saveValue()
            exitApplication()
        },
        title = "LevelUp-Soul",
    ) {
        App()
    }
}