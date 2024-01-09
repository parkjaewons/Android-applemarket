package com.example.applemarket

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.ArrayAdapter
import android.widget.LinearLayout.VERTICAL
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productList = Product.productData()

        val adapter = Adapter(productList)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        //상품 사이에 회색 줄 추가
        val decoration = DividerItemDecoration(this, VERTICAL)
        binding.recyclerview.addItemDecoration(decoration)

        //알림 버튼
        binding.notiButton.setOnClickListener {
            notification()
        }

        adapter.itemClick = object : Adapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val clickProduct = productList[position]
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("Item", clickProduct)
                startActivity(intent)
            }
        }

        // Spinner
        val adList = resources.getStringArray(R.array.location)
        val adAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, adList)
        binding.spinner.adapter = adAdapter

        //맨위로 가기 버튼
        //addOnScrollListener = 현재 스크롤 상태 체크
        var isTop = true
        binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                //canScrollVertically = 스크롤이 최상단 , SCROLL_STATE_IDLE = 현재 스크롤 하지 않은 상태
                if (!binding.recyclerview.canScrollVertically(-1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    binding.fabTop.visibility = View.INVISIBLE
                    binding.fabTop.startAnimation(AlphaAnimation(1f, 0f).also {
                        it.duration = 1000
                    })
                } else if (isTop) {
                    binding.fabTop.visibility = View.VISIBLE
                    binding.fabTop.startAnimation(AlphaAnimation(0f, 1f).also {
                        it.duration = 1000
                    })
                    isTop = false
                }
            }
        })
        binding.fabTop.setOnClickListener {
            //smoothScrollToPosition = 부드럽게 스크롤 상단으로 옮김
            binding.recyclerview.smoothScrollToPosition(0)
        }
    }

    //알림
    private fun notification() {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channelId = "one-channel"
                val channelName = "My Channel"
                val channel = NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                manager.createNotificationChannel(channel)
                NotificationCompat.Builder(this, channelId)
            } else {
                NotificationCompat.Builder(this)
            }
        builder.run {
            setSmallIcon(R.drawable.ic_launcher_background)
            setContentTitle("키워드 알림")
            setContentText("설정한 키워드에 대한 알림이 도착했습니다!!")
        }
        manager.notify(11, builder.build())
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        AlertDialog.Builder(this).apply {
            setIcon(R.drawable.chat)
            setTitle("종료")
            setMessage("정말로 종료하시겠습니까?")
            setPositiveButton("확인") { dialog, _ ->
                finish()
            }
            setNegativeButton("취소") { dialog, _ ->
                dialog.dismiss()
            }
            show()
        }
    }
}
