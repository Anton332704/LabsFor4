package edu.bstu.iipo_15_ivt_1.kuznetsov_anton.railway;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import edu.bstu.iipo_15_ivt_1.helppac.SqlHelper;

/**
 * Created by user on 15.12.2015.
 */
public class MyCursorAdapter extends ResourceCursorAdapter {

    public static final String COLUMN_TXT = "txt";
    SqlHelper helper;
    SQLiteDatabase database;
    public MyCursorAdapter(Context context, int layout, Cursor c, int flags) {
        super(context, layout, c, flags);
        helper = new SqlHelper(context);
        database = helper.getWritableDatabase();
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        final int idInt = cursor.getInt(0);

        int ticketId = cursor.getColumnIndex("_id");

        final String numberTrainStr = cursor.getInt(cursor.getColumnIndex("numbertrain")) + "";
        final String numberFromStr = cursor.getString(cursor.getColumnIndex("fromtown")) + "";
        final String numberToStr = cursor.getString(cursor.getColumnIndex("totown")) + "";
        final String numberStartStr = cursor.getString(cursor.getColumnIndex("timestart")) + "";
        final String numberFinishStr = cursor.getString(cursor.getColumnIndex("timefinish")) + "";
        final String numberDateStartStr = cursor.getString(cursor.getColumnIndex("datestart")) + "";
        final String numberDateFinishStr = cursor.getString(cursor.getColumnIndex("datefinish")) + "";
        final String numberNumbCarStr = cursor.getInt(cursor.getColumnIndex("number_carriage")) + "";
        final String numberNumbSitStr = cursor.getInt(cursor.getColumnIndex("number_carriage")) + "";


        ((TextView) view.findViewById(R.id.TextNumber)).setText(numberTrainStr);
        ((TextView) view.findViewById(R.id.TextNameFrom)).setText(numberFromStr);
        ((TextView) view.findViewById(R.id.TextNameTo)).setText(numberToStr);
        ((TextView) view.findViewById(R.id.TextTimeStart)).setText(numberStartStr);
        ((TextView) view.findViewById(R.id.TextTimeFinish)).setText(numberFinishStr);
        ((TextView) view.findViewById(R.id.TextDateStart)).setText(numberDateStartStr);
        ((TextView) view.findViewById(R.id.TextDateFinish)).setText(numberDateFinishStr);
        ((TextView) view.findViewById(R.id.TextCarriage)).setText(numberNumbCarStr);
        ((TextView) view.findViewById(R.id.TextSit)).setText(numberNumbSitStr);
        ImageView imageViewDelete = (ImageView) view.findViewById(R.id.imageDelete);
            imageViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int delCount = database.delete("ticket", "_id = " + idInt, null);
                    notifyDataSetChanged();
                    notifyDataSetInvalidated();
                    String tables1 = "ticket as TK inner join train as TR on TR._id = TK.train_id";
                    String farInt = Singleton.INSTANCE.far;
                    String selection = "type_train_id = " + farInt;
                    Cursor cursor = database.query(tables1, null, selection, null, null, null, null);
                    changeCursor(cursor);
                }
            });

            ImageView imageSms = (ImageView)view.findViewById(R.id.imageSms);
            imageSms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentSms = new Intent(context, ActivitySendSms.class);
                    intentSms.putExtra("FromTown", numberFromStr );
                    intentSms.putExtra("DateStart", numberDateStartStr);
                    intentSms.putExtra("ToTown", numberToStr);
                    intentSms.putExtra("Start", numberStartStr );
                    context.startActivity(intentSms);
                }
            });
    }
}