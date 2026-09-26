package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.ChatMessage
import com.example.model.SlideData
import com.example.model.ThemePalette

@Composable
fun PresenterModeView(
    currentSlide: SlideData,
    nextSlide: SlideData?,
    currentSlideIndex: Int,
    totalSlides: Int,
    theme: ThemePalette,
    aiTutorMessages: List<ChatMessage>,
    isAiTutorLoading: Boolean,
    aiTutorError: String?,
    onSendAiTutorMessage: (String) -> Unit,
    onPrevSlide: () -> Unit,
    onNextSlide: () -> Unit,
    onClosePresenterMode: () -> Unit,
    onSpeakNotes: () -> Unit,
    isSpeaking: Boolean,
    mainSlideContent: @Composable () -> Unit,
    nextSlideContent: @Composable (() -> Unit)?,
    modifier: Modifier = Modifier
) {
    var rightPaneTab by remember { mutableIntStateOf(0) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0F172A))
            .padding(16.dp)
            .testTag("presenter_mode_view")
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Top Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFEF4444))
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "PRESENTER MODE (LIVE DECK)",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Slide ${currentSlideIndex + 1} of $totalSlides",
                        fontSize = 12.sp,
                        color = Color(0xFF94A3B8)
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onSpeakNotes,
                        modifier = Modifier.testTag("tts_notes_button")
                    ) {
                        Icon(
                            imageVector = if (isSpeaking) Icons.Default.VolumeUp else Icons.Default.RecordVoiceOver,
                            contentDescription = "Read Notes",
                            tint = if (isSpeaking) Color(0xFF10B981) else Color.White
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    IconButton(
                        onClick = onClosePresenterMode,
                        modifier = Modifier.testTag("close_presenter_mode_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close Presenter Mode",
                            tint = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Split View: Main Slide (Left 60%) + Right Pane (40%)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Left: Main Stage Slide
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(2.dp, theme.primaryColor),
                    modifier = Modifier
                        .weight(1.2f)
                        .fillMaxHeight()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(16f / 9f)
                                .clip(RoundedCornerShape(8.dp))
                        ) {
                            mainSlideContent()
                        }
                    }
                }

                // Right Column: Notes & Preview OR Persistent AI Tutor
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Right Pane Tab Selector
                    TabRow(
                        selectedTabIndex = rightPaneTab,
                        containerColor = Color(0xFF1E293B),
                        contentColor = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                    ) {
                        Tab(
                            selected = rightPaneTab == 0,
                            onClick = { rightPaneTab = 0 },
                            text = {
                                Text(
                                    "NOTES & PREVIEW",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (rightPaneTab == 0) theme.primaryColor else Color(0xFF94A3B8)
                                )
                            }
                        )
                        Tab(
                            selected = rightPaneTab == 1,
                            onClick = { rightPaneTab = 1 },
                            text = {
                                Text(
                                    "AI TUTOR CHAT",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (rightPaneTab == 1) theme.primaryColor else Color(0xFF94A3B8)
                                )
                            }
                        )
                    }

                    if (rightPaneTab == 0) {
                        // Next Slide Preview
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
                            shape = RoundedCornerShape(12.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF334155)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.7f)
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    text = "NEXT SLIDE PREVIEW",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF94A3B8),
                                    letterSpacing = 1.sp
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                if (nextSlide != null && nextSlideContent != null) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .aspectRatio(16f / 9f)
                                            .clip(RoundedCornerShape(6.dp))
                                    ) {
                                        nextSlideContent()
                                    }
                                } else {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "End of Presentation Deck",
                                            fontSize = 12.sp,
                                            color = Color(0xFF64748B)
                                        )
                                    }
                                }
                            }
                        }

                        // Rich Presenter Notes Box
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
                            shape = RoundedCornerShape(12.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF334155)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1.3f)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "PRESENTER TALKING POINTS",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF38BDF8),
                                        letterSpacing = 1.sp
                                    )

                                    Button(
                                        onClick = onSpeakNotes,
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = if (isSpeaking) Color(0xFF10B981) else Color(0xFF334155)
                                        ),
                                        shape = RoundedCornerShape(6.dp),
                                        modifier = Modifier.height(28.dp)
                                    ) {
                                        Text(
                                            text = if (isSpeaking) "Speaking..." else "Voice Read",
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .verticalScroll(rememberScrollState())
                                ) {
                                    Text(
                                        text = currentSlide.presenterNotes,
                                        fontSize = 13.sp,
                                        color = Color.White,
                                        lineHeight = 19.sp,
                                        fontWeight = FontWeight.Normal
                                    )
                                }
                            }
                        }
                    } else {
                        // Persistent AI Tutor Chat Interface
                        AiTutorChatView(
                            currentSlide = currentSlide,
                            messages = aiTutorMessages,
                            isLoading = isAiTutorLoading,
                            errorMessage = aiTutorError,
                            onSendMessage = onSendAiTutorMessage,
                            theme = theme,
                            modifier = Modifier.weight(2f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Bottom Navigation Controls
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onPrevSlide,
                    enabled = currentSlideIndex > 0,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF334155)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.height(40.dp)
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Previous")
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Previous Slide", fontSize = 12.sp)
                }

                Text(
                    text = "${currentSlideIndex + 1} / $totalSlides",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Button(
                    onClick = onNextSlide,
                    enabled = currentSlideIndex < totalSlides - 1,
                    colors = ButtonDefaults.buttonColors(containerColor = theme.primaryColor),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.height(40.dp)
                ) {
                    Text("Next Slide", fontSize = 12.sp)
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Next")
                }
            }
        }
    }
}
