package jonstewardappreciation.cs160.berkeley.edu;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignUp extends Activity {
	EditText username;
	EditText password;
	static String curUserName = "";
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        Button goToCustomize = (Button) findViewById(R.id.customizeButton);
        //Button goToHub = (Button) findViewById(R.id.mainHubButton);
        
        goToCustomize.setOnClickListener(customizeListen);
        //goToHub.setOnClickListener(goToHubListen);
        
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
	}
	
	
    private OnClickListener customizeListen = new OnClickListener(){
    	Toast errorToast;
		private DBAdapter1 db1;
		private Cursor c;
    	public void onClick(View v)
		{
    		 
    		 String usename = username.getText().toString();
    		 String pswd = password.getText().toString();
    		 if (usename.length() >= 1 &&  pswd.length() >= 1){
    			 //Store the username and pw in Preferences for the next screen
    			 SharedPreferences settings = getSharedPreferences(Settings.PREFS_NAME, 0);
	    		 SharedPreferences.Editor editor = settings.edit();
	             editor.putString("username", usename);
	             editor.putString("password", pswd);
	             editor.commit();
	    		 curUserName = usename;
	    		 Intent myIntent = new Intent(v.getContext(), Settings.class);
	    		 myIntent.putExtra("username", usename);
	    		 myIntent.putExtra("password", pswd);
	    		 
	    		 //Store new user information in the database
	    		 db1 = FamilyWhiteboard.db.open();
	    		 c = db1.getUser(usename);
	    		 if (c.moveToFirst())
	    		 {
	    			 Log.w("Signing Up", "Duplicate User");
	    			 Toast.makeText(getBaseContext(), "Someone already has this name.", Toast.LENGTH_LONG).show();
	    		 }
	    		 else {
	    			 db1.insertUser(usename, pswd);
	    			 Log.w("New user created", usename + " " + pswd);
		    		 //Start activity
		             startActivityForResult(myIntent, 0);
		             finish();
	    		 }
	    		 Log.w("Signing Up", "Signing Up");
	    		 db1.close();
    		 }else{
    			 if (usename.length() < 1){
    			 errorToast = Toast.makeText(getBaseContext(), "Please enter a username of at least 6 characters!", Toast.LENGTH_LONG);
        		 errorToast.show();}
    			 else{
    				 errorToast = Toast.makeText(getBaseContext(), "Please enter a password of at least 6 characters!", Toast.LENGTH_LONG);
            		 errorToast.show();
    			 }
    		 }
		}
    };
    
    /*private OnClickListener goToHubListen = new OnClickListener(){
    	Toast errorToast;
    	public void onClick(View v)
		{	
    		String usename = username.getText().toString();
   		 String pswd = password.getText().toString();
   		 if (usename.length() >= 1 &&  pswd.length() >= 1){
   		 //Toast testToast = Toast.makeText(getBaseContext(), "Username "+ usename, Toast.LENGTH_LONG);
   		 //testToast.show();
   		 curUserName = usename;
   		 Intent myIntent = new Intent(v.getContext(), Hub.class);
            startActivityForResult(myIntent, 0);
   		 }else{
   			 if (usename.length() < 1){
   			 errorToast = Toast.makeText(getBaseContext(), "Please enter a username of at least 6 characters!", Toast.LENGTH_LONG);
       		 errorToast.show();}
   			 else{
   				 errorToast = Toast.makeText(getBaseContext(), "Please enter a password of at least 6 characters!", Toast.LENGTH_LONG);
           		 errorToast.show();
   			 }
   		 }
		}
    };*/
}