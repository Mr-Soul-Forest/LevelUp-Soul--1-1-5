package fireforestsoul.levelupsoul

import androidx.compose.runtime.*
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(viewModel: AppViewModel) {
    val appStatus by viewModel.appStatus.collectAsState()

    when (appStatus) {
        AppStatus.LOADING -> LoadingContent()
        AppStatus.TABLE -> TableContent()
        else -> LoadingContent()
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1)
            if (appStatus == AppStatus.LOADING) {
                loading(viewModel)
            }
        }
    }

}