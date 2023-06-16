package com.example.wellfit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wellfit.databinding.FragmentLibraryBinding
import java.util.*
import kotlin.collections.ArrayList

//운동 라이브러리 화면
class LibraryFragment : Fragment(){
    lateinit var binding: FragmentLibraryBinding

    lateinit var lowerBodyAdapter: LibAdapter
    lateinit var chestAdapter: LibAdapter
    lateinit var backAdapter: LibAdapter
    lateinit var shoulderAdapter: LibAdapter
    lateinit var armAdapter: LibAdapter

    val lowerBodyData:ArrayList<Exercise_explain> = ArrayList()
    val chestData:ArrayList<Exercise_explain> = ArrayList()
    val backData:ArrayList<Exercise_explain> = ArrayList()
    val shoulderData:ArrayList<Exercise_explain> = ArrayList()
    val armData:ArrayList<Exercise_explain> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentLibraryBinding.inflate(inflater, container, false)
        initData()
        initButton()
        initRecyclerView()
        initSearch()
        return binding.root
    }

    fun initData(){
        val scan1 = Scanner(resources.openRawResource(R.raw.lower_body))
        val scan6 = Scanner(resources.openRawResource(R.raw.lower_body_ex))
        while(scan1.hasNextLine()){
            val name = scan1.nextLine()
            val link = scan1.nextLine()
            val explain = scan6.nextLine()
            lowerBodyData.add(Exercise_explain(name, link, explain))
        }
        val scan2 = Scanner(resources.openRawResource(R.raw.chest))
        val scan7 = Scanner(resources.openRawResource(R.raw.lower_body_ex))
        while(scan2.hasNextLine()){
            val name = scan2.nextLine()
            val link = scan2.nextLine()
            val explain = scan7.nextLine()
            chestData.add(Exercise_explain(name, link, explain))
        }
        val scan3 = Scanner(resources.openRawResource(R.raw.back))
        val scan8 = Scanner(resources.openRawResource(R.raw.lower_body_ex))
        while(scan3.hasNextLine()){
            val name = scan3.nextLine()
            val link = scan3.nextLine()
            val explain = scan8.nextLine()
            backData.add(Exercise_explain(name, link, explain))
        }
        val scan4 = Scanner(resources.openRawResource(R.raw.shoulder))
        val scan9 = Scanner(resources.openRawResource(R.raw.lower_body_ex))
        while(scan4.hasNextLine()){
            val name = scan4.nextLine()
            val link = scan4.nextLine()
            val explain = scan9.nextLine()
            shoulderData.add(Exercise_explain(name, link, explain))
        }
        val scan5 = Scanner(resources.openRawResource(R.raw.arm))
        val scan10 = Scanner(resources.openRawResource(R.raw.lower_body_ex))
        while(scan5.hasNextLine()){
            val name = scan5.nextLine()
            val link = scan5.nextLine()
            val explain = scan10.nextLine()
            armData.add(Exercise_explain(name, link, explain))
        }
    }
    fun initRecyclerView(){
        binding.libRecycleLowerbody.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
        lowerBodyAdapter = LibAdapter(lowerBodyData)
        lowerBodyAdapter.itemClickListener = object : LibAdapter.OnItemClickListener{
            override fun OnItemClick(position: Int) {
                showModal("하체", lowerBodyData[position])
            }
        }
        binding.libRecycleLowerbody.adapter = lowerBodyAdapter

        binding.libRecycleChest.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
        chestAdapter = LibAdapter(chestData)
        chestAdapter.itemClickListener = object : LibAdapter.OnItemClickListener{
            override fun OnItemClick(position: Int) {
                showModal("가슴", chestData[position])
            }
        }
        binding.libRecycleChest.adapter = chestAdapter

        binding.libRecycleBack.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
        backAdapter = LibAdapter(backData)
        backAdapter.itemClickListener = object : LibAdapter.OnItemClickListener{
            override fun OnItemClick(position: Int) {
                showModal("등", backData[position])
            }
        }
        binding.libRecycleBack.adapter = backAdapter

        binding.libRecycleShoulder.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
        shoulderAdapter = LibAdapter(shoulderData)
        shoulderAdapter.itemClickListener = object : LibAdapter.OnItemClickListener{
            override fun OnItemClick(position: Int) {
                showModal("어깨", shoulderData[position])
            }
        }
        binding.libRecycleShoulder.adapter = shoulderAdapter

        binding.libRecycleArm.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
        armAdapter = LibAdapter(armData)
        armAdapter.itemClickListener = object : LibAdapter.OnItemClickListener{
            override fun OnItemClick(position: Int) {
                showModal("팔", armData[position])
            }
        }
        binding.libRecycleArm.adapter = armAdapter
    }
    private fun initButton() {  //button을 누를 시 recycler view visibility 설정
        binding.libLowerBody.setOnClickListener {
            recyclerViewVisibility(binding.libRecycleLowerbody)
        }
        binding.libChest.setOnClickListener {
            recyclerViewVisibility(binding.libRecycleChest)
        }
        binding.libBack.setOnClickListener {
            recyclerViewVisibility(binding.libRecycleBack)
        }
        binding.libShoulder.setOnClickListener {
            recyclerViewVisibility(binding.libRecycleShoulder)
        }
        binding.libArm.setOnClickListener {
            recyclerViewVisibility(binding.libRecycleArm)
        }
    }
    private fun recyclerViewVisibility(recyclerView: View) {
        if(recyclerView.visibility == View.VISIBLE) recyclerView.visibility
        else{
            binding.libRecycleLowerbody.visibility = View.GONE
            binding.libRecycleChest.visibility = View.GONE
            binding.libRecycleBack.visibility = View.GONE
            binding.libRecycleShoulder.visibility = View.GONE
            binding.libRecycleArm.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }

    private fun showModal(ex_category:String, data:Exercise_explain){
        val modalFragment = ModalFragment() // 모달 Fragment 인스턴스 생성
        modalFragment.setData(ex_category, data.name, data.explain, data.link)
        modalFragment.show(requireActivity().supportFragmentManager, "MyModal")
    }

    private fun initSearch(){
        binding.searchBtn.setOnClickListener {
            val searchText = binding.searchView.text.toString().trim()
            val filteredList = ArrayList<Exercise_explain>()

            if(binding.libRecycleLowerbody.visibility == View.VISIBLE) {
                // 검색어와 일치하는 아이템을 찾아 filteredList에 추가
                for (exercise in lowerBodyData) {
                    if (exercise.name.contains(searchText, ignoreCase = true)) {
                        filteredList.add(exercise)
                    }
                }
                // RecyclerView 어댑터에 새로운 리스트 설정하여 갱신
                lowerBodyAdapter.lib_search(filteredList)
            }
            if(binding.libRecycleChest.visibility == View.VISIBLE) {
                for (exercise in chestData) {
                    if (exercise.name.contains(searchText, ignoreCase = true)) {
                        filteredList.add(exercise)
                    }
                }
                chestAdapter.lib_search(filteredList)
            }
            if(binding.libRecycleBack.visibility == View.VISIBLE) {
                for (exercise in backData) {
                    if (exercise.name.contains(searchText, ignoreCase = true)) {
                        filteredList.add(exercise)
                    }
                }
                backAdapter.lib_search(filteredList)
            }
            if(binding.libRecycleShoulder.visibility == View.VISIBLE) {
                for (exercise in shoulderData) {
                    if (exercise.name.contains(searchText, ignoreCase = true)) {
                        filteredList.add(exercise)
                    }
                }
                shoulderAdapter.lib_search(filteredList)
            }
            if(binding.libRecycleArm.visibility == View.VISIBLE) {
                for (exercise in armData) {
                    if (exercise.name.contains(searchText, ignoreCase = true)) {
                        filteredList.add(exercise)
                    }
                }
                armAdapter.lib_search(filteredList)
            }
            binding.searchView.text.clear()
        }
    }
}
