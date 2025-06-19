package fireforestsoul.levelupsoul

import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(viewModel: AppViewModel) {
    val appStatus by viewModel.appStatus.collectAsState()

    when (appStatus) {
        AppStatus.LOADING -> LoadingContent()
        AppStatus.TABLE -> TableContent(viewModel)
        AppStatus.SET_HABIT_DAY_TODAY -> {
            TableContent(viewModel, 10.dp)
            SetHabitDayToday(viewModel)
        }
        AppStatus.CREATE_HABIT -> CreateHabit(viewModel)
        AppStatus.HABIT_STATISTICS -> HabitStatistics(viewModel)
        AppStatus.EDIT_HABIT -> EditHabit(viewModel)
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