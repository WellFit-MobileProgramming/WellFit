package com.example.wellfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wellfit.databinding.ActivitySignup2Binding

class SignupActivity2 : AppCompatActivity() {
    lateinit var binding: ActivitySignup2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignup2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        initLayout()
    }
    private fun initLayout() {
        val moveBtn = binding.moveSignup
        moveBtn.setOnClickListener{
            val intent = Intent(this,SignupActivity3::class.java)
            startActivity(intent)
        }
    }
}