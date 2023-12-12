package mx.com.test.android.presentation.utils

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import mx.com.test.android.presentation.R

class NotificationService(
    private val context: Context
) {
    private val notificationManager = context.getSystemService(NotificationManager::class.java)
    private val picture = BitmapFactory.decodeResource(context.resources, R.drawable.flight_detail)


    fun showNotification(id: Int, title: String, contentText: String) {
        cancelNotification(id)
        val notification = createNotification(title = title, contentText = contentText)
        notificationManager.notify(id, notification)
    }

    private fun createNotification(title: String, contentText: String): Notification {
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        return NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(contentText)
            .setSmallIcon(R.drawable.ic_notification)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setStyle(
                NotificationCompat
                    .BigPictureStyle()
                    .bigPicture(picture)
                    .bigLargeIcon(picture)
            )
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .build()

    }

    private fun cancelNotification(id: Int) {
        notificationManager.cancel(id)
    }

    companion object {
        const val NOTIFICATION_CHANNEL_NAME = "App Test"
        const val NOTIFICATION_CHANNEL_ID = "flight_channel_id"
    }
}