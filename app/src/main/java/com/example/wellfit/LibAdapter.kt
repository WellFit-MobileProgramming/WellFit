package com.example.wellfit

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.example.wellfit.databinding.FragmentLibraryBinding
import com.example.wellfit.databinding.RowBinding


class LibAdapter(val items:ArrayList<Exercise>) : RecyclerView.Adapter<LibAdapter.ViewHolder>() {

    interface OnItemClickListener{
        fun OnItemClick(data: Exercise)
    }
    var itemClickListener:OnItemClickListener?=null

    inner class ViewHolder(val binding: RowBinding):
            RecyclerView.ViewHolder(binding.root){
                init {
                    binding.libRecycleItem.setOnClickListener {
                        itemClickListener?.OnItemClick(items[adapterPosition])
                        //모달 띄우는 작업 수행
                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = RowBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exercise = items[position]
        val name = exercise.name

        holder.binding.libRecycleItem.text = name
    }
}