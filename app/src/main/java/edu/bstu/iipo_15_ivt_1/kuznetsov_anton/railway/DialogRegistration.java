package edu.bstu.iipo_15_ivt_1.kuznetsov_anton.railway;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import edu.bstu.iipo_15_ivt_1.kuznetsov_anton.railway.R;

/**
 * Created by user on 22.12.2015.
 */
public class DialogRegistration extends DialogFragment {

    EditText editName, editPas;
    final Uri uriData2 = Uri.parse("content://com.mycontentprovider.data/");
    static final String LOGIN = "login";
    static final String PASSWORD = "password";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_registration, null);
        editName = (EditText)v.findViewById(R.id.editTextNameRegDialog);
        editPas = (EditText)v.findViewById(R.id.editTextPassDialog);
        (v.findViewById(R.id.btnYes)).setOnClickListener(addMan);
        return v;
    }

    View.OnClickListener addMan = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String nameStr = editName.getText().toString();
            String pasStr = editPas.getText().toString();
            ContentValues cv = new ContentValues();
            cv.put(LOGIN, nameStr);
            cv.put(PASSWORD, pasStr);
            Uri uui = getActivity().getContentResolver().insert(uriData2 , cv);
            String str = uui.toString();
            dismiss();
        }
    };
}
