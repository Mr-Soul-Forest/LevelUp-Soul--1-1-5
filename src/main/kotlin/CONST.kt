enum class AppStatus(val n: Int) {
    LOADING(0),
    TABLE(1),
    CREATE_HABIT(2),
    HABIT_STATISTICS(3)
}

var app_status: AppStatus = AppStatus.LOADING

//alpha(000) beta(001) version(010)
//t.x.x.x.x
const val app_version: Long = 0 //000.000.000.000.000