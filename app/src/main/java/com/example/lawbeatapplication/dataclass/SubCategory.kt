package com.example.lawbeatapplication.dataclass

import com.google.gson.annotations.SerializedName

data class SubCategory(
    @SerializedName("id") val tid : Int,
    @SerializedName("name") val name : String,
    @SerializedName("weight") val weight : Int
)
