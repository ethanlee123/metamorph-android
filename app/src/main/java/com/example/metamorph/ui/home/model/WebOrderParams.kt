package com.example.metamorph.ui.home.model

data class WebOrderParams(
    var applicationId: Int = 4,
    var currentUserId: Int?  = null,
    var clientId: String? = null,
    var translatorId:String? = null,
    var pageNumber: Int = 1,
    var orderMinStatus: Int = 3,
    var trgLangId: String = "1484e197-70f8-4f64-98cf-e0f06eff6f49",
    var cultureId: String = "jp",
    var pageSize: String = "1"
)
