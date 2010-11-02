package jonstewardappreciation.cs160.berkeley.edu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends Activity {
	EditText username;
	EditText password;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        Button goToCustomize = (Button) findViewById(R.id.customizeButton);
        Button goToHub = (Button) findViewById(R.id.mainHubButton);
        
        goToCustomize.setOnClickListener(customizeListen);
        goToHub.setOnClickListener(goToHubListen);
        
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
	
	
	}
	
	
    private OnClickListener customizeListen = new OnClickListener(){
    	public void onClick(View v)
		{
    		 String usename = username.getText().toString();
    		 String pswd = password.getText().toString();
    		 Toast testToast = Toast.makeText(getBaseContext(), "Username "+ usename, Toast.LENGTH_LONG);
    		 testToast.show();
		}
    };
    
    private OnClickListener goToHubListen = new OnClickListener(){
    	public void onClick(View v)
		{
    		
		}
    };
}