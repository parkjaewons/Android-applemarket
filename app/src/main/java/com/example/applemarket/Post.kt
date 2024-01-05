package com.example.applemarket

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Post(
    val itemImage: Int,
    val itemPrice: Int,
    val itemTitle: String,
    val itemInfo: String,
    val itemAddress: String,
    val nickname: String,
    val heartCount: Int,
    val comment: Int
) : Parcelable