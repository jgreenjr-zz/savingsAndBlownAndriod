package org.nwgreens.savings.savingsandblown;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by root on 1/18/16.
 */
public class MyAlarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent i = new Intent(context, NotificationIntent.class);
        context.startService(i);
    }
}
