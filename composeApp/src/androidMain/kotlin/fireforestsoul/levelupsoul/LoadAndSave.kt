package fireforestsoul.levelupsoul

import android.content.Context
import android.content.SharedPreferences

private var context: Context? = null

fun initStorage(appContext: Context) {
    context = appContext
}

private val prefs: SharedPreferences
    get() = context!!.getSharedPreferences("LevelUp-Soul.FireForestSouls-saving", Context.MODE_PRIVATE)

actual fun saveValue() {
    prefs.edit().putLong("app_version", app_version).apply()
}

actual fun loadValue() {
    val oldAppVersion: Long = if (prefs.contains("app_version")) prefs.getLong("app_version", app_version) else app_version
}