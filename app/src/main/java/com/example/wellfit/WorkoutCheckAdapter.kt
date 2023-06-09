package com.example.wellfit

import android.annotation.SuppressLint
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.example.wellfit.databinding.ItemRecordWorkoutBinding
import com.example.wellfit.databinding.ItemWorkoutCheckBinding
import com.example.wellfit.databinding.RowBinding


class WorkoutCheckAdapter(val items:ArrayList<WorkoutType>) : RecyclerView.Adapter<WorkoutCheckAdapter.ViewHolder>() {
    var selected = arrayListOf<Boolean>()

    //뷰홀더 생성->호출되는 함수->아이템 뷰 객체를 만들어서 뷰홀더에 던져줌
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemWorkoutCheckBinding = ItemWorkoutCheckBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        for(i in 0 until items.size){
            selected.add(false)
        }
        return ViewHolder(binding)
    }

    //뷰홀더에 데이터를 바인딩해줘야 할 때마다 호출되는 함수 => 엄청나게 많이 호출
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.bind(position)
        holder.binding.itemWorkoutCheckBtn.setOnClickListener {
            selected[position] = !selected[position]
            notifyDataSetChanged()
        }
    }

    //뷰홀더
    inner class ViewHolder(val binding: ItemWorkoutCheckBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.workoutCheckTypeTv.text = items[position].type
            binding.itemWorkoutNameTv.text = items[position].name
            if (selected[position] ){
                binding.itemWorkoutCheckBtn.setImageResource(R.drawable.check)
            }else{
                binding.itemWorkoutCheckBtn.setImageResource(R.drawable.noncheck)
            }

        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}