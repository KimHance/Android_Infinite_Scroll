package com.example.infinite_scroll.model

import com.google.gson.annotations.SerializedName

data class Url(
    @SerializedName("raw")
    val url : String
)