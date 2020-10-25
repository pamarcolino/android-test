package com.pamarcolino.sofie.network.dto

import com.google.gson.annotations.SerializedName
import java.util.*

data class TaskDTO(
    @SerializedName("_id") val id: UUID,
    @SerializedName("description") val description: String,
    @SerializedName("email") val email: String,
    @SerializedName("when") val date: String,
    @SerializedName("title") val title: String
)