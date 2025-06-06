package fireforestsoul.levelupsoul

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIApplicationDidEnterBackgroundNotification
import platform.Foundation.NSNotificationCenter

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
    App()
}