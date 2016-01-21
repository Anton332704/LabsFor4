package edu.bstu.iipo_15_ivt_1.kuznetsov_anton.railway;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by user on 14.10.2015.
 */
public class HelloActivity extends AppCompatActivity
{
    static final String TABLE_NAME = "login_table";
    static final String LOGIN = "login";
    static final String PASSWORD = "password";
    final Uri uriData2 = Uri.parse("content://com.mycontentprovider.data/" + TABLE_NAME);

    EditText editName, editPasswd;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.hello_activity);
        editName = (EditText) findViewById(R.id.input_login);
        editPasswd =  (EditText) findViewById(R.id.input_password);
    }

    public void enterToMainActivity(View v)
    {
        String loginStr = editName.getText().toString();
        String passwordStr = editPasswd.getText().toString();
        Cursor cursor = getContentResolver().query(uriData2, null, null, null, null);
        String infoStr = "";
        boolean exist = false;
        if (cursor != null) {
            cursor.moveToFirst();
            do {
                String qLog = cursor.getString(cursor.getColumnIndex(LOGIN));
                String qPas = cursor.getString(cursor.getColumnIndex(PASSWORD));
                if (qLog.equals(loginStr) && qPas.equals(passwordStr))
                {
                    exist = true;
                    break;
                }
            } while (cursor.moveToNext() == true);
        }

        if(exist)
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    public void startRegistration(View v)
    {
        DialogRegistration registration = new DialogRegistration();
        registration.show(getSupportFragmentManager(), null);
    }
}


