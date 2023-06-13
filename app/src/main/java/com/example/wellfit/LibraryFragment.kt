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
    val LowerBodyData:ArrayList<Exercise> = ArrayList()
    val ChestData:ArrayList<Exercise> = ArrayList()
    val BackData:ArrayList<Exercise> = ArrayList()
    val ShoulderData:ArrayList<Exercise> = ArrayList()
    val ArmData:ArrayList<Exercise> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentLibraryBinding.inflate(inflater, container, false)
        initData()
        initRecyclerView()
        initButton()
        return binding.root
    }

    fun initData(){
        val scan1 = Scanner(resources.openRawResource(R.raw.lower_body))
        while(scan1.hasNextLine()){
            val name = scan1.nextLine()
            val link = scan1.nextLine()
            LowerBodyData.add(Exercise(name, link))
        }
        val scan2 = Scanner(resources.openRawResource(R.raw.chest))
        while(scan2.hasNextLine()){
            val name = scan2.nextLine()
            val link = scan2.nextLine()
            ChestData.add(Exercise(name, link))
        }
        val scan3 = Scanner(resources.openRawResource(R.raw.back))
        while(scan3.hasNextLine()){
            val name = scan3.nextLine()
            val link = scan3.nextLine()
            BackData.add(Exercise(name, link))
        }
        val scan4 = Scanner(resources.openRawResource(R.raw.shoulder))
        while(scan4.hasNextLine()){
            val name = scan4.nextLine()
            val link = scan4.nextLine()
            ShoulderData.add(Exercise(name, link))
        }
        val scan5 = Scanner(resources.openRawResource(R.raw.arm))
        while(scan5.hasNextLine()){
            val name = scan5.nextLine()
            val link = scan5.nextLine()
            ArmData.add(Exercise(name, link))
        }
    }
    fun initRecyclerView(){
        binding.libRecycleLowerbody.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
        val lowerBodyAdapter = LibAdapter(LowerBodyData)
        binding.libRecycleLowerbody.adapter = lowerBodyAdapter

        binding.libRecycleChest.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
        val chestAdapter = LibAdapter(ChestData)
        binding.libRecycleLowerbody.adapter = chestAdapter

        binding.libRecycleBack.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
        val backAdapter = LibAdapter(BackData)
        binding.libRecycleLowerbody.adapter = backAdapter

        binding.libRecycleShoulder.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
        val shoulderAdapter = LibAdapter(ShoulderData)
        binding.libRecycleLowerbody.adapter = shoulderAdapter

        binding.libRecycleArm.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
        val armAdapter = LibAdapter(ArmData)
        binding.libRecycleLowerbody.adapter = armAdapter

    }

    fun initButton(){  //button을 누를 시 recycler view visibility 설정
        binding.libLowerBody.setOnClickListener {
            if(binding.libRecycleLowerbody.visibility == View.VISIBLE)
                binding.libRecycleLowerbody.visibility = View.VISIBLE
            else{
                binding.libRecycleLowerbody.visibility = View.VISIBLE
                binding.libRecycleChest.visibility = View.GONE
                binding.libRecycleBack.visibility = View.GONE
                binding.libRecycleShoulder.visibility = View.GONE
                binding.libRecycleArm.visibility = View.GONE
            }
        }
        binding.libChest.setOnClickListener {
            if(binding.libRecycleChest.visibility == View.VISIBLE)
                binding.libRecycleChest.visibility = View.VISIBLE
            else{
                binding.libRecycleLowerbody.visibility = View.GONE
                binding.libRecycleChest.visibility = View.VISIBLE
                binding.libRecycleBack.visibility = View.GONE
                binding.libRecycleShoulder.visibility = View.GONE
                binding.libRecycleArm.visibility = View.GONE
            }
        }
        binding.libBack.setOnClickListener {
            if(binding.libRecycleBack.visibility == View.VISIBLE)
                binding.libRecycleBack.visibility = View.VISIBLE
            else{
                binding.libRecycleLowerbody.visibility = View.GONE
                binding.libRecycleChest.visibility = View.GONE
                binding.libRecycleBack.visibility = View.VISIBLE
                binding.libRecycleShoulder.visibility = View.GONE
                binding.libRecycleArm.visibility = View.GONE
            }
        }
        binding.libShoulder.setOnClickListener {
            if(binding.libRecycleShoulder.visibility == View.VISIBLE)
                binding.libRecycleShoulder.visibility = View.VISIBLE
            else{
                binding.libRecycleLowerbody.visibility = View.GONE
                binding.libRecycleChest.visibility = View.GONE
                binding.libRecycleBack.visibility = View.GONE
                binding.libRecycleShoulder.visibility = View.VISIBLE
                binding.libRecycleArm.visibility = View.GONE
            }
        }
        binding.libArm.setOnClickListener {
            if(binding.libRecycleArm.visibility == View.VISIBLE)
                binding.libRecycleArm.visibility = View.VISIBLE
            else{
                binding.libRecycleLowerbody.visibility = View.GONE
                binding.libRecycleChest.visibility = View.GONE
                binding.libRecycleBack.visibility = View.GONE
                binding.libRecycleShoulder.visibility = View.GONE
                binding.libRecycleArm.visibility = View.VISIBLE
            }
        }
    }
}
