package com.example.wellfit

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatRadioButton

class MealCheck: AppCompatActivity() {
    lateinit var radio_b : AppCompatRadioButton
    lateinit var radio_l : AppCompatRadioButton
    lateinit var radio_d : AppCompatRadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_diet)
        radio_b = findViewById(R.id.radio_breakfast)
        radio_l = findViewById(R.id.radio_lunch)
        radio_d = findViewById(R.id.radio_dinner)
    }

    fun onRadioButtonClicked(view: View) {
        var isSelected: Boolean = true
        if (view is AppCompatRadioButton)
            isSelected = view.isChecked()
        when(view.getId()) {
            R.id.radio_breakfast ->
                if (isSelected) {
                    radio_b.setTextColor(Color.parseColor("#FFFFFF"))
                    radio_l.setTextColor(Color.parseColor("#92CFA5"))
                    radio_d.setTextColor(Color.parseColor("#92CFA5"))
                }
            R.id.radio_lunch ->
                if (isSelected) {
                    radio_l.setTextColor(Color.parseColor("#FFFFFF"))
                    radio_b.setTextColor(Color.parseColor("#92CFA5"))
                    radio_d.setTextColor(Color.parseColor("#92CFA5"))
                }
            R.id.radio_dinner ->
                if (isSelected) {
                    radio_d.setTextColor(Color.parseColor("#FFFFFF"))
                    radio_l.setTextColor(Color.parseColor("#92CFA5"))
                    radio_b.setTextColor(Color.parseColor("#92CFA5"))
                }
        }
    }
}