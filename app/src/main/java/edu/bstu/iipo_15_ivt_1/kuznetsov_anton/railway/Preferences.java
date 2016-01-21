package edu.bstu.iipo_15_ivt_1.kuznetsov_anton.railway;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.View;
import android.widget.Toast;

import edu.bstu.iipo_15_ivt_1.helppac.SqlHelper;
import edu.bstu.iipo_15_ivt_1.kuznetsov_anton.railway.R;

/**
 * Created by user on 09.11.2015.
 */
public class Preferences extends PreferenceActivity {
    SqlHelper sqlHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        sqlHelper = new SqlHelper(this);
    }

    public void clearDataBase(View view)
    {
        SQLiteDatabase database = sqlHelper.getWritableDatabase();
        database.delete("ticket", null, null);
        Toast.makeText(this, "123", Toast.LENGTH_SHORT).show();
    }
}
