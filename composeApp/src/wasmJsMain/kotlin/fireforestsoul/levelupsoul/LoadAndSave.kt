package fireforestsoul.levelupsoul

import kotlinx.browser.localStorage

actual fun saveValue() {
    localStorage.setItem("app_version", app_version.toString())
}

actual fun loadValue() {
    val oldAppVersion = localStorage.getItem("app_version")?.toLongOrNull()
//    if (oldAppVersion) {
//        loading old type
//    }
}