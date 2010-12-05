package jonstewardappreciation.cs160.berkeley.edu;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

public class Hub extends Activity {
	protected static ListView lv1;
	protected static ArrayList<Comment> comments = new ArrayList();
	private Context v;

	private void loadThreads() {
		
	/** 
	 * 
	 * Database 
	 * 
	 * **/
	//Open the database 
		DBAdapter1 db1 = FamilyWhiteboard.db.open();
//	//Insert a post (String title, String content, int priority, String author)
//		db1.insertPost("title1", "content1", 1, "simran");
//		db1.insertPost("title2", "content2", 1, "someoneElse");
		//db1.deleteAllPosts();
		
		// This is a really stupid way of doing this, but whatever.
		db1.deletePost("Hey", "John");
		db1.deletePost("Test Thread", "Steven");
		db1.deletePost("Emergency", "Alex");
		db1.deletePost("Hi", "Courtney");
		db1.deletePost("Sup", "Simi");
		 
		db1.insertPost("Hey", "content", 1, "John");
		db1.insertPost("Test Thread", "content", 2, "Steven");
		db1.insertPost("Emergency", "content", 3, "Alex");
		db1.insertPost("Hi", "content", 2, "Courtney");
		db1.insertPost("Sup", "content", 1, "Simi");
	//Get all posts
		Log.w("Clear", "Clearing comments");
		comments.clear();
		Cursor c = db1.getAllPosts();
		startManagingCursor(c); 
		while (c.moveToNext()) {  
			String title = c.getString(c.getColumnIndex("title"));
			String content = c.getString(c.getColumnIndex("content"));
			int priority = c.getInt(c.getColumnIndex("priority"));
			String author = c.getString(c.getColumnIndex("author"));
			Log.w("Post", title + " " + content + " " + priority + " " + author);
			comments.add(new Comment(author, title, priority));
		}	
	//Close the database
		db1.close();
		
	}
	
	
	@Override
	public void onCreate(Bundle icicle) {
		v = this;

		loadThreads();
		super.onCreate(icicle);
		setContentView(R.layout.hub);
		lv1 = (ListView) findViewById(R.id.ListView01);
		// By using setAdpater method in listview we an add string array in
		// list.
		HubAdapter adapt = new HubAdapter(this, R.layout.list_item, comments);
		lv1.setAdapter(adapt);

		lv1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent myIntent = new Intent(v, ViewThread.class);
				startActivityForResult(myIntent, 0);
			}
		});

		Button down = (Button) findViewById(R.id.ScrollDown);
		down.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// scroll up
				
				int newY = lv1.getScrollY() + 50;
				newY = newY <= lv1.getBottom() ? newY : lv1.getBottom();
				lv1.scrollTo(0, newY);
			}
		});

		Button up = (Button) findViewById(R.id.ScrollUp);
		up.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// scroll down
				int newY = lv1.getScrollY() - 50;
				newY = newY >= 0 ? newY : 0;
				lv1.scrollTo(0, newY);
			}
		});

		Button closeButton = (Button) this.findViewById(R.id.createThread);
		closeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View vi) {
				Intent myIntent = new Intent(v, AddComment.class);
				startActivityForResult(myIntent, 0);
			}
		});

		Button settings = (Button) this.findViewById(R.id.Settings);
		settings.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View vi) {
				Intent myIntent = new Intent(v, Settings.class);
				startActivityForResult(myIntent, 0);
			}
		});
		Button logout = (Button) this.findViewById(R.id.LogOut);
		logout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View vi) {
				finish();
			}
		});

	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case (0): {
			if (resultCode == Activity.RESULT_OK) {
				HubAdapter adapt = new HubAdapter(v, R.layout.list_item,
						comments);
				lv1.setAdapter(adapt);
				Toast.makeText(v, "cancelIcon", Toast.LENGTH_SHORT).show();
			}
			break;
		}
		}
	}

	private class HubAdapter extends ArrayAdapter<Comment> {

		private ArrayList<Comment> items;

		public HubAdapter(Context context, int textViewResourceId,
				ArrayList<Comment> items) {
			super(context, textViewResourceId, items);
			this.items = items;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.list_item, null);
			}
			Comment o = items.get(position);
			if (o != null) {
				TextView tt = (TextView) v.findViewById(R.id.Hub_Name);
				TextView bt = (TextView) v.findViewById(R.id.TextView02);
				ImageView userimg = (ImageView) v.findViewById(R.id.userimg);
				ImageView img = (ImageView) v.findViewById(R.id.Hub_Priority);
				if (tt != null) {
					tt.setText("" + o.getName());
				}
				if (bt != null) {
					bt.setText("" + o.getComment());
				}
				if (img != null) {
					switch (o.getPriority()) {
					default:
						img.setImageResource(R.drawable.priority_1);
						break;
					case 2:
						img.setImageResource(R.drawable.priority_2);
						break;
					case 3:
						img.setImageResource(R.drawable.priority_3);
						break;
					}
				}
				if (userimg != null) {
					String name = o.getName();
					
					if(name.equals("Alex")) {
						userimg.setImageResource(R.drawable.dogone);
					} else if(name.equals("Simi")) {
						userimg.setImageResource(R.drawable.userimg);
					} else if(name.equals("John")) {
						userimg.setImageResource(R.drawable.giraffe);
					} else if(name.equals("Steven")) {
						userimg.setImageResource(R.drawable.bear);
					} else if(name.equals("Courtney")) {
						userimg.setImageResource(R.drawable.horse);
					}
				}
			}
			return v;
		}
	}
}
