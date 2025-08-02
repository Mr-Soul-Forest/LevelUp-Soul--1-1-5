package fireforestsoul.levelupsoul

import com.ionspin.kotlin.bignum.decimal.BigDecimal

var withExponent = false

fun BigDecimal.toBestString(): String {
    return if (withExponent) this.toString() else this.toPlainString()
}