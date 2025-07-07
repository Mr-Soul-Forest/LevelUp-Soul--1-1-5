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

var countFilesLoad = 0

fun loading(viewModel: AppViewModel) {
    if (countFilesLoad == 1) {
        loadValue()
    }
    else if (countFilesLoad > 1 && countFilesLoad - 2 < habits.size) {
        habits[countFilesLoad - 2].updateDate()
    }
    else if (countFilesLoad != 0) {
        changeLanguage()
        viewModel.setStatus(AppStatus.TABLE)
    }

    countFilesLoad++
}
