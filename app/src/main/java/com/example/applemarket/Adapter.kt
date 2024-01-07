package com.example.applemarket

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.ActivityMainBinding
import com.example.applemarket.databinding.RecyclerviewItemBinding
import java.text.DecimalFormat

class Adapter(val items: MutableList<Post>) : RecyclerView.Adapter<Adapter.Holder>() {
    private var onClickListener: OnClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.Holder {
        val binding =
            RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Adapter.Holder, position: Int) {
        val item = items[position]
        val money = item.itemPrice
        val format = DecimalFormat("#,###")

        holder.imageView.setImageResource(item.itemImage)
        holder.titleText.text = item.itemTitle
        holder.address.text = item.itemAddress
        holder.price.text = format.format(money) + "원"
        holder.comment.text = item.itemcomment.toString()
        holder.heart.text = item.itemheartCount.toString()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface itemClick {
        fun onClick(view: View, position: Int)
    }

    interface longClick {
        fun onLongClick(view: View, position: Int)
    }

    inner class Holder(val binding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imageView = binding.ivListImage
        val titleText = binding.tvTitle
        val address = binding.tvAddress
        val price = binding.tvPrice
        val comment = binding.tvComment
        val heart = binding.tvHeart
    }

}