package com.pamarcolino.sofie.network.response

import com.google.gson.annotations.SerializedName
import com.pamarcolino.sofie.network.dto.TaskDTO

data class DeleteTaskResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val task: TaskDTO
)