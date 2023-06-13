package com.example.wellfit

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
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

        val errorBox = binding.pwErrorBox
        val errorMsg = binding.erroMsg
        errorBox.visibility = View.INVISIBLE

        val moveBtn = binding.moveSignup

        var isId = false
        var isPw = false
        var isPwCer = false

        binding.idEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                isId = binding.idEt.text.isNotEmpty()
                checkInput(isId,isPw,isPwCer)
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        binding.pwEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                isPw = binding.pwEt.text.isNotEmpty()
                checkInput(isId,isPw,isPwCer)
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        binding.pwCertificationEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                isPwCer = binding.pwCertificationEt.text.isNotEmpty()
                checkInput(isId,isPw,isPwCer)
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        moveBtn.setOnClickListener{
            val email = binding.idEt.text.toString()
            val pw = binding.pwEt.text.toString()
            val pwCertification = binding.pwCertificationEt.text.toString()
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                errorMsg.text = "이메일 형식을 지켜주세요!"
                errorBox.visibility = View.VISIBLE
            }else{
                if (!Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%^&*()-])(?=.*[a-zA-Z]).{8,20}$", pw)) {
                    errorMsg.text = "비밀번호 형식을 지켜주세요!"
                    errorBox.visibility = View.VISIBLE
                }else{
                    if(pw != pwCertification){
                        errorMsg.text = "비밀번호가 일치하지 않습니다!"
                        errorBox.visibility = View.VISIBLE
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

    fun checkInput(isId:Boolean, isPw:Boolean, isPwCer:Boolean) {
        if(isId&&isPw&&isPwCer){
            binding.moveSignup.setBackgroundColor(Color.parseColor("#92CFA5"))
            binding.moveSignup.isEnabled = true
        }else{
            binding.moveSignup.setBackgroundColor(Color.parseColor("#CCE7D4"))
            binding.moveSignup.isEnabled = false
        }
    }
}