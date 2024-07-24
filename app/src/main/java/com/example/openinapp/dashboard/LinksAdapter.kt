package com.example.openinapp.dashboard

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.openinapp.databinding.ItemLinkBinding
import com.example.openinapp.helperUtils.formatDateString
import com.example.openinapp.model.Links
import java.util.UUID


class LinksAdapter(private val context: Context, private val list : List<Links>):RecyclerView.Adapter<LinksAdapter.ViewHolder>() {
    inner class ViewHolder(val binding : ItemLinkBinding) : RecyclerView.ViewHolder(binding.root)
    private val clipboardManager: ClipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLinkBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return list.count()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.binding.linkIV.context)
            .load(list[position].icon)
            .into(holder.binding.linkIV)



        holder.binding.linkNameTV.text = list[position].linkName
        holder.binding.linkCreatedDateTV.text = list[position].creationDate?.let {
            formatDateString(
                it
            )
        }
        holder.binding.linkClickCountTV.text = list[position].totalClicks.toString()
        holder.binding.linkUrlBtn.text = list[position].linkUrl

        holder.binding.linkUrlBtn.setOnClickListener(){
            copyToClipboard(list[position].linkUrl.toString())
        }


    }
    fun copyToClipboard(text: String){
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(UUID.randomUUID().toString(), text)
        clipboard.setPrimaryClip(clip)
    }

}