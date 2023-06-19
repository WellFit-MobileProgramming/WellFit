package com.example.wellfit

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wellfit.databinding.FragmentLibraryBinding
import com.example.wellfit.databinding.FragmentWorkoutSelectBinding
import java.util.*
import kotlin.collections.ArrayList

//운동 라이브러리 화면
class LibraryFragment : Fragment(){
    lateinit var binding: FragmentLibraryBinding
    lateinit var adapter: LibraryAdapter

    var workoutData: ArrayList<Library> = ArrayList()
    var specialDate = ""
    var type = "하체"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentLibraryBinding.inflate(inflater, container, false)
        LibraryData.initDataList()
        specialDate = arguments?.getString("date").toString()
        workoutData = LibraryData.getList("lower_body")!!
        initButton()
        initRecyclerView()
        initSearch()
        return binding.root
    }


    fun initRecyclerView() {
        binding.libRecyclerview.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL, false
        )
        adapter = LibraryAdapter(workoutData)
        binding.libRecyclerview.adapter = adapter
        adapter.setMyItemClickListener(object :
            LibraryAdapter.MyItemClickListener {
            override fun onItemClick(workout : Library) {
                showModal(workout)
            }
        })
        adapter.notifyDataSetChanged()
    }

    private fun showModal(data:Library){
        val modalFragment = ModalFragment() // 모달 Fragment 인스턴스 생성
        modalFragment.setData(data.kind, data.name, data.content)
        modalFragment.show(requireActivity().supportFragmentManager, "MyModal")
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
            var homeFragment = HomeFragment()
            var bundle = Bundle()
            homeFragment.arguments = bundle
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, homeFragment)
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
    fun initSearch() {
        binding.searchBtn.setOnClickListener {
            val searchText = binding.searchView.text.toString().trim()
            val filteredList = ArrayList<Library>()
                // 검색어와 일치하는 아이템을 찾아 filteredList에 추가
                for (exercise in workoutData) {
                    if (exercise.name.contains(searchText, ignoreCase = true)) {
                        filteredList.add(exercise)
                    }
                // RecyclerView 어댑터에 새로운 리스트 설정하여 갱신
            }
            if(filteredList.isNotEmpty()){
                workoutData = filteredList
            }
            initRecyclerView()
        }
    }
}
