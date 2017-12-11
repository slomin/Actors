package com.kotlinblog.actors.data.rest

import com.google.gson.annotations.SerializedName

data class Actor(
        @SerializedName("name") val name: String,
        @SerializedName("description") val description: String,
        @SerializedName("dob") val dob: String,
        @SerializedName("country") val country: String,
        @SerializedName("height") val height: String,
        @SerializedName("spouse") val spouse: String,
        @SerializedName("children") val children: String,
        @SerializedName("image") val image: String
)