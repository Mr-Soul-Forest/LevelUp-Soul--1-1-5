package fireforestsoul.levelupsoul

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontFamily
import org.jetbrains.compose.resources.painterResource
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

val textNoSeeColor = Color(127, 127, 127)
val textSeeUiColor = Color(255, 255, 255)

val UI_light_color = Color(37, 40, 44)
val UI_color = Color(25, 28, 23)
val UI_dark_color = Color(19, 20, 24)

val UI_dark_x05_color = averageColor(listOf(UI_color, UI_dark_color))
val UI_extra_dark_color = averageColor(listOf(UI_dark_color, Color.Black))
val UI_dark_x2_color = averageColor(listOf(UI_dark_color, UI_extra_dark_color))

@Composable
fun MainMenuContent(
    viewModel: AppViewModel, verticalScrollForTableContent: ScrollState, horizontalScrollForTableContent: ScrollState
) {
    val appStatus by viewModel.appStatus.collectAsState()
    var countdownDate by remember {
        mutableStateOf(
            Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        )
    }

    Box(
        modifier = Modifier
            .background(UI_color)
            .fillMaxSize()
    )
    Scaffold(
        modifier = Modifier
            .padding(WindowInsets.systemBars.asPaddingValues()),
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(UI_color)
            ) {
                if (appStatus == AppStatus.TABLE || appStatus == AppStatus.TABLE_UPDATER) {
                    Row(
                        modifier = Modifier
                            .height(48.dp)
                            .fillMaxWidth()
                            .padding(10.dp, 0.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(
                            text = ts_Habits,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Default,
                            color = textSeeUiColor,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(0.8f)
                        )
                        if ("Android" !in getPlatform().name) {
                            IconButton(onClick = {
                                if (appStatus == AppStatus.TABLE) {
                                    saveValue()
                                    export()
                                }
                            }) {
                                Image(
                                    painter = painterResource(Res.drawable.export),
                                    contentDescription = ts_Export_habits,
                                    modifier = Modifier.size(28.dp),
                                    colorFilter = ColorFilter.tint(getSoulRealColor())
                                )
                            }
                            IconButton(onClick = {
                                if (appStatus == AppStatus.TABLE) {
                                    saveValue()
                                    import {
                                        countFilesLoad = 0
                                        viewModel.setStatus(AppStatus.LOADING)
                                    }
                                }
                            }) {
                                Image(
                                    painter = painterResource(Res.drawable.import_icon),
                                    contentDescription = ts_Import_habits,
                                    modifier = Modifier.size(28.dp),
                                    colorFilter = ColorFilter.tint(getSoulRealColor())
                                )
                            }
                        }
                        IconButton(onClick = {
                            if (appStatus == AppStatus.TABLE) {
                                viewModel.setStatus(AppStatus.CREATE_HABIT)
                            }
                        }) {
                            Image(
                                painter = painterResource(Res.drawable.add_habit),
                                contentDescription = ts_Create_habit,
                                modifier = Modifier.size(28.dp),
                                colorFilter = ColorFilter.tint(getSoulRealColor())
                            )
                        }
                        DatePickerDialog(countdownDate, viewModel) {
                            countdownDate = it
                        }

                        var expanded0 by remember { mutableStateOf(false) }

                        Box {
                            Text(
                                language.name,
                                fontSize = 16.sp,
                                color = averageColor(listOf(textSeeUiColor, getSoulRealColor())),
                                modifier = Modifier
                                    .border(1.dp, textNoSeeColor, RoundedCornerShape(10.dp))
                                    .clickable { expanded0 = true }
                                    .padding(5.dp),
                                textAlign = TextAlign.Center
                            )
                            DropdownMenu(
                                expanded = expanded0,
                                onDismissRequest = { expanded0 = false },
                                modifier = Modifier
                                    .background(UI_color)
                                    .width(50.dp)
                            ) {
                                Languages.entries.forEach { mode ->
                                    DropdownMenuItem(
                                        onClick = {
                                            language = mode
                                            expanded0 = false
                                            countFilesLoad = 0
                                            saveValue()
                                            viewModel.setStatus(AppStatus.LOADING)
                                        },
                                        text = {
                                            Text(
                                                text = mode.name,
                                                fontSize = 16.sp,
                                                color = textNoSeeColor
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
                if (appStatus == AppStatus.SOUL_STATISTICS) {
                    Row(
                        modifier = Modifier
                            .height(48.dp)
                            .fillMaxWidth()
                            .padding(10.dp, 0.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = ts_Soul_statistic,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = textSeeUiColor
                        )
                    }
                }
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(UI_color)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    if (appStatus == AppStatus.HABITS_LIST) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.background(UI_light_color, RoundedCornerShape(40.dp))
                                .padding(horizontal = 15.dp)
                        ) {
                            IconButton(onClick = {
                                viewModel.setStatus(AppStatus.HABITS_LIST)
                            }) {
                                Image(
                                    painter = painterResource(Res.drawable.habits_list),
                                    contentDescription = ts_Habits,
                                    modifier = Modifier.size(28.dp)
                                )
                            }
                            Text(
                                text = ts_habits_list,
                                fontSize = 14.sp,
                                fontFamily = FontFamily.Monospace,
                                color = textSeeUiColor,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    } else {
                        IconButton(onClick = {
                            viewModel.setStatus(AppStatus.HABITS_LIST)
                        }) {
                            Image(
                                painter = painterResource(Res.drawable.habits_list_mono),
                                contentDescription = ts_Habits,
                                modifier = Modifier.size(28.dp),
                                colorFilter = ColorFilter.tint(getSoulRealColor(), BlendMode.Modulate)
                            )
                        }
                    }
                    if (appStatus == AppStatus.TABLE) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.background(UI_light_color, RoundedCornerShape(40.dp))
                                .padding(horizontal = 15.dp)
                        ) {
                            IconButton(onClick = {
                                viewModel.setStatus(AppStatus.TABLE_UPDATER)
                            }) {
                                Image(
                                    painter = painterResource(Res.drawable.habits_table),
                                    contentDescription = ts_Habits,
                                    modifier = Modifier.size(28.dp)
                                )
                            }
                            Text(
                                text = ts_habits_table,
                                fontSize = 14.sp,
                                fontFamily = FontFamily.Monospace,
                                color = textSeeUiColor,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    } else {
                        IconButton(onClick = {
                            viewModel.setStatus(AppStatus.TABLE_UPDATER)
                        }) {
                            Image(
                                painter = painterResource(Res.drawable.habits_table_mono),
                                contentDescription = ts_Habits,
                                modifier = Modifier.size(28.dp),
                                colorFilter = ColorFilter.tint(getSoulRealColor(), BlendMode.Modulate)
                            )
                        }
                    }
                    if (appStatus == AppStatus.SOUL_STATISTICS) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.background(UI_light_color, RoundedCornerShape(40.dp))
                                .padding(horizontal = 15.dp)
                        ) {
                            IconButton(onClick = {
                                viewModel.setStatus(AppStatus.SOUL_STATISTICS)
                            }) {
                                Image(
                                    painter = painterResource(Res.drawable.soul_stat),
                                    contentDescription = ts_Habits,
                                    modifier = Modifier.size(28.dp)
                                )
                            }
                            Text(
                                text = ts_soul_statistic,
                                fontSize = 14.sp,
                                fontFamily = FontFamily.Monospace,
                                color = textSeeUiColor,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    } else {
                        IconButton(onClick = {
                            viewModel.setStatus(AppStatus.SOUL_STATISTICS)
                        }) {
                            Image(
                                painter = painterResource(Res.drawable.soul_stat_mono),
                                contentDescription = ts_Habits,
                                modifier = Modifier.size(28.dp),
                                colorFilter = ColorFilter.tint(getSoulRealColor(), BlendMode.Modulate)
                            )
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            if (appStatus == AppStatus.TABLE)
                TableContent(viewModel, verticalScrollForTableContent, horizontalScrollForTableContent, countdownDate)
            if (appStatus == AppStatus.SOUL_STATISTICS)
                SoulStatisticsContent()
        }
    }
}