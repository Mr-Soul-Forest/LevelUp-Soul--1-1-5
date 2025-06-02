enum class AppStatus(val n: Int) {
    LOADING(0),
    TABLE(1),
    CREATE_HABIT(2),
    HABIT_STATISTICS(3)
}

val app_status: AppStatus = AppStatus.LOADING