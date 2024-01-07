package com.example.applemarket

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Post(
    val itemImage: Int,
    val itemTitle: String,
    val itemAddress: String,
    val itemPrice: String,
    val itemInfo: String,
    val itemnickname: String,
    val itemheartCount: Int,
    val itemcomment: Int
) : Parcelable