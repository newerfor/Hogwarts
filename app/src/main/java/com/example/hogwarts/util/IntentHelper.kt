package com.example.hogwarts.util

import android.content.Context
import android.content.Intent
import com.example.hogwarts.util.Constant.ID_KEY

class IntentHelper {
    fun intentStartById(nameActivity: String, context: Context, id: String) {
        val actClass = Class.forName(nameActivity)
        val intent = Intent(context, actClass)
        intent.putExtra(ID_KEY, id.toString())
        context.startActivity(intent)
    }
}