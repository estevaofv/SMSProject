package sms.smsproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.session.MediaSession;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private SmsManager smsManager;
    private EditText sms;

    private Tokenize token = new Tokenize();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sms = (EditText)findViewById(R.id.sms);


    }


    public void enviar(View v){
        smsManager = SmsManager.getDefault();

        String phoneNo = "06281685163";

        String txt = token.getToken() + "," + sms.getText().toString();

        try{
            smsManager.sendTextMessage(phoneNo, null, txt, null, null);
            Toast.makeText(this,"Sms enviado com sucesso",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(this,"Erro no envio do sms", Toast.LENGTH_LONG).show();
        }

        sms.setText("");
    }

    public void receber(String txt){
        Intent i = new Intent(this,ReceiveSMSActivity.class);
        i.putExtra(ReceiveSMSActivity.MSG_RECEVED, txt);
        startActivity(i);
    }


    /*
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle=intent.getExtras();

            Object[] messages=(Object[])bundle.get("pdus");
            SmsMessage[] sms=new SmsMessage[messages.length];

            for(int n=0;n<messages.length;n++){
                sms[n]=SmsMessage.createFromPdu((byte[]) messages[n]);
            }

            String message = "";
            for(SmsMessage msg:sms){
                message += "From: "+msg.getOriginatingAddress()+" "+ "Message: "+msg.getMessageBody();
            }

            bundle = null;
            sms = null;
            messages = null;
            receber(message);
        }
    };
    */



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
