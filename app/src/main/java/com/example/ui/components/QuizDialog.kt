package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.model.QuizQuestion
import com.example.model.ThemePalette

@Composable
fun QuizDialog(
    questions: List<QuizQuestion>,
    answers: Map<Int, Int>,
    onAnswerSelected: (questionId: Int, optionIndex: Int) -> Unit,
    onDismiss: () -> Unit,
    theme: ThemePalette
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            colors = CardDefaults.cardColors(containerColor = theme.surfaceColor),
            shape = RoundedCornerShape(16.dp),
            border = androidx.compose.foundation.BorderStroke(2.dp, theme.primaryColor),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .testTag("quiz_dialog")
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(theme.primaryColor),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Quiz,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "KNOWLEDGE CHECK",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = theme.textColorPrimary
                            )
                            Text(
                                text = "Mastering Professional Nuance",
                                fontSize = 10.sp,
                                color = theme.textColorSecondary
                            )
                        }
                    }

                    IconButton(onClick = onDismiss) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close Quiz",
                            tint = theme.textColorPrimary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                questions.forEachIndexed { qIdx, q ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = theme.backgroundColor),
                        shape = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, theme.cardBorderColor),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "Q${qIdx + 1}: ${q.scenario}",
                                fontSize = 11.sp,
                                color = theme.accentColor,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = q.question,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = theme.textColorPrimary
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            val selectedOpt = answers[q.id]

                            q.options.forEachIndexed { optIdx, optionText ->
                                val isSelected = selectedOpt == optIdx
                                val isCorrect = optIdx == q.correctAnswerIndex

                                val optionBg = when {
                                    selectedOpt != null && isCorrect -> Color(0xFF10B981).copy(alpha = 0.2f)
                                    isSelected && !isCorrect -> Color(0xFFEF4444).copy(alpha = 0.2f)
                                    else -> theme.surfaceColor
                                }

                                val optionBorder = when {
                                    selectedOpt != null && isCorrect -> Color(0xFF10B981)
                                    isSelected && !isCorrect -> Color(0xFFEF4444)
                                    else -> theme.cardBorderColor
                                }

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(optionBg)
                                        .border(1.dp, optionBorder, RoundedCornerShape(8.dp))
                                        .clickable { onAnswerSelected(q.id, optIdx) }
                                        .padding(10.dp)
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = optionText,
                                            fontSize = 12.sp,
                                            color = theme.textColorPrimary,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                            modifier = Modifier.weight(1f)
                                        )

                                        if (selectedOpt != null && isCorrect) {
                                            Icon(
                                                imageVector = Icons.Default.CheckCircle,
                                                contentDescription = "Correct",
                                                tint = Color(0xFF10B981),
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                    }
                                }
                            }

                            if (selectedOpt != null) {
                                Spacer(modifier = Modifier.height(6.dp))
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(theme.primaryColor.copy(alpha = 0.1f))
                                        .padding(8.dp)
                                ) {
                                    Text(
                                        text = "Explanation: ${q.explanation}",
                                        fontSize = 11.sp,
                                        color = theme.primaryColor,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }
                    }
                }

                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = theme.primaryColor),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth().height(44.dp)
                ) {
                    Text("Done Practice", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
