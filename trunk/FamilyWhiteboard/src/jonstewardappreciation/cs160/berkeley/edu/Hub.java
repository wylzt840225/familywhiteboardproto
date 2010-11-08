package jonstewardappreciation.cs160.berkeley.edu;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View;


public class Hub extends Activity
{
	private ListView lv1;
	private String lv_arr[]={"Android","iPhone","BlackBerry","AndroidPeople"};
	@Override
	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		setContentView(R.layout.hub);
	lv1=(ListView)findViewById(R.id.ListView01);
	// By using setAdpater method in listview we an add string array in list.
	ArrayAdapter<String> adapt = new ArrayAdapter<String>(this,R.layout.list_item, R.id.TextView02);
	for (int i = 0; i < lv_arr.length; i++)
	{
		adapt.add(lv_arr[i]);
	}
	lv1.setAdapter(adapt);
	}
}
