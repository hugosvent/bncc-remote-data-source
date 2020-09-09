package com.javent.bncc_remote_data_source.model
import com.google.gson.annotations.SerializedName

data class LookUpData(
    @SerializedName("img_icon")
    val imgIcon: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String
)
