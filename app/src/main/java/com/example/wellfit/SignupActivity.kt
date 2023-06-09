package com.example.wellfit

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.wellfit.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    var gender = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLayout()
    }

    private fun initLayout() {
        val moveBtn = binding.moveSignUp
        binding.manBtn.setOnClickListener {
            if(gender=="남성"){
                gender = ""
                binding.manBtn.setTextColor(Color.parseColor("#478C5C"))
                binding.manBtn.setBackgroundResource(R.drawable.signupbackground2)
            }else{
                gender = "남성"
                binding.manBtn.setTextColor(Color.parseColor("#ffffff"))
                binding.manBtn.setBackgroundResource(R.drawable.signupbackground)
                binding.womanBtn.setTextColor(Color.parseColor("#478C5C"))
                binding.womanBtn.setBackgroundResource(R.drawable.signupbackground2)
            }
        }
        binding.womanBtn.setOnClickListener {
            if(gender=="여성"){
                gender = ""
                binding.womanBtn.setTextColor(Color.parseColor("#478C5C"))
                binding.womanBtn.setBackgroundResource(R.drawable.signupbackground2)
            }else{
                gender = "여성"
                binding.womanBtn.setTextColor(Color.parseColor("#ffffff"))
                binding.womanBtn.setBackgroundResource(R.drawable.signupbackground)
                binding.manBtn.setTextColor(Color.parseColor("#478C5C"))
                binding.manBtn.setBackgroundResource(R.drawable.signupbackground2)
            }
        }

        moveBtn.setOnClickListener{
            if (binding.name.text.isEmpty() || binding.birth.text.isEmpty() || binding.phone.text.isEmpty() || gender == ""){
                Toast.makeText(this, "다시 확인해주세요.", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this,SignupActivity2::class.java)
                intent.putExtra("name",binding.name.text)
                intent.putExtra("birth",binding.birth.text)
                intent.putExtra("phone",binding.phone.text)
                intent.putExtra("gender",gender)
                startActivity(intent)
            }
        }

    }
}