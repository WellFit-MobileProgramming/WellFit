package com.example.wellfit

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.wellfit.databinding.FragmentModalBinding

class ModalFragment : DialogFragment() {
    lateinit var binding: FragmentModalBinding

    var type:String?=null
    var name:String?=null
    var content:String?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentModalBinding.inflate(inflater, container, false)
        initBtn()
        return binding.root
    }

    private fun initBtn(){
        binding.lmodalClose.setOnClickListener {
            dismiss()
        }
        binding.explain.movementMethod = ScrollingMovementMethod()
        binding.goToLink.setOnClickListener {
            val searchString = this.name + " 운동 방법"
            val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/results?search_query=$searchString"))
            startActivity(intent)
        }
    }

    fun setData(type: String, name: String, content: String){
        this.type = type
        this.name = name
        this.content = content
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        return dialog
    }

    override fun onStart(){
        super.onStart()
        if(this.type == "lower_body"){
            binding.categoryBtn.text = "하체"
        }else if (this.type == "arm"){
            binding.categoryBtn.text = "팔"
        }else if (this.type == "back"){
            binding.categoryBtn.text = "등"
        }else if (this.type == "chest"){
            binding.categoryBtn.text = "가슴"
        }else if (this.type == "shoulder"){
            binding.categoryBtn.text = "어깨"
        }
        binding.exName.text = this.name
        binding.explain.text = this.content
        dialog?.window?.apply {
            setGravity(Gravity.BOTTOM)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // 배경 투명하게 설정
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, (resources.displayMetrics.heightPixels * 0.6).toInt()) // 다이얼로그의 크기 재조정
        }
    }
}