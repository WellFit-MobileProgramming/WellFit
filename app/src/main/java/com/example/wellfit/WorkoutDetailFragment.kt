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
import com.example.wellfit.databinding.FragmentWorkoutDetailBinding
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date
import java.util.Locale

class WorkoutDetailFragment : Fragment(){

    lateinit var binding: FragmentWorkoutDetailBinding
    val recordWorkout = ArrayList<RecordWorkout>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentWorkoutDetailBinding.inflate(inflater, container, false)

        setData()

        //캘린더 액티비티에서 값이 넘어왔을 경우
        val specialDate = arguments?.getString("changeDate")
        if (specialDate != null) {
            //날짜 조정
            binding.workoutDetailMonth.setText(specialDate.substring(4,6).toInt().toString()+"월 "+specialDate.substring(6).toInt().toString()+"일")

            binding.workoutDetailRecyclerview.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            val workoutDetailRVAdapter = WorkoutDetailRVAdapter()
            binding.workoutDetailRecyclerview.adapter = workoutDetailRVAdapter


        }else{
            //값이 넘어오지 않는경우(오늘날짜 default)
            val now: Long = System.currentTimeMillis()
            val date = Date(now)
            val dateFormat = SimpleDateFormat("yyyyMMdd", Locale("ko", "KR"))
            val stringDate = dateFormat.format(date)

            binding.workoutDetailMonth.setText(stringDate.substring(4,6).toInt().toString()+"월 "+stringDate.substring(6).toInt().toString()+"일")

            binding.workoutDetailRecyclerview.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            val workoutDetailRVAdapter = WorkoutDetailRVAdapter()
            binding.workoutDetailRecyclerview.adapter = workoutDetailRVAdapter
        }
        return binding.root
    }


    private fun setData() {
        for (i in 0 until 11){
            recordWorkout.add(RecordWorkout("사이드 레터럴 라이즈","2kg / 3kg / 4kg / 5kg \n 3kg / 4kg"))
        }
    }
}