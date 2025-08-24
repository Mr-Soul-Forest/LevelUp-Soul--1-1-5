package fireforestsoul.levelupsoul

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font

@Composable
fun JetBrainsFont(): FontFamily {
    return FontFamily(
        Font(Res.font.JetBrainsMono_ExtraBold, FontWeight.ExtraBold, FontStyle.Normal),
        Font(Res.font.JetBrainsMono_Italic, FontWeight.Normal, FontStyle.Italic),
        Font(Res.font.JetBrainsMono_Medium, FontWeight.Medium, FontStyle.Normal),
        Font(Res.font.JetBrainsMono_Bold, FontWeight.Bold, FontStyle.Normal),
        Font(Res.font.JetBrainsMono_Regular, FontWeight.Normal, FontStyle.Normal),
        Font(Res.font.JetBrainsMono_Bold_Italic, FontWeight.Bold, FontStyle.Italic),
        Font(Res.font.JetBrainsMono_ExtraBold_Italic, FontWeight.ExtraBold, FontStyle.Italic),
        Font(Res.font.JetBrainsMono_Medium_Italic, FontWeight.Normal, FontStyle.Italic)
    )
}