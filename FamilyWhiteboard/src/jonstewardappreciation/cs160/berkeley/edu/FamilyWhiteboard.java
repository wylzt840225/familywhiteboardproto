package jonstewardappreciation.cs160.berkeley.edu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FamilyWhiteboard extends Activity {
    /** Called when the activity is first created. */
	
	public static DBAdapter1 db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        db = new DBAdapter1(this);
        
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
    		 Intent myIntent = new Intent(v.getContext(), SignIn.class);
             startActivityForResult(myIntent, 0);
		}
    };
}