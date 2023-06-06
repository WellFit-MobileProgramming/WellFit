package com.example.wellfit

import HorizontalItemDecorator
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wellfit.databinding.FragmentRecordBinding
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date
import java.util.Locale

class RecordFragment : Fragment(){

    lateinit var binding: FragmentRecordBinding
    val recordWorkout = ArrayList<RecordWorkout>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRecordBinding.inflate(inflater, container, false)

        setData()

        //캘린더 액티비티에서 값이 넘어왔을 경우
        val specialDate = arguments?.getString("changeDate")
        if (specialDate != null) {
            //날짜, 구분선, 메시지 조정
            binding.recordMonth.setText(specialDate.substring(4,6).toInt().toString()+"월")
            binding.recordDateTv.setText(specialDate.substring(4,6).toInt().toString()+"월 "+specialDate.substring(6).toInt().toString()+"일")

            //주간캘린더 불러오기
            binding.recordWeekRecyclerview.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            val recordWeekRVAdapter = RecordWeekRVAdapter(specialDate)
            binding.recordWeekRecyclerview.adapter = recordWeekRVAdapter
            //주간캘린더 클릭이벤트
            recordWeekRVAdapter.setMyItemClickListener(object :
                RecordWeekRVAdapter.MyItemClickListener {
                override fun onItemClick(day: String) {
                    binding.recordMonth.setText(day.substring(4,6).toInt().toString()+"월")
                    binding.recordDateTv.setText(day.substring(4,6).toInt().toString()+"월 "+day.substring(6).toInt().toString()+"일")
                }
            })

            binding.recordWorkoutRecyclerview.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            val recordWorkoutRVAdapter = RecordWorkoutRVAdapter(recordWorkout)
            binding.recordWorkoutRecyclerview.adapter = recordWorkoutRVAdapter

            recordWorkoutRVAdapter.setMyItemClickListener(object :
                RecordWorkoutRVAdapter.MyItemClickListener {
                    override fun onItemClick(position: Int) {
                        TODO("Not yet implemented")
                    }
                }
            )


        }else{
            //캘린더액티비티에서 값이 넘어오지 않는경우(오늘날짜 default)
            init()
            val now: Long = System.currentTimeMillis()
            val date = Date(now)
            val dateFormat = SimpleDateFormat("yyyyMMdd", Locale("ko", "KR"))
            val stringDate = dateFormat.format(date)

            binding.recordWeekRecyclerview.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            val recordWeekRVAdapter = RecordWeekRVAdapter(stringDate)
            binding.recordWeekRecyclerview.adapter = recordWeekRVAdapter

            recordWeekRVAdapter.setMyItemClickListener(object :
                RecordWeekRVAdapter.MyItemClickListener {
                override fun onItemClick(day: String) {
                }
            })
        }
        //화면 넓이
        val display = requireActivity().windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x
        val height = size.y
        binding.recordWeekRecyclerview.addItemDecoration(HorizontalItemDecorator(width/75,height/200))

        onClick()

        return binding.root
    }

    private fun onClick(){
        binding.recordWorkoutPlusBtn.setOnClickListener {
            var workoutDetailFragment = WorkoutDetailFragment()
            var bundle = Bundle()
            var date = binding.recordDateTv.text.toString()
            bundle.putString("date", date)
            workoutDetailFragment.arguments = bundle
            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.main_frm, workoutDetailFragment)
                .commit()
        }
        binding.recordDietPlusBtn.setOnClickListener {
            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.main_frm, DietFragment())
                .commit()
        }
    }

    private fun init() {
        //시작시 해당월, 날짜 (오늘날짜로 default)
        binding.recordDateTv.setText(setdate().substring(4,6).toInt().toString()+"월 "+setdate().substring(6).toInt().toString()+"일")
        binding.recordMonth.setText(setMonth())

    }

    private fun setdate(): String {
        val now: Long = System.currentTimeMillis()
        val date = Date(now)
        val dateFormat = SimpleDateFormat("yyyyMMdd", Locale("ko", "KR"))
        val stringDate = dateFormat.format(date)

        return stringDate
    }
    //해당월 설정 함수
    private fun setMonth(): String {
        val now: Long = System.currentTimeMillis()
        val month = Date(now)
        val monthFormat = SimpleDateFormat("MM", Locale("ko", "KR"))
        val stringMonth = (monthFormat.format(month).toInt()).toString()+"월"

        return stringMonth
    }

    private fun setData() {
        for (i in 0 until 11){
            recordWorkout.add(RecordWorkout("사이드 레터럴 라이즈","2kg / 3kg / 4kg / 5kg \n 3kg / 4kg"))
        }
    }
}