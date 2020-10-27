package com.pamarcolino.sofie.util

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.pamarcolino.sofie.R

class AlertDialogUtil {
    companion object {
        fun show(context: Context, title: String, message: String, action: () -> Unit){

            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setMessage(message)
            builder.setPositiveButton(R.string.lbl_ok){ _, _ ->
                action()
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()


        }
    }
}