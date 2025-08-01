package fireforestsoul.levelupsoul

import androidx.compose.ui.graphics.Color
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

enum class AppStatus {
    LOADING,
    TABLE,
    CREATE_HABIT,
    HABIT_STATISTICS,
    EDIT_HABIT,
    TABLE_UPDATER,
    SOUL_STATISTICS,
    HABITS_LIST
}

/**
 * Loading steps for everyone:
 *
 * `oldAppVersion`
 *
 * v > 0.0.0 `habitsSize` { `nameOfHabit` `nameOfUnitsOfDimension` `typeOfGoalHabits` `needGoal` `needDays`...
 *
 * > v > 0.2.0 ...`typeOfColorHabits` `colorGood`...
 *
 * > v > 1.0.0 ...`changeLevel` `changeNeedGoalWithLevel` `changeNeedDaysWithLevel`...
 *
 * > v > 0.0.0 ...`startDate`...
 *
 * > v < 1.0.0 ...`lastDate`...
 *
 * > v > 1.0.0 ...`lastLevelChangeDate` `level`...
 *
 * > v > 0.0.0 ...`habitDaySize` { `today` `totalOfAPeriod` `correctly` } }
 *
 * v > 0.3.0 `soul_color_type` `soul_color` `soul_name`
 *
 * v > 1.0.0 `soul_level` `soul_last_level_change_date`
 *
 * v > 1.0.1 `language`
 */
const val app_version: Long = 1001000000 //version(001).001.000.000

const val save_file_name = "LevelUp-Soul.FireForestSouls-saving"

var habits: MutableList<Habit> = mutableListOf(
    Habit()
)

var soul_color_type = TypeOfColorHabits.ADAPTIVE
var soul_color = Color(200, 200, 200)
var soul_name = ts_Mr_Soul_Forest
var soul_level = 0
var soul_last_level_change_date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

var language: Languages = Languages.EN