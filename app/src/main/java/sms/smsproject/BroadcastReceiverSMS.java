package sms.smsproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class BroadcastReceiverSMS extends BroadcastReceiver {

    private Tokenize token = new Tokenize();
    private Notifyer notifyer;
    public BroadcastReceiverSMS() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Bundle bundle=intent.getExtras();

        notifyer = new Notifyer();

        Object[] messages=(Object[])bundle.get("pdus");
        SmsMessage[] sms=new SmsMessage[messages.length];

        for(int n=0;n<messages.length;n++){
            sms[n]=SmsMessage.createFromPdu((byte[]) messages[n]);
        }

        String message = "";
        for(SmsMessage msg:sms){
            message += msg.getOriginatingAddress() +","+msg.getMessageBody();
        }

        bundle = null;
        sms = null;
        messages = null;

        if(token.isValid(message)){
            notifyer.notify(context,message);
        }


    }
}
