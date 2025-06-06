package fireforestsoul.levelupsoul

import java.io.File

val file = File(save_file_name)

actual fun saveValue() {
    file.writeText(app_version.toString())
}

actual fun loadValue() {
    val oldAppVersion: Long = if (file.exists()) file.readText().toLong() else app_version
//    if (oldAppVersion) {
//        loading old type
//    }
}
