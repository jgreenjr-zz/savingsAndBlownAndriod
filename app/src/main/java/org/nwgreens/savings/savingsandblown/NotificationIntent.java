package org.nwgreens.savings.savingsandblown;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by root on 1/18/16.
 */
public class NotificationIntent extends IntentService {

    public NotificationIntent(){
        super("NotificationService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences myPrefFile = getSharedPreferences("MyPrefFile",0);

        Calendar cd = Calendar.getInstance();
        cd.setTime(new Date());
        cd.add(Calendar.DATE, -1);
        Date currentDate = cd.getTime();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

        String lastLoginTime = myPrefFile.getString("LoginTime", simpleDateFormat.format(currentDate));
        try {

            currentDate = simpleDateFormat.parse(lastLoginTime);

        }
        catch(ParseException pe){

        }

        cd.setTime(new Date());

        cd.add(Calendar.HOUR, -24);

        if(currentDate.before(cd.getTime())) {
            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(android.R.drawable.star_on)
                            .setContentTitle("Green Savings and Blown")
                            .setContentText("You have not logged into GSB")
                            .setAutoCancel(true)
                            .setColor(Color.GREEN)
                            .setSound(alarmSound).setStyle(new NotificationCompat.BigTextStyle().bigText("You have not logged into GSB in a while, are you missing anything?"));

            Intent resultIntent = new Intent(getApplicationContext(), LoginActivity.class);

            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(001, mBuilder.build());
        }


            stopService(intent);

    }
}
