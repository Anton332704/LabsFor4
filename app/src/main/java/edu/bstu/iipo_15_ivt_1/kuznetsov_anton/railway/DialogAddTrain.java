package edu.bstu.iipo_15_ivt_1.kuznetsov_anton.railway;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import edu.bstu.iipo_15_ivt_1.helppac.SqlHelper;
import edu.bstu.iipo_15_ivt_1.kuznetsov_anton.railway.R;

/**
 * Created by user on 16.12.2015.
 */
public class DialogAddTrain extends DialogFragment implements View.OnClickListener{

    EditText editNumber, editFrom, editTo;
    SqlHelper helper;
    Context cont;
    public DialogAddTrain(Context context) {
        cont = context;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Title!");
        View v = inflater.inflate(R.layout.dialog_layout, null);
        editNumber = (EditText)v.findViewById(R.id.editTextNumberTrainDialog);
        editFrom = (EditText)v.findViewById(R.id.editTextFromDialog);
        editTo = (EditText)v.findViewById(R.id.editTextToDialog);
        v.findViewById(R.id.btnYes).setOnClickListener(this);
        v.findViewById(R.id.btnNo).setOnClickListener(this);

        return v;
    }

    public void addTrainFromDialog(View v)
    {
        int numberInt = Integer.parseInt(editNumber.getText().toString());
        String fromStr = editFrom.getText().toString();
        String toStr = editTo.getText().toString();

        helper = new SqlHelper(cont);
        SQLiteDatabase database = helper.getWritableDatabase();

        Cursor c = database.query("train", null, null, null, null, null, null);
        ContentValues cv = new ContentValues();
        cv.put("_id", numberInt);
        cv.put("numbertrain", numberInt);
        cv.put("fromtown", fromStr);
        cv.put("totown", toStr);

    }

    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btnYes:
                int numberInt = Integer.parseInt(editNumber.getText().toString());
                String fromStr = editFrom.getText().toString();
                String toStr = editTo.getText().toString();

                helper = new SqlHelper(cont);
                SQLiteDatabase database = helper.getWritableDatabase();

                //Cursor c = database.query("train", null, null, null, null, null, null);
                ContentValues cv = new ContentValues();
                cv.put("_id", numberInt);
                cv.put("numbertrain", numberInt);
                cv.put("fromtown", fromStr);
                cv.put("totown", toStr);
                database.insert("train", null, cv);
                cv.clear();
                break;
            default:
                break;
        }
        dismiss();
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);

    }
}
