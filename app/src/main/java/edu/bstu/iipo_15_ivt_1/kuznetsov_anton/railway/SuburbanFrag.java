package edu.bstu.iipo_15_ivt_1.kuznetsov_anton.railway;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import edu.bstu.iipo_15_ivt_1.helppac.SqlHelper;

/**
 * Created by user on 01.10.2015.
 */
public class SuburbanFrag extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    TextView textTime;
    //SwipeListView swipeListView;
    ListView listView;
    ArrayList<ItemTrain> arrayList;
    TrainTimeAdapter trainTimeAdapter;
    Context context;
    File myF;
    String jsonTrains = "";
    SqlHelper sqlHelper;
    MyCursorAdapter cursorAdapter;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
        sqlHelper = new SqlHelper(context);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SQLiteDatabase sqLiteDatabase = sqlHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("numbertrain", 54);
            sqLiteDatabase.insert("students", null, cv);
        }
    };


    @Override
    public void onResume() {
        super.onResume();
        this.getLoaderManager().getLoader(3).forceLoad();
    }

    View.OnClickListener addTrain = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, AddTrain.class);
            startActivityForResult(intent, 1);
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_timing, container, false);
        Button buttonAdd = (Button) view.findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(addTrain);
        arrayList = new ArrayList<ItemTrain>();
        SQLiteDatabase database = sqlHelper.getWritableDatabase();
        listView = (ListView)view.findViewById(R.id.listViewSw);
        getLoaderManager().initLoader(3, null, this);

        cursorAdapter = new MyCursorAdapter(context, R.layout.card_train, null, 0);
        listView.setAdapter(cursorAdapter);
        return view;
    }

    public int convertDpToPixel(float dp) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return (int) px;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoaderMy cursorLoaderMy = new CursorLoaderMy(context, "0");
        return cursorLoaderMy;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        cursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}