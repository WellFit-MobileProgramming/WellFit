package com.example.wellfit;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

class MealCheck : AppCompatActivity() {
    lateinit var radio_b : AppCompetRadioButton
    lateinit var radio_l : AppCompetRadioButton
    lateinit var radio_d : AppCompetRadioButton

    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContextView(R.layout.fragment_diet)
        radio_b = findViewById(R.id.radio_breakfast)
        radio_l = findViewById(R.id.radio_lunch)
        radio_d = findViewById(R.id.radion_dinner)
    }

    public fun onRadioButtonClicked(view: View) {
        var isSelected: boolean? = null
        if (view is AppCompatRadioButton)
            isSelected = view.isChecked()
        when(view.getId()) {
            R.id.radio_breakfast ->
                if (isSelected) {
                    radio_breakfast.setTextColor(Color.parseColor("#FFFFFF"))
                    radio_lunch.setTextColor(Color.parseColor("#92CFA5"))
                    radio_dinner.setTextColor(Color.parseColor("#92CFA5"))
                }
            R.id.radio_lunch ->
                if (isSelected) {
                    radio_lunch.setTextColor(Color.parseColor("#FFFFFF"))
                    radio_breakfast.setTextColor(Color.parseColor("#92CFA5"))
                    radio_dinner.setTextColor(Color.parseColor("#92CFA5"))
                }
            R.id.radio_dinner ->
                if (isSelected) {
                    radio_dinner.setTextColor(Color.parseColor("#FFFFFF"))
                    radio_lunch.setTextColor(Color.parseColor("#92CFA5"))
                    radio_breakfast.setTextColor(Color.parseColor("#92CFA5"))
                }
        }
    }
}