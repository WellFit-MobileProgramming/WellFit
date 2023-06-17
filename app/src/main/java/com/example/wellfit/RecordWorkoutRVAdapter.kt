package com.example.wellfit

import android.annotation.SuppressLint
import android.graphics.Color
import android.icu.util.UniversalTimeScale
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wellfit.databinding.ItemRecordWorkoutBinding

class RecordWorkoutRVAdapter(private val recordWorkout: ArrayList<WorkoutType>): RecyclerView.Adapter<RecordWorkoutRVAdapter.ViewHolder>(){
    var selected = arrayListOf<Boolean>()


    //뷰홀더 생성->호출되는 함수->아이템 뷰 객체를 만들어서 뷰홀더에 던져줌
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemRecordWorkoutBinding = ItemRecordWorkoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        for(i in 0 until recordWorkout.size){
            selected.add(false)
        }
        return ViewHolder(binding)
    }

    //뷰홀더에 데이터를 바인딩해줘야 할 때마다 호출되는 함수 => 엄청나게 많이 호출
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.bind(position)

        holder.itemView.setOnClickListener{
            selected[position] = !selected[position]
            notifyDataSetChanged()
        }
    }

    //뷰홀더
    inner class ViewHolder(val binding: ItemRecordWorkoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.itemWorkoutTitleTv.text = recordWorkout[position].name


            if (selected[position] ){
                binding.itemLayout.setBackgroundColor(Color.parseColor("#CCE7D4"))
                var text = ""
                for(i in 0 until recordWorkout[position].workoutCount.size){
                    if(i != 0){
                        text += "\n"
                    }
                    text += "${i+1}set  ${recordWorkout[position].workoutCount[i].kg}kg  ${recordWorkout[position].workoutCount[i].count}회"
                    binding.itemWorkoutSetTv.text = text
                }
            }else{
                binding.itemLayout.setBackgroundColor(Color.parseColor("#ffffff"))
                var text = ""
                for(i in 0 until recordWorkout[position].workoutCount.size){
                    if(i != 0){
                        text += " / "
                    }
                    text += "${recordWorkout[position].workoutCount[i].kg}kg"
                    binding.itemWorkoutSetTv.text = text
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return recordWorkout.size
    }


}

