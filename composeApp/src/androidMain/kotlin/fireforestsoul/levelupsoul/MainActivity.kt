package fireforestsoul.levelupsoul

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.remember
import androidx.activity.compose.BackHandler

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        initStorage(applicationContext)

        setContent {
            val viewModel = remember { AppViewModel() }
            App(viewModel)
        }
    }

    override fun onStop() {
        super.onStop()
        saveValue()
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    val viewModel = remember { AppViewModel() }
    App(viewModel)

    BackHandler(
        enabled = true,
        onBack = {
            when (viewModel.appStatus.value) {
                AppStatus.TABLE -> {
                    saveValue()
                    viewModel.setStatus(AppStatus.LOADING)
                }
                AppStatus.EDIT_HABIT -> {
                    viewModel.setStatus(AppStatus.HABIT_STATISTICS)
                }
                else -> {
                    viewModel.setStatus(AppStatus.TABLE)
                }
            }
        }
    )
}