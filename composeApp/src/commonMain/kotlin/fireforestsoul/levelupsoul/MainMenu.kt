/**Copyright 2025 Forge-of-Ovorldule (https://github.com/Forge-of-Ovorldule) and Mr-Soul-Forest (https://github.com/Mr-Soul-Forest)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

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
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.graphics.painter.Painter

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
            .background(UIC)
            .fillMaxSize()
    )
    Scaffold(
        modifier = Modifier
            .padding(WindowInsets.systemBars.asPaddingValues()),
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(UIC)
            ) {
                if (appStatus == AppStatus.TABLE || appStatus == AppStatus.TABLE_UPDATER || appStatus == AppStatus.HABITS_LIST) {
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
                            color = UICT_see,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(0.8f)
                        )
                        if ("Android" !in getPlatform().name) {
                            IconButton(onClick = {
                                saveValue()
                                export()
                            }) {
                                Image(
                                    painter = painterResource(Res.drawable.export),
                                    contentDescription = ts_Export_habits,
                                    modifier = Modifier.size(28.dp),
                                    colorFilter = ColorFilter.tint(getSoulRealColor())
                                )
                            }
                            IconButton(onClick = {
                                saveValue()
                                import {
                                    countFilesLoad = 0
                                    viewModel.setStatus(AppStatus.LOADING)
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
                            viewModel.setStatus(AppStatus.CREATE_HABIT)
                        }) {
                            Image(
                                painter = painterResource(Res.drawable.add_habit),
                                contentDescription = ts_Create_habit,
                                modifier = Modifier.size(28.dp),
                                colorFilter = ColorFilter.tint(getSoulRealColor())
                            )
                        }
                        if (appStatus == AppStatus.TABLE || appStatus == AppStatus.TABLE_UPDATER) {
                            DatePickerDialog(countdownDate) {
                                countdownDate = it
                            }
                        }

                        SettingsDialog()

                        var expanded0 by remember { mutableStateOf(false) }

                        Box {
                            Text(
                                language.name,
                                fontSize = 16.sp,
                                color = averageColor(listOf(UICT_see, getSoulRealColor())),
                                modifier = Modifier
                                    .border(1.dp, UICT_no_see, RoundedCornerShape(10.dp))
                                    .clickable { expanded0 = true }
                                    .padding(5.dp),
                                textAlign = TextAlign.Center
                            )
                            DropdownMenu(
                                expanded = expanded0,
                                onDismissRequest = { expanded0 = false },
                                modifier = Modifier
                                    .background(UIC)
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
                                                color = UICT_no_see
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
                            color = UICT_see
                        )
                    }
                }
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(UIC)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    AnimatedTabItem(
                        isActive = appStatus == AppStatus.HABITS_LIST,
                        onClick = { viewModel.setStatus(AppStatus.HABITS_LIST) },
                        activeIcon = painterResource(Res.drawable.habits_list),
                        inactiveIcon = painterResource(Res.drawable.habits_list_mono),
                        text = ts_habits_list,
                        contentDescription = ts_Habits,
                        inactiveColor = getSoulRealColor()
                    )

                    AnimatedTabItem(
                        isActive = appStatus == AppStatus.TABLE,
                        onClick = { viewModel.setStatus(AppStatus.TABLE_UPDATER) },
                        activeIcon = painterResource(Res.drawable.habits_table),
                        inactiveIcon = painterResource(Res.drawable.habits_table_mono),
                        text = ts_habits_table,
                        contentDescription = ts_Habits,
                        inactiveColor = getSoulRealColor()
                    )

                    AnimatedTabItem(
                        isActive = appStatus == AppStatus.SOUL_STATISTICS,
                        onClick = { viewModel.setStatus(AppStatus.SOUL_STATISTICS) },
                        activeIcon = painterResource(Res.drawable.soul_stat),
                        inactiveIcon = painterResource(Res.drawable.soul_stat_mono),
                        text = ts_soul_statistic,
                        contentDescription = ts_Habits,
                        inactiveColor = getSoulRealColor()
                    )
                }
            }
        }
    ) { paddingValues ->
        val verticalScrollForHabitsListContent = rememberScrollState()

        Box(modifier = Modifier.padding(paddingValues)) {
            if (appStatus == AppStatus.TABLE || appStatus == AppStatus.TABLE_UPDATER)
                TableContent(viewModel, verticalScrollForTableContent, horizontalScrollForTableContent, countdownDate)
            if (appStatus == AppStatus.SOUL_STATISTICS)
                SoulStatisticsContent()
            if (appStatus == AppStatus.HABITS_LIST || appStatus == AppStatus.HABITS_LIST_UPDATER)
                HabitsListContent(verticalScrollForHabitsListContent, viewModel)
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedTabItem(
    isActive: Boolean,
    onClick: () -> Unit,
    activeIcon: Painter,
    inactiveIcon: Painter,
    text: String,
    contentDescription: String,
    inactiveColor: Color
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isActive) UIC_light else Color.Transparent,
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = Spring.DampingRatioMediumBouncy
        )
    )

    val iconColor by animateColorAsState(
        targetValue = if (isActive) UICT_see else inactiveColor,
        animationSpec = tween(durationMillis = 300)
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(40.dp)
            )
            .padding(horizontal = if (isActive) 15.dp else 0.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.size(48.dp)
        ) {
            AnimatedContent(
                targetState = isActive,
                transitionSpec = {
                    scaleIn(animationSpec = tween(150)) + fadeIn() with
                            scaleOut(animationSpec = tween(150)) + fadeOut()
                }
            ) { active ->
                Image(
                    painter = if (active) activeIcon else inactiveIcon,
                    contentDescription = contentDescription,
                    modifier = Modifier.size(28.dp),
                    colorFilter = if (!active) {
                        ColorFilter.tint(iconColor, BlendMode.Modulate)
                    } else {
                        null
                    }
                )
            }
        }

        AnimatedVisibility(
            visible = isActive,
            enter = fadeIn(animationSpec = tween(150)) + expandHorizontally(expandFrom = Alignment.Start),
            exit = fadeOut(animationSpec = tween(150)) + shrinkHorizontally(shrinkTowards = Alignment.End)
        ) {
            Text(
                text = text,
                fontSize = 14.sp,
                fontFamily = FontFamily.Monospace,
                color = iconColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}