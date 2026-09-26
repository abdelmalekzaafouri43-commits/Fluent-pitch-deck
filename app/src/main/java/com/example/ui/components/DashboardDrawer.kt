package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuOpen
import androidx.compose.material.icons.filled.CoPresent
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MotionPhotosOn
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Slideshow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.SlideData
import com.example.model.ThemePalette
import com.example.model.TransitionType

@Composable
fun DashboardDrawer(
    slides: List<SlideData>,
    currentSlideIndex: Int,
    onSlideSelected: (Int) -> Unit,
    currentTheme: ThemePalette,
    onThemeSelected: (ThemePalette) -> Unit,
    isPresenterMode: Boolean,
    onTogglePresenterMode: () -> Unit,
    currentTransition: TransitionType,
    onTransitionSelected: (TransitionType) -> Unit,
    onOpenQuiz: () -> Unit,
    onShareDeck: () -> Unit,
    isExpanded: Boolean,
    onToggleExpand: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dashboardBg = currentTheme.leftDashboardBg

    Column(
        modifier = modifier
            .fillMaxHeight()
            .width(if (isExpanded) 260.dp else 64.dp)
            .background(dashboardBg)
            .padding(vertical = 12.dp, horizontal = if (isExpanded) 12.dp else 6.dp)
            .testTag("left_dashboard_drawer")
    ) {
        // Top Dashboard Header with collapse toggle
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = if (isExpanded) Arrangement.SpaceBetween else Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isExpanded) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(currentTheme.primaryColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Slideshow,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "DASHBOARD",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = "Executive Slides",
                            fontSize = 10.sp,
                            color = Color(0xFF94A3B8)
                        )
                    }
                }
            }

            IconButton(
                onClick = onToggleExpand,
                modifier = Modifier.testTag("toggle_dashboard_button")
            ) {
                Icon(
                    imageVector = if (isExpanded) Icons.AutoMirrored.Filled.MenuOpen else Icons.Default.Menu,
                    contentDescription = "Toggle Dashboard",
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider(color = Color(0xFF334155), thickness = 1.dp)
        Spacer(modifier = Modifier.height(12.dp))

        if (isExpanded) {
            // Presenter Mode Toggle Button
            Button(
                onClick = onTogglePresenterMode,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isPresenterMode) currentTheme.accentColor else Color(0xFF1E293B),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .testTag("presenter_mode_button")
            ) {
                Icon(
                    imageVector = Icons.Default.CoPresent,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (isPresenterMode) "Presenter Mode: ON" else "Presenter Mode",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Slide Thumbnails Panel
            Text(
                text = "SLIDE NAVIGATION",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF94A3B8),
                letterSpacing = 1.sp,
                modifier = Modifier.padding(start = 4.dp, bottom = 6.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(slides) { index, slide ->
                    ThumbnailCard(
                        slide = slide,
                        index = index,
                        isSelected = currentSlideIndex == index,
                        onClick = { onSlideSelected(index) },
                        theme = currentTheme
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(color = Color(0xFF334155), thickness = 1.dp)
            Spacer(modifier = Modifier.height(10.dp))

            // Theme Switcher Section
            Text(
                text = "CUSTOM THEMES",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF94A3B8),
                letterSpacing = 1.sp,
                modifier = Modifier.padding(start = 4.dp, bottom = 6.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                ThemePalette.values().forEach { palette ->
                    ThemeChip(
                        palette = palette,
                        isSelected = currentTheme == palette,
                        onSelect = { onThemeSelected(palette) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Transition Switcher
            Text(
                text = "TRANSITION EFFECT",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF94A3B8),
                letterSpacing = 1.sp,
                modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                TransitionType.values().forEach { transition ->
                    TransitionChip(
                        transition = transition,
                        isSelected = currentTransition == transition,
                        onSelect = { onTransitionSelected(transition) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Quiz & Export Actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onOpenQuiz,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B82F6)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(38.dp)
                        .testTag("quiz_mode_button")
                ) {
                    Icon(imageVector = Icons.Default.Quiz, contentDescription = null, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Quiz", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = onShareDeck,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B981)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(38.dp)
                        .testTag("export_deck_button")
                ) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = null, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Export", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        } else {
            // Collapsed Left Strip (Icon Buttons only)
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                IconButton(onClick = onTogglePresenterMode) {
                    Icon(
                        imageVector = Icons.Default.CoPresent,
                        contentDescription = "Presenter Mode",
                        tint = if (isPresenterMode) currentTheme.accentColor else Color.White
                    )
                }

                IconButton(onClick = onOpenQuiz) {
                    Icon(imageVector = Icons.Default.Quiz, contentDescription = "Quiz", tint = Color.White)
                }

                IconButton(onClick = onShareDeck) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = "Export Deck", tint = Color.White)
                }

                Spacer(modifier = Modifier.weight(1f))

                // Slide index indicator
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(currentTheme.primaryColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${currentSlideIndex + 1}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
private fun ThumbnailCard(
    slide: SlideData,
    index: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
    theme: ThemePalette
) {
    val bgColor by animateColorAsState(
        if (isSelected) theme.primaryColor.copy(alpha = 0.3f) else Color(0xFF1E293B),
        label = "thumbBg"
    )
    val borderColor by animateColorAsState(
        if (isSelected) theme.primaryColor else Color(0xFF334155),
        label = "thumbBorder"
    )

    Card(
        colors = CardDefaults.cardColors(containerColor = bgColor),
        border = androidx.compose.foundation.BorderStroke(1.dp, borderColor),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .testTag("thumbnail_slide_$index")
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(if (isSelected) theme.primaryColor else Color(0xFF475569)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${slide.slideNumber}",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = slide.title,
                    fontSize = 11.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = slide.subtitle,
                    fontSize = 9.sp,
                    color = Color(0xFF94A3B8),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun ThemeChip(
    palette: ThemePalette,
    isSelected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor = if (isSelected) palette.primaryColor else Color(0xFF334155)

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(Color(0xFF1E293B))
            .border(1.dp, borderColor, RoundedCornerShape(6.dp))
            .clickable { onSelect() }
            .padding(vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(palette.primaryColor)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = palette.displayName.take(3).uppercase(),
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
private fun TransitionChip(
    transition: TransitionType,
    isSelected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bgColor = if (isSelected) Color(0xFF3B82F6).copy(alpha = 0.3f) else Color(0xFF1E293B)
    val borderColor = if (isSelected) Color(0xFF3B82F6) else Color(0xFF334155)

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(bgColor)
            .border(1.dp, borderColor, RoundedCornerShape(6.dp))
            .clickable { onSelect() }
            .padding(vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = transition.displayName,
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}
