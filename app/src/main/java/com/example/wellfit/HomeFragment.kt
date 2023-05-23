package com.example.wellfit
import HorizontalItemDecorator
import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wellfit.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

//홈화면
class HomeFragment : Fragment(){

    lateinit var binding: FragmentHomeBinding
    private lateinit var callback: OnBackPressedCallback
    //보여줄 년, 월
    private var viewDate = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        init()
        setMonth()

        //달력 레이아웃 매니저
        val gridLayoutManager = GridLayoutManager(requireContext(), 7)
        binding.calendarGridview.layoutManager = gridLayoutManager

        binding.calendarGridview.addItemDecoration(HorizontalItemDecorator(15,15))

        viewDate = initDate().substring(0,6)

        val calendarAdapter = CalendarAdapter(viewDate)
        binding.calendarGridview.adapter = calendarAdapter
        calendarAdapter.setMyItemClickListener(object :
            CalendarAdapter.MyItemClickListener {
            override fun onItemClick(date: String) {
            }
        })

        val cal = Calendar.getInstance()
        cal.set(initDate().substring(0,4).toInt(), initDate().substring(4,6).toInt() - 1, 1)
        val date = Date(cal.timeInMillis)
        val dateFormat2 = SimpleDateFormat("yyyyMMdd", Locale("ko", "KR"))
        viewDate = dateFormat2.format(date)
        binding.homeRightarrow.setOnClickListener {
            Log.e("이후달",(cal.get(Calendar.MONTH )+1).toString())
                cal.add(Calendar.MONTH, +1)
                binding.homeMonth.text = (cal.get(Calendar.MONTH) + 1).toString() + "월"
                val date = Date(cal.timeInMillis)
                val dateFormat2 = SimpleDateFormat("yyyyMMdd", Locale("ko", "KR"))
                viewDate = dateFormat2.format(date)
            val calendarAdapter = CalendarAdapter(viewDate)
            binding.calendarGridview.adapter = calendarAdapter
        }
        binding.homeLeftarrow.setOnClickListener {
                cal.add(Calendar.MONTH, -1)
                binding.homeMonth.text = (cal.get(Calendar.MONTH) + 1).toString() + "월"
                val date = Date(cal.timeInMillis)
                val dateFormat2 = SimpleDateFormat("yyyyMMdd", Locale("ko", "KR"))
                viewDate = dateFormat2.format(date)
            val calendarAdapter = CalendarAdapter(viewDate)
            binding.calendarGridview.adapter = calendarAdapter
        }
        return binding.root
    }

    //초기함수
    private fun init() {
        binding.homeMonth.text = setMonth()
    }

    //초기 달 설정
    private fun setMonth(): String {
        val now: Long = System.currentTimeMillis()
        val date = Date(now)
        val dateFormat2 = SimpleDateFormat("MM", Locale("ko", "KR"))
        val stringDate = (dateFormat2.format(date).toInt()).toString() + "월"
        return stringDate
    }

    //초기 날짜 설정
    fun initDate(): String {
        val now: Long = System.currentTimeMillis()
        val date = Date(now)
        val dateFormat = SimpleDateFormat("yyyyMMdd", Locale("ko", "KR"))
        val stringDate = dateFormat.format(date)
        Log.e("날짜",stringDate)
        return stringDate
    }
}

