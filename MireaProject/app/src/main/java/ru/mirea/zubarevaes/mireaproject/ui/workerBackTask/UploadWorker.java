package ru.mirea.zubarevaes.mireaproject.ui.workerBackTask;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class UploadWorker extends Worker {

    public UploadWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        sendNotification("Ваша фоновая задача завершена");
        return Result.success();
    }

    // метод для отправки уведомления
    private void sendNotification(String message) {
        // получаем доступ к сервису уведомлений
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        String channelId = "BackgroundTaskChannel";

        // проверяем версию Android, поскольку каналы уведомлений требуются начиная с Android 8 (Oreo)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Создаем канал уведомлений
            NotificationChannel channel = new NotificationChannel(channelId, "Background Task Channel", NotificationManager.IMPORTANCE_DEFAULT);
            // Добавляем канал уведомлений в менеджер уведомлений
            notificationManager.createNotificationChannel(channel);
        }

        // создаем уведомление
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setSmallIcon(android.R.drawable.ic_dialog_info) // Иконка уведомления
                .setContentTitle("Background Task Notification") // Заголовок уведомления
                .setContentText(message) // Текст уведомления
                .setAutoCancel(true); // Уведомление автоматически исчезнет после нажатия

        // отправляем уведомление
        notificationManager.notify(1, builder.build());
    }
}
