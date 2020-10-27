package com.pamarcolino.sofie.network.dto

import com.google.gson.annotations.SerializedName
import com.pamarcolino.sofie.model.Task
import java.util.*

data class TaskDTO(
    @SerializedName("_id") val id: UUID,
    @SerializedName("description") val description: String,
    @SerializedName("email") val email: String,
    @SerializedName("title") val title: String
)

fun TaskDTO.parseToModel() = Task(
    id,
    description,
    email,
    title
)