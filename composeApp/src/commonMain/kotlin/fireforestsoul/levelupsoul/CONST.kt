package fireforestsoul.levelupsoul

enum class AppStatus(val n: Int) {
    LOADING(0),
    TABLE(1),
    CREATE_HABIT(2),
    HABIT_STATISTICS(3)
}

//alpha(000) beta(001) version(010)
//t.x.x.x.x
const val app_version: Long = 1000000 //000.000.001.000.000

const val save_file_name = "LevelUp-Soul.FireForestSouls-saving"

var habits: MutableList<Habit> = mutableListOf(
    Habit(nameOfHabit = "First"),
    Habit(nameOfHabit = "Second"),
    Habit(),
    Habit(nameOfHabit = "1"),
    Habit(nameOfHabit = "2"),
    Habit(nameOfHabit = "3"),
    Habit(nameOfHabit = "4"),
    Habit(nameOfHabit = "5"),
    Habit(nameOfHabit = "6"),
    Habit(nameOfHabit = "7"),
    Habit(nameOfHabit = "8"))
