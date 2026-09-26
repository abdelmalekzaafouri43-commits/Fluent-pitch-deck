package com.example.viewmodel

import android.app.Application
import android.content.Intent
import android.speech.tts.TextToSpeech
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.BuildConfig
import com.example.model.ChatMessage
import com.example.model.QuizQuestion
import com.example.model.SlideData
import com.example.model.ThemePalette
import com.example.model.TransitionType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.Locale
import java.util.concurrent.TimeUnit

class PresentationViewModel(application: Application) : AndroidViewModel(application), TextToSpeech.OnInitListener {

    val slides: List<SlideData> = listOf(
        SlideData(
            id = 1,
            slideNumber = 1,
            title = "Mastering Professional Nuance: Advanced English Grammar",
            subtitle = "Navigating Workplace Dynamics & Real-Life Communication",
            themeCategory = "Executive Overview & Syntax Precision",
            presenterNotes = "Welcome the audience and explain that today we connect complex syntax to immediate real-world utility. Language structure directly influences executive presence, risk perception, and stakeholder trust.",
            summary = "Title slide establishing high-impact workplace grammar principles."
        ),
        SlideData(
            id = 2,
            slideNumber = 2,
            title = "The Present Perfect vs. Past Simple",
            subtitle = "The Tech Startup Pitch & Career Journey",
            themeCategory = "Time Anchor & Continuous Impact",
            presenterNotes = "Point out that the Present Perfect builds a bridge between past achievements and current status. Misusing the Past Simple when pitching ongoing momentum implies the growth has stopped.",
            summary = "Definite past actions vs. continuous momentum up to present."
        ),
        SlideData(
            id = 3,
            slideNumber = 3,
            title = "Conditionals: First vs. Second",
            subtitle = "Real Estate, Financial Risk, & Negotiation",
            themeCategory = "Probability & Strategic Intent Signal",
            presenterNotes = "Emphasize that choosing the 2nd conditional in business negotiation accidentally signals to the client that you think the scenario is highly improbable.",
            summary = "Real/Likely outcomes vs. Hypothetical/Unlikely negotiation scenarios."
        ),
        SlideData(
            id = 4,
            slideNumber = 4,
            title = "Active vs. Passive Voice",
            subtitle = "Crisis Management & PR Communication",
            themeCategory = "The Accountability Spectrum",
            presenterNotes = "Discuss when to strategically use passive voice (focusing on the victim/system) vs active voice (taking ownership during crisis response).",
            summary = "High accountability ownership vs. diplomatic deflection."
        ),
        SlideData(
            id = 5,
            slideNumber = 5,
            title = "Modal Verbs of Deduction",
            subtitle = "Forensic Audit & Corporate Strategy Diagnostics",
            themeCategory = "Data-Driven Certainty Scale (0% - 100%)",
            presenterNotes = "Explain how corporate analysts use modal verbs (Must, Might/Could, Can't) to qualify their confidence levels during data analysis and strategic forecasting.",
            summary = "Expressing precise confidence levels based on empirical evidence."
        )
    )

    private val _currentSlideIndex = MutableStateFlow(0)
    val currentSlideIndex: StateFlow<Int> = _currentSlideIndex.asStateFlow()

    private val _isPresenterMode = MutableStateFlow(false)
    val isPresenterMode: StateFlow<Boolean> = _isPresenterMode.asStateFlow()

    private val _currentTheme = MutableStateFlow(ThemePalette.CORPORATE)
    val currentTheme: StateFlow<ThemePalette> = _currentTheme.asStateFlow()

    private val _currentTransition = MutableStateFlow(TransitionType.SLIDE)
    val currentTransition: StateFlow<TransitionType> = _currentTransition.asStateFlow()

    private val _isDashboardOpen = MutableStateFlow(true)
    val isDashboardOpen: StateFlow<Boolean> = _isDashboardOpen.asStateFlow()

    // Slide 2 Interactive state: Present Perfect
    private val _investorChoice = MutableStateFlow(0)
    val investorChoice: StateFlow<Int> = _investorChoice.asStateFlow()

    // Slide 3 Interactive state: Conditionals
    private val _selectedConditional = MutableStateFlow(1)
    val selectedConditional: StateFlow<Int> = _selectedConditional.asStateFlow()

    // Slide 4 Interactive state: Active/Passive slider
    private val _accountabilityValue = MutableStateFlow(0.8f)
    val accountabilityValue: StateFlow<Float> = _accountabilityValue.asStateFlow()

