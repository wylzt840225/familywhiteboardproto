package jonstewardappreciation.cs160.berkeley.edu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FamilyWhiteboard extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        Button login = (Button) findViewById(R.id.loginButton);
        Button signup = (Button) findViewById(R.id.newUserButton);
        
        login.setOnClickListener(loginListen);
        signup.setOnClickListener(newUserListen);
        
    }
    
    private OnClickListener newUserListen = new OnClickListener(){
    	public void onClick(View v)
		{
    		 Intent myIntent = new Intent(v.getContext(), SignUp.class);
             startActivityForResult(myIntent, 0);
		}
    };
    
    private OnClickListener loginListen = new OnClickListener(){
    	public void onClick(View v)
		{	
    		 Intent myIntent = new Intent(v.getContext(), Hub.class);
             startActivityForResult(myIntent, 0);
		}
    };
}