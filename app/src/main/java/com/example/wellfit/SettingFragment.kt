package com.example.wellfit
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.wellfit.databinding.FragmentSettingBinding

//설정화면
class SettingFragment : Fragment(){
    lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)

        binding.profileEditBtn.setOnClickListener {
            var name = binding.name.text.toString()
            val dlg = EditDialogActivity(activity as AppCompatActivity)
            dlg.setOnOKClickedListener {
                    content->
                if(content==="ok"){

                    Toast.makeText(activity, "이름: $name", Toast.LENGTH_SHORT).show()
                }
            }
            dlg.show("이름을 ${name}으로 수정하시겠습니까?")
        }

        var isSelectedTimerNum = 0
        var isSelectedTime : String = "01:30"
        binding.timer1.setOnClickListener{
            if(isSelectedTimerNum!==1){
                isSelectedTimerNum=1
                isSelectedTime = "01:00"
                binding.timer1.setBackgroundResource(R.drawable.setting_timer_bg)
                binding.timer2.setBackgroundResource(R.drawable.calendar_box)
                binding.timer3.setBackgroundResource(R.drawable.calendar_box)
                binding.timer4.setBackgroundResource(R.drawable.calendar_box)
            }
        }
        binding.timer2.setOnClickListener{
            if(isSelectedTimerNum!==2){
                isSelectedTimerNum=2
                isSelectedTime = "01:30"
                binding.timer2.setBackgroundResource(R.drawable.setting_timer_bg)
                binding.timer1.setBackgroundResource(R.drawable.calendar_box)
                binding.timer3.setBackgroundResource(R.drawable.calendar_box)
                binding.timer4.setBackgroundResource(R.drawable.calendar_box)
            }
        }
        binding.timer3.setOnClickListener{
            if(isSelectedTimerNum!==3){
                isSelectedTimerNum=3
                isSelectedTime = "02:00"
                binding.timer3.setBackgroundResource(R.drawable.setting_timer_bg)
                binding.timer1.setBackgroundResource(R.drawable.calendar_box)
                binding.timer2.setBackgroundResource(R.drawable.calendar_box)
                binding.timer4.setBackgroundResource(R.drawable.calendar_box)
            }
        }
        binding.timer4.setOnClickListener{
            if(isSelectedTimerNum!==4){
                isSelectedTimerNum=4
                isSelectedTime = "02:30"
                binding.timer4.setBackgroundResource(R.drawable.setting_timer_bg)
                binding.timer1.setBackgroundResource(R.drawable.calendar_box)
                binding.timer3.setBackgroundResource(R.drawable.calendar_box)
                binding.timer2.setBackgroundResource(R.drawable.calendar_box)
            }
        }


        var editHeight:String
        var editWeight: String

        binding.infoEditBtn.setOnClickListener {
            val dlg = EditDialogActivity(activity as AppCompatActivity)
            dlg.setOnOKClickedListener {
                content->
                if(content==="ok"){
                    editHeight=binding.editHeight.text.toString()
                    editWeight = binding.editWeight.text.toString()
                    Toast.makeText(activity, "키: $editHeight, 몸무게: $editWeight, 타이머: $isSelectedTime", Toast.LENGTH_SHORT).show()
                }
            }
            dlg.show("운동 및 식단 정보를 수정하시겠습니까?")

        }

        binding.logoutBtn.setOnClickListener {
            val dlg = EditDialogActivity(activity as AppCompatActivity)
            dlg.setOnOKClickedListener {
                    content->
                if(content==="ok"){
                    Toast.makeText(activity, "로그아웃!", Toast.LENGTH_SHORT).show()
                }
            }
            dlg.show("로그아웃 하시겠습니까?")
        }
        binding.withdrawBtn.setOnClickListener {
            val dlg = EditDialogActivity(activity as AppCompatActivity)
            dlg.setOnOKClickedListener {
                    content->
                if(content==="ok"){
                    Toast.makeText(activity, "회원탈퇴!", Toast.LENGTH_SHORT).show()
                }
            }
            dlg.show("회원탈퇴 하시겠습니까?")
        }

        return binding.root
    }
}

