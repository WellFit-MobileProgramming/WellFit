package com.example.wellfit

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.fragment.app.Fragment
import com.example.wellfit.databinding.FragmentDietBinding

class DietFragment : Fragment() {
    lateinit var binding: FragmentDietBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDietBinding.inflate(inflater, container, false)

        initRadio()

        return binding.root
    }

    fun initRadio() {
        binding.radioGroup.setOnCheckedChangeListener {radioGroup, checkedID ->
            when (checkedID) {
                R.id.radio_breakfast -> {
                    binding.radioBreakfast.setTextColor(Color.parseColor("#FFFFFF"))
                    binding.radioLunch.setTextColor(Color.parseColor("#92CFA5"))
                    binding.radioDinner.setTextColor(Color.parseColor("#92CFA5"))
                    binding.firstEt.setText("")
                    binding.secondEt.setText("")
                }
                R.id.radio_lunch -> {
                    binding.radioLunch.setTextColor(Color.parseColor("#FFFFFF"))
                    binding.radioBreakfast.setTextColor(Color.parseColor("#92CFA5"))
                    binding.radioDinner.setTextColor(Color.parseColor("#92CFA5"))
                    binding.firstEt.setText("")
                    binding.secondEt.setText("")
                }
                R.id.radio_dinner -> {
                    binding.radioDinner.setTextColor(Color.parseColor("#FFFFFF"))
                    binding.radioLunch.setTextColor(Color.parseColor("#92CFA5"))
                    binding.radioBreakfast.setTextColor(Color.parseColor("#92CFA5"))
                    binding.firstEt.setText("")
                    binding.secondEt.setText("")
                }
            }
        }
    }


}