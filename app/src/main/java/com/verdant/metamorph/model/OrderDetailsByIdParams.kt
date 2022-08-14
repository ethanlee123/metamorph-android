package com.verdant.metamorph.model


data class OrderDetailsByIdParams(
    var cultureId: String = "jp",
    var orderNo: String = "",
    var application: Int = 4
)
