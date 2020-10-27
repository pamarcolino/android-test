package com.pamarcolino.sofie.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class Task(
    val id: UUID,
    val description: String,
    val email: String,
    val title: String
) : Parcelable