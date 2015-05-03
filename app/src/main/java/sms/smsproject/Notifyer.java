package sms.smsproject;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

/**
 * Created by sanderson on 02/05/2015.
 */
public class Notifyer {

    public static int NOTIFY_ID=100;

    public void notify(Context context, String txt){

        NotificationCompat.Builder mBuilder;
        mBuilder = new NotificationCompat.Builder(context);

        // ICONE
        mBuilder.setSmallIcon(R.drawable.alert);
        //TITULO
        mBuilder.setContentTitle("Machine Alert");
        //TEXTO DA NOTIFICAÇÃO
        mBuilder.setContentText("Process command");
        //VIBRAR
        mBuilder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });
        //LuZ DE ALERTA
        mBuilder.setLights(Color.RED, 3000, 3000);
        //SOM
        Uri sound = Uri.parse("android.resource://" +
                               context.getPackageName() +
                               "/" + R.raw.alert);
        mBuilder.setSound(sound);


        // INTENT PARA CHAMAR ACTIVITY
        Intent intent = new Intent(context, ReceiveSMSActivity.class);
        intent.putExtra(ReceiveSMSActivity.MSG_RECEVED, txt);


        // criando pendencia de chamada da activity
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);



        // Instância do gerenciador de notificações NotificationManager
        NotificationManager mNotifyMgr =
            (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        // NOTIFICA AO ANDROID com o id=100
        mNotifyMgr.notify(100, mBuilder.build());


    }
}
