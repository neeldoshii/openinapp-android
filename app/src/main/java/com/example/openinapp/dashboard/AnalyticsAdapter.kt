package com.example.openinapp.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.openinapp.databinding.ItemAnalyticsBinding
import com.example.openinapp.model.Analytics

class AnalyticsAdapter(private val list : List<Analytics>): RecyclerView.Adapter<AnalyticsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemAnalyticsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAnalyticsBinding.inflate(LayoutInflater.from(parent.context),null,false)
        return ViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return list.count()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        list[position].analyticsIcon?.let { holder.binding.analyticsIV.setImageResource(it) }
        holder.binding.analyticsDescriptionTV.text = list[position].analyticsDescription

        if (!list[position].analyticsCount.toString().isNullOrBlank()){
            holder.binding.analyticsCount.text = list[position].analyticsCount.toString()
        } else{
            holder.binding.analyticsCount.text = "N/A"

        }

    }


}







