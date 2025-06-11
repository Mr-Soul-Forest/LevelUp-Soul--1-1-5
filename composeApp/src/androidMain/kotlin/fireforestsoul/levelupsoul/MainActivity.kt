package fireforestsoul.levelupsoul

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.remember

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
    App()
}