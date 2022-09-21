package com.example.lawbeatapplication.dataclass

import com.google.gson.annotations.SerializedName

data class PagerResponse(
    @SerializedName("current_page") val current_page : Int? = null,
    @SerializedName("total_items") val total_items : Int? = null,
    @SerializedName("total_pages") val total_pages : String? = null,
    @SerializedName("items_per_page") val items_per_page : String? = null
)
