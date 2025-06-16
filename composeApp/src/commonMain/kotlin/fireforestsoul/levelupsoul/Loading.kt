package fireforestsoul.levelupsoul

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate

expect fun saveValue()
expect fun loadValue()

@Composable
fun LoadingContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(25, 25, 25, 255)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            BlueFireFlame()
            Spacer(modifier = Modifier.height(40.dp))
            LoadingTextAnimation()
        }
    }
}

private var countFilesLoad = 0

fun loading(viewModel: AppViewModel) {
    if (countFilesLoad == 1) {
        loadValue()
        habits[0].lastDate = habits[0].startDate
        habits[0].startDate = LocalDate(2025,6,5)
    }
    else if (countFilesLoad > 1 && countFilesLoad - 2 < habits.size) {
        habits[countFilesLoad - 2].updateDate()
    }
    else if (countFilesLoad != 0) {
        viewModel.setStatus(AppStatus.TABLE)
    }

    countFilesLoad++
}
