package com.example.wellfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.wellfit.databinding.ActivitySignup3Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignupActivity3 : AppCompatActivity() {
    lateinit var binding: ActivitySignup3Binding
    private var auth : FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignup3Binding.inflate(layoutInflater)
        auth = Firebase.auth
        setContentView(binding.root)
        initLayout()
    }

    private fun initLayout() {
        val name = intent.getStringExtra("name")
        val birth = intent.getStringExtra("birth")
        val phone = intent.getStringExtra("phone")
        val gender = intent.getStringExtra("gender")
        val email = intent.getStringExtra("email")
        val pw = intent.getStringExtra("pw")
        val moveBtn = binding.moveHome
        moveBtn.setOnClickListener{
            if (email != null && pw !=null) {
                createAccount(email,pw, intent)
            }
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
    private fun createAccount(email:String,pw:String,intent: Intent){
        auth?.createUserWithEmailAndPassword(email, pw)
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    intent.putExtra("signup",true)
                } else {
                    intent.putExtra("signup",false)
                }
            }
    }
}