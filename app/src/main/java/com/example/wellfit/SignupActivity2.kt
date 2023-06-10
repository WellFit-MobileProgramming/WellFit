package com.example.wellfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.wellfit.databinding.ActivitySignup2Binding
import java.util.regex.Pattern

class SignupActivity2 : AppCompatActivity() {
    lateinit var binding: ActivitySignup2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignup2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        initLayout()
    }
    private fun initLayout() {
        val intent1 = intent
        val name = intent1.getStringExtra("name").toString()
        val birth = intent1.getStringExtra("birth").toString()
        val phone = intent1.getStringExtra("phone").toString()
        val gender = intent1.getStringExtra("gender").toString()
        Log.e("돼?",name)

        val moveBtn = binding.moveSignup
        moveBtn.setOnClickListener{
            val email = binding.idEt.text.toString()
            val pw = binding.pwEt.text.toString()
            val pwCertification = binding.pwCertificationEt.text.toString()
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "이메일 형식이 아닙니다", Toast.LENGTH_SHORT).show()
            }else{
                if (!Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%^&*()-])(?=.*[a-zA-Z]).{8,20}$", pw)) {
                    Toast.makeText(this, "비밀번호 형식을 지켜주세요.", Toast.LENGTH_SHORT).show()
                }else{
                    if(pw != pwCertification){
                        Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                    }else{
                        val intent2 = Intent(this,SignupActivity3::class.java)
                        intent2.putExtra("name",name)
                        intent2.putExtra("birth",birth)
                        intent2.putExtra("phone",phone)
                        intent2.putExtra("gender",gender)
                        intent2.putExtra("email",email)
                        intent2.putExtra("pw",pw)
                        startActivity(intent2)
                    }
                }
            }
        }
    }
}