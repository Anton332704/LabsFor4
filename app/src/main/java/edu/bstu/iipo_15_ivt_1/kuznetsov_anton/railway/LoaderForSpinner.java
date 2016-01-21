package edu.bstu.iipo_15_ivt_1.kuznetsov_anton.railway;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.CursorLoader;

import edu.bstu.iipo_15_ivt_1.helppac.SqlHelper;

/**
 * Created by user on 22.12.2015.
 */
public class LoaderForSpinner extends CursorLoader {
    SqlHelper helper;
    public LoaderForSpinner(Context context) {
        super(context);
        helper = new SqlHelper(context);
    }

    @Override
    public Cursor loadInBackground() {
        SQLiteDatabase database = helper.getWritableDatabase();
        Cursor c = database.query("train", null, null, null, null, null, null);
        return c;
    }
}
