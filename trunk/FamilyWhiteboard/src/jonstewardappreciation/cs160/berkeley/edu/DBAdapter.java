package jonstewardappreciation.cs160.berkeley.edu;
import java.util.Random;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter 
{
	int id = 0;
	public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    private static final String TAG = "DBAdapter";
    
    private static final String DATABASE_NAME = "users";
    private static final String DATABASE_TABLE = "tblUsersAndPass";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
        "create table tblUsersAndPass (username integer primary key autoincrement, "
        + "Password text not null );";

    private final Context context;  
    
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx) 
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
    
	private static class DatabaseHelper extends SQLiteOpenHelper 
    {
        DatabaseHelper(Context context) 
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) 
        {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, 
                              int newVersion) 
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion 
                  + " to "
                  + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS tblUsersAndPass");
            onCreate(db);
        }
    }    
    
    //---opens the database---
    public DBAdapter open() throws SQLException 
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---    
    public void close() 
    {
        DBHelper.close();
    }
    
    //---insert a title into the database---
    public long insertName(String name) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_USERNAME, name);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    public int getAllEntries() 
    {
        Cursor cursor = db.rawQuery(
                    "SELECT COUNT(Quote) FROM tblRandomQuotes", null);
                if(cursor.moveToFirst()) {
                    return cursor.getInt(0);
                }
                return cursor.getInt(0);

    }
    
    public String getRandomEntry() 
    {
    	
    	id = getAllEntries();
    	Random random = new Random();
    	int rand = random.nextInt(getAllEntries());
    	if(rand == 0)
    		++rand;
        Cursor cursor = db.rawQuery(
                    "SELECT Quote FROM tblRandomQuotes WHERE _id = " + rand, null);
                if(cursor.moveToFirst()) {
                    return cursor.getString(0);
                }
                return cursor.getString(0);

    }
	    
}
