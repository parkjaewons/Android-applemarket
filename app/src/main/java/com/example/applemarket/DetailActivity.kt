package com.example.applemarket

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.applemarket.databinding.ActivityDetailBinding
import com.example.applemarket.databinding.ActivityMainBinding
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val getItem = intent.getParcelableExtra<Post>("Item")
        getItem?.let {
            val money = getItem.itemPrice
            val format = DecimalFormat("#,###")
            with(binding) {
                ivProduct.setImageResource(it.itemImage)
                tvDenickname.text = it.itemnickname
                tvDeadress.text = it.itemAddress
                tvTemperature.text = it.usermanners
                ivIcon.setImageResource(it.mannersImage)
                tvDeTitle.text = it.itemTitle
                tvDeinfo.text = it.itemInfo
                tvDeprice.text = "${format.format(money)}원"
            }
        }
        binding.backButton.setOnClickListener {
            finish()
        }
    }
}