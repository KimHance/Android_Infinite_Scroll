package com.example.infinite_scroll.model

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("total")
    val total : Int,
    @SerializedName("total_pages")
    val pages : Int,
    @SerializedName("results")
    val results : List<Picture>
)