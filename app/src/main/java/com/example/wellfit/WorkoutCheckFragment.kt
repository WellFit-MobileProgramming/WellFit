package com.example.wellfit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wellfit.databinding.FragmentWorkoutCheckBinding
import com.example.wellfit.databinding.FragmentWorkoutDetailBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.collections.ArrayList

class WorkoutCheckFragment : Fragment(){

    lateinit var binding: FragmentWorkoutCheckBinding
    private var auth : FirebaseAuth? = null
    private var fireStore : FirebaseFirestore? = null
    lateinit var adapter: WorkoutCheckAdapter
    var workoutList = ArrayList<WorkoutType>()
    var specialDate : String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentWorkoutCheckBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        fireStore = Firebase.firestore

        specialDate = arguments?.getString("date").toString()
        workoutList = arguments?.getSerializable("data") as ArrayList<WorkoutType>

        if (specialDate != null) {
            //날짜 조정
            binding.workoutDetailNameTv.text =
                "${specialDate!!.substring(4, 6).toInt()}월 ${specialDate!!.substring(6).toInt()}일"

            binding.workoutDetailRecyclerview.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            var workoutCheckRVAdapter = WorkoutCheckAdapter(workoutList)
            binding.workoutDetailRecyclerview.adapter = workoutCheckRVAdapter
        }
        return binding.root
    }

}
