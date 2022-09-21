package com.example.lawbeatapplication.dataclass

data class CategoryResponse(
    val statusCode:Int,
    val message:String,
    val data:ArrayList<DataResponse>
)