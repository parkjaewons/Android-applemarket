package com.example.applemarket

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.applemarket.databinding.ActivityDetailBinding
import com.example.applemarket.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var isLiked = false

    @SuppressLint("SetTextI18n", "NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getParcelableExtra("Item", com.example.applemarket.Post::class.java)
        val getItem = intent.getParcelableExtra("Item", Post::class.java)
        getItem?.let {
            val money = getItem.itemPrice
            val format = DecimalFormat("#,###")
            with(binding) {
                ivProduct.setImageResource(it.itemImage)
                ivIcon.setImageResource(it.mannersImage)
                tvDenickname.text = it.itemnickname
                tvDeadress.text = it.itemAddress
                tvTemperature.text = it.usermanners
                tvDeTitle.text = it.itemTitle
                tvDeinfo.text = it.itemInfo
                tvDeprice.text = "${format.format(money)}원"
                isLiked = it.isLiked == true

                ivDeheart.setImageResource(
                    if (isLiked) {
                        R.drawable.isheart
                    } else {
                        R.drawable.heart
                    }
                )
                ivDeheart.setOnClickListener {
                    isLiked = if (isLiked) {
                        ivDeheart.setImageResource(R.drawable.heart)
                        false
                    } else {
                        ivDeheart.setImageResource(R.drawable.isheart)
                        Snackbar.make(
                            binding.constraintLayout,
                            "관심 목록에 추가되었습니다.",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        true
                    }
                }
            }
        }
        binding.backButton.setOnClickListener {
            val likePosition = intent.getIntExtra("likePosition", 0)
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("likePosition", likePosition)
                putExtra("isLiked", isLiked)
            }
            setResult(Activity.RESULT_OK, intent)
            if (!isFinishing) finish()
        }
    }
}