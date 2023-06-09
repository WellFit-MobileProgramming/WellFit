package com.example.wellfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.wellfit.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private var auth : FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        initLayout()
    }

    // 로그아웃하지 않을 시 자동 로그인 , 회원가입시 바로 로그인 됨
    public override fun onStart() {
        super.onStart()
        moveMainPage(auth?.currentUser)
    }

    private fun initLayout() {
        val intent1 = intent
        val intent2 = Intent(this,SignupActivity::class.java)
        val signupCheck = intent1.getBooleanExtra("signup",false)
        if(signupCheck) {
            Toast.makeText(
                this, "회원가입을 축하드립니다!",
                Toast.LENGTH_SHORT
            ).show()
        }
        val moveBtn = binding.moveSignup
        val loginBtn = binding.loginBtn
        moveBtn.setOnClickListener{
            startActivity(intent2)
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
            signIn(binding.id.text.toString(),binding.pw.text.toString())
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
    // 로그인
    private fun signIn(email: String, password: String) {

        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            baseContext, "로그인에 성공 하였습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                        moveMainPage(auth?.currentUser)
                    } else {
                        Toast.makeText(
                            baseContext, "로그인에 실패 하였습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
    // 유저정보 넘겨주고 메인 액티비티 호출
    fun moveMainPage(user: FirebaseUser?){
        if( user!= null){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}