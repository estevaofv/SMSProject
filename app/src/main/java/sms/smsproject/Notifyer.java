package sms.smsproject;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by sanderson on 02/05/2015.
 */
public class Notifyer {

    public static int NOTIFY_ID=100;

    public void notify(String txt){

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MyApp.getAppContext());
        mBuilder.setSmallIcon(R.drawable.alert);
        mBuilder.setContentTitle("Status Machine");
        mBuilder.setContentText("Click to process command");


        Intent resultIntent = new Intent(MyApp.getAppContext(), ReceiveSMSActivity.class);

        resultIntent.putExtra(ReceiveSMSActivity.MSG_RECEVED, txt);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        MyApp.getAppContext(),
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setContentIntent(resultPendingIntent);



        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) MyApp.getAppContext().getSystemService(MyApp.NOTIFICATION_SERVICE);

        // Builds the notification and issues it.
        mNotifyMgr.notify(NOTIFY_ID, mBuilder.build());


    }
}
