/**Copyright 2025 Forge-of-Ovorldule (https://github.com/Forge-of-Ovorldule) and Mr-Soul-Forest (https://github.com/Mr-Soul-Forest)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package fireforestsoul.levelupsoul

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

@Composable
fun LoadingTextAnimation() {
    val infiniteTransition = rememberInfiniteTransition()

    val ellipsis by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = 6.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    BasicText(
        text =
            if (ellipsis < 4.0f) ts_Forge_of_Ovorldule_present + ".".repeat(ellipsis.toInt())
            else ts_Forge_of_Ovorldule_present + " ".repeat(6 - ellipsis.toInt()),
        style = TextStyle(
            fontSize = 10.sp,
            fontWeight = FontWeight.Thin,
            fontFamily = FontFamily.Monospace,
            color = averageColor(getBlueFireColor())
        )
    )
}
