package fireforestsoul.levelupsoul

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.toBigDecimal

class HabitDay(var today: BigDecimal = 0.0.toBigDecimal()) {
    var totalOfAPeriod = 0.toBigDecimal()
    var correctly: Boolean = false
}