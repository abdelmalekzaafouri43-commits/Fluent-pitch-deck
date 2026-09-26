package com.example.ui.slides

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Quad
import com.example.model.SlideData
import com.example.model.ThemePalette

@Composable
fun PresentPerfectSlide(
    slide: SlideData,
    theme: ThemePalette,
    selectedChoice: Int,
    onChoiceSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(theme.backgroundColor)
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Slide Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = slide.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = theme.textColorPrimary
                )
                Text(
                    text = "Theme: ${slide.subtitle}",
                    fontSize = 13.sp,
                    color = theme.accentColor,
                    fontWeight = FontWeight.Medium
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(theme.primaryColor.copy(alpha = 0.15f))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "COMPARISON GRID",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = theme.primaryColor
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Side-by-Side Comparison Columns
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Left Column: Past Simple
            Card(
                colors = CardDefaults.cardColors(containerColor = theme.surfaceColor),
                border = androidx.compose.foundation.BorderStroke(1.dp, theme.cardBorderColor),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.History,
                                contentDescription = null,
                                tint = theme.textColorSecondary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "PAST SIMPLE",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = theme.textColorSecondary,
                                letterSpacing = 1.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(theme.backgroundColor)
                                .padding(12.dp)
                        ) {
                            Text(
                                text = "\"We launched our MVP in 2024.\"",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = theme.textColorPrimary
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "• Definite Time Anchor: Specified time in the past (in 2024).\n• Completed Event: Action is finished and closed off.\n• Use Case: Stating static milestones or historic events.",
                            fontSize = 12.sp,
                            color = theme.textColorSecondary,
                            lineHeight = 18.sp
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFF64748B).copy(alpha = 0.15f))
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "Grammar Rule: Subject + Past Tense Verb + Finished Time Phrase",
                            fontSize = 11.sp,
                            color = theme.textColorPrimary,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            // Right Column: Present Perfect
            Card(
                colors = CardDefaults.cardColors(containerColor = theme.surfaceColor),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, theme.primaryColor),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.TrendingUp,
                                contentDescription = null,
                                tint = theme.primaryColor,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "PRESENT PERFECT",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = theme.primaryColor,
                                letterSpacing = 1.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(theme.primaryColor.copy(alpha = 0.1f))
                                .padding(12.dp)
                        ) {
                            Text(
                                text = "\"We have raised \$2M since inception.\"",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = theme.primaryColor
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "• Indefinite Time / Unfinished Window: Focus on result NOW.\n• Continuous Impact: Bridge between past action and current runway.\n• Use Case: Demonstrating ongoing pitch momentum to VCs.",
                            fontSize = 12.sp,
                            color = theme.textColorSecondary,
                            lineHeight = 18.sp
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(theme.primaryColor.copy(alpha = 0.15f))
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "Grammar Rule: Subject + Have/Has + Past Participle + Duration/Since",
                            fontSize = 11.sp,
                            color = theme.primaryColor,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Interactive Element: Investor Trust Analyzer
        Card(
            colors = CardDefaults.cardColors(containerColor = theme.surfaceColor),
            border = androidx.compose.foundation.BorderStroke(1.dp, theme.cardBorderColor),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "INTERACTIVE DIAGNOSTIC: Test Pitch Phrase on Investor Perception",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = theme.textColorPrimary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    InvestorOptionCard(
                        text = "\"We launched MVP in 2024\"",
                        isSelected = selectedChoice == 0,
                        onClick = { onChoiceSelected(0) },
                        theme = theme,
                        modifier = Modifier.weight(1f)
                    )
                    InvestorOptionCard(
                        text = "\"We have raised \$2M since inception\"",
                        isSelected = selectedChoice == 1,
                        onClick = { onChoiceSelected(1) },
                        theme = theme,
                        modifier = Modifier.weight(1f)
                    )
                    InvestorOptionCard(
                        text = "\"We raised \$2M in 2023\"",
                        isSelected = selectedChoice == 2,
                        onClick = { onChoiceSelected(2) },
                        theme = theme,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                val (score, statusText, statusIcon, iconColor) = when (selectedChoice) {
                    0 -> Quad(75, "Solid milestone confirmation. Past Simple clearly dates historical launch.", Icons.Default.CheckCircle, theme.accentColor)
                    1 -> Quad(98, "High Investor Confidence! Present Perfect connects past capital to active ongoing runway.", Icons.Default.TrendingUp, Color(0xFF10B981))
                    else -> Quad(35, "Investor Suspicion: Past Simple implies money was spent years ago; asks 'What is current runway?'", Icons.Default.Warning, Color(0xFFEF4444))
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = statusIcon,
                        contentDescription = null,
                        tint = iconColor,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Trust Score: $score%",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = iconColor
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    LinearProgressIndicator(
                        progress = { score / 100f },
                        color = iconColor,
                        trackColor = theme.cardBorderColor,
                        modifier = Modifier
                            .weight(1f)
                            .height(6.dp)
                            .clip(CircleShape)
                    )
                }

                Text(
                    text = statusText,
                    fontSize = 11.sp,
                    color = theme.textColorSecondary,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun InvestorOptionCard(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    theme: ThemePalette,
    modifier: Modifier = Modifier
) {
    val bgColor by animateColorAsState(
        if (isSelected) theme.primaryColor.copy(alpha = 0.15f) else theme.backgroundColor,
        label = "bg"
    )
    val borderColor by animateColorAsState(
        if (isSelected) theme.primaryColor else theme.cardBorderColor,
        label = "border"
    )

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(bgColor)
            .border(1.dp, borderColor, RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(horizontal = 8.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelected) theme.primaryColor else theme.textColorPrimary,
            maxLines = 1
        )
    }
}
