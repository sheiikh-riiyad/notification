// NotificationListener.kt
package com.notificationlogger

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import org.json.JSONArray
import java.io.File

class NotificationListener : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val extras = sbn.notification.extras
        val title = extras.getString("android.title") ?: ""
        val text = extras.getCharSequence("android.text")?.toString() ?: ""
        val packageName = sbn.packageName

        val message = "$packageName - $title: $text"
        Log.d("NotificationLogger", message)

        try {
            val file = File(applicationContext.filesDir, "notification.txt")
            Log.d("NotificationLogger", "Writing to file: ${file.absolutePath}")

            val jsonArray = if (file.exists()) {
                JSONArray(file.readText())
            } else {
                JSONArray()
            }

            jsonArray.put(message)
            file.writeText(jsonArray.toString())

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
