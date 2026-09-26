package com.example.model

import androidx.compose.ui.graphics.Color

data class Quad<A, B, C, D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D
)

enum class ThemePalette(val displayName: String, val description: String) {
    CORPORATE("Corporate Professional", "Deep Navy, Slate Blue & Crisp White"),
    CREATIVE("Creative Editorial", "Warm Terracotta, Sage Green & Cream"),
    CYBER("Cyber Tech Minimal", "Dark Mode: Jet Black, Neon Mint & Slate Gray");

    val primaryColor: Color
        get() = when (this) {
            CORPORATE -> Color(0xFF1E40AF)
            CREATIVE -> Color(0xFFC2410C)
            CYBER -> Color(0xFF10B981)
        }

    val backgroundColor: Color
        get() = when (this) {
            CORPORATE -> Color(0xFFF8FAFC)
            CREATIVE -> Color(0xFFFFFBEB)
            CYBER -> Color(0xFF090D16)
        }

    val surfaceColor: Color
        get() = when (this) {
            CORPORATE -> Color(0xFFFFFFFF)
            CREATIVE -> Color(0xFFFEF3C7)
            CYBER -> Color(0xFF1E293B)
        }

    val cardBorderColor: Color
        get() = when (this) {
            CORPORATE -> Color(0xFFCBD5E1)
            CREATIVE -> Color(0xFFFDE68A)
            CYBER -> Color(0xFF334155)
        }

    val textColorPrimary: Color
        get() = when (this) {
            CORPORATE -> Color(0xFF0F172A)
            CREATIVE -> Color(0xFF451A03)
            CYBER -> Color(0xFFF8FAFC)
        }

    val textColorSecondary: Color
        get() = when (this) {
            CORPORATE -> Color(0xFF475569)
            CREATIVE -> Color(0xFF78350F)
            CYBER -> Color(0xFF94A3B8)
        }

    val accentColor: Color
        get() = when (this) {
            CORPORATE -> Color(0xFF0284C7)
            CREATIVE -> Color(0xFFD97706)
            CYBER -> Color(0xFF34D399)
        }

    val leftDashboardBg: Color
        get() = when (this) {
            CORPORATE -> Color(0xFF0F172A)
            CREATIVE -> Color(0xFF292524)
            CYBER -> Color(0xFF020617)
        }
}

enum class TransitionType(val displayName: String) {
    SLIDE("Slide"),
    FADE("Fade"),
    ZOOM("Zoom")
}

data class SlideData(
    val id: Int,
    val slideNumber: Int,
    val title: String,
    val subtitle: String,
    val themeCategory: String,
    val presenterNotes: String,
    val summary: String
)

data class QuizQuestion(
    val id: Int,
    val slideId: Int,
    val scenario: String,
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val explanation: String
)
