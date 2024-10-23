package com.example.basicui_demo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SecondActivity : AppCompatActivity() {
    private lateinit var nextButton: Button
    private lateinit var messageTextView: TextView  // Add this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        initializeViews()
        setupListeners()
        displayReceivedMessage()  // Add this
    }

    private fun initializeViews() {
        nextButton = findViewById(R.id.nextButton)
        messageTextView = findViewById(R.id.messageTextView)  // Add this
    }

    private fun setupListeners() {
        nextButton.setOnClickListener { changeActivity() }
    }

    private fun displayReceivedMessage() {
        val message = intent.getStringExtra("GREETING_MESSAGE") ?: "No greeting found"
        val value = intent.getFloatExtra("FONT_SIZE", 18f)
        messageTextView.text = message
        messageTextView.textSize = value
    }

    private fun changeActivity() {
        val intent = Intent(this, LayoutActivity::class.java)
        startActivity(intent)
    }
}