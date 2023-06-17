package com.example.wellfit

import android.annotation.SuppressLint
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.example.wellfit.databinding.ItemRecordWorkoutBinding
import com.example.wellfit.databinding.RowBinding


class WorkoutSelectAdapter(val items:ArrayList<Library>) : RecyclerView.Adapter<WorkoutSelectAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(workoutName : String)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: WorkoutSelectAdapter.MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    //뷰홀더 생성->호출되는 함수->아이템 뷰 객체를 만들어서 뷰홀더에 던져줌
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RowBinding = RowBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    //뷰홀더에 데이터를 바인딩해줘야 할 때마다 호출되는 함수 => 엄청나게 많이 호출
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.bind(position)
        holder.itemView.setOnClickListener{
            mItemClickListener.onItemClick(items[position].name)
        }
    }

    //뷰홀더
    inner class ViewHolder(val binding: RowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.libRecycleItem.text = items[position].name
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}