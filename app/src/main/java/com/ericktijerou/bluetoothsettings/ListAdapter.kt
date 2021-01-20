package com.ericktijerou.bluetoothsettings

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(private val list: List<String>)
    : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie: String = list[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {
        private var mTitleView: TextView? = null


        init {
            mTitleView = itemView.findViewById(R.id.tvOption)
        }

        fun bind(movie: String) {
            mTitleView?.text = movie
        }

    }
}