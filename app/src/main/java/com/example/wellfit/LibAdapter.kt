package com.example.wellfit

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.example.wellfit.databinding.RowBinding


class LibAdapter(val itemsData:ArrayList<Exercise_explain>):
    RecyclerView.Adapter<LibAdapter.ViewHolder>() {
    private var items: ArrayList<Exercise_explain> = itemsData
    interface OnItemClickListener{
        fun OnItemClick(position: Int)
    }
    var itemClickListener:OnItemClickListener?=null

    inner class ViewHolder(val binding: RowBinding): RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClickListener?.OnItemClick(position)
                }
            }
        }
    }

    fun lib_search(newItems: ArrayList<Exercise_explain>){
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