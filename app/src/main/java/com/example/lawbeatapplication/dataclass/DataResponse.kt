package com.example.lawbeatapplication.dataclass

import com.google.gson.annotations.SerializedName

class DataResponse(
    @field:SerializedName("id")
    val tid : String,

    @field:SerializedName("name")
    val name : String,

    @field:SerializedName("weight")
    val weight : String,

    @SerializedName("subcat")
    val subcat : List<SubCategory>
    )