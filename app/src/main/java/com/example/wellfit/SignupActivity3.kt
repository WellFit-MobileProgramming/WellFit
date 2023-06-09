package com.example.wellfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.wellfit.databinding.ActivitySignup3Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignupActivity3 : AppCompatActivity() {
    lateinit var binding: ActivitySignup3Binding
    private var auth : FirebaseAuth? = null
    private var fireStore : FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignup3Binding.inflate(layoutInflater)
        auth = Firebase.auth
        fireStore = Firebase.firestore
        setContentView(binding.root)
        initLayout()
    }

    private fun initLayout() {
        val intent1 = intent
        val intent2 = Intent(this,LoginActivity::class.java)
        val name = intent1.getStringExtra("name")
        val birth = intent1.getStringExtra("birth")
        val phone = intent1.getStringExtra("phone")
        val gender = intent1.getStringExtra("gender")
        val email = intent1.getStringExtra("email")
        val pw = intent1.getStringExtra("pw")
        val moveBtn = binding.moveHome
        moveBtn.setOnClickListener{
            val height = binding.height.text.toString()
            val weight = binding.weight.text.toString()
            if (name !=null && birth !=null&&phone != null&&gender != null&&email != null && pw !=null) {
                if(height.isNotEmpty() && weight.isNotEmpty()){
                    Log.e("돼","ehlsl")
                    createAccount(name,birth,phone,gender,email,pw,intent2)
                }
            }
        }
    }
    private fun createAccount(name:String,birth:String,phone:String,gender:String,email:String,pw:String,intent:Intent){
        auth?.createUserWithEmailAndPassword(email, pw)
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    intent.putExtra("signup",true)
                    val userInfo = User(auth?.uid,name,birth,phone,gender,email,binding.height.text.toString(),binding.weight.text.toString())
                    fireStore?.collection("users")?.document(auth?.uid.toString())?.set(userInfo)
                    startActivity(intent)
                } else {
                    intent.putExtra("signup",false)
                    Log.e("회원가입 실패",task.result.toString())
                }
            }
    }
}