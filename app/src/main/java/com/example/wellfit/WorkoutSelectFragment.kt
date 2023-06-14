package com.example.wellfit

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentWorkoutSelectBinding.inflate(inflater, container, false)
        LibraryData.initDataList()
        workoutData = LibraryData.getList("lower_body")!!
        initButton()
        initRecyclerView()
        return binding.root
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
            override fun onItemClick(position: Int) {
                TODO("Not yet implemented")
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
            }
        }
    }
}
