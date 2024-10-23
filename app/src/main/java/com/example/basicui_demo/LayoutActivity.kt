package com.example.basicui_demo

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.FrameLayout
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Spinner
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class LayoutActivity : AppCompatActivity() {
    private lateinit var demoContainer: FrameLayout
    private lateinit var layoutSpinner: Spinner

    private companion object {
        val LAYOUTS = arrayOf(
            "Linear Layout (Vertical)",
            "Linear Layout (Horizontal)",
            "Relative Layout",
            "Constraint Layout",
            "Grid Layout",
            "Table Layout"
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)

        demoContainer = findViewById(R.id.demoContainer)
        layoutSpinner = findViewById(R.id.layoutSpinner)

        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            LAYOUTS
        ).also { adapter ->
            layoutSpinner.adapter = adapter
        }

        layoutSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                updateLayoutDemo(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun updateLayoutDemo(position: Int) {
        // Remove any existing views
        demoContainer.removeAllViews()

        // Create new demo layout based on selection
        val demoLayout = when (position) {
            0 -> createVerticalLinearLayoutDemo()
            1 -> createHorizontalLinearLayoutDemo()
            2 -> createRelativeLayoutDemo()
            3 -> createConstraintLayoutDemo()
            4 -> createGridLayoutDemo()
            5 -> createTableLayoutDemo()
            else -> null
        }

        demoLayout?.let { demoContainer.addView(it) }
    }

    private fun createVerticalLinearLayoutDemo(): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )

            val texts = arrayOf("Top", "Center", "Bottom")
            val weights = floatArrayOf(1f, 2f, 1f)
            val colors = intArrayOf(Color.RED, Color.GREEN, Color.BLUE)

            texts.forEachIndexed { index, text ->
                addView(TextView(context).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        0,
                        weights[index]
                    ).apply {
                        setMargins(8, 8, 8, 8)
                    }
                    setText(text)
                    setBackgroundColor(colors[index])
                    gravity = Gravity.CENTER
                    setTextColor(Color.WHITE)
                })
            }
        }
    }

    private fun createHorizontalLinearLayoutDemo(): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )

            val texts = arrayOf("Left", "Center", "Right")
            val weights = floatArrayOf(1f, 2f, 1f)
            val colors = intArrayOf(Color.RED, Color.GREEN, Color.BLUE)

            texts.forEachIndexed { index, text ->
                addView(TextView(context).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        weights[index]
                    ).apply {
                        setMargins(8, 8, 8, 8)
                    }
                    setText(text)
                    setBackgroundColor(colors[index])
                    gravity = Gravity.CENTER
                    setTextColor(Color.WHITE)
                })
            }
        }
    }

    private fun createRelativeLayoutDemo(): RelativeLayout {
        return RelativeLayout(this).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )

            // Center view
            val centerView = TextView(context).apply {
                id = View.generateViewId()
                layoutParams = RelativeLayout.LayoutParams(200, 200).apply {
                    addRule(RelativeLayout.CENTER_IN_PARENT)
                }
                text = "Center"
                setBackgroundColor(Color.GREEN)
                gravity = Gravity.CENTER
                setTextColor(Color.WHITE)
            }

            // Top view
            val topView = TextView(context).apply {
                layoutParams = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    100
                ).apply {
                    addRule(RelativeLayout.ALIGN_PARENT_TOP)
                }
                text = "Top"
                setBackgroundColor(Color.RED)
                gravity = Gravity.CENTER
                setTextColor(Color.WHITE)
            }

            // Bottom view
            val bottomView = TextView(context).apply {
                layoutParams = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    100
                ).apply {
                    addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
                }
                text = "Bottom"
                setBackgroundColor(Color.BLUE)
                gravity = Gravity.CENTER
                setTextColor(Color.WHITE)
            }

            addView(centerView)
            addView(topView)
            addView(bottomView)
        }
    }

    private fun createGridLayoutDemo(): GridLayout {
        return GridLayout(this).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )

            rowCount = 3
            columnCount = 3

            val colors = intArrayOf(
                Color.RED, Color.GREEN, Color.BLUE,
                Color.YELLOW, Color.CYAN, Color.MAGENTA,
                Color.GRAY, Color.DKGRAY, Color.BLACK
            )

            repeat(9) { i ->
                addView(TextView(context).apply {
                    layoutParams = GridLayout.LayoutParams().apply {
                        width = 0
                        height = 0
                        setMargins(8, 8, 8, 8)
                        columnSpec = GridLayout.spec(i % 3, 1f)
                        rowSpec = GridLayout.spec(i / 3, 1f)
                    }
                    text = "Cell ${i + 1}"
                    setBackgroundColor(colors[i])
                    gravity = Gravity.CENTER
                    setTextColor(Color.WHITE)
                })
            }
        }
    }

    private fun createTableLayoutDemo(): TableLayout {
        return TableLayout(this).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )

            isStretchAllColumns = true

            val data = arrayOf(
                arrayOf("Header 1", "Header 2", "Header 3"),
                arrayOf("Row 1-1", "Row 1-2", "Row 1-3"),
                arrayOf("Row 2-1", "Row 2-2", "Row 2-3")
            )

            val rowColors = intArrayOf(Color.DKGRAY, Color.LTGRAY, Color.GRAY)

            data.forEachIndexed { rowIndex, rowData ->
                addView(TableRow(context).apply {
                    rowData.forEach { cellText ->
                        addView(TextView(context).apply {
                            text = cellText
                            setPadding(16, 16, 16, 16)
                            setBackgroundColor(rowColors[rowIndex])
                            gravity = Gravity.CENTER
                            setTextColor(Color.WHITE)
                        })
                    }
                })
            }
        }
    }

    private fun createConstraintLayoutDemo(): ConstraintLayout {
        return ConstraintLayout(this).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )

            // Center button
            val centerButton = Button(context).apply {
                id = View.generateViewId()
                text = "Center"
                layoutParams = ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                    bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
                    startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                    endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                }
            }

            // Top button
            val topButton = Button(context).apply {
                id = View.generateViewId()
                text = "Top"
                layoutParams = ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                    startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                    endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                    bottomToTop = centerButton.id
                }
            }

            // Bottom button
            val bottomButton = Button(context).apply {
                text = "Bottom"
                layoutParams = ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
                    startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                    endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                    topToBottom = centerButton.id
                }
            }

            addView(centerButton)
            addView(topButton)
            addView(bottomButton)
        }
    }
}