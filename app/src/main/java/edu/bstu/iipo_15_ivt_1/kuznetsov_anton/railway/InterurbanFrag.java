package edu.bstu.iipo_15_ivt_1.kuznetsov_anton.railway;

import android.app.Activity;
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

import java.util.ArrayList;

/**
 * Created by user on 01.10.2015.
 */
public class InterurbanFrag extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
    Context context;
    TextView textTime;
    TextView textMos;
    ArrayList<ItemTrain> arrayList;
    //SwipeListView swipeListView;
    //SwipeListView swipeListView;
    ListView listView;
    MyCursorAdapter myCursorAdapter;
    SQLiteDatabase database;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
        //context = getActivity();

        getLoaderManager().initLoader(0, null, this);
        //getLoaderManager().initLoader(0, null, this);
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
        View v = inflater.inflate(R.layout.frag_timing, container, false);
//
        listView = (ListView)v.findViewById(R.id.listViewSw);

        myCursorAdapter = new MyCursorAdapter(context, R.layout.card_train, null, 0);

        listView.setAdapter(myCursorAdapter);
        Button btnAdd = (Button)v.findViewById(R.id.buttonAdd);
        btnAdd.setOnClickListener(addTrain);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.getLoaderManager().getLoader(0).forceLoad();
    }

    public int convertDpToPixel(float dp) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return (int) px;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoaderMy cursorLoaderMy = new CursorLoaderMy(context, "1");
        return cursorLoaderMy;
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        myCursorAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
