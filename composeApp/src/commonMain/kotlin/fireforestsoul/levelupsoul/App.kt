package fireforestsoul.levelupsoul

import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.*
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(viewModel: AppViewModel) {
    val appStatus by viewModel.appStatus.collectAsState()

    val verticalScrollForTableContent = rememberScrollState()
    val horizontalScrollForTableContent = rememberScrollState()
    when (appStatus) {
        AppStatus.LOADING -> LoadingContent()
        AppStatus.TABLE -> MainMenuContent(viewModel, verticalScrollForTableContent, horizontalScrollForTableContent)
        AppStatus.CREATE_HABIT -> CreateHabit(viewModel)
        AppStatus.HABIT_STATISTICS -> HabitStatistics(viewModel)
        AppStatus.EDIT_HABIT -> EditHabit(viewModel)
        AppStatus.TABLE_UPDATER -> {
            viewModel.setStatus(AppStatus.TABLE)
            MainMenuContent(viewModel, verticalScrollForTableContent, horizontalScrollForTableContent)
        }
        AppStatus.SOUL_STATISTICS -> MainMenuContent(viewModel, verticalScrollForTableContent, horizontalScrollForTableContent)
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