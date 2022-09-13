package com.verdant.metamorph.model

data class NotificationRowResponse(
    var topic: String? = null,
    var orderNo: String? = null,
    var deliveryDate: String? = null,
    var translatorPay: Int? = null,
    var translatorFee: Int? = null,
    var orderStatusName: String? = null,
    var webOrderTitle: String? = null,
    var deliveryPlan: String? = null
)