package com.example.metamorph.model


data class OrderDetailsByIdParams(
    var cultureId: String = "jp",
    var orderNo: String = "",
    var application: Int = 4
)
