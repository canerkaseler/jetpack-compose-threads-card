package com.canerkaseler.threadscard.entity

import com.canerkaseler.threadscard.R

data class User(
    val username: String = "CANERKASELER",
    val instagram: String = "canerkaseler",
    val userId: String = "071030501",
    val date: String = "WED JUL 7",
    val time: String = "03:24 P.M.",
    val userImage: Int = R.drawable.ic_user_avatar,
    val userQrCode: Int = R.drawable.ic_qr_code,
)
