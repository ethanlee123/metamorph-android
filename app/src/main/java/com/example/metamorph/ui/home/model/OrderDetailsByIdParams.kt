package com.example.metamorph.ui.home.model


data class OrderDetailsByIdParams(
    var cultureId: String = "jp",
    var orderNo: String = "",
    var application: Int = 4
)

//data class WebOrderResponse(
//    var date: String = "",
//    var time: String = "",
//    var paid: String = "",
//    var earn: String = "",
//    var status: String = "",
//    var from: String = "",
//    var order: String = "",
//    var trn: String = ""
//)
