package edu.bstu.iipo_15_ivt_1.helppac;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by user on 29.12.2015.
 */
public class SendSms extends BroadcastReceiver {
    Context context;

    public SendSms(Context context) {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        switch(getResultCode()) {
            case Activity.RESULT_OK:
                Toast.makeText(context, "SMS отправлено", Toast.LENGTH_SHORT).show();
                break;
            case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                Toast.makeText(context, "не удалось отправить SMS", Toast.LENGTH_SHORT).show();
                break;
            case SmsManager.RESULT_ERROR_RADIO_OFF:
                Toast.makeText(context, "модуль связи выключен", Toast.LENGTH_SHORT).show();
                //super.getAbortBroadcast();
                this.abortBroadcast();
                clearAbortBroadcast();
                break;
            case SmsManager.RESULT_ERROR_NULL_PDU:
                Toast.makeText(context, "PDU error", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
