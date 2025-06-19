package fireforestsoul.levelupsoul

enum class AppStatus() {
    LOADING,
    TABLE,
    SET_HABIT_DAY_TODAY,
    CREATE_HABIT,
    HABIT_STATISTICS
}

//alpha(000) beta(001) version(010)
//t.x.x.x.x
const val app_version: Long = 2000000 //alpha(000).000.002.000.000

const val save_file_name = "LevelUp-Soul.FireForestSouls-saving"

var habits: MutableList<Habit> = mutableListOf(
    Habit()
)