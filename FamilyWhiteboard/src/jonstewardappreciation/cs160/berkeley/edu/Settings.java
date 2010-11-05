package jonstewardappreciation.cs160.berkeley.edu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Settings extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        
        Button profilePictureEdit = (Button) findViewById(R.id.profilePictureEdit);
        Button groupsEdit = (Button) findViewById(R.id.groupsEdit);
        
        profilePictureEdit.setOnClickListener(pictureListen);
        groupsEdit.setOnClickListener(groupsListen);
        
    }
    
    private OnClickListener pictureListen = new OnClickListener(){
    	public void onClick(View v)
		{
    		 //Intent myIntent = new Intent(v.getContext(), SignUp.class);
             //startActivityForResult(myIntent, 0);
		}
    };
    
    private OnClickListener groupsListen = new OnClickListener(){
    	public void onClick(View v)
		{
    		//
		}
    };
}