package com.verdant.metamorph.model

// Used in OrderDetails response
data class WebDocument(
    val CharacterCount: Int,
    val CreatedDate: String,
    val DownloadURL: String,
    val Extension: String,
    val FileDescription: String,
    val FileName: String,
    val FileSize: Int,
    val ID: String,
    val IsActive: Boolean,
    val OrderID: String,
    val OriginalFileName: String,
    val Status: Int,
    val UploadDate: String,
    val WordCount: Int
)