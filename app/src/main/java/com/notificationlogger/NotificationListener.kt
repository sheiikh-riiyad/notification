package com.notificationlogger

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.google.firebase.database.FirebaseDatabase
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
            val file = File(applicationContext.filesDir, "notifications.json")

            // 🔄 Load existing array or create new one
            val jsonArray = if (file.exists()) {
                JSONArray(file.readText())
            } else {
                JSONArray()
            }

            // ➕ Add new message
            jsonArray.put(message)

            // 💾 Save full array
            file.writeText(jsonArray.toString())

            // 🔄 Upload only this message
            uploadToFirebase(message)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun uploadToFirebase(message: String) {
        val ref = FirebaseDatabase.getInstance().getReference("notifications")

        val data = mapOf(
            "message" to message,
            "timestamp" to System.currentTimeMillis()
        )

        ref.push().setValue(data)
            .addOnSuccessListener {
                Log.d("FirebaseUpload", "Uploaded to Firebase: $message")
            }
            .addOnFailureListener {
                Log.e("FirebaseUpload", "Firebase upload failed", it)
            }
    }
}
