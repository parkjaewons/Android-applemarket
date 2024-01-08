package com.example.applemarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.applemarket.databinding.ActivityDetailBinding
import com.example.applemarket.databinding.ActivityMainBinding
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val getItem = intent.getParcelableExtra<Post>("Item")
        getItem?.let {
            val money = getItem.itemPrice
            val format = DecimalFormat("#,###")
            binding.ivProduct.setImageResource(it.itemImage)
            binding.tvDenickname.text = it.itemnickname
            binding.tvDeadress.text = it.itemAddress
            binding.tvTemperature.text = it.usermanners
            binding.ivIcon.setImageResource(it.mannersImage)
            binding.tvDeTitle.text = it.itemTitle
            binding.tvDeinfo.text = it.itemInfo
            binding.tvDeprice.text = "${format.format(money) + "원"}"
        }
        binding.backButton.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}