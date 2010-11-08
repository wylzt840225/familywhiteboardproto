package jonstewardappreciation.cs160.berkeley.edu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        submit = (Button) findViewById(R.id.submit);
        rg_priority = (RadioGroup) findViewById(R.id.rg_pri);
        tv_addgps = (TextView) findViewById(R.id.tv_addgps);
        iv_addgps = (ImageView) findViewById(R.id.iv_addgps);
        tv_addpic = (TextView) findViewById(R.id.tv_addpic);
        iv_addpic = (ImageView) findViewById(R.id.iv_addpic);
        tv_gpsloc = (TextView) findViewById(R.id.tv_gpsloc);
        msgbox	  = (EditText) findViewById(R.id.msgbox);
        msgbox.clearFocus();
        
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
				tv_gpsloc.setText("Location: 37.875598,-122.258812");
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
					tv_gpsloc.setText("Location: 37.875598,-122.258812");
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