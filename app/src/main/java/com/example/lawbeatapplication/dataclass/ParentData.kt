package com.example.lawbeatapplication.dataclass

data class ParentData(
    val parentTitle:DataResponse?=null,
    var type:Int = Constants.PARENT,
    var subList : MutableList<SubCategory> = ArrayList(),
    var isExpanded:Boolean = false
)
