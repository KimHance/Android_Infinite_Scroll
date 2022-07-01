package com.example.infinite_scroll.model

import com.google.gson.annotations.SerializedName

class PicList(
    @SerializedName("id")
    val id : String,
    @SerializedName("urls")
    val url : List<Url>
)