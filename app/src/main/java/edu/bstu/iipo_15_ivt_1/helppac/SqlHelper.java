package edu.bstu.iipo_15_ivt_1.helppac;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.widget.Toast;

import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.spec.EncodedKeySpec;

/**
 * Created by user on 16.11.2015.
 */
public class SqlHelper extends SQLiteOpenHelper {
    String jsonTrains = "";
    File myF;

    private static final String TRAIN_TABLE = "train";

    private static final String TRAIN_ID = "_id";
    private static final String TRAIN_NUMBER= "numbertrain";
    private static final String TRAIN_FROM = "fromtown";
    private static final String TRAIN_TO = "totown";
    private static final String TRAIN_TYPE_ID = "type_train_id";
    private static final String CREATE_TABLE_TRAIN = "CREATE TABLE " + TRAIN_TABLE +"(" + TRAIN_ID + " integer primary key, " +
            TRAIN_NUMBER + " integer, " + TRAIN_FROM + " text, " + TRAIN_TO  + " text);";

    private static final String TICKET_TABLE = "ticket";

    private static final String TICKET_ID = "_id";
    private static final String TICKET_TIME_START= "timestart";
    private static final String TICKET_TIME_FINISH = "timefinish";
    private static final String TICKET_TRAIN_ID = "train_id";
    private static final String TICKET_CARRIAGE = "number_carriage";
    private static final String TICKET_SEAT = "number_seat";
    private static final String TICKET_DATE_START = "datestart";
    private static final String TICKET_DATE_FINISH = "datefinish";

    private static final String CREATE_TABLE_TICKET = "CREATE TABLE " + TICKET_TABLE + "(" + TICKET_ID +
            " integer primary key autoincrement, " + TICKET_TIME_START +  " text, " +
            TICKET_TIME_FINISH + " text, " +
            TICKET_TRAIN_ID + " integer, " + TICKET_CARRIAGE + " integer, " +
            TICKET_SEAT + " integer, " + TICKET_DATE_START + " text, " +
            TICKET_DATE_FINISH + " text, "  + TRAIN_TYPE_ID + " text);";



    private static final String TYPE_TRAIN_TABLE = "typeTrain";

    private static final String TYPE_ID = "_id";
    private static final String TYPE_NAME = "type_train";

    private static final String CREATE_TABLE_TYPE = "CREATE TABLE " + TYPE_TRAIN_TABLE +
            "(" + TYPE_ID + " integer primary key autoincrement, " +
            TYPE_NAME + " text);";

    public SqlHelper(Context context) {
        super(context, "my_test_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        int j = 0;

        myF = new File(Environment.getExternalStorageDirectory()+"/trains_info.txt");
        if(myF.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(myF);
                InputStreamReader streamReader = new InputStreamReader(fileInputStream, "Cp1251");
                BufferedReader bufferedReader = new BufferedReader(streamReader);
                String strLine = "";
                while ((strLine = bufferedReader.readLine()) != null) {
                    jsonTrains += strLine;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            String s = new String(jsonTrains.getBytes(), "Cp1251");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        db.execSQL(CREATE_TABLE_TRAIN);

        db.execSQL(CREATE_TABLE_TYPE);

        db.execSQL(CREATE_TABLE_TICKET);

        db.execSQL("CREATE TABLE mytab(_id integer primary key autoincrement,timestart text);");

        try {
            JSONObject jsonObject = new JSONObject(jsonTrains);
            JSONObject trainsJson = (JSONObject) jsonObject.get("trains");
            JSONObject ticketsJson = (JSONObject) jsonObject.get("tickets");
            JSONObject typeJson = (JSONObject) jsonObject.get("type_train");
            int i = 0;
            int supId = 0;
            JSONObject trainsJsonFirs;
            while(i < 2)
            {
                trainsJsonFirs = (JSONObject)trainsJson.get(i+"");
                String numberStr = trainsJsonFirs.getString("numbertrain");
                int numberInt = Integer.parseInt(numberStr);
                String fromStr = trainsJsonFirs.getString("from");
                String toStr = trainsJsonFirs.getString("toend");
                int typeTrainId = trainsJsonFirs.getInt("type_train");

                supId = numberInt;
                ContentValues cv = new ContentValues();
                cv.put(TRAIN_ID, numberInt);
                cv.put(TRAIN_NUMBER, numberInt);
                cv.put(TRAIN_FROM, fromStr);
                cv.put(TRAIN_TO, toStr);
                //cv.put(TRAIN_TYPE_ID, "0");

                db.insert(TRAIN_TABLE, null, cv);
                cv.clear();
                i++;
            }
            i = 0;
            JSONObject ticketsJsonObj;

            ticketsJsonObj = (JSONObject)ticketsJson.get(i+"");
            String timeStartStr = ticketsJsonObj.getString("timestart");
            String timeFinishStr = ticketsJsonObj.getString("timefinish");
            int trainId = ticketsJsonObj.getInt("train_id");
            int numberCarriage = ticketsJsonObj.getInt("number_carriage");
            int numberSits = ticketsJsonObj.getInt("number_sits");

            ContentValues cv = new ContentValues();
            cv.put(TICKET_TIME_START, timeStartStr);
            cv.put(TICKET_TIME_FINISH, timeFinishStr);
            cv.put(TICKET_DATE_START, "15.12.2015");
            cv.put(TICKET_DATE_FINISH, "16.12.2015");
            cv.put(TICKET_TRAIN_ID, supId);
            cv.put(TICKET_CARRIAGE, numberCarriage);
            cv.put(TICKET_SEAT, numberSits);
            cv.put(TRAIN_TYPE_ID, "0");

            long rt = db.insert(TICKET_TABLE, null, cv);

            cv.clear();
            i++;
            i = 0;
            JSONObject typeJsonObj;
            while(i < 3)
            {
                String typeTrainStr = typeJson.getString(i + "");
                cv.put(TYPE_NAME, typeTrainStr);
                db.insert(TYPE_TRAIN_TABLE, null, cv);
                i++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

// номер поезда, следование от , до , дата время отправления, дата время прибытия