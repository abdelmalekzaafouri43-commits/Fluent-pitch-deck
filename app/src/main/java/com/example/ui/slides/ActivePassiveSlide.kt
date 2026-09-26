package com.example.ui.slides

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.SlideData
import com.example.model.ThemePalette
import kotlin.math.roundToInt

@Composable
fun ActivePassiveSlide(
    slide: SlideData,
    theme: ThemePalette,
    accountabilityValue: Float,
    onAccountabilityChanged: (Float) -> Unit,
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
                    text = "ACCOUNTABILITY SPECTRUM",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = theme.primaryColor
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Static Voice Comparison Cards
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Active Voice Card
            Card(
                colors = CardDefaults.cardColors(containerColor = theme.surfaceColor),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFF10B981)),
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
                                imageVector = Icons.Default.VerifiedUser,
                                contentDescription = null,
                                tint = Color(0xFF10B981),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "ACTIVE VOICE (HIGH ACCOUNTABILITY)",
                                fontSize = 11.sp,
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
                                text = "\"Our engineering team made a critical database error.\"",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = theme.textColorPrimary
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "• Subject = Doer of Action (\"Our engineering team\").\n• Clear Ownership: Explicitly names the responsible team.\n• Stakeholder Impact: Builds trust through immediate transparency.",
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
                            text = "Best For: Direct leadership accountability, crisis resolution.",
                            fontSize = 11.sp,
                            color = theme.textColorPrimary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Passive Voice Card
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
                                imageVector = Icons.Default.Shield,
                                contentDescription = null,
                                tint = Color(0xFF64748B),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "PASSIVE VOICE (DEFLECTING / FORMAL)",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFF64748B),
                                letterSpacing = 0.5.sp
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
                                text = "\"A database error was made.\"",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = theme.textColorPrimary
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "• Doer Omitted: The actor is completely absent.\n• Institutional Distance: Creates formal detachment.\n• Strategic Use: Focuses on victim/process or avoids blaming individuals.",
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
                            text = "Best For: Legal neutrality or focusing on system state over blame.",
                            fontSize = 11.sp,
                            color = theme.textColorPrimary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Interactive Accountability Slider Spectrum
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
                            imageVector = Icons.Default.Campaign,
                            contentDescription = null,
                            tint = theme.primaryColor,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "INTERACTIVE PR TONE SLIDER",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = theme.textColorPrimary
                        )
                    }

                    val pct = (accountabilityValue * 100).roundToInt()
                    Text(
                        text = "Accountability Level: $pct%",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = if (pct > 50) Color(0xFF10B981) else Color(0xFFEAB308)
                    )
                }

                Slider(
                    value = accountabilityValue,
                    onValueChange = onAccountabilityChanged,
                    valueRange = 0f..1f,
                    colors = SliderDefaults.colors(
                        thumbColor = theme.primaryColor,
                        activeTrackColor = theme.primaryColor,
                        inactiveTrackColor = theme.cardBorderColor
                    ),
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                val generatedSentence = when {
                    accountabilityValue < 0.25f -> "\"A critical database error was made during deployment.\""
                    accountabilityValue < 0.65f -> "\"An error was made by our deployment team during maintenance.\""
                    else -> "\"Our engineering team made a critical database error during deployment.\""
                }

                val analysisTone = when {
                    accountabilityValue < 0.25f -> "Extreme Passive Deflection — Omits doer completely. Sounds bureaucratic."
                    accountabilityValue < 0.65f -> "Agentive Passive — Mentions team at end, but sentence remains passive."
                    else -> "Maximum Active Ownership — Direct, transparent executive statement."
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(theme.backgroundColor)
                        .padding(10.dp)
                ) {
                    Column {
                        Text(
                            text = generatedSentence,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = theme.primaryColor
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "PR Tone Analysis: $analysisTone",
                            fontSize = 11.sp,
                            color = theme.textColorSecondary
                        )
                    }
                }
            }
        }
    }
}
