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

        init()

        return binding.root
    }

    fun init() {
        MealCheck()
    }
}