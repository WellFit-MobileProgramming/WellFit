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

    val lowerBodyData:ArrayList<Exercise> = ArrayList()
    val chestData:ArrayList<Exercise> = ArrayList()
    val backData:ArrayList<Exercise> = ArrayList()
    val shoulderData:ArrayList<Exercise> = ArrayList()
    val armData:ArrayList<Exercise> = ArrayList()
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
        while(scan1.hasNextLine()){
            val name = scan1.nextLine()
            val link = scan1.nextLine()
            lowerBodyData.add(Exercise(name, link))
        }
        val scan2 = Scanner(resources.openRawResource(R.raw.chest))
        while(scan2.hasNextLine()){
            val name = scan2.nextLine()
            val link = scan2.nextLine()
            chestData.add(Exercise(name, link))
        }
        val scan3 = Scanner(resources.openRawResource(R.raw.back))
        while(scan3.hasNextLine()){
            val name = scan3.nextLine()
            val link = scan3.nextLine()
            backData.add(Exercise(name, link))
        }
        val scan4 = Scanner(resources.openRawResource(R.raw.shoulder))
        while(scan4.hasNextLine()){
            val name = scan4.nextLine()
            val link = scan4.nextLine()
            shoulderData.add(Exercise(name, link))
        }
        val scan5 = Scanner(resources.openRawResource(R.raw.arm))
        while(scan5.hasNextLine()){
            val name = scan5.nextLine()
            val link = scan5.nextLine()
            armData.add(Exercise(name, link))
        }
    }
    fun initRecyclerView(){
        binding.libRecycleLowerbody.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
        lowerBodyAdapter = LibAdapter(lowerBodyData)
        binding.libRecycleLowerbody.adapter = lowerBodyAdapter
        lowerBodyAdapter.itemClickListener = object : LibAdapter.OnItemClickListener{
            override fun OnItemClick(data: Exercise) {

            }
        }

        binding.libRecycleChest.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
        chestAdapter = LibAdapter(chestData)
        binding.libRecycleChest.adapter = chestAdapter
        chestAdapter.itemClickListener = object : LibAdapter.OnItemClickListener{
            override fun OnItemClick(data: Exercise) {

            }
        }

        binding.libRecycleBack.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
        backAdapter = LibAdapter(backData)
        binding.libRecycleBack.adapter = backAdapter
        backAdapter.itemClickListener = object : LibAdapter.OnItemClickListener{
            override fun OnItemClick(data: Exercise) {

            }
        }

        binding.libRecycleShoulder.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
        shoulderAdapter = LibAdapter(shoulderData)
        binding.libRecycleShoulder.adapter = shoulderAdapter
        shoulderAdapter.itemClickListener = object : LibAdapter.OnItemClickListener{
            override fun OnItemClick(data: Exercise) {

            }
        }

        binding.libRecycleArm.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
        armAdapter = LibAdapter(armData)
        binding.libRecycleArm.adapter = armAdapter
        armAdapter.itemClickListener = object : LibAdapter.OnItemClickListener{
            override fun OnItemClick(data: Exercise) {

            }
        }
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

    private fun initSearch(){
        binding.searchBtn.setOnClickListener {
            val searchText = binding.searchView.text.toString().trim()
            val filteredList = ArrayList<Exercise>()

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
