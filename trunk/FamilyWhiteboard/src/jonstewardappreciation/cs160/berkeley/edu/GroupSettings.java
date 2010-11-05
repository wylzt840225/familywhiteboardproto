package jonstewardappreciation.cs160.berkeley.edu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GroupSettings extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        
        Button invite = (Button) findViewById(R.id.invite);
        Button save = (Button) findViewById(R.id.saveIcon);
        Button cancel = (Button) findViewById(R.id.cancelIcon);
        
        invite.setOnClickListener(inviteListen);
        save.setOnClickListener(saveListen);
        cancel.setOnClickListener(cancelListen);
    }
    
    private OnClickListener inviteListen = new OnClickListener(){
    	public void onClick(View v)
		{
    		 //Intent myIntent = new Intent(v.getContext(), SignUp.class);
             //startActivityForResult(myIntent, 0);
		}
    };
    
    private OnClickListener saveListen = new OnClickListener(){
    	public void onClick(View v)
		{
    		//
		}
    };
    
    private OnClickListener cancelListen = new OnClickListener(){
    	public void onClick(View v)
		{
    		//
		}
    };
    

}