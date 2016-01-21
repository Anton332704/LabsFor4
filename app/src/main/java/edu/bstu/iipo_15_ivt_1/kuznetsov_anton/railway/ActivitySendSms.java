package edu.bstu.iipo_15_ivt_1.kuznetsov_anton.railway;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import edu.bstu.iipo_15_ivt_1.helppac.Item_Contact;
import edu.bstu.iipo_15_ivt_1.helppac.SendSms;

/**
 * Created by user on 28.12.2015.
 */
public class ActivitySendSms extends AppCompatActivity{
    EditText editTextSms, editTextNumberSms;
    Spinner spinner;
    Context context;
    ContactsAdapter adapterSpinTrain;
    ArrayList<Item_Contact> itemContacts;
    private final static String SENT = "SENT_SMS_ACTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_sms_activity);
        itemContacts = new ArrayList<Item_Contact>();
        context = this;
        editTextSms = (EditText) findViewById(R.id.editTextSmsField);
        editTextNumberSms = (EditText) findViewById(R.id.editNumberSmsField);

        spinner = (Spinner) findViewById(R.id.spinnerContakts);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editTextNumberSms.setText(((TextView)view.findViewById(R.id.textViewContNumber)).getText());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        editTextSms.setText("Привет! " + getIntent().getStringExtra("DateStart") + " в "
                + getIntent().getStringExtra("Start") + " я уезжаю в " +
                getIntent().getStringExtra("ToTown") + " из " + getIntent().getStringExtra("FromTown"));

        spinner.setSelection(1);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[] {CommonDataKinds.Phone.DISPLAY_NAME, CommonDataKinds.Phone.NUMBER}, null, null, null);
        if (cursor.getCount() > 0)
        {
            while (cursor.moveToNext())
            {
                itemContacts.add(new Item_Contact(cursor.getString(0), cursor.getString(1)));
            }
        }
        adapterSpinTrain = new ContactsAdapter(itemContacts, context);
        spinner.setAdapter(adapterSpinTrain);
    }

    public void sendSms(View view)
    {
        String textSms = editTextSms.getText().toString();
        String numberSms = editTextNumberSms.getText().toString();
        SmsManager smsManager = SmsManager.getDefault();
        SendSms sendSms = new SendSms(context);
        registerReceiver(sendSms, new IntentFilter(SENT));
        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
        smsManager.sendTextMessage(numberSms, null, textSms, sentPI, null);
    }



}
