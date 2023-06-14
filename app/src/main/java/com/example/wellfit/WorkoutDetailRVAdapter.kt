package com.example.wellfit

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wellfit.databinding.ItemRecordWorkoutBinding
import com.example.wellfit.databinding.ItemWorkoutDetailBinding

class WorkoutDetailRVAdapter(var countList : ArrayList<WorkoutCount>): RecyclerView.Adapter<WorkoutDetailRVAdapter.ViewHolder>(){
    interface MyItemClickListener {
        fun onItemClick(size : Int)
        fun sendData(list: ArrayList<WorkoutCount>)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }


    //뷰홀더 생성->호출되는 함수->아이템 뷰 객체를 만들어서 뷰홀더에 던져줌
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemWorkoutDetailBinding = ItemWorkoutDetailBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    //뷰홀더에 데이터를 바인딩해줘야 할 때마다 호출되는 함수 => 엄청나게 많이 호출
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.bind(position)
        holder.binding.itemWorkoutDetailKgEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                countList[position].kg = holder.binding.itemWorkoutDetailKgEt.text.toString()
                mItemClickListener.sendData(countList)
            }

        })
        holder.binding.itemWorkoutDetailCountEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                countList[position].count = holder.binding.itemWorkoutDetailCountEt.text.toString()
                mItemClickListener.sendData(countList)
            }

        })
        holder.binding.itemWorkoutDetailClose.setOnClickListener {
            removeItem(position)
            mItemClickListener.onItemClick(countList.size)
        }
    }

    //뷰홀더
    inner class ViewHolder(val binding: ItemWorkoutDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            binding.itemWorkoutDetailNumTv.text = "${(position+1)}st"
        }
    }

    override fun getItemCount(): Int = countList.size

    @SuppressLint("NotifyDataSetChanged")
    private fun removeItem(position: Int){
        countList.removeAt(position)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(){
        countList.add(WorkoutCount("",""))
        notifyDataSetChanged()
    }

}

