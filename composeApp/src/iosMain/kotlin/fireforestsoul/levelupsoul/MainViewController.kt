package fireforestsoul.levelupsoul

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIApplicationDidEnterBackgroundNotification
import platform.Foundation.NSNotificationCenter
import androidx.compose.runtime.remember

fun setupSaveOnBackground() {
    NSNotificationCenter.defaultCenter.addObserverForName(
        name = UIApplicationDidEnterBackgroundNotification,
        `object` = null,
        queue = null
    ) { notification ->
        saveValue()
    }
}

fun MainViewController() = ComposeUIViewController {
    setupSaveOnBackground()
    val viewModel = remember { AppViewModel() }
    App(viewModel)
}