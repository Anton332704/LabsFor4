package edu.bstu.iipo_15_ivt_1.kuznetsov_anton.railway;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 23.10.2015.
 */
public class SwipeTrainAdapter extends BaseAdapter {
    ArrayList<ItemTrain> arList;
    Context cont;
    LayoutInflater inflater;

    public SwipeTrainAdapter(ArrayList<ItemTrain> arList, Context cont) {
        this.arList = arList;
        this.cont = cont;
        inflater = (LayoutInflater)cont.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return arList.size();
    }

    @Override
    public Object getItem(int position) {
        return arList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.card_train, parent, false);
        final ItemTrain train = (ItemTrain) getItem(position);
        ((TextView)view.findViewById(R.id.TextNameFrom)).setText(train.nameFrom);
        return view;
    }
}
