package com.example.lawbeatapplication.dataclass

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @Expose
    @SerializedName("data")
    val data: List<NewsData>?,
    @Expose
    @SerializedName("message")
    val message: String,
    @Expose
    @SerializedName("pager")
    val pager: List<PagerResponse>,
    @Expose
    @SerializedName("statusCode")
    val statusCode: Int
)
