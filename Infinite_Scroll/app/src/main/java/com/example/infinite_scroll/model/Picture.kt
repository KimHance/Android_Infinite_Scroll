package com.example.infinite_scroll.model

import com.google.gson.annotations.SerializedName

data class Picture(
    @SerializedName("id")
    val id : String,
    @SerializedName("urls")
    val url : Url
)