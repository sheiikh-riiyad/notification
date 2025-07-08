# 📱 NotificationLogger (Kotlin)

**NotificationLogger** is a simple personal-use Android app written in **pure Kotlin** that runs silently in the background, captures all incoming Android system notifications (e.g. from WhatsApp, Messenger, SMS), stores them in local storage, and persists even after app kills or device reboots.

---

## 🚀 Features

- ✅ Collects **all app notifications** in real-time
- ✅ Stores notifications in local file `notification.txt`
- ✅ Runs in the background using a **foreground service**
- ✅ **Auto-starts** on device boot
- ✅ Works silently — **no visible UI or notifications**
- ✅ Built in **Jetpack Compose**
- ✅ Minimal battery and resource usage

---

## 🛠 Built With

- **Kotlin** (pure Android)
- **Jetpack Compose** (for UI)
- **ForegroundService** (background process)
- **NotificationListenerService** (notification capture)
- **BootReceiver** (auto start on reboot)
- Android SDK 24+

---

## 🧩 App Structure

```plaintext
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/notificationlogger/
│   │   │   │   ├── MainActivity.kt           # Compose UI to display logs
│   │   │   │   ├── NotificationListener.kt   # Listens to all system notifications
│   │   │   │   ├── ForegroundService.kt      # Background foreground service
│   │   │   │   └── BootReceiver.kt           # Starts service on boot
│   │   │   └── AndroidManifest.xml           # Permissions, services, receivers
│   └── ...
├── build.gradle
└── README.md
