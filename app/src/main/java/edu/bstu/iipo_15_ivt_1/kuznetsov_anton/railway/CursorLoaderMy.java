package edu.bstu.iipo_15_ivt_1.kuznetsov_anton.railway;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.CursorLoader;

import edu.bstu.iipo_15_ivt_1.helppac.SqlHelper;

/**
 * Created by user on 20.12.2015.
 */
public class CursorLoaderMy extends CursorLoader {
    private static final String TRAIN_TABLE = "train";
    private static final String TICKET_TABLE = "ticket";
    private static final String TRAIN_TYPE_ID = "type_train_id";
    Context cont;
    SqlHelper helper;
    String farInt;
    public CursorLoaderMy(Context context, String f) {
        super(context);
        cont = context;
        helper = new SqlHelper(context);
        farInt = f;
    }

    @Override
    public Cursor loadInBackground() {
        SQLiteDatabase database = helper.getWritableDatabase();
        String tables = TICKET_TABLE + " as TK inner join " + TRAIN_TABLE + " as TR on TR._id = TK.train_id";
        String tables1 = "ticket as TK inner join train as TR on TR._id = TK.train_id";
        String selection = "type_train_id = " + farInt;
        Cursor c = database.query(tables1, null, selection, null, null, null, null);
        return c;
    }
}
