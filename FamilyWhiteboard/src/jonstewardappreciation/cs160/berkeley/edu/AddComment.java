package jonstewardappreciation.cs160.berkeley.edu;

import android.app.Activity;
//import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class AddComment extends Activity {
	
	Button submit;
	RadioGroup rg_priority;
	TextView tv_addgps;
	ImageView iv_addgps;
	TextView tv_addpic;
	ImageView iv_addpic;
	TextView tv_gpsloc;
	EditText msgbox;
	boolean first = true;
	int topic;
	
	private void refreshThreadList(DBAdapter1 db) {
		Hub.comments.clear();
		Cursor c = db.getAllPosts();
		startManagingCursor(c); 
		while (c.moveToNext()) {  
			String title = c.getString(c.getColumnIndex("title"));
			String content = c.getString(c.getColumnIndex("content"));
			int priority = c.getInt(c.getColumnIndex("priority"));
			int id = c.getInt(c.getColumnIndex("id")); 
			String author = c.getString(c.getColumnIndex("author"));
			Log.w("Post", title + " " + content + " " + priority + " " + author);
			Hub.comments.add(new Comment(id, author, title, priority));
		}	
	//Close the database
		db.close();
	}
	
	private void refreshThreadList(DBAdapter1 db, int parent) {
		ViewThread.comments.clear();
		Cursor c = db.getAllPosts(parent);
		startManagingCursor(c); 
		while (c.moveToNext()) {  
			String title = c.getString(c.getColumnIndex("title"));
			String content = c.getString(c.getColumnIndex("content"));
			int priority = c.getInt(c.getColumnIndex("priority"));
			int id = c.getInt(c.getColumnIndex("id")); 
			String author = c.getString(c.getColumnIndex("author"));
			Log.w("Post", title + " " + content + " " + priority + " " + author);
			ViewThread.comments.add(new Comment(id, author, title, priority));
		}	
	//Close the database
		db.close();
	}
	
	public void addPost(String title, String content, int pri, String author) {
		DBAdapter1 db1 = FamilyWhiteboard.db.open();
		if (this.topic == 0)
		{
			db1.insertPost(title, content, pri, author);
			refreshThreadList(db1);
		}
		else
		{
			db1.insertPost(title, content, pri, author, this.topic);
			refreshThreadList(db1, this.topic);
		}
		
	}
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addingcomment);
        
		SharedPreferences settings = getSharedPreferences(Settings.PREFS_NAME, 0);
        String curUserName = settings.getString("username", "");
        
        topic = this.getIntent().getIntExtra("topic", 0);
        
        submit = (Button) findViewById(R.id.submit);
        rg_priority = (RadioGroup) findViewById(R.id.rg_pri);
        tv_addgps = (TextView) findViewById(R.id.tv_addgps);
        iv_addgps = (ImageView) findViewById(R.id.iv_addgps);
        tv_addpic = (TextView) findViewById(R.id.tv_addpic);
        iv_addpic = (ImageView) findViewById(R.id.iv_addpic);
        tv_gpsloc = (TextView) findViewById(R.id.tv_gpsloc);
        msgbox	  = (EditText) findViewById(R.id.msgbox);
        msgbox.clearFocus();
        
        if (this.topic != 0)
        {
        	findViewById(R.id.pri).setVisibility(View.GONE);
        	findViewById(R.id.rg_pri).setVisibility(View.GONE);
        }
        
        TextView username = (TextView) findViewById(R.id.username);
        username.setText(curUserName);
        msgbox.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(first) {
					msgbox.setText("");
				}
				first = false;
			}
		});
        
        submit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
		
				int commentPriority = 1;
				String commentText;
				String addingName;
				RadioButton priority;
				priority = (RadioButton) findViewById(R.id.lowpri);
				if (priority.isChecked()){ commentPriority = 1;}
				priority = (RadioButton) findViewById(R.id.medpri);
				if (priority.isChecked()){ commentPriority = 2;}
				priority = (RadioButton) findViewById(R.id.highpri);
				if (priority.isChecked()){ commentPriority = 3;}
				commentText = msgbox.getText().toString();

				SharedPreferences settings = getSharedPreferences(Settings.PREFS_NAME, 0);
		        String curUserName = settings.getString("username", "");
		        
				addingName = curUserName;
				if (addingName.length()<=2){
					addingName = "Previous User";
				}
				addPost(commentText, commentText, commentPriority, addingName);
				//Hub.comments.add(0, new Comment(addingName,commentText,commentPriority));
				setResult(RESULT_OK);
                finish();
			}
		});
        
        tv_addgps.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// Intent i = new Intent(..., ...);
				 //	i.putExtra(..., ...);
		          //  startActivity(i);
				if(tv_gpsloc.getText().length()  <= 1) {
				SystemClock.sleep(1000);
				tv_gpsloc.setText("Location: 37.875,-122.258");
				}
			}
		});
        iv_addgps.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// Intent i = new Intent(..., ...);
				 //	i.putExtra(..., ...);
		          //  startActivity(i);
				if(tv_gpsloc.getText().length()  <= 1) {
					SystemClock.sleep(1000);
					tv_gpsloc.setText("Location: 37.875,-122.258");
					}
			}
		});
        
        tv_addpic.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// Intent i = new Intent(..., ...);
				 //	i.putExtra(..., ...);
		          //  startActivity(i);
			}
		});
        iv_addpic.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// Intent i = new Intent(..., ...);
				 //	i.putExtra(..., ...);
		          //  startActivity(i);
			}
		});
    }
}