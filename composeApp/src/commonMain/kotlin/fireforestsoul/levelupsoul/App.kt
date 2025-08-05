package fireforestsoul.levelupsoul

import androidx.compose.foundation.layout.Box
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

    val showMainMenu = when (appStatus) {
        AppStatus.TABLE,
        AppStatus.TABLE_UPDATER,
        AppStatus.HABITS_LIST_UPDATER,
        AppStatus.SOUL_STATISTICS,
        AppStatus.HABITS_LIST -> true

        else -> false
    }

    Box {
        when (appStatus) {
            AppStatus.LOADING -> LoadingContent()
            AppStatus.CREATE_HABIT -> CreateHabit(viewModel)
            AppStatus.HABIT_STATISTICS -> HabitStatistics(viewModel)
            AppStatus.EDIT_HABIT -> EditHabit(viewModel)
            else -> {
                if (appStatus == AppStatus.TABLE_UPDATER) {
                    LaunchedEffect(Unit) {
                        viewModel.setStatus(AppStatus.TABLE)
                    }
                }
                if (appStatus == AppStatus.HABITS_LIST_UPDATER) {
                    LaunchedEffect(Unit) {
                        viewModel.setStatus(AppStatus.HABITS_LIST)
                    }
                }
            }
        }
        if (showMainMenu) {
            MainMenuContent(
                viewModel,
                verticalScrollForTableContent,
                horizontalScrollForTableContent
            )
        }
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