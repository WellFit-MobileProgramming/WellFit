package com.example.wellfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        val intent = Intent(this,SignupActivity3::class.java)
        val name = intent.getStringExtra("name")
        val birth = intent.getStringExtra("birth")
        val phone = intent.getStringExtra("phone")
        val gender = intent.getStringExtra("gender")

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
                    if(!pw.equals(pwCertification)){
                        Toast.makeText(this, pw.equals(pwCertification).toString(), Toast.LENGTH_SHORT).show()
//                        Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                    }else{
                        intent.putExtra("name",name)
                        intent.putExtra("birth",birth)
                        intent.putExtra("phone",phone)
                        intent.putExtra("gender",gender)
                        intent.putExtra("email",email)
                        intent.putExtra("pw",pw)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}