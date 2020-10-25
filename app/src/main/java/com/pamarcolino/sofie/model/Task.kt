package com.pamarcolino.sofie.model

import java.io.Serializable
import java.util.*

class Task(
    val id: UUID,
    val description: String,
    val email: String,
    val date: String,
    val title: String
) : Serializable