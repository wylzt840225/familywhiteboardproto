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
	@Override
	public void onCreate(Bundle icicle)
	{
		v = this;
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
                        if (tt != null) {
                              tt.setText(""+o.getName());                            }
                        if(bt != null){
                              bt.setText(""+ o.getComment());
                        }
                }
                return v;
        }
}
}
