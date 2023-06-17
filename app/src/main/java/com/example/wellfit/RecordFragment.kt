package com.example.wellfit

import HorizontalItemDecorator
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wellfit.databinding.FragmentRecordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class RecordFragment : Fragment() {

    lateinit var binding: FragmentRecordBinding
    private var auth: FirebaseAuth? = null
    private var fireStore: FirebaseFirestore? = null
    val recordWorkout = ArrayList<WorkoutType>()
    lateinit var workoutAdapter : RecordWorkoutRVAdapter
    var specialDate = ""

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRecordBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        fireStore = Firebase.firestore
        binding.recordWorkoutRecyclerview.minimumHeight = 130

        //캘린더 액티비티에서 넘겨준 값 받아옴
        specialDate = arguments?.getString("changeDate").toString()
        //날짜, 구분선, 메시지 조정
        binding.recordMonth.text = specialDate!!.substring(4, 6).toInt().toString() + "월"
        binding.recordDateTv.text =
            specialDate!!.substring(4, 6).toInt().toString() + "월 " + specialDate!!.substring(6)
                .toInt().toString() + "일"
        settingPlusBtn()

        //주간캘린더 불러오기
        binding.recordWeekRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val recordWeekRVAdapter = RecordWeekRVAdapter(specialDate!!)
        binding.recordWeekRecyclerview.adapter = recordWeekRVAdapter
        //주간캘린더 클릭이벤트
        recordWeekRVAdapter.setMyItemClickListener(object :
            RecordWeekRVAdapter.MyItemClickListener {
            override fun onItemClick(day: String) {
                binding.recordMonth.text = day.substring(4, 6).toInt().toString() + "월"
                binding.recordDateTv.text =
                    day.substring(4, 6).toInt().toString() + "월 " + day.substring(6).toInt()
                        .toString() + "일"
                specialDate= day
                recordWorkout.clear()
                settingPlusBtn()
                setData()
            }
        })

        initWorkoutRecyclerView()

        setData()

        //화면 넓이
        val display = requireActivity().windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x
        val height = size.y
        binding.recordWeekRecyclerview.addItemDecoration(
            HorizontalItemDecorator(
                width / 75,
                height / 200
            )
        )

        onClick()

        return binding.root
    }
    fun settingPlusBtn() = if (today()!=specialDate){
        binding.recordWorkoutPlusBtn.visibility = View.GONE
        binding.recordWorkoutPlusTv.visibility = View.GONE
    }else{
        binding.recordWorkoutPlusBtn.visibility = View.VISIBLE
        binding.recordWorkoutPlusTv.visibility = View.VISIBLE
    }

    fun initWorkoutRecyclerView(){
        binding.recordWorkoutRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        workoutAdapter = RecordWorkoutRVAdapter(recordWorkout)
        binding.recordWorkoutRecyclerview.adapter = workoutAdapter
        workoutAdapter.notifyDataSetChanged()
    }

    private fun onClick() {

        binding.recordWorkoutPlusBtn.setOnClickListener {
            var workoutSelectFragment = WorkoutSelectFragment()
            var bundle = Bundle()
            bundle.putString("date", specialDate)
            workoutSelectFragment.arguments = bundle
            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.main_frm, workoutSelectFragment)
                .commit()
        }
        binding.recordLeftarrow.setOnClickListener{
            var homeFragment = HomeFragment()
            var bundle = Bundle()
            homeFragment.arguments = bundle
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, homeFragment)
                .commitAllowingStateLoss()
        }
        binding.homeClose.setOnClickListener{
            var homeFragment = HomeFragment()
            var bundle = Bundle()
            homeFragment.arguments = bundle
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, homeFragment)
                .commitAllowingStateLoss()
        }
        binding.recordWorkoutStartBtn.setOnClickListener {
            val dlg = EditDialogActivity(activity as AppCompatActivity)
            dlg.setOnOKClickedListener {
                    content->
                if(content==="ok"){
                    var workoutCheckFragment = WorkoutCheckFragment()
                    var bundle = Bundle()
                    bundle.putString("date",specialDate)
                    bundle.putSerializable("data",recordWorkout)
                    workoutCheckFragment.arguments = bundle
                    (context as MainActivity).supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, workoutCheckFragment)
                        .commitAllowingStateLoss()
                }
            }
            dlg.show("운동을 시작하시겠습니까?")
        }
    }

    private fun today(): String {
        val now: Long = System.currentTimeMillis()
        val date = Date(now)
        val dateFormat3 = SimpleDateFormat("yyyyMMdd", Locale("ko", "KR"))
        return dateFormat3.format(date)
    }

    private fun setData() {
        val workoutRef = fireStore?.collection("workouts")?.document(auth?.uid.toString())
        workoutRef?.get()
            ?.addOnSuccessListener { data ->
                if(data.exists()){
                    val workoutArray  = data.toObject<Workout>()
                    if (workoutArray != null) {
                        for (i in workoutArray.workoutType!!) {
                            if (i.date == specialDate){
                                recordWorkout.add(i)
                                Log.e("운동", recordWorkout.toString())
                            }
                        }
                        initWorkoutRecyclerView()
                    }
                }else{
                    binding.recordWorkoutEmpty.visibility = View.VISIBLE
                }
                if (recordWorkout.isEmpty()){
                    binding.recordWorkoutEmpty.visibility = View.VISIBLE
                }else{
                    binding.recordWorkoutEmpty.visibility = View.GONE
                }
            }
            ?.addOnFailureListener {
                Log.e("운동리스트", "데이터수신실패")
                binding.recordWorkoutEmpty.visibility = View.VISIBLE
            }
    }
}
