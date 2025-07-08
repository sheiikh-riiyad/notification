package com.notificationlogger

import android.content.Context
import java.io.File

object NotificationStorage {
    private const val FILE_NAME = "notifications.json"

    fun save(context: Context, notifications: List<String>) {
        val file = File(context.filesDir, FILE_NAME)
        file.writeText(notifications.joinToString("\n"))
    }

    fun load(context: Context): List<String> {
        val file = File(context.filesDir, FILE_NAME)
        return if (file.exists()) {
            file.readLines()
        } else {
            emptyList()
        }
    }
}
