package com.pamarcolino.sofie.network.response

import com.google.gson.annotations.SerializedName
import com.pamarcolino.sofie.network.dto.TaskDTO

data class GetTaskResponse(@SerializedName("data") val tasks: List<TaskDTO>)