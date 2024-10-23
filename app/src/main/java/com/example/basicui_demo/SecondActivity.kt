package com.example.basicui_demo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class SecondActivity : AppCompatActivity() {
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        initializeViews()
        setupListeners()
    }

    private fun initializeViews() {
        nextButton = findViewById(R.id.nextButton)
    }

    private fun setupListeners() {
        nextButton.setOnClickListener { changeActivity() }
    }

    private fun changeActivity() {
        val intent = Intent(this, LayoutActivity::class.java)
        startActivity(intent)
    }
}