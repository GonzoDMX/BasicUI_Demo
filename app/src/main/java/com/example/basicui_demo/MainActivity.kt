package com.example.basicui_demo

import android.content.Intent
import android.graphics.Color
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.ProgressBar
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import com.example.basicui_demo.databinding.ActivityMainBinding
import com.google.android.material.chip.ChipGroup
import com.google.android.material.slider.Slider
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var nameEditText: TextInputEditText
    private lateinit var greetButton: Button
    private lateinit var clearButton: Button
    private lateinit var changeButton: Button
    private lateinit var outputTextView: TextView
    private lateinit var showTimeCheckBox: CheckBox
    private lateinit var greetingTypeRadioGroup: RadioGroup
    private lateinit var progressBar: ProgressBar
    private lateinit var titleSpinner: Spinner
    private lateinit var darkModeSwitch: SwitchMaterial
    private lateinit var fontSizeSlider: Slider
    private lateinit var colorChipGroup: ChipGroup


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        initializeViews()
        setupSpinner()
        setupListeners()
    }

    private fun initializeViews() {
        nameEditText = findViewById(R.id.nameEditText)
        greetButton = findViewById(R.id.greetButton)
        clearButton = findViewById(R.id.clearButton)
        changeButton = findViewById(R.id.changeButton)
        outputTextView = findViewById(R.id.outputTextView)
        showTimeCheckBox = findViewById(R.id.showTimeCheckBox)
        greetingTypeRadioGroup = findViewById(R.id.greetingTypeRadioGroup)
        progressBar = findViewById(R.id.progressBar)
        titleSpinner = findViewById(R.id.titleSpinner)
        darkModeSwitch = findViewById(R.id.darkModeSwitch)
        fontSizeSlider = findViewById(R.id.fontSizeSlider)
    }

    private fun setupSpinner() {
        ArrayAdapter.createFromResource(
            this,
            R.array.titles_array,  // Define this in strings.xml: Mr., Mrs., Ms., Dr.
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            titleSpinner.adapter = adapter
        }
    }

    private fun setupListeners() {
        greetButton.setOnClickListener { generateGreeting() }
        clearButton.setOnClickListener { clearFields() }
        changeButton.setOnClickListener { changeActivity() }

        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
        }

        fontSizeSlider.addOnChangeListener { _, value, _ ->
            outputTextView.textSize = value
        }

        // Simulate progress
        Handler(Looper.getMainLooper()).postDelayed({
            progressBar.progress = 100
        }, 2000)
    }

    private fun generateGreeting() {
        val name = nameEditText.text.toString().trim()
        if (name.isEmpty()) {
            nameEditText.error = "Please enter a name"
            return
        }

        val title = titleSpinner.selectedItem.toString()
        val greeting = when (greetingTypeRadioGroup.checkedRadioButtonId) {
            R.id.casualGreeting -> "Hey"
            R.id.formalGreeting -> "Good day"
            else -> "Hello"
        }

        var message = "$greeting, $title $name!"

        if (showTimeCheckBox.isChecked) {
            val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
            message += "\nCurrent time is: $currentTime"
        }

        outputTextView.text = message
    }

    private fun clearFields() {
        nameEditText.text?.clear()
        outputTextView.text = ""
        showTimeCheckBox.isChecked = false
        greetingTypeRadioGroup.check(R.id.casualGreeting)
        titleSpinner.setSelection(0)
        darkModeSwitch.isChecked = false
        fontSizeSlider.value = 18f
        colorChipGroup.clearCheck()
        progressBar.progress = 0
    }

    private fun changeActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }
}