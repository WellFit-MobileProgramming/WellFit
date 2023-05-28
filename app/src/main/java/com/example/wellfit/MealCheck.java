package com.example.wellfit;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;

public class MealCheck extends AppCompatActivity {
    AppCompatRadioButton radio_breakfast, radio_lunch, radio_dinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_diet);
        radio_breakfast = findViewById(R.id.radio_breakfast);
        radio_lunch = findViewById(R.id.radio_lunch);
        radio_dinner = findViewById(R.id.radio_dinner);
    }

    public void onRadioButtonClicked(View view) {
        boolean isSelected = ((AppCompatRadioButton)view).isChecked();
        switch (view.getId()){
            case R.id.radio_breakfast:
                if(isSelected){
                    radio_breakfast.setTextColor(Color.parseColor("#FFFFFF"));
                    radio_lunch.setTextColor(Color.parseColor("#92CFA5"));
                    radio_dinner.setTextColor(Color.parseColor("#92CFA5"));
                }
                break;
            case R.id.radio_lunch:
                if(isSelected){
                    radio_lunch.setTextColor(Color.parseColor("#FFFFFF"));
                    radio_breakfast.setTextColor(Color.parseColor("#92CFA5"));
                    radio_dinner.setTextColor(Color.parseColor("#92CFA5"));
                }
                break;
            case R.id.radio_dinner:
                if(isSelected){
                    radio_dinner.setTextColor(Color.parseColor("#FFFFFF"));
                    radio_lunch.setTextColor(Color.parseColor("#92CFA5"));
                    radio_breakfast.setTextColor(Color.parseColor("#92CFA5"));
                }
                break;
        }
    }
}
