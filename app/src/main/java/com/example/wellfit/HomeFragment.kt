package com.example.wellfit
import HorizontalItemDecorator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.OnBackPressedCallback
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
    lateinit var calendarAdapter: CalendarAdapter
    private lateinit var callback: OnBackPressedCallback
    //보여줄 년, 월
    private var viewDate = ""
    //보여줄 년, 월, 일
    private var allDate = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        init()
        setMonth()
        onClick()

        //달력 레이아웃 매니저
        val gridLayoutManager = GridLayoutManager(requireContext(), 7)
        binding.calendarGridview.layoutManager = gridLayoutManager

        binding.calendarGridview.addItemDecoration(HorizontalItemDecorator(15,15))

        viewDate = initDate().substring(0,6)

        calendarAdapter = CalendarAdapter(viewDate)
        binding.calendarGridview.adapter = calendarAdapter
        calendarAdapter.setMyItemClickListener(object :
            CalendarAdapter.MyItemClickListener {
            override fun onItemClick(date: String) {
                allDate = date
                val selectDay = allDate.substring(4,6).toInt().toString() + "월 " +allDate.substring(6,8).toInt().toString() +"일"
                Log.e("날짜",selectDay)
                calendarAdapter.notifyDataSetChanged()
                binding.homeDateTv.text = selectDay
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
            calendarAdapter = CalendarAdapter(viewDate)
            binding.calendarGridview.adapter = calendarAdapter
            calendarAdapter.setMyItemClickListener(object :
                CalendarAdapter.MyItemClickListener {
                override fun onItemClick(date: String) {
                    allDate = date
                    val selectDay = allDate.substring(4,6).toInt().toString() + "월 " +allDate.substring(6,8).toInt().toString() +"일"
                    Log.e("날짜",selectDay)
                    calendarAdapter.notifyDataSetChanged()
                    binding.homeDateTv.text = selectDay
                }
            })
        }
        binding.homeLeftarrow.setOnClickListener {
                cal.add(Calendar.MONTH, -1)
                binding.homeMonth.text = (cal.get(Calendar.MONTH) + 1).toString() + "월"
                val date = Date(cal.timeInMillis)
                val dateFormat2 = SimpleDateFormat("yyyyMMdd", Locale("ko", "KR"))
                viewDate = dateFormat2.format(date)
            calendarAdapter = CalendarAdapter(viewDate)
            binding.calendarGridview.adapter = calendarAdapter
            calendarAdapter.setMyItemClickListener(object :
                CalendarAdapter.MyItemClickListener {
                override fun onItemClick(date: String) {
                    allDate = date
                    val selectDay = allDate.substring(4,6).toInt().toString() + "월 " +allDate.substring(6,8).toInt().toString() +"일"
                    Log.e("날짜",selectDay)
                    calendarAdapter.notifyDataSetChanged()
                    binding.homeDateTv.text = selectDay
                }
            })
        }
        return binding.root
    }

    private fun onClick() {
        binding.homePencil.setOnClickListener {
            var recordFragment = RecordFragment()
            var bundle = Bundle()
            bundle.putString("changeDate", allDate)
            Log.e("date",allDate)
            recordFragment.arguments = bundle
            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.main_frm, recordFragment)
                .commit()
        }
    }

    //초기함수
    private fun init() {
        binding.homeMonth.text = setMonth()
        binding.homeDateTv.text = setDate()
    }

    //초기 달 설정
    private fun setMonth(): String {
        val now: Long = System.currentTimeMillis()
        val date = Date(now)
        val dateFormat2 = SimpleDateFormat("MM", Locale("ko", "KR"))
        val stringDate = (dateFormat2.format(date).toInt()).toString() + "월"
        return stringDate
    }

    private fun setDate(): String {
        val now: Long = System.currentTimeMillis()
        val date = Date(now)
        val dateFormat1 = SimpleDateFormat("MM", Locale("ko", "KR"))
        val dateFormat2 = SimpleDateFormat("dd", Locale("ko", "KR"))
        val dateFormat3 = SimpleDateFormat("yyyyMMdd", Locale("ko", "KR"))
        allDate = (dateFormat3.format(date)).toString()
        val stringDate = (dateFormat1.format(date).toInt()).toString()+ "월 " +(dateFormat2.format(date).toInt()).toString() + "일"
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

