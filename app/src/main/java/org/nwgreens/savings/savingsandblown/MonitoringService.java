package org.nwgreens.savings.savingsandblown;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;

/**
 * Created by root on 1/21/16.
 */
public class MonitoringService extends IntentService {
    public MonitoringService(){
        super("MonitoringService");
    }

    PendingIntent notificatonAlarmIntent;
    @Override
    protected void onHandleIntent(Intent intent) {
        Intent i = new Intent(getApplicationContext(), NotificationIntent.class);
        startService(i);
        Intent alarmIntent = new Intent(this, MyAlarm.class);
        long scTime = 60 * 60000;//mins
        notificatonAlarmIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + scTime, scTime, notificatonAlarmIntent);
    }

    @Override
    public boolean stopService(Intent name) {
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.cancel(notificatonAlarmIntent);
        return super.stopService(name);
    }
}
