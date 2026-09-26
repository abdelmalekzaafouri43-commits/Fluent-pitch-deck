package com.example.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
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
import com.example.model.ThemePalette
import com.example.model.TransitionType

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SlideContainer(
    currentSlideIndex: Int,
    totalSlides: Int,
    theme: ThemePalette,
    transitionType: TransitionType,
    onPrevSlide: () -> Unit,
    onNextSlide: () -> Unit,
    content: @Composable (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val progress = (currentSlideIndex + 1).toFloat() / totalSlides.toFloat()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(theme.backgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Centered 16:9 Slide Stage
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = theme.surfaceColor),
                shape = RoundedCornerShape(16.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, theme.cardBorderColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(16.dp))
                    .testTag("16_9_slide_container")
            ) {
                AnimatedContent(
                    targetState = currentSlideIndex,
                    transitionSpec = {
                        when (transitionType) {
                            TransitionType.SLIDE -> {
                                if (targetState > initialState) {
                                    (slideInHorizontally { width -> width } + fadeIn(tween(300)))
                                        .togetherWith(slideOutHorizontally { width -> -width } + fadeOut(tween(300)))
                                } else {
                                    (slideInHorizontally { width -> -width } + fadeIn(tween(300)))
                                        .togetherWith(slideOutHorizontally { width -> width } + fadeOut(tween(300)))
                                }
                            }
                            TransitionType.FADE -> {
                                fadeIn(tween(300)).togetherWith(fadeOut(tween(300)))
                            }
                            TransitionType.ZOOM -> {
                                (scaleIn(initialScale = 0.8f) + fadeIn()).togetherWith(scaleOut(targetScale = 1.1f) + fadeOut())
                            }
                        }
                    },
                    label = "slideTransition"
                ) { targetIndex ->
                    content(targetIndex)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Bottom Controls & Progress Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Previous Slide Button
            IconButton(
                onClick = onPrevSlide,
                enabled = currentSlideIndex > 0,
                modifier = Modifier.testTag("prev_slide_button")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Previous Slide",
                    tint = if (currentSlideIndex > 0) theme.textColorPrimary else theme.cardBorderColor
                )
            }

            // Progress Bar & Counter
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f).padding(horizontal = 16.dp)
            ) {
                LinearProgressIndicator(
                    progress = { progress },
                    color = theme.primaryColor,
                    trackColor = theme.cardBorderColor,
                    modifier = Modifier
                        .weight(1f)
                        .height(8.dp)
                        .clip(CircleShape)
                        .testTag("slide_progress_bar")
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "${currentSlideIndex + 1} / $totalSlides",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = theme.textColorPrimary
                )
            }

            // Next Slide Button
            IconButton(
                onClick = onNextSlide,
                enabled = currentSlideIndex < totalSlides - 1,
                modifier = Modifier.testTag("next_slide_button")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Next Slide",
                    tint = if (currentSlideIndex < totalSlides - 1) theme.textColorPrimary else theme.cardBorderColor
                )
            }
        }
    }
}
