package com.example.wellfit

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.wellfit.databinding.FragmentModalBinding

class ModalFragment : DialogFragment() {
    lateinit var binding: FragmentModalBinding

    var ex_category:String?=null
    var ex_name:String?=null
    var explain:String?=null
    var ex_link:String?=null

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
        binding.goToLink.setOnClickListener {
            val searchString = this.ex_name + " 운동 방법"
            val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/results?search_query=$searchString"))
            startActivity(intent)
        }
    }

    fun setData(ex_category: String, ex_name: String, explain: String, ex_link: String){
        this.ex_category = ex_category
        this.ex_name = ex_name
        this.explain = explain
        this.ex_link = ex_link
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        return dialog
    }

    override fun onStart(){
        super.onStart()
        binding.categoryBtn.text = this.ex_category
        binding.exName.text = this.ex_name
        binding.explain.text = this.explain
        dialog?.window?.apply {
            setGravity(Gravity.BOTTOM)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // 배경 투명하게 설정
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, (resources.displayMetrics.heightPixels * 0.6).toInt()) // 다이얼로그의 크기 재조정
        }
    }
}