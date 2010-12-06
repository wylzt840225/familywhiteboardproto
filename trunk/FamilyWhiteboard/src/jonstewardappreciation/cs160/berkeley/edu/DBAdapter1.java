package jonstewardappreciation.cs160.berkeley.edu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter1
{
	public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_TIME_CREATED = "time_created";
    public static final String KEY_TITLE = "title";
    public static final String KEY_CONTENT = "content";
    public static final String KEY_PRIORITY = "priority";
    public static final String KEY_PARENT = "parent";
    public static final String KEY_AUTHOR = "author";
    private static final String TAG = "DBAdapter1";
    
    private static final String DATABASE_NAME = "familywb";
    private static final String DATABASE_TABLE_USERS = "users";
    private static final String DATABASE_TABLE_POSTS = "myposts";
    private static final int DATABASE_VERSION = 4; 

    private static final String DATABASE_CREATE_USERS =
        "create table users ("
        + "username text primary key not null, password text not null);";
    private static final String DATABASE_CREATE_POSTS =
        "create table myposts ("
        + "id integer primary key autoincrement not null, parent int, title text, time_created int, content text, priority int, author text);";
    
    private final Context context; 
    
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter1(Context ctx) 
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
            db.execSQL(DATABASE_CREATE_USERS);
            db.execSQL(DATABASE_CREATE_POSTS);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, 
        int newVersion) 
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion 
                    + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS users");
            db.execSQL("DROP TABLE IF EXISTS myposts");
            
            onCreate(db);
        }
    }    
    
    //---opens the database---
    public DBAdapter1 open() throws SQLException 
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---    
    public void close() 
    {
        DBHelper.close();
    }
    
    //---insert a user into the database---
    public long insertUser(String username, String password) 
    {
    	Log.w(TAG, "insertUser " + DATABASE_NAME);
    	ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_USERNAME, username);
        initialValues.put(KEY_PASSWORD, password);
        return db.insert(DATABASE_TABLE_USERS, null, initialValues);
    }
    
    public long insertPost(String title, String content, int priority, String author, int parent)
    {
    	int time = (int) Math.round(System.currentTimeMillis()/ 1000.0);
    	Log.w(TAG, "insertPost " + DATABASE_NAME + " " + title + " " + content + " " + priority + " " + author);
    	ContentValues initialValues = new ContentValues();

    	initialValues.put(KEY_TITLE, title);
        initialValues.put(KEY_TIME_CREATED, time);
        initialValues.put(KEY_CONTENT, content);
        initialValues.put(KEY_PRIORITY, priority);
        initialValues.put(KEY_AUTHOR, author);
        initialValues.put(KEY_PARENT, parent);
        return db.insert(DATABASE_TABLE_POSTS, null, initialValues);
    }
    
  //---insert a post into the database---
    public long insertPost(String title, String content, int priority, String author) 
    {
    	int time = (int) Math.round(System.currentTimeMillis()/ 1000.0);
    	Log.w(TAG, "insertPost " + DATABASE_NAME + " " + title + " " + content + " " + priority + " " + author);
    	ContentValues initialValues = new ContentValues();

    	initialValues.put(KEY_TITLE, title);
        initialValues.put(KEY_TIME_CREATED, time);
        initialValues.put(KEY_CONTENT, content);
        initialValues.put(KEY_PRIORITY, priority);
        initialValues.put(KEY_AUTHOR, author);
        return db.insert(DATABASE_TABLE_POSTS, null, initialValues);
    }

    //---deletes a particular user---
    public boolean deleteUser(String username) 
    {
    	Log.w(TAG, "deleteUser " + DATABASE_NAME);
    	return db.delete(DATABASE_TABLE_USERS, KEY_USERNAME + 
        		"=" + username, null) > 0;
    }
    
    //---drop myposts---
    public void deleteAllPosts() {
    	Log.w(TAG, "deleting all posts");
    	db.execSQL("DELETE FROM "+DATABASE_TABLE_POSTS + " WHERE 1=1");
    }

    //---deletes a particular post---
    public boolean deletePost(String title) 
    {
    	Log.w(TAG, "deletePost " + DATABASE_NAME);
    	return db.delete(DATABASE_TABLE_POSTS, KEY_TITLE + 
        		"=" + title, null) > 0;
    }
    //---delete post by author---
    public boolean deletePost(String title, String author) 
    {
    	Log.w(TAG, "deletePostByUser " + DATABASE_NAME);
    	return db.delete(DATABASE_TABLE_POSTS, KEY_TITLE + 
        		"='" + title +"' AND " + KEY_AUTHOR + "='" + author+ "'", null) > 0;
    }

    //---retrieves all the users---
    public Cursor getAllUsers() 
    {
    	Log.w(TAG, "getAllUsers  " + DATABASE_NAME);
    	return db.query(DATABASE_TABLE_USERS, new String[] {
        		KEY_USERNAME,
        		KEY_PASSWORD}, 
                null, 
                null, 
                null, 
                null, 
                null);
    }

    //---retrieves all the topics---
    public Cursor getAllTopics() 
    {
    	Log.w(TAG, "getAllTopics " + DATABASE_NAME);
    	return db.query(DATABASE_TABLE_POSTS, null, KEY_PARENT + " is null", null, null, null, KEY_TIME_CREATED + " DESC");
    }
    
  //---retrieves all the posts---
    public Cursor getAllPosts() 
    {
    	Log.w(TAG, "getAllPosts " + DATABASE_NAME);
    	return db.query(DATABASE_TABLE_POSTS, null, null, null, null, null, KEY_TIME_CREATED + " DESC");
    }
    
    //---retrieves all the posts for a topic---//
    public Cursor getAllPosts(int topic) 
    {
    	Log.w(TAG, "getAllPosts " + DATABASE_NAME);
    	return db.query(DATABASE_TABLE_POSTS, null, KEY_PARENT + "='" + Integer.toString(topic) + "' OR id="+topic, null, null, null, KEY_TIME_CREATED + " DESC");
    }
    

    //---retrieves a particular user---
    public Cursor getUser(String username)  
    {
    	Log.w(TAG, "getUser " + DATABASE_NAME);
    	Cursor mCursor =
                db.query(true, DATABASE_TABLE_USERS, new String[] {KEY_USERNAME, KEY_PASSWORD}, 
                		KEY_USERNAME + "='" + username + "'", 
                		null,
                		null, 
                		null, 
                		null, 
                		null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---retrieves a user with a password---//
    //---retrieves a particular user---
    public Cursor userLogin(String username, String password)  
    {
    	Log.w(TAG, "userLogin " + DATABASE_NAME);
    	Cursor mCursor =
                db.query(true, DATABASE_TABLE_USERS, new String[] {KEY_USERNAME, KEY_PASSWORD}, 
                		KEY_USERNAME + "='" + username + "' AND " + KEY_PASSWORD + "='" + password + "'", 
                		null,
                		null, 
                		null, 
                		null, 
                		null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    
    //---retrieves a particular post---
    public Cursor getPost(String title) throws SQLException 
    {
    	Log.w(TAG, "getPost " + DATABASE_NAME);
    	Cursor mCursor =
                db.query(true, DATABASE_TABLE_POSTS, new String[] {
                		KEY_TITLE, 
                		KEY_CONTENT,
                		KEY_PRIORITY,
                		KEY_AUTHOR
                		}, 
                		KEY_TITLE + "=" + title, 
                		null,
                		null, 
                		null, 
                		null, 
                		null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a user---
    public boolean updateUser(String username, String password) 
    {
    	Log.w(TAG, "updateUser " + DATABASE_NAME);
    	ContentValues args = new ContentValues();
        args.put(KEY_USERNAME, username);
        args.put(KEY_PASSWORD, password);
        return db.update(DATABASE_TABLE_USERS, args, 
                         KEY_USERNAME + "=" + username, null) > 0;
    }
    
}