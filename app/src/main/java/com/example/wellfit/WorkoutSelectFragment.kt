package com.example.wellfit

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wellfit.databinding.FragmentLibraryBinding
import com.example.wellfit.databinding.FragmentWorkoutSelectBinding
import io.grpc.internal.JsonUtil.getList
import java.util.*
import kotlin.collections.ArrayList

//운동 라이브러리 화면
class WorkoutSelectFragment : Fragment() {
    lateinit var binding: FragmentWorkoutSelectBinding
    lateinit var adapter: WorkoutSelectAdapter

    var workoutData: ArrayList<Library> = ArrayList()
    var specialDate = ""
    var type = "하체"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentWorkoutSelectBinding.inflate(inflater, container, false)
        LibraryData.initDataList()
        specialDate = arguments?.getString("date").toString()
        workoutData = LibraryData.getList("lower_body")!!
        initLayout()
        initButton()
        initRecyclerView()
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    fun initLayout(){
        binding.libText.text =
            "${specialDate.substring(4, 6).toInt()}월 ${specialDate.substring(6).toInt()}일 운동"
    }

    fun initRecyclerView() {
        binding.libRecyclerview.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL, false
        )
        adapter = WorkoutSelectAdapter(workoutData)
        binding.libRecyclerview.adapter = adapter
        adapter.setMyItemClickListener(object :
            WorkoutSelectAdapter.MyItemClickListener {
            override fun onItemClick(workoutName : String) {
                Log.e("운동이름",workoutName)
                val workoutDetailFragment = WorkoutDetailFragment()
                val bundle = Bundle()
                bundle.putString("date", specialDate)
                bundle.putString("type", type)
                bundle.putString("name", workoutName)
                workoutDetailFragment.arguments = bundle
                activity?.supportFragmentManager!!.beginTransaction()
                    .replace(R.id.main_frm, workoutDetailFragment)
                    .commit()
            }
        })
        adapter.notifyDataSetChanged()
    }

    private fun initButton() {
        binding.libLowerBody.setTextColor(Color.parseColor("#ffffff"))
        binding.libLowerBody.setBackgroundResource(R.drawable.view_box)
        binding.libLowerBody.setOnClickListener {
            binding.libLowerBody.setBackgroundResource(R.drawable.view_box)
            binding.libLowerBody.setTextColor(Color.parseColor("#ffffff"))
            binding.libArm.setBackgroundResource(R.drawable.box_unclick)
            binding.libArm.setTextColor(Color.parseColor("#478C5C"))
            binding.libBack.setBackgroundResource(R.drawable.box_unclick)
            binding.libBack.setTextColor(Color.parseColor("#478C5C"))
            binding.libChest.setBackgroundResource(R.drawable.box_unclick)
            binding.libChest.setTextColor(Color.parseColor("#478C5C"))
            binding.libShoulder.setBackgroundResource(R.drawable.box_unclick)
            binding.libShoulder.setTextColor(Color.parseColor("#478C5C"))
            workoutData = LibraryData.getList("lower_body")!!
            initRecyclerView()
            type = "하체"
        }

        binding.libArm.setOnClickListener {
            binding.libLowerBody.setBackgroundResource(R.drawable.box_unclick)
            binding.libLowerBody.setTextColor(Color.parseColor("#478C5C"))
            binding.libArm.setBackgroundResource(R.drawable.view_box)
            binding.libArm.setTextColor(Color.parseColor("#ffffff"))
            binding.libBack.setBackgroundResource(R.drawable.box_unclick)
            binding.libBack.setTextColor(Color.parseColor("#478C5C"))
            binding.libChest.setBackgroundResource(R.drawable.box_unclick)
            binding.libChest.setTextColor(Color.parseColor("#478C5C"))
            binding.libShoulder.setBackgroundResource(R.drawable.box_unclick)
            binding.libShoulder.setTextColor(Color.parseColor("#478C5C"))
            workoutData = LibraryData.getList("arm")!!
            initRecyclerView()
            type = "팔"
        }

        binding.libBack
            .setOnClickListener {
                binding.libLowerBody.setBackgroundResource(R.drawable.box_unclick)
                binding.libLowerBody.setTextColor(Color.parseColor("#478C5C"))
                binding.libArm.setBackgroundResource(R.drawable.box_unclick)
                binding.libArm.setTextColor(Color.parseColor("#478C5C"))
                binding.libBack.setBackgroundResource(R.drawable.view_box)
                binding.libBack.setTextColor(Color.parseColor("#ffffff"))
                binding.libChest.setBackgroundResource(R.drawable.box_unclick)
                binding.libChest.setTextColor(Color.parseColor("#478C5C"))
                binding.libShoulder.setBackgroundResource(R.drawable.box_unclick)
                binding.libShoulder.setTextColor(Color.parseColor("#478C5C"))
                workoutData = LibraryData.getList("back")!!
                initRecyclerView()
                type = "등"
            }

        binding.libChest.setOnClickListener {
            binding.libLowerBody.setBackgroundResource(R.drawable.box_unclick)
            binding.libLowerBody.setTextColor(Color.parseColor("#478C5C"))
            binding.libArm.setBackgroundResource(R.drawable.box_unclick)
            binding.libArm.setTextColor(Color.parseColor("#478C5C"))
            binding.libBack.setBackgroundResource(R.drawable.box_unclick)
            binding.libBack.setTextColor(Color.parseColor("#478C5C"))
            binding.libChest.setBackgroundResource(R.drawable.view_box)
            binding.libChest.setTextColor(Color.parseColor("#ffffff"))
            binding.libShoulder.setBackgroundResource(R.drawable.box_unclick)
            binding.libShoulder.setTextColor(Color.parseColor("#478C5C"))
            workoutData = LibraryData.getList("chest")!!
            initRecyclerView()
            type = "가슴"
        }
        binding.libShoulder.apply {
            setOnClickListener {
                binding.libLowerBody.setBackgroundResource(R.drawable.box_unclick)
                binding.libLowerBody.setTextColor(Color.parseColor("#478C5C"))
                binding.libArm.setBackgroundResource(R.drawable.box_unclick)
                binding.libArm.setTextColor(Color.parseColor("#478C5C"))
                binding.libBack.setBackgroundResource(R.drawable.box_unclick)
                binding.libBack.setTextColor(Color.parseColor("#478C5C"))
                binding.libChest.setBackgroundResource(R.drawable.box_unclick)
                binding.libChest.setTextColor(Color.parseColor("#478C5C"))
                binding.libShoulder.setBackgroundResource(R.drawable.view_box)
                binding.libShoulder.setTextColor(Color.parseColor("#ffffff"))
                workoutData = LibraryData.getList("shoulder")!!
                initRecyclerView()
                type = "어깨"
            }
        }
        binding.workoutCheckLeftarrow.setOnClickListener{
            var recordFragment = RecordFragment()
            var bundle = Bundle()
            recordFragment.arguments = bundle
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, recordFragment)
                .commitAllowingStateLoss()
        }
        binding.workoutCheckClose.setOnClickListener{
            var homeFragment = HomeFragment()
            var bundle = Bundle()
            homeFragment.arguments = bundle
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, homeFragment)
                .commitAllowingStateLoss()
        }
    }
}
