package com.edominguez.compasstest.utils

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

class Functions {

    companion object {

        fun copyText(activity: Activity, text: String) {
            val clipboardManager =
                activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("text", text)
            clipboardManager.setPrimaryClip(clipData)
        }

    }
}