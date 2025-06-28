package fireforestsoul.levelupsoul

enum class AppStatus() {
    LOADING,
    TABLE,
    CREATE_HABIT,
    HABIT_STATISTICS,
    EDIT_HABIT,
    TABLE_UPDATER
}

//alpha(000) beta(001) version(010)
//t.x.x.x.x
const val app_version: Long = 3000000 //alpha(000).000.003.000.000

const val save_file_name = "LevelUp-Soul.FireForestSouls-saving"

var habits: MutableList<Habit> = mutableListOf(
    Habit()
)