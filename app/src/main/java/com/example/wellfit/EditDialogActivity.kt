package com.example.wellfit

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import com.example.wellfit.databinding.ActivityEditDialogBinding

class EditDialogActivity(private val context: AppCompatActivity) {
    private lateinit var binding : ActivityEditDialogBinding
    private val dlg = Dialog(context)
    private lateinit var listener: MyDialogOKClickedListener

    fun show(content : String) {
        binding = ActivityEditDialogBinding.inflate(context.layoutInflater)

        dlg.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        dlg.setContentView(binding.root)     //다이얼로그에 사용할 xml 파일을 불러옴
        dlg.setCancelable(false)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히지 않도록 함

        binding.content.text = content

        //ok 버튼 동작
        binding.okBtn.setOnClickListener {
            listener.onOKClicked("ok")
            dlg.dismiss()
        }
        binding.close.setOnClickListener {
            dlg.dismiss()
        }

        dlg.show()
    }

    fun setOnOKClickedListener(listener:(String)->Unit){
        this.listener = object :MyDialogOKClickedListener{
            override fun onOKClicked(content: String) {
                listener(content)
            }
        }
    }

    interface MyDialogOKClickedListener{
        fun onOKClicked(content: String)
    }
}