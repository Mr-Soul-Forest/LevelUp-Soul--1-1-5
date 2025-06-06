package fireforestsoul.levelupsoul

import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview

import levelup_soul.composeapp.generated.resources.Res
import levelup_soul.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    if (app_status == AppStatus.LOADING) {
        Loading()
    }
}