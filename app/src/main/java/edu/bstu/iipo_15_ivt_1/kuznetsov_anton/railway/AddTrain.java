package edu.bstu.iipo_15_ivt_1.kuznetsov_anton.railway;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import edu.bstu.iipo_15_ivt_1.helppac.SqlHelper;

/**
 * Created by user on 21.10.2015.
 */
public class AddTrain extends FragmentActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    Context context;
    EditText editNumber, editNameFrom, editNameTo, editTimeStart, editTimeFinish, editDateStart, editDateFinish, editNumberCar, editNumberSits;
    Spinner spinner;
    SqlHelper helper;
    TimePicker timePickerStart;
    TimePicker timePickerFinish;
    AdapterSpinTrain adapterSpinTrain;
    CheckBox checkFar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_train);
        context = this;
        spinner = (Spinner)findViewById(R.id.spinnerMy);

        editTimeStart = (EditText)findViewById(R.id.editTextTimeStart);
        editTimeFinish = (EditText)findViewById(R.id.editTextTimeFinish);
        editDateStart = (EditText)findViewById(R.id.editTextDateStart);
        editDateFinish = (EditText)findViewById(R.id.editTextDateFinish);
        editNumberCar = (EditText)findViewById(R.id.editTextNumberCarriage);
        editNumberSits = (EditText)findViewById(R.id.editTextNumberSits);
        checkFar = (CheckBox)findViewById(R.id.checkBoxFar);

        helper = new SqlHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();

       /* Cursor c = database.query("train", null, null, null, null, null, null);

        String[] from = {"numbertrain", "fromtown", "totown"};
        int[] to = {R.id.textViewNumSpin, R.id.textViewFromSpin, R.id.textViewToSpin};
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(context, R.layout.spiner_item, null, from, to);*/


        getSupportLoaderManager().initLoader(1, null, this);
        adapterSpinTrain = new AdapterSpinTrain(context, R.layout.spiner_item, null, 0);
        spinner.setAdapter(adapterSpinTrain);
        spinner.setSelection(1);
        spinner.setPrompt("Номер поездов");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "выбрал" + position, Toast.LENGTH_SHORT);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //database.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().getLoader(1).forceLoad();
    }

    public void addTrain(View view)
    {
        int number = 0;
        int NumberCar = 0;
        int NumberSits = 0;
        String strFrom;
        String strTo;
        try
        {
            //number = Integer.parseInt(editNumber.getText().toString());
            View v = spinner.getSelectedView();
            String numSpinStr = ((TextView)v.findViewById(R.id.textViewNumSpin)).getText().toString();
            strFrom = ((TextView)v.findViewById(R.id.textViewFromSpin)).getText().toString();
            strTo = ((TextView)v.findViewById(R.id.textViewToSpin)).getText().toString();
            number = Integer.parseInt(numSpinStr);
            NumberCar = Integer.parseInt(editNumberCar.getText().toString());
            NumberSits = Integer.parseInt(editNumberSits.getText().toString());
        }
        catch (Exception e)
        {
            Toast.makeText(context, "Номер поезда, номер вагона и номер сиденья должны иметь целочисленный тип", Toast.LENGTH_LONG).show();
            return;
        }
        //timePickerFinish.getCurrentHour();
        String strTimeStart = editTimeStart.getText().toString();
        String strTimeFinish = editTimeFinish.getText().toString();
        String strDateStart = editDateStart.getText().toString();
        String strDateFinish = editDateFinish.getText().toString();
        String farInt = "0";
        boolean far = checkFar.isChecked();
        if(far)
        {
            farInt = "1";
        }
        else farInt = "0";

        if(NumberCar > 0 && NumberSits > 0 && number > 0)
        {
            SqlHelper sqlHelper = new SqlHelper(this);
            SQLiteDatabase database = sqlHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("_id", number);
            cv.put("numbertrain", number);
            cv.put("fromtown", strFrom);
            cv.put("totown", strTo);

            database.insert("train", null, cv);
            cv.clear();

            cv.put("timestart", strTimeStart);
            cv.put("timefinish", strTimeFinish);
            cv.put("datestart", strDateStart);
            cv.put("datefinish", strDateFinish);
            cv.put("train_id", number);
            cv.put("number_carriage", NumberCar);
            cv.put("number_seat", NumberSits);
            cv.put("type_train_id", farInt);
            long idInt = database.insert("ticket", null, cv);
            cv.clear();
            Toast.makeText(context, getString(R.string.add_ok) + idInt, Toast.LENGTH_LONG).show();
        }
        else return;

        Intent intentAdd = new Intent();
        /*intentAdd.putExtra("NameFrom", strFrom);
        intentAdd.putExtra("NameTo", strTo);
        intentAdd.putExtra("Time", "12");*/
        this.setResult(RESULT_OK, intentAdd);
    }

    public void addTrainDialog(View v)
    {
        DialogAddTrain dialogAddTrain = new DialogAddTrain(context);
        dialogAddTrain.show(getSupportFragmentManager(), "");
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new LoaderForSpinner(context);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapterSpinTrain.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
