package com.example.model

data class ChatMessage(
    val id: String = java.util.UUID.randomUUID().toString(),
    val sender: String, // "User" or "AI Tutor"
    val isUser: Boolean,
    val text: String,
    val timestamp: Long = System.currentTimeMillis()
)
