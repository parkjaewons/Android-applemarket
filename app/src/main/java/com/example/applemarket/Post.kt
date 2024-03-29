package com.example.applemarket

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Post(
    val itemImage: Int,
    val itemTitle: String,
    val itemInfo: String,
    val itemnickname: String,
    val itemPrice: Int,
    val itemAddress: String,
    var itemheartCount: Int,
    val itemcomment: Int,
    val usermanners: String,
    val mannersImage: Int,
    var isLiked: Boolean = false,
) : Parcelable