    // Slide 5 Interactive state: Certainty evidence checks
    private val _evidenceLogs = MutableStateFlow(setOf(1, 3))
    val evidenceLogs: StateFlow<Set<Int>> = _evidenceLogs.asStateFlow()

    // Quiz State
    private val _showQuiz = MutableStateFlow(false)
    val showQuiz: StateFlow<Boolean> = _showQuiz.asStateFlow()

    private val _quizAnswers = MutableStateFlow(mapOf<Int, Int>())
    val quizAnswers: StateFlow<Map<Int, Int>> = _quizAnswers.asStateFlow()

    // Text To Speech
    private var tts: TextToSpeech? = null
    private val _isSpeaking = MutableStateFlow(false)
    val isSpeaking: StateFlow<Boolean> = _isSpeaking.asStateFlow()

    // AI Tutor State
    private val _aiTutorMessages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val aiTutorMessages: StateFlow<List<ChatMessage>> = _aiTutorMessages.asStateFlow()

    private val _isAiTutorLoading = MutableStateFlow(false)
    val isAiTutorLoading: StateFlow<Boolean> = _isAiTutorLoading.asStateFlow()

    private val _aiTutorError = MutableStateFlow<String?>(null)
    val aiTutorError: StateFlow<String?> = _aiTutorError.asStateFlow()

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    fun sendAiTutorMessage(userQuestion: String) {
        val userMsg = ChatMessage(sender = "User", isUser = true, text = userQuestion)
        _aiTutorMessages.update { it + userMsg }
        _isAiTutorLoading.value = true
        _aiTutorError.value = null

        val currentSlide = slides[_currentSlideIndex.value]

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val apiKey = BuildConfig.GEMINI_API_KEY
                if (apiKey.isEmpty() || apiKey == "MY_GEMINI_API_KEY") {
                    withContext(Dispatchers.Main) {
                        _isAiTutorLoading.value = false
                        val reply = "Hello! I am your AI Grammar Tutor. On '${currentSlide.title}': ${currentSlide.presenterNotes}"
                        _aiTutorMessages.update { it + ChatMessage(sender = "AI Tutor", isUser = false, text = reply) }
                    }
                    return@launch
                }

                val promptText = """
                    You are an expert AI English Grammar & Executive Communication Tutor.
                    The user is viewing Slide ${currentSlide.slideNumber}: "${currentSlide.title}" (${currentSlide.subtitle}).
                    Topic summary: "${currentSlide.summary}"
                    Presenter talking points: "${currentSlide.presenterNotes}"

                    User question: "$userQuestion"

                    Provide a helpful, precise answer focusing on practical workplace nuance and grammar rules. Keep answers concise under 120 words.
                """.trimIndent()

                val jsonBody = JSONObject().apply {
                    put("contents", JSONArray().apply {
                        put(JSONObject().apply {
                            put("parts", JSONArray().apply {
                                put(JSONObject().apply {
                                    put("text", promptText)
                                })
                            })
                        })
                    })
                }

                val mediaType = "application/json; charset=utf-8".toMediaType()
                val body = jsonBody.toString().toRequestBody(mediaType)
                val request = Request.Builder()
                    .url("https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent?key=$apiKey")
                    .post(body)
                    .build()

                val response = okHttpClient.newCall(request).execute()
                val responseStr = response.body?.string() ?: ""

                if (response.isSuccessful && responseStr.isNotEmpty()) {
                    val root = JSONObject(responseStr)
                    val candidates = root.optJSONArray("candidates")
                    val firstCandidate = candidates?.optJSONObject(0)
                    val contentObj = firstCandidate?.optJSONObject("content")
                    val parts = contentObj?.optJSONArray("parts")
                    val textResult = parts?.optJSONObject(0)?.optString("text")

                    val replyText = textResult ?: "Regarding ${currentSlide.title}: ${currentSlide.presenterNotes}"

                    withContext(Dispatchers.Main) {
                        _isAiTutorLoading.value = false
                        _aiTutorMessages.update { it + ChatMessage(sender = "AI Tutor", isUser = false, text = replyText) }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        _isAiTutorLoading.value = false
                        val fallback = "AI Tutor advice on ${currentSlide.title}: ${currentSlide.presenterNotes}"
                        _aiTutorMessages.update { it + ChatMessage(sender = "AI Tutor", isUser = false, text = fallback) }
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _isAiTutorLoading.value = false
                    val fallback = "AI Tutor advice on ${currentSlide.title}: ${currentSlide.presenterNotes}"
                    _aiTutorMessages.update { it + ChatMessage(sender = "AI Tutor", isUser = false, text = fallback) }
                }
            }
        }
    }

    init {
        tts = TextToSpeech(application, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts?.language = Locale.US
        }
    }

    fun speakPresenterNotes() {
        val notes = slides[_currentSlideIndex.value].presenterNotes
        if (_isSpeaking.value) {
            tts?.stop()
            _isSpeaking.value = false
        } else {
            tts?.speak(notes, TextToSpeech.QUEUE_FLUSH, null, "PresenterNotesTTS")
            _isSpeaking.value = true
        }
    }

    fun nextSlide() {
        if (_currentSlideIndex.value < slides.size - 1) {
            _currentSlideIndex.update { it + 1 }
        }
    }

    fun prevSlide() {
        if (_currentSlideIndex.value > 0) {
            _currentSlideIndex.update { it - 1 }
        }
    }

    fun goToSlide(index: Int) {
        if (index in slides.indices) {
            _currentSlideIndex.value = index
        }
    }

    fun togglePresenterMode() {
        _isPresenterMode.update { !it }
    }

    fun setTheme(theme: ThemePalette) {
        _currentTheme.value = theme
    }

    fun setTransition(transition: TransitionType) {
        _currentTransition.value = transition
    }

    fun toggleDashboard() {
        _isDashboardOpen.update { !it }
    }

    fun setInvestorChoice(choice: Int) {
        _investorChoice.value = choice
    }

    fun setSelectedConditional(choice: Int) {
        _selectedConditional.value = choice
    }

    fun setAccountabilityValue(value: Float) {
        _accountabilityValue.value = value
    }

    fun toggleEvidenceLog(id: Int) {
        _evidenceLogs.update { current ->
            if (current.contains(id)) current - id else current + id
        }
    }

    fun toggleQuiz() {
        _showQuiz.update { !it }
    }

    fun answerQuiz(questionId: Int, optionIndex: Int) {
        _quizAnswers.update { it + (questionId to optionIndex) }
    }

    val quizQuestions = listOf(
        QuizQuestion(
            id = 1,
            slideId = 2,
            scenario = "Pitching to a VC firm regarding your startup's active funding round:",
            question = "Which sentence correctly signals that your company is currently backed and still operating with that capital?",
            options = listOf(
                "We raised \$2M in seed capital in 2024.",
                "We have raised \$2M since inception.",
                "We had raised \$2M last year."
            ),
            correctAnswerIndex = 1,
            explanation = "Present Perfect ('have raised... since inception') connects past fundraising to your current ongoing status."
        ),
        QuizQuestion(
            id = 2,
            slideId = 3,
            scenario = "In a high-stakes real estate deal, you want to propose a fee discount if the buyer closes this week:",
            question = "Which conditional choice signals a realistic, highly likely deal closing?",
            options = listOf(
                "If you close the deal this week, we will grant a 5% rebate.",
                "If you closed the deal this week, we would grant a 5% rebate.",
                "If you had closed the deal this week, we would have granted a rebate."
            ),
            correctAnswerIndex = 0,
            explanation = "1st Conditional ('If you close... we will grant') communicates an actionable, real-world offer."
        ),
        QuizQuestion(
            id = 3,
            slideId = 4,
            scenario = "PR Statement during an outage to demonstrate corporate integrity:",
            question = "Which statement uses Active Voice to take maximum ownership?",
            options = listOf(
                "A system outage was experienced by users.",
                "Errors were made in the server cluster.",
                "Our infrastructure team made a deployment configuration error."
            ),
            correctAnswerIndex = 2,
            explanation = "Active Voice specifies the actor ('Our infrastructure team') and action, showing maximum transparency."
        )
    )

    fun exportPresentation(): String {
        val sb = StringBuilder()
        sb.append("# Executive Presentation Deck: Advanced English Grammar\n\n")
        slides.forEach { slide ->
            sb.append("## Slide ${slide.slideNumber}: ${slide.title}\n")
            sb.append("**Subtitle**: ${slide.subtitle}\n")
            sb.append("**Theme**: ${slide.themeCategory}\n")
            sb.append("**Presenter Notes**: ${slide.presenterNotes}\n\n")
            sb.append("---\n\n")
        }
        return sb.toString()
    }

    fun shareDeckIntent(): Intent {
        val text = exportPresentation()
        return Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Executive Presentation: Mastering Professional Nuance")
            putExtra(Intent.EXTRA_TEXT, text)
        }
    }

    override fun onCleared() {
        tts?.stop()
        tts?.shutdown()
        super.onCleared()
    }
}
