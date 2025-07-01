package fireforestsoul.levelupsoul

import androidx.compose.ui.graphics.Color

enum class AppStatus() {
    LOADING,
    TABLE,
    CREATE_HABIT,
    HABIT_STATISTICS,
    EDIT_HABIT,
    TABLE_UPDATER,
    SOUL_STATISTICS
}

//alpha(000) beta(001) version(010)
//t.x.x.x
const val app_version: Long = 1000000000 //version(001).000.000.000

const val save_file_name = "LevelUp-Soul.FireForestSouls-saving"

var habits: MutableList<Habit> = mutableListOf(
    Habit()
)

var soul_color_type = TypeOfColorHabits.ADAPTIVE
var soul_color = Color(200,200,200)
var soul_name = "Mr. Soul Forest"