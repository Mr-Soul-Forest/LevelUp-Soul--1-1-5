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
import androidx.compose.ui.draw.clip
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
import androidx.compose.ui.text.style.TextAlign
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

var UI_color = Color(40, 40, 40)
val textNoSeeColor = Color(127, 127, 127)
val textSeeUiColor = Color(255, 255, 255)
val UI_dark_color = Color(25, 25, 25)

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
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = ts_Habits,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Default,
                            color = textSeeUiColor,
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
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
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10.dp))
                                        .size(28.dp),
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
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10.dp))
                                        .size(28.dp),
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
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .size(28.dp),
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
                                color = textSeeUiColor,
                                modifier = Modifier
                                    .border(1.dp, textNoSeeColor, RoundedCornerShape(15.dp))
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
                    IconButton(onClick = {
                        viewModel.setStatus(AppStatus.TABLE_UPDATER)
                    }) {
                        Image(
                            painter = painterResource(Res.drawable.habits),
                            contentDescription = ts_Habits,
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .size(28.dp),
                        )
                    }
                    IconButton(onClick = {
                        if (appStatus == AppStatus.TABLE) {
                            viewModel.setStatus(AppStatus.SOUL_STATISTICS)
                        }
                    }) {
                        Image(
                            painter = painterResource(Res.drawable.soul_stat),
                            contentDescription = ts_Soul_statistic,
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .size(28.dp),
                        )
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