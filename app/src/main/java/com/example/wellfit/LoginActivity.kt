package com.example.wellfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.example.wellfit.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLayout()
    }

    private fun initLayout() {
        val moveBtn = binding.moveSignup
        val loginBtn = binding.loginBtn
        moveBtn.setOnClickListener{
            val intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }
        val id = binding.id
        val pw = binding.pw

        var isId = false
        var isPw = false

        id.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                isId = id.length()>0
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        pw.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(pw.length()>0&&isId){
                    loginBtn.isEnabled = true
                    loginBtn.setBackgroundResource(R.drawable.loginbtnbackgroundactive)
                }else{
                    loginBtn.isEnabled=false
                    loginBtn.setBackgroundResource(R.drawable.loginbtnbackground)
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        loginBtn.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}