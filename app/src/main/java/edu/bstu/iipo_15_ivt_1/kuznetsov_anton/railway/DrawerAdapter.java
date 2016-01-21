package edu.bstu.iipo_15_ivt_1.kuznetsov_anton.railway;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 12.10.2015.
 */
public class DrawerAdapter extends BaseAdapter {
    Context base;
    ArrayList<ItemMenu> itemMenus;
    LayoutInflater inflater;

    public DrawerAdapter(Context context, ArrayList<ItemMenu> menus) {
        base = context;
        itemMenus = menus;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return itemMenus.size();
    }

    @Override
    public Object getItem(int position) {
        return itemMenus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if(getItemId(position) == 0)
        {
            if(v == null)
            {
                v = inflater.inflate(R.layout.header, parent, false);
            }
        }
        else if(getItemId(position) != 0){

            v = inflater.inflate(R.layout.item_left, parent, false);
            final ItemMenu itemMenu = (ItemMenu) getItem(position);
            ((TextView) v.findViewById(R.id.textViewLeft)).setText(itemMenu.getName());
            ((TextView)v.findViewById(R.id.textViewLeft)).setText(itemMenu.getName());
        }
       // ((ImageView)v.findViewById(R.id.imageViewLeft)).setImageResource(itemMenu.getIcon());
        return v;
    }
}
