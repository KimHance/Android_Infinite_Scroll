package com.example.infinite_scroll.domain.model

import com.google.gson.annotations.SerializedName

data class Url(
    @SerializedName("thumb")
    val url : String
)