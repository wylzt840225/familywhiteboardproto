package jonstewardappreciation.cs160.berkeley.edu;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
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


public class Hub extends Activity
{
	protected static ListView lv1;
	protected static ArrayList<Comment> comments= new ArrayList();
	private Context v;
	@Override
	public void onCreate(Bundle icicle)
	{
		v = this;
		comments.add(new Comment("John", "Hey", 1));
		comments.add(new Comment("Steven", "Test Thread", 2));
		comments.add(new Comment("Alex", "Emergency", 3));
		comments.add(new Comment("Courtney", "Hi", 2));
		comments.add(new Comment("Simi", "Sup", 1));
		super.onCreate(icicle);
		setContentView(R.layout.hub);
		lv1=(ListView)findViewById(R.id.ListView01);
		// By using setAdpater method in listview we an add string array in list.
		HubAdapter adapt = new HubAdapter(this,R.layout.list_item, comments);
		lv1.setAdapter(adapt);
		
	
		lv1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
	    		 Intent myIntent = new Intent(v, ViewThread.class);
	             startActivityForResult(myIntent, 0);
			}
		});
		
		Button closeButton = (Button)this.findViewById(R.id.createThread);
		closeButton.setOnClickListener(new OnClickListener() {
		    @Override
		    public void onClick(View vi) {
				 Intent myIntent = new Intent(v, AddComment.class);
		         startActivityForResult(myIntent, 0);
		    }
		  });
		
		Button settings = (Button)this.findViewById(R.id.Settings);
		settings.setOnClickListener(new OnClickListener() {
		    @Override
		    public void onClick(View vi) {
				 Intent myIntent = new Intent(v, Settings.class);
		         startActivityForResult(myIntent, 0);
		    }
		  });
		Button logout= (Button)this.findViewById(R.id.LogOut);
		logout.setOnClickListener(new OnClickListener() {
		    @Override
		    public void onClick(View vi) {
				finish();
		    }
		  });
		
	}
	public void onActivityResult(int requestCode, int resultCode, Intent data) {     
		  super.onActivityResult(requestCode, resultCode, data); 
		  switch(requestCode) { 
		    case (0) : { 
		      if (resultCode == Activity.RESULT_OK) { 
			         HubAdapter adapt = new HubAdapter(v,R.layout.list_item, comments);
					 lv1.setAdapter(adapt);
					 Toast.makeText(v, "cancelIcon", Toast.LENGTH_SHORT).show();
		      } 
		      break; 
		    } 
		  } 
		}
	private class HubAdapter extends ArrayAdapter<Comment> {

        private ArrayList<Comment> items;

        public HubAdapter(Context context, int textViewResourceId, ArrayList<Comment> items) {
                super(context, textViewResourceId, items);
                this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
                View v = convertView;
                if (v == null) {
                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.list_item, null);
                }
                Comment o = items.get(position);
                if (o != null) {
                        TextView tt = (TextView) v.findViewById(R.id.Hub_Name);
                        TextView bt = (TextView) v.findViewById(R.id.TextView02);
                        ImageView img = (ImageView) v.findViewById(R.id.Hub_Priority);
                        if (tt != null) {
                              tt.setText(""+o.getName());                            }
                        if(bt != null){
                              bt.setText(""+ o.getComment());
                        }
                        if(img != null){
                        	switch (o.getPriority())
                        	{
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
                }
                return v;
        }
}
}
