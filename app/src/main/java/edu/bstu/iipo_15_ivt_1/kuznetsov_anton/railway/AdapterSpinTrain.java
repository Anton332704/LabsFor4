package edu.bstu.iipo_15_ivt_1.kuznetsov_anton.railway;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import edu.bstu.iipo_15_ivt_1.kuznetsov_anton.railway.R;

/**
 * Created by user on 22.12.2015.
 */
public class AdapterSpinTrain extends ResourceCursorAdapter {


    public AdapterSpinTrain(Context context, int layout, Cursor c, int flags) {
        super(context, layout, c, flags);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {

        ((TextView) view.findViewById(R.id.textViewNumSpin)).setText(cursor.getInt(cursor.getColumnIndex("numbertrain")) + "");
        ((TextView) view.findViewById(R.id.textViewFromSpin)).setText(cursor.getString(cursor.getColumnIndex("fromtown")) + "");
        ((TextView) view.findViewById(R.id.textViewToSpin)).setText(cursor.getString(cursor.getColumnIndex("totown")) + "");

        ((ImageView)view.findViewById(R.id.imageDeleteSpin)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "нажато" + cursor.getPosition(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
