package com.pamarcolino.sofie.network.response

import com.google.gson.annotations.SerializedName
import com.pamarcolino.sofie.network.dto.TaskDTO

data class SaveTaskResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val task: TaskDTO)