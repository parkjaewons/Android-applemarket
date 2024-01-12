package com.example.applemarket

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.RecyclerviewItemBinding
import java.text.DecimalFormat

class Adapter(private val items: MutableList<Post>) : RecyclerView.Adapter<Adapter.Holder>() {
    var itemClick: ItemClick? = null
    interface ItemClick {
        fun onClick(view: View, position: Int)
        fun onLongClick(view: View, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.Holder {
        val binding =
            RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Adapter.Holder, position: Int) {
        val item = items[position]
        //1000단위 마다 콤마(,) 출력
        val money = item.itemPrice
        val format = DecimalFormat("#,###")

        with(holder) {
            imageView.setImageResource(item.itemImage)
            titleText.text = item.itemTitle
            address.text = item.itemAddress
            price.text = "${format.format(money)}원"
            comment.text = item.itemcomment.toString()
            heart.text = item.itemheartCount.toString()
            itemView.setOnClickListener {
                if (itemClick != null) {
                    itemClick!!.onClick(it, position)
                }
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
            with(binding) {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        itemClick?.onClick(it, position)
                    }
                }
                root.setOnLongClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        itemClick?.onLongClick(it, position)
                        true
                    } else false
                }
            }
        }
    }
}