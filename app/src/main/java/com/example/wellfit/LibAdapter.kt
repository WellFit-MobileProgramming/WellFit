package com.example.wellfit

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.example.wellfit.databinding.RowBinding


class LibAdapter(val itemsData:ArrayList<Exercise>) : RecyclerView.Adapter<LibAdapter.ViewHolder>() {
    private var items: ArrayList<Exercise> = itemsData
    interface OnItemClickListener{
        fun OnItemClick(data: Exercise)
    }
    var itemClickListener:OnItemClickListener?=null

    inner class ViewHolder(val binding: RowBinding):
            RecyclerView.ViewHolder(binding.root){

            }

    fun lib_search(newItems: ArrayList<Exercise>){
        items = newItems
        notifyDataSetChanged()
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
        holder.binding.libRecycleItem.text = items[position].name
    }
}