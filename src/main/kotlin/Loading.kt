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

fun isDesktop(): Boolean {
    val os = System.getProperty("os.name").lowercase()
    return os.contains("win") || os.contains("mac") || os.contains("linux")
}
fun saveValue() {
    val file = File("LevelUp-Souls.FireForestSouls-saving")
    file.writeText(app_version.toString())
}

fun loadValue() {
    val file = File("LevelUp-Souls.FireForestSouls-saving")
    println(if (file.exists()) file.readText().toLong() else 0L)
}

@Composable
fun Loading() {

    if (isDesktop()) {
        saveValue()
        loadValue()
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
