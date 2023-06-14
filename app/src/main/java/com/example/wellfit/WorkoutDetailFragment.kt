package com.example.wellfit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
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

class WorkoutDetailFragment : Fragment(){

    lateinit var binding: FragmentWorkoutDetailBinding
    private var auth : FirebaseAuth? = null
    private var fireStore : FirebaseFirestore? = null
    var countList = ArrayList<WorkoutCount>()
    var specialDate : String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentWorkoutDetailBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        fireStore = Firebase.firestore

        //캘린더 액티비티에서 값이 넘어왔을 경우
        specialDate = arguments?.getString("date")
        if (specialDate != null) {
            Log.e("날짜Wk",specialDate.toString())
            //날짜 조정
            binding.workoutDetailMonth.text =
                "${specialDate!!.substring(4, 6).toInt()}월 ${specialDate!!.substring(6).toInt()}일"

            binding.workoutDetailRecyclerview.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            var workoutDetailRVAdapter = WorkoutDetailRVAdapter(countList)
            binding.workoutDetailRecyclerview.adapter = workoutDetailRVAdapter
            workoutDetailRVAdapter.setMyItemClickListener(object : WorkoutDetailRVAdapter.MyItemClickListener{
                override fun onItemClick(size: Int) {
                    if(size<5){
                        binding.workoutDetailPlusBtn.visibility = View.VISIBLE
                    }
                }

                override fun sendData(list: ArrayList<WorkoutCount>) {
                    countList = list
                }

            })
            onClick(workoutDetailRVAdapter)
            binding.workoutDetailRecyclerview.adapter = workoutDetailRVAdapter


        }else{
            //값이 넘어오지 않는경우(오늘날짜 default)
            val now: Long = System.currentTimeMillis()
            val date = Date(now)
            val dateFormat = SimpleDateFormat("yyyyMMdd", Locale("ko", "KR"))
            val stringDate = dateFormat.format(date)

            binding.workoutDetailMonth.text =
                "${stringDate.substring(4, 6).toInt()}월 ${stringDate.substring(6).toInt()}일"

            binding.workoutDetailRecyclerview.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            var workoutDetailRVAdapter = WorkoutDetailRVAdapter(countList)
            binding.workoutDetailRecyclerview.adapter = workoutDetailRVAdapter
            workoutDetailRVAdapter.setMyItemClickListener(object : WorkoutDetailRVAdapter.MyItemClickListener{
                override fun onItemClick(size: Int) {
                    if(size<5){
                        binding.workoutDetailPlusBtn.visibility = View.VISIBLE
                    }
                }

                override fun sendData(list: ArrayList<WorkoutCount>) {
                    countList = list
                }

            })
            onClick(workoutDetailRVAdapter)
            binding.workoutDetailRecyclerview.adapter = workoutDetailRVAdapter
        }
        return binding.root
    }

    private fun onClick(adapter: WorkoutDetailRVAdapter) {
        var workoutType: WorkoutType? = null
        binding.workoutDetailCompleteBtn.setOnClickListener {
            for (i in countList) {
                workoutType = WorkoutType(
                    specialDate.toString(),
                    binding.workoutDetailTypeTv.text.toString(),
                    binding.workoutDetailNameTv.text.toString(),
                    countList
                )
            }

            val workoutRef = fireStore?.collection("workouts")?.document(auth?.uid.toString())
            workoutRef?.get()
                ?.addOnSuccessListener { result ->
                    if(result.exists()){
                        val workoutArray: ArrayList<WorkoutType> =
                            result.get("workoutType") as ArrayList<WorkoutType>
                        workoutArray.add(workoutType!!)
                        result.reference.update("workoutType", workoutArray)
                            .addOnSuccessListener {
                                Log.e("운동데이터 업데이트완료", workoutArray.toString())
                                // 업데이트 완료
                                startRecordFragment()
                            }
                            .addOnFailureListener { exception ->
                                Log.e("운동데이터 업데이트 실패", exception.toString())
                            }
                    }else{
                        Log.e("운동데이터 조회 실패", "실패")
                        val workoutInfo = ArrayList<WorkoutType>()
                        workoutInfo.add(workoutType!!)
                        val workout = Workout(workoutInfo)
                        workoutRef?.set(workout)?.addOnSuccessListener {
                            startRecordFragment()
                            Log.e("운동데이터 저장 성공", "콜렉션")
                        }?.addOnFailureListener { exception ->
                            Log.e("운동데이터 저장 실패", exception.toString())
                        }
                    }
            }
                ?.addOnFailureListener { exception ->
                    Log.e("운동데이터 조회 실패", exception.toString())
                    val workoutInfo = ArrayList<WorkoutType>()
                    workoutInfo.add(workoutType!!)
                    val workout = Workout(workoutInfo)
                    workoutRef?.set(workout)?.addOnSuccessListener {
                        startRecordFragment()
                        Log.e("운동데이터 저장 성공", "콜렉션")
                    }?.addOnFailureListener { exception ->
                        Log.e("운동데이터 저장 실패", exception.toString())
                    }
                }
        }

        binding.workoutDetailPlusBtn.setOnClickListener {
            adapter.addItem()
            if(countList.size > 4){
                binding.workoutDetailPlusBtn.visibility = View.GONE
            }
        }
    }

    private fun startRecordFragment() {
        var recordFragment = RecordFragment()
        var bundle = Bundle()
        bundle.putString("changeDate", specialDate)
        recordFragment.arguments = bundle
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, recordFragment)
            .commitAllowingStateLoss()
    }

}
