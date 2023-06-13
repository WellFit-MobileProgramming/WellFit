package com.example.wellfit

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.wellfit.databinding.ActivitySignupBinding
import java.util.regex.Pattern
import kotlin.properties.Delegates

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

        var isName = false
        var isBirth = false
        var isPhone = false

        val errorBox = binding.errorBox
        val errorMsg = binding.erroMsg

        errorBox.visibility = View.INVISIBLE

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
            checkInput(isName,isBirth,isPhone,gender)
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
            checkInput(isName,isBirth,isPhone,gender)
        }

        //이름, 생년월일, 핸드폰 번호 입력 실시간 감지
        binding.name.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                isName = binding.name.text.isNotEmpty()
                checkInput(isName,isBirth,isPhone,gender)
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        binding.birth.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                isBirth = binding.birth.text.isNotEmpty()
                checkInput(isName,isBirth,isPhone,gender)
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        binding.phone.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                isPhone = binding.phone.text.isNotEmpty()
                checkInput(isName,isBirth,isPhone,gender)
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        //다음 버튼 클릭
        moveBtn.setOnClickListener{
            //핸드폰 번호 형식 검사
            val regex = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";
            if(!Pattern.matches(regex,binding.phone.text.toString())){
                errorMsg.text = "핸드폰 번호을 형식에 맞게 작성해주세요"
                errorBox.visibility = View.VISIBLE
            }else{
                //생년월일 형식 검사
                if(binding.birth.text.length<6){
                    errorMsg.text = "생년월일을 형식에 맞게 작성해주세요!"
                    errorBox.visibility = View.VISIBLE
                }else{
                    val intent = Intent(this,SignupActivity2::class.java)
                    intent.putExtra("name",binding.name.text.toString())
                    intent.putExtra("birth",binding.birth.text.toString())
                    intent.putExtra("phone",binding.phone.text.toString())
                    intent.putExtra("gender",gender)
                    Log.e("왜?",binding.name.text.toString())
                    startActivity(intent)
                }
            }
        }
    }

    //이름, 생년월일, 핸드폰번호, 성별 입력 확인
    fun checkInput(isName:Boolean, isBirth:Boolean, isPhone:Boolean, gender:String) {
        if(isName&&isBirth&&isPhone&&gender.isNotEmpty()){
            binding.moveSignUp.setBackgroundColor(Color.parseColor("#92CFA5"))
            binding.moveSignUp.isEnabled = true
        }else{
            binding.moveSignUp.setBackgroundColor(Color.parseColor("#CCE7D4"))
            binding.moveSignUp.isEnabled = false
        }
    }
}