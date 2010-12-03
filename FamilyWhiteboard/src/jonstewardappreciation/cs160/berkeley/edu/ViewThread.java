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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;


public class ViewThread extends Activity
{
	private ListView lv1;
	private ArrayList<Comment> comments= new ArrayList();
	private Context v;
	private boolean firstTime = true;
	
	private void maybeAddFakePosts() {
		if(firstTime) {
			Comment c = new Comment("John", "First topic", 1);
			c.linkComment(new Comment("Steven", "Here is a reply", 2));
			c.linkComment(new Comment("Alex", "Another Reply", 3));
			c.linkComment(new Comment("Courtney", "And another..", 2));
			c.linkComment(new Comment("Simi", "Last Reply", 1));
			
			comments.add(c);
			while (c.next != null)
			{
				comments.add(c.next);
				c = c.next;
			}
		}
		firstTime = false;
	}
	@Override
	public void onCreate(Bundle icicle)
	{
		v = this;
		
		maybeAddFakePosts();
		
		super.onCreate(icicle);
		setContentView(R.layout.viewthread);
		lv1=(ListView)findViewById(R.id.ListView01);
		// By using setAdpater method in listview we an add string array in list.
		HubAdapter adapt = new HubAdapter(this,R.layout.list_item2, comments);
		lv1.setAdapter(adapt);

		Button closeButton = (Button)this.findViewById(R.id.addComment2);
		closeButton.setOnClickListener(new OnClickListener() {
		    @Override
		    public void onClick(View vi) {
				 Intent myIntent = new Intent(v, AddComment.class);
		         startActivityForResult(myIntent, 0);
		    }
		  });

		/*Button settings = (Button)this.findViewById(R.id.Settings);
		settings.setOnClickListener(new OnClickListener() {
		    @Override
		    public void onClick(View vi) {
				 Intent myIntent = new Intent(v, Settings.class);
		         startActivityForResult(myIntent, 0);
		    }
		  });*/

		Button back = (Button)this.findViewById(R.id.BackButton);
		back.setOnClickListener(new OnClickListener() {
		    @Override
		    public void onClick(View vi) {
				 finish();
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
                    v = vi.inflate(R.layout.list_item2, null);
                }
                Comment o = items.get(position);
                if (o != null) {
                        TextView tt = (TextView) v.findViewById(R.id.Hub_Name);
                        TextView bt = (TextView) v.findViewById(R.id.TextView02);
                        ImageView img = (ImageView) v.findViewById(R.id.Hub_Priority);
                        ImageView userimg = (ImageView) v.findViewById(R.id.userimg2);
                        if (tt != null) {
                              tt.setText(""+o.getName());                            }
                        if(bt != null){
                              bt.setText(""+ o.getComment());
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
