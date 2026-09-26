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
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import com.example.model.SlideData
import com.example.model.ThemePalette

@Composable
fun ConditionalsSlide(
    slide: SlideData,
    theme: ThemePalette,
    selectedConditional: Int,
    onConditionalSelected: (Int) -> Unit,
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
                    text = "FLOWCHART DESIGN",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = theme.primaryColor
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Split Flowchart Boxes
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Section A: First Conditional
            Card(
                colors = CardDefaults.cardColors(containerColor = theme.surfaceColor),
                border = androidx.compose.foundation.BorderStroke(
                    if (selectedConditional == 1) 2.dp else 1.dp,
                    if (selectedConditional == 1) theme.primaryColor else theme.cardBorderColor
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable { onConditionalSelected(1) }
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
                                tint = Color(0xFF10B981),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "1ST CONDITIONAL (REAL / LIKELY)",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFF10B981),
                                letterSpacing = 0.5.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFF10B981).copy(alpha = 0.1f))
                                .padding(12.dp)
                        ) {
                            Text(
                                text = "\"If the interest rates drop, property sales will spike.\"",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = theme.textColorPrimary
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "• Structure: If + Present Simple, Will + Base Verb\n• Meaning: Actionable, real-world probability (Real Condition).\n• Strategic Message: High likelihood, active forecast.",
                            fontSize = 12.sp,
                            color = theme.textColorSecondary,
                            lineHeight = 18.sp
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFF10B981).copy(alpha = 0.15f))
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "Signal: 'We anticipate this outcome and are preparing for it.'",
                            fontSize = 11.sp,
                            color = theme.textColorPrimary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Section B: Second Conditional
            Card(
                colors = CardDefaults.cardColors(containerColor = theme.surfaceColor),
                border = androidx.compose.foundation.BorderStroke(
                    if (selectedConditional == 2) 2.dp else 1.dp,
                    if (selectedConditional == 2) theme.primaryColor else theme.cardBorderColor
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable { onConditionalSelected(2) }
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Casino,
                                contentDescription = null,
                                tint = Color(0xFFEAB308),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "2ND CONDITIONAL (HYPOTHETICAL)",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFFEAB308),
                                letterSpacing = 0.5.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFEAB308).copy(alpha = 0.1f))
                                .padding(12.dp)
                        ) {
                            Text(
                                text = "\"If I won the lottery, I would buy a commercial skyscraper.\"",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = theme.textColorPrimary
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "• Structure: If + Past Simple, Would + Base Verb\n• Meaning: Pure hypothesis, unlikely/imaginary scenario.\n• Strategic Warning: Signals low probability or doubt.",
                            fontSize = 12.sp,
                            color = theme.textColorSecondary,
                            lineHeight = 18.sp
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFEAB308).copy(alpha = 0.15f))
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "Signal: 'This is a dream or remote possibility, unlikely to happen.'",
                            fontSize = 11.sp,
                            color = theme.textColorPrimary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Interactive Negotiation Risk Flowchart
        Card(
            colors = CardDefaults.cardColors(containerColor = theme.surfaceColor),
            border = androidx.compose.foundation.BorderStroke(1.dp, theme.cardBorderColor),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.BusinessCenter,
                        contentDescription = null,
                        tint = theme.primaryColor,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "NEGOTIATION SIMULATOR: Choosing Conditional in Client Meetings",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = theme.textColorPrimary
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    ConditionalChoiceChip(
                        label = "Option A: 1st Conditional",
                        subtext = "\"If you order 500 units, we will give 10% off.\"",
                        isSelected = selectedConditional == 1,
                        onClick = { onConditionalSelected(1) },
                        theme = theme,
                        modifier = Modifier.weight(1f)
                    )
                    ConditionalChoiceChip(
                        label = "Option B: 2nd Conditional",
                        subtext = "\"If you ordered 500 units, we would give 10% off.\"",
                        isSelected = selectedConditional == 2,
                        onClick = { onConditionalSelected(2) },
                        theme = theme,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                val (message, isWarning) = if (selectedConditional == 1) {
                    Pair(
                        "✔ Strategic Success: The client hears an actionable, confident business proposition. High closing probability.",
                        false
                    )
                } else {
                    Pair(
                        "⚠ Negotiation Mistake: Using 2nd conditional ('ordered/would') subtly signals to the client that you doubt they have the budget or capacity to buy 500 units!",
                        true
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            if (isWarning) Color(0xFFEF4444).copy(alpha = 0.15f)
                            else Color(0xFF10B981).copy(alpha = 0.15f)
                        )
                        .padding(10.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = if (isWarning) Icons.Default.Warning else Icons.Default.Check,
                            contentDescription = null,
                            tint = if (isWarning) Color(0xFFDC2626) else Color(0xFF16A34A),
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = message,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isWarning) Color(0xFFB91C1C) else Color(0xFF15803D)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ConditionalChoiceChip(
    label: String,
    subtext: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    theme: ThemePalette,
    modifier: Modifier = Modifier
) {
    val bgColor by animateColorAsState(
        if (isSelected) theme.primaryColor.copy(alpha = 0.15f) else theme.backgroundColor,
        label = "chipBg"
    )
    val borderColor by animateColorAsState(
        if (isSelected) theme.primaryColor else theme.cardBorderColor,
        label = "chipBorder"
    )

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(bgColor)
            .border(1.dp, borderColor, RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Column {
            Text(
                text = label,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) theme.primaryColor else theme.textColorPrimary
            )
            Text(
                text = subtext,
                fontSize = 10.sp,
                color = theme.textColorSecondary,
                maxLines = 2
            )
        }
    }
}
