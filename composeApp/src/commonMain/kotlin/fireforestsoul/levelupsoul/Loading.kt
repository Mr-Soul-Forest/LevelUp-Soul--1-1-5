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
import java.io.File

fun saveValueDesktop() {
    val file = File("LevelUp-Souls.FireForestSouls-saving")
    file.writeText(app_version.toString())
}

fun loadValueDesktop() {
    val file = File("LevelUp-Souls.FireForestSouls-saving")
    val oldAppVersion: Long = if (file.exists()) file.readText().toLong() else app_version
//    if (old_app_version) {
//        loading old type
//    }
}

@Composable
fun Loading() {
    if (isDesktop()) {
        saveValueDesktop()
        loadValueDesktop()
    }

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
