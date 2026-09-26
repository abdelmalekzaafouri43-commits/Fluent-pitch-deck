package com.example.ui.slides

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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FactCheck
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.animation.animateColorAsState
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
fun ModalsSlide(
    slide: SlideData,
    theme: ThemePalette,
    evidenceLogs: Set<Int>,
    onToggleEvidence: (Int) -> Unit,
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
                    text = "CERTAINTY SCALE 0% - 100%",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = theme.primaryColor
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 3 Scale Cards (0% Can't, 50% Might/Could, 100% Must)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ScaleCard(
                level = "0% Certainty",
                modalName = "CAN'T / COULDN'T",
                quote = "\"The competitor can't be gaining market share; their site is completely down.\"",
                usage = "Impossibility Deduction based on conflicting negative evidence.",
                color = Color(0xFFEF4444),
                theme = theme,
                modifier = Modifier.weight(1f)
            )
            ScaleCard(
                level = "50% Certainty",
                modalName = "MIGHT / COULD",
                quote = "\"They might be shifting focus to enterprise accounts.\"",
                usage = "Possibility Hypothesis based on ambiguous indicators.",
                color = Color(0xFFEAB308),
                theme = theme,
                modifier = Modifier.weight(1f)
            )
            ScaleCard(
                level = "100% Certainty",
                modalName = "MUST",
                quote = "\"Revenue skyrocketed; their new localization strategy must be working.\"",
                usage = "Logical Necessity based on overwhelming positive proof.",
                color = Color(0xFF10B981),
                theme = theme,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Forensic Diagnostic Tool
        Card(
            colors = CardDefaults.cardColors(containerColor = theme.surfaceColor),
            border = androidx.compose.foundation.BorderStroke(1.dp, theme.cardBorderColor),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.FactCheck,
                            contentDescription = null,
                            tint = theme.primaryColor,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "FORENSIC AUDIT: Select Evidence Logs to Calculate Deduction Confidence",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = theme.textColorPrimary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Evidence Checklist Toggles
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    EvidenceToggleChip(
                        id = 1,
                        label = "E1: Server Downtime Log (48h)",
                        isChecked = evidenceLogs.contains(1),
                        onToggle = onToggleEvidence,
                        theme = theme,
                        modifier = Modifier.weight(1f)
                    )
                    EvidenceToggleChip(
                        id = 2,
                        label = "E2: Enterprise VP Hires",
                        isChecked = evidenceLogs.contains(2),
                        onToggle = onToggleEvidence,
                        theme = theme,
                        modifier = Modifier.weight(1f)
                    )
                    EvidenceToggleChip(
                        id = 3,
                        label = "E3: Revenue +300% Spike",
                        isChecked = evidenceLogs.contains(3),
                        onToggle = onToggleEvidence,
                        theme = theme,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Calculated Deduction Result
                val (modalVerb, deductionSentence, certaintyPct, accentCol) = when {
                    evidenceLogs.contains(1) && !evidenceLogs.contains(3) -> Quad(
                        "CAN'T",
                        "\"With 48h server outage logs, the competitor CAN'T be gaining active web market share.\"",
                        10,
                        Color(0xFFEF4444)
                    )
                    evidenceLogs.contains(3) -> Quad(
                        "MUST",
                        "\"With revenue +300% empirical audit proof, the localization strategy MUST be working!\"",
                        95,
                        Color(0xFF10B981)
                    )
                    evidenceLogs.contains(2) -> Quad(
                        "MIGHT",
                        "\"With enterprise VP hiring logs, they MIGHT be expanding into B2B contracts.\"",
                        55,
                        Color(0xFFEAB308)
                    )
                    else -> Quad(
                        "MIGHT",
                        "\"Insufficient evidence logs selected. Select audit items above.\"",
                        50,
                        theme.textColorSecondary
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Confidence Level ($certaintyPct%):",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = accentCol
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    LinearProgressIndicator(
                        progress = { certaintyPct / 100f },
                        color = accentCol,
                        trackColor = theme.cardBorderColor,
                        modifier = Modifier
                            .weight(1f)
                            .height(6.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Modal: $modalVerb",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = accentCol
                    )
                }

                Text(
                    text = deductionSentence,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = theme.textColorPrimary,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun ScaleCard(
    level: String,
    modalName: String,
    quote: String,
    usage: String,
    color: Color,
    theme: ThemePalette,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = theme.surfaceColor),
        border = androidx.compose.foundation.BorderStroke(1.dp, color.copy(alpha = 0.5f)),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.fillMaxHeight()
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(color)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = level,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = color
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = modalName,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = theme.textColorPrimary
                )

                Spacer(modifier = Modifier.height(6.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .background(color.copy(alpha = 0.1f))
                        .padding(8.dp)
                ) {
                    Text(
                        text = quote,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = theme.textColorPrimary,
                        lineHeight = 14.sp
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = usage,
                    fontSize = 10.sp,
                    color = theme.textColorSecondary,
                    lineHeight = 13.sp
                )
            }
        }
    }
}

@Composable
private fun EvidenceToggleChip(
    id: Int,
    label: String,
    isChecked: Boolean,
    onToggle: (Int) -> Unit,
    theme: ThemePalette,
    modifier: Modifier = Modifier
) {
    val bgColor by animateColorAsState(
        if (isChecked) theme.primaryColor.copy(alpha = 0.15f) else theme.backgroundColor,
        label = "evBg"
    )
    val borderColor by animateColorAsState(
        if (isChecked) theme.primaryColor else theme.cardBorderColor,
        label = "evBorder"
    )

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(bgColor)
            .border(1.dp, borderColor, RoundedCornerShape(6.dp))
            .clickable { onToggle(id) }
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(14.dp)
                    .clip(CircleShape)
                    .background(if (isChecked) theme.primaryColor else Color.Transparent)
                    .border(1.dp, theme.cardBorderColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                if (isChecked) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(10.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = label,
                fontSize = 10.sp,
                fontWeight = if (isChecked) FontWeight.Bold else FontWeight.Normal,
                color = theme.textColorPrimary,
                maxLines = 1
            )
        }
    }
}
