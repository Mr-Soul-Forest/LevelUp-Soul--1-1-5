package fireforestsoul.levelupsoul

import androidx.compose.runtime.*
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    if (app_status == AppStatus.LOADING) {
        LoadingContent()
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1)
            if (app_status == AppStatus.LOADING) {
                loading()
            }
        }
    }

}