package edu.cnm.deepdive.notificationchannel;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationCompat.Builder;

public class NotificationHelper extends ContextWrapper {

  public static final String channel1Id = "channel1Id";
  public static final String channel1Name = "channel1Name";
  public static final String channel2Id = "channel2Id";
  public static final String channel2Name = "channel2Name";

  private NotificationManager mManager;

  public NotificationHelper(Context base) {
    super(base);
    if (VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      createChannels();
    }
  }

  @RequiresApi(api = VERSION_CODES.O)
  public void createChannels() {
    NotificationChannel channel1 = new NotificationChannel(channel1Id, channel1Name,
        NotificationManager.IMPORTANCE_DEFAULT);
    channel1.enableLights(true);
    channel1.enableVibration(true);
    channel1.setLightColor(R.color.colorPrimary);
    channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

    getManager().createNotificationChannel(channel1);

    NotificationChannel channel2 = new NotificationChannel(channel2Id, channel2Name,
        NotificationManager.IMPORTANCE_DEFAULT);
    channel2.enableLights(true);
    channel2.enableVibration(true);
    channel2.setLightColor(R.color.colorPrimary);
    channel2.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

    getManager().createNotificationChannel(channel2);


  }

  public NotificationManager getManager() {
    if (mManager == null) {
      mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

    }
    return mManager;
  }

  public NotificationCompat.Builder getChannel1Notification(String title, String message) {
    return new Builder(getApplicationContext(), channel1Id)
        .setContentTitle(title)
        .setContentText(message)
        .setSmallIcon(R.drawable.ic_one);

  }

  public NotificationCompat.Builder getChannel2Notification(String title, String message) {
    return new Builder(getApplicationContext(), channel2Id)
        .setContentTitle(title)
        .setContentText(message)
        .setSmallIcon(R.drawable.ic_two);

  }
}
