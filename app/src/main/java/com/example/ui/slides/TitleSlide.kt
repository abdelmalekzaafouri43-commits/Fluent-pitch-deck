package com.example.ui.slides

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.SlideData
import com.example.model.ThemePalette

@Composable
fun TitleSlide(
    slide: SlideData,
    theme: ThemePalette,
    onStartDeck: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        theme.surfaceColor,
                        theme.backgroundColor
                    )
                )
            )
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Badge
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(theme.primaryColor.copy(alpha = 0.15f))
                    .border(1.dp, theme.primaryColor.copy(alpha = 0.3f), CircleShape)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = null,
                    tint = theme.primaryColor,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "EXECUTIVE WORKPLACE CURRICULUM",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = theme.primaryColor,
                    letterSpacing = 1.5.sp
                )
            }

            // Main Hero Header
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Text(
                    text = slide.title,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = theme.textColorPrimary,
                    textAlign = TextAlign.Center,
                    lineHeight = 36.sp,
                    fontFamily = FontFamily.Serif
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = slide.subtitle,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = theme.textColorSecondary,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.5.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onStartDeck,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = theme.primaryColor,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.height(48.dp)
                ) {
                    Text(
                        text = "Begin Presentation",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Start"
                    )
                }
            }

            // Bottom Feature Pillars
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                FeaturePillarCard(
                    title = "Syntax Precision",
                    desc = "Exact time anchors for investor trust",
                    icon = Icons.Default.MenuBook,
                    theme = theme,
                    modifier = Modifier.weight(1f)
                )
                FeaturePillarCard(
                    title = "Negotiation Signal",
                    desc = "Strategic conditional choices",
                    icon = Icons.Default.Psychology,
                    theme = theme,
                    modifier = Modifier.weight(1f)
                )
                FeaturePillarCard(
                    title = "PR & Crisis Tone",
                    desc = "Active accountability vs. diplomacy",
                    icon = Icons.Default.RecordVoiceOver,
                    theme = theme,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun FeaturePillarCard(
    title: String,
    desc: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    theme: ThemePalette,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = theme.surfaceColor
        ),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, theme.cardBorderColor),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(theme.accentColor.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = theme.accentColor,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Text(
                    text = title,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = theme.textColorPrimary
                )
                Text(
                    text = desc,
                    fontSize = 11.sp,
                    color = theme.textColorSecondary,
                    lineHeight = 14.sp
                )
            }
        }
    }
}
