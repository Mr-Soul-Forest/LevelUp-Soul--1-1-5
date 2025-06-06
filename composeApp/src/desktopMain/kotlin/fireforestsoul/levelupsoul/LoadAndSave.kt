package fireforestsoul.levelupsoul

import java.io.File

actual fun saveValue() {
    val file = File(save_file_name)
    file.writeText(app_version.toString())
}

actual fun loadValue() {
    val file = File(save_file_name)
    val oldAppVersion: Long = if (file.exists()) file.readText().toLong() else app_version
//    if (oldAppVersion) {
//        loading old type
//    }
}
