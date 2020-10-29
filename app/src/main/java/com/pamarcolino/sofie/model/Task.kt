package com.pamarcolino.sofie.model

import android.os.Parcelable
import android.text.TextUtils
import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import kotlinx.android.parcel.Parcelize
import java.util.*
import com.pamarcolino.sofie.BR

@Parcelize
class Task(
    private val _id: UUID,
    private var _description: String,
    private var _email: String,
    private var _title: String
) : BaseObservable(), Parcelable {

    val id: UUID
        get() = _id

    var title: String
        @Bindable get() = _title
        set(value) {
            _title = value
            notifyPropertyChanged(BR.title)
        }

    var description: String
        @Bindable get() = _description
        set(value) {
            _description = value
            notifyPropertyChanged(BR.description)
        }

    var email: String
        @Bindable get() = _email
        set(value) {
            _email = value
            notifyPropertyChanged(BR.email)
        }

    fun isValid(): Boolean {
        return !TextUtils.isEmpty(title) &&
                !TextUtils.isEmpty(email) &&
                !TextUtils.isEmpty(description) &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}