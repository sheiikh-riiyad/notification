package com.notificationlogger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.notificationlogger.ui.theme.NotificationLoggerTheme
import org.json.JSONArray
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NotificationLoggerTheme {
                NotificationScreen()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun NotificationScreen() {
        val context = this
        val notifications = remember { mutableStateListOf<String>() }

        LaunchedEffect(Unit) {
            val file = File(context.filesDir, "notification.txt")
            if (file.exists()) {
                try {
                    file.forEachLine {
                        notifications.add(it)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Notification Logger") })
            },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp)
                ) {
                    if (notifications.isEmpty()) {
                        Text("No notifications found.")
                    } else {
                        LazyColumn {
                            items(notifications) { msg ->
                                Text(msg, modifier = Modifier.padding(vertical = 4.dp))
                            }
                        }
                    }
                }
            }
        )
    }
}
