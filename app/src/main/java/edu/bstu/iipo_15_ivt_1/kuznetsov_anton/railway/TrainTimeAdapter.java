package edu.bstu.iipo_15_ivt_1.kuznetsov_anton.railway;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 21.10.2015.
 */
public class TrainTimeAdapter extends BaseAdapter {
    ArrayList<ItemTrain> arList;
    Context cont;
    LayoutInflater inflater;

    public TrainTimeAdapter(ArrayList<ItemTrain> arList, Context cont) {
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

        View view = inflater.inflate(R.layout.card_train, parent, false);//inflater.inflate(R.layout.card_train, parent, false);
        final ItemTrain train = (ItemTrain) getItem(position);

//        view.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//
//                arList.remove(position);
//                notifyDataSetChanged();
//                return false;
//            }
//        });
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                ((TextView)v.findViewById(R.id.TextName)).setText("www");
//                    arList.remove(position);
//                    notifyDataSetChanged();
//            }
//        });

//        Button buttonDelete = (Button)view.findViewById(R.id.buttonDeleteTrain);
//        buttonDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                arList.remove(position);
//                notifyDataSetChanged();
//            }
//        });

        ((TextView)view.findViewById(R.id.TextNumber)).setText(train.number + "");
        ((TextView)view.findViewById(R.id.TextNameFrom)).setText(train.nameFrom);
        ((TextView)view.findViewById(R.id.TextNameTo)).setText(train.nameTo);
        ((TextView)view.findViewById(R.id.TextTimeStart)).setText(train.timeStart);
        ((TextView)view.findViewById(R.id.TextTimeFinish)).setText(train.timeFinish);
        ((TextView)view.findViewById(R.id.TextDateStart)).setText(train.dateStart);
        ((TextView)view.findViewById(R.id.TextDateFinish)).setText(train.dateFinish);
        ((TextView)view.findViewById(R.id.TextCarriage)).setText(train.carriage + "");
        ((TextView)view.findViewById(R.id.TextSit)).setText(train.sit + "");

        ((ImageView)view.findViewById(R.id.imageTrain)).setImageResource(R.drawable.ellip);

        return view;
    }
}
