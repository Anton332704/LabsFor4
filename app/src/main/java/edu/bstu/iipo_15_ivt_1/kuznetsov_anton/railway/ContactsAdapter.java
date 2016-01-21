package edu.bstu.iipo_15_ivt_1.kuznetsov_anton.railway;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.bstu.iipo_15_ivt_1.helppac.Item_Contact;
import edu.bstu.iipo_15_ivt_1.kuznetsov_anton.railway.R;

/**
 * Created by user on 29.12.2015.
 */
public class ContactsAdapter extends BaseAdapter {

    ArrayList<Item_Contact> itemContacts;
    LayoutInflater inflater;
    Context context;

    public ContactsAdapter(ArrayList<Item_Contact> itemContacts, Context context) {
        this.itemContacts = itemContacts;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return itemContacts.size();
    }

    @Override
    public Object getItem(int position) {
        return itemContacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        public TextView textViewName;
        public TextView textViewNumb;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        View rowView = convertView;
        if (rowView == null) {
            rowView = inflater.inflate(R.layout.item_contact, null, true);
            holder = new ViewHolder();
            holder.textViewName = (TextView) rowView.findViewById(R.id.textViewContName);
            holder.textViewNumb = (TextView) rowView.findViewById(R.id.textViewContNumber);

            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        holder.textViewName.setText(itemContacts.get(position).getName());
        holder.textViewNumb.setText(itemContacts.get(position).getNumber());

        return rowView;
    }
}