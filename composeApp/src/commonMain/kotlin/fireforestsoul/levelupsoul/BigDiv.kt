package fireforestsoul.levelupsoul

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.DecimalMode
import com.ionspin.kotlin.bignum.decimal.RoundingMode
import com.ionspin.kotlin.bignum.decimal.toBigDecimal

private val mode = DecimalMode(
    decimalPrecision = 50,
    roundingMode = RoundingMode.ROUND_HALF_TO_EVEN
)

operator fun BigDecimal.div(other: Float): BigDecimal {
    return this.divide(other.toBigDecimal(), mode)
}

operator fun BigDecimal.div(other: Double): BigDecimal {
    return this.divide(other.toBigDecimal(), mode)
}

fun BigDecimal.saveDiv(other: BigDecimal): BigDecimal {
    return this.divide(other, mode)
}

fun BigDecimal.saveDiv(other: Int): BigDecimal {
    return this.divide(other.toBigDecimal(), mode)
}

fun BigDecimal.saveDiv(other: Long): BigDecimal {
    return this.divide(other.toBigDecimal(), mode)
}