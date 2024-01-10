package com.example.applemarket

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.RecyclerviewItemBinding
import java.text.DecimalFormat

class Adapter(private val items: MutableList<Post>) : RecyclerView.Adapter<Adapter.Holder>() {
    var itemClick: ItemClick? = null
    var longitemClick: LongItemClick? = null

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    interface LongItemClick {
        fun onLongClick(view: View, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.Holder {
        val binding =
            RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Adapter.Holder, position: Int) {
        val item = items[position]
        //1000단위 마다 콤마(,) 출력
        val money = item.itemPrice
        val format = DecimalFormat("#,###")

        holder.imageView.setImageResource(item.itemImage)
        holder.titleText.text = item.itemTitle
        holder.address.text = item.itemAddress
        holder.price.text = format.format(money) + "원"
        holder.comment.text = item.itemcomment.toString()
        holder.heart.text = item.itemheartCount.toString()
        holder.itemView.setOnClickListener {
            if (itemClick != null) {
                itemClick!!.onClick(it, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class Holder(val binding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imageView = binding.ivListImage
        val titleText = binding.tvTitle
        val address = binding.tvAddress
        val price = binding.tvPrice
        val comment = binding.tvComment
        val heart = binding.tvHeart

        init {
            imageView.clipToOutline = true

            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClick?.onClick(it, position)
                }
            }
            binding.root.setOnLongClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    longitemClick?.onLongClick(it, position)
                    true
                } else false
            }
        }
    }

}