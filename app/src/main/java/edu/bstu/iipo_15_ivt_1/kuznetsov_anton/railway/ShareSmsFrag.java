package edu.bstu.iipo_15_ivt_1.kuznetsov_anton.railway;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import edu.bstu.iipo_15_ivt_1.helppac.SqlHelper;
import edu.bstu.iipo_15_ivt_1.kuznetsov_anton.railway.R;

/**
 * Created by user on 14.10.2015.
 */
public class ShareSmsFrag extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>
{
    ListView listSms;
    Context context;
    EditText editSearch;
    Button btnSearch;
    SqlHelper helper;
    private static final String TRAIN_TABLE = "train";
    private static final String TICKET_TABLE = "ticket";
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
        helper = new SqlHelper(context);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    View.OnClickListener onClickListenerSearch = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.share_ticket_frag, container, false);
        listSms = (ListView)view.findViewById(R.id.listViewSms);
        editSearch = (EditText) view.findViewById(R.id.editTextSearch);
        btnSearch = (Button) view.findViewById(R.id.buttonSearch);
        btnSearch.setOnClickListener(onClickListenerSearch);
        return view;
    }

    class SearchCursorLoader extends CursorLoader {
        public SearchCursorLoader(Context context) {
            super(context);
        }

        @Override
        public Cursor loadInBackground() {
            SQLiteDatabase database = helper.getWritableDatabase();
            String tables = TICKET_TABLE + " as TK inner join " + TRAIN_TABLE + " as TR on TR._id = TK.train_id";
            String tables1 = "ticket as TK inner join train as TR on TR._id = TK.train_id";
            Cursor c = database.query(tables1, null, null, null, null, null, null);
            return c;
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new SearchCursorLoader(context);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
