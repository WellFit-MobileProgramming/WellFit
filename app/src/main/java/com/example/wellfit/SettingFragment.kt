package com.example.wellfit
import android.content.Context
import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.wellfit.databinding.FragmentSettingBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

//설정화면
class SettingFragment : Fragment(){
    lateinit var binding: FragmentSettingBinding
    private var auth : FirebaseAuth? = null
    var isSelectedTime : String = "01:30"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        auth = Firebase.auth
        binding = FragmentSettingBinding.inflate(inflater, container, false)

        //db 정보가져오기
        val db = FirebaseFirestore.getInstance()

        db.collection("users").document(auth?.uid.toString())
            .get()
            .addOnSuccessListener { result->
                binding.name.setText(result["name"].toString())
                binding.editWeight.setText(result["weight"].toString())
                binding.editHeight.setText(result["height"].toString())
            }

        var map = mutableMapOf<String, Any>()

        //프로필 수정
        binding.profileEditBtn.setOnClickListener {
            var name = binding.name.text.toString()
            val dlg = EditDialogActivity(activity as AppCompatActivity)
            dlg.setOnOKClickedListener {
                    content->
                if(content==="ok"){
                    map["name"]=name
                    db.collection("users").document(auth?.uid.toString())
                        .update(map)
                        .addOnCompleteListener {
                            if(it.isSuccessful){
                                Toast.makeText(activity, "이름: $name", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }
            dlg.show("이름을 ${name}으로 수정하시겠습니까?")
        }

        //타이머 선택
        var isSelectedTimerNum = 0
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


        //식단, 운동 정보 수정
        var editHeight:String
        var editWeight: String

        binding.infoEditBtn.setOnClickListener {
            editHeight=binding.editHeight.text.toString()
            editWeight = binding.editWeight.text.toString()
            val dlg = EditDialogActivity(activity as AppCompatActivity)
            dlg.setOnOKClickedListener {
                content->
                if(content==="ok"){
                    map["height"]=editHeight
                    db.collection("users").document(auth?.uid.toString())
                        .update(map)
                        .addOnCompleteListener {
                            if(it.isSuccessful){
                                map["weight"]=editWeight
                                db.collection("users").document(auth?.uid.toString())
                                    .update(map)
                                    .addOnCompleteListener {
                                        if(it.isSuccessful){
                                            Log.e("수정",editHeight)
                                            Toast.makeText(binding.root.context, "키: $editHeight, 몸무게: $editWeight, 타이머: $isSelectedTime", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                            }
                        }
                    val timer = Timer(isSelectedTime)
                    db.collection("timer").document(auth?.uid.toString())
                        .set(timer)
                        .addOnSuccessListener {
                            Log.e("SettingFragment", "토스트 메시지 호출 직전")
                            Toast.makeText(activity?.applicationContext, "타이머: $isSelectedTime", Toast.LENGTH_LONG).show()
                            Log.e("SettingFragment", "토스트 메시지 호출 직후")
                        }
                }
            }
            dlg.show("운동 및 식단 정보를 수정하시겠습니까?")

        }

        //로그아웃
        binding.logoutBtn.setOnClickListener {
            val dlg = EditDialogActivity(activity as AppCompatActivity)
            dlg.setOnOKClickedListener {
                    content->
                if(content==="ok"){
                    val intent = Intent(activity, LoginActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    auth?.signOut()
                }
            }
            dlg.show("로그아웃 하시겠습니까?")
        }

        //회원탈퇴
        binding.withdrawBtn.setOnClickListener {
            val dlg = EditDialogActivity(activity as AppCompatActivity)
            dlg.setOnOKClickedListener {
                    content->
                if(content==="ok"){
                    val user = auth!!.currentUser!!
                    user.delete()
                        .addOnCompleteListener {
                            task ->
                            if(task.isSuccessful){
                                val intent = Intent(activity, LoginActivity::class.java)
                               intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                               startActivity(intent)
                            }
                        }
                }
            }
            dlg.show("회원탈퇴 하시겠습니까?")
        }

        binding.homeLeftarrow.setOnClickListener {
            var homeFragment = HomeFragment()
            var bundle = Bundle()
            homeFragment.arguments = bundle
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, homeFragment)
                .commitAllowingStateLoss()
        }

        return binding.root
    }

    lateinit var callback: OnBackPressedCallback
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                var homeFragment = HomeFragment()
                var bundle = Bundle()
                homeFragment.arguments = bundle
                (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, homeFragment)
                    .commitAllowingStateLoss()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
}

