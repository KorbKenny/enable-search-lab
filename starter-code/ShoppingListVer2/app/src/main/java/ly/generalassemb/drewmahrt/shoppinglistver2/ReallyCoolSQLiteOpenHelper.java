package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.content.ClipData;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KorbBookProReturns on 10/25/16.
 */

public class ReallyCoolSQLiteOpenHelper extends SQLiteOpenHelper{
    private static final String TAG = ReallyCoolSQLiteOpenHelper.class.getCanonicalName();
    private List<ItemObject> mObjectList;

    private static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "SHOPPING_DB";
    public static final String LIST_TABLE_NAME = "SHOPPING_LIST";

//    public static final String COL_ID = "ID";
    public static final String COL_ITEM_NAME = "ITEM_NAME";
    public static final String COL_DESCRIPTION = "DESCRIPTION";
    public static final String COL_PRICE = "PRICE";
    public static final String COL_TYPE = "TYPE";

    public static final String[] SHOPPING_COLUMNS = {COL_ITEM_NAME,COL_DESCRIPTION,COL_PRICE,COL_TYPE};

    private static final String CREATE_SHOPPING_LIST_TABLE =
            "CREATE TABLE " + LIST_TABLE_NAME +
                    "(" +
//                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    COL_ITEM_NAME + " TEXT, " +
                    COL_DESCRIPTION + " TEXT, " +
                    COL_PRICE + " TEXT, " +
                    COL_TYPE + " TEXT)";

    private static ReallyCoolSQLiteOpenHelper mInstance;

    public static ReallyCoolSQLiteOpenHelper getInstance(Context context){
        if(mInstance == null){
            mInstance = new ReallyCoolSQLiteOpenHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    private ReallyCoolSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SHOPPING_LIST_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + LIST_TABLE_NAME);
        this.onCreate(db);
    }

    public List<ItemObject> getAllAsList(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(LIST_TABLE_NAME,
                new String[]{COL_ITEM_NAME,COL_DESCRIPTION,COL_PRICE,COL_TYPE},
                null,null,null,null,null);

        List<ItemObject> itemList = new ArrayList<>();

        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                String name = cursor.getString(cursor.getColumnIndex(COL_ITEM_NAME));
                String description = cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION));
                String price = cursor.getString(cursor.getColumnIndex(COL_PRICE));
                String type = cursor.getString(cursor.getColumnIndex(COL_TYPE));

                ItemObject item = new ItemObject(name,description,price,type);
                itemList.add(item);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return itemList;
    }

    public List<ItemObject> searchForItems(String query){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(LIST_TABLE_NAME,
                null,
                COL_ITEM_NAME + " LIKE ?",
                new String[]{"%"+query+"%"},
                null,
                null,
                COL_ITEM_NAME,
                null);

        List<ItemObject> items = new ArrayList<>();

        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                ItemObject o = new ItemObject(
                        cursor.getString(cursor.getColumnIndex(COL_ITEM_NAME)),
                        cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(COL_PRICE)),
                        cursor.getString(cursor.getColumnIndex(COL_TYPE)));
                items.add(o);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return items;


    }




}
