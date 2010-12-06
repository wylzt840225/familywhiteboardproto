package jonstewardappreciation.cs160.berkeley.edu;

import android.app.Activity;
import android.app.AlertDialog;
//import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends Activity {
    int SELECT_IMAGE_INTENT = 0;
    int GROUPS_INTENT = 1;
    
    public static final String PREFS_NAME = "MyPrefsFile";
    public static final boolean DEBUG = false;
    public static String userName = "";
    EditText nameEdit;
    EditText passwordEdit;
    TextView groupEdit;
    ImageView groupEditImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        
        // Edit your profile picture
        final Button profilePictureEdit = (Button) findViewById(R.id.profilePictureEdit);
        profilePictureEdit.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(DEBUG) Toast.makeText(Settings.this, "profilePictureEdit", Toast.LENGTH_SHORT).show();
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), SELECT_IMAGE_INTENT);
            }
        });
        
        // Create a new group
        final Button groupsEdit = (Button) findViewById(R.id.groupsEdit);
        groupsEdit.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	if(DEBUG) Toast.makeText(Settings.this, "groupsEdit", Toast.LENGTH_SHORT).show();
            	Intent sendingIntent = new Intent(Settings.this, GroupSettings.class);
            	sendingIntent.putExtra("groupName", "");
                sendingIntent.putExtra("invitee1", "");
                sendingIntent.putExtra("invitee2", "");
                startActivityForResult(sendingIntent, GROUPS_INTENT);
            }
        });
        
        // Save all activity and return to previous activity
        final Button saveIcon = (Button) findViewById(R.id.saveIcon);
        saveIcon.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	if(DEBUG) Toast.makeText(Settings.this, "saveIcon", Toast.LENGTH_SHORT).show();
            	userName = nameEdit.getText().toString();
            	SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            	SharedPreferences.Editor editor = settings.edit();
            	editor.putString("name", userName);
            	editor.commit();
            	startActivity(new Intent(v.getContext(), Hub.class));
            	finish();
            }
        });
        
        // Cancel activity (no saves) 
        final Button cancelIcon = (Button) findViewById(R.id.cancelIcon);
        cancelIcon.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	if(DEBUG) Toast.makeText(Settings.this, "cancelIcon", Toast.LENGTH_SHORT).show();
            	finish();
            }
        });
        
        // Help
        final Button helpIcon = (Button) findViewById(R.id.helpIcon);
        helpIcon.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	// prepare the alert box
                AlertDialog.Builder alertbox = new AlertDialog.Builder(Settings.this);
                // set the message to display
                alertbox.setMessage("Create a new group to interact with by clicking 'Create a new group'");
	            // show it
                alertbox.show();

            }
        });
        
        groupEdit = (TextView) findViewById(R.id.group1Name);
        groupEditImage = (ImageView) findViewById(R.id.group1);
        
        // Click on a group you created to edit it again
        final Button group1Name = (Button) findViewById(R.id.group1Name);
        final String groupName = group1Name.getText().toString();
        group1Name.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	if(DEBUG) Toast.makeText(Settings.this, "group1Name", Toast.LENGTH_SHORT).show();
            	Intent sendingIntent = new Intent(Settings.this, GroupSettings.class);
            	SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            	sendingIntent.putExtra("groupName", groupName);
                sendingIntent.putExtra("invitee1", settings.getString("invitee1", " "));
                sendingIntent.putExtra("invitee2", settings.getString("invitee2", " "));
                startActivityForResult(sendingIntent, GROUPS_INTENT);
            }
        });
        
        // Edit your user name
        nameEdit = (EditText) findViewById(R.id.nameEdit);
        nameEdit.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus) userName = nameEdit.getText().toString();
			}
		});
        
        // Edit your password
        passwordEdit = (EditText) findViewById(R.id.passwordEdit);
        
        // Restore existing preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String name = settings.getString("username", userName);
        String pswd = settings.getString("password", "");
        nameEdit.setText(name);
        passwordEdit.setText(pswd);
    }
        
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      
      if (requestCode == SELECT_IMAGE_INTENT) {
        if (resultCode == Activity.RESULT_OK) {
          Uri selectedImage = data.getData();
      		if(DEBUG) Toast.makeText(Settings.this, "on activity result with image", Toast.LENGTH_SHORT).show();
          // TODO Do something with the select image URI
        }
      }
      else if (requestCode == GROUPS_INTENT) {
          if (resultCode == Activity.RESULT_OK) {
        	  //if(DEBUG) Toast.makeText(Settings.this, "on activity result with group", Toast.LENGTH_SHORT).show();
        	  String groupName = data.getStringExtra("groupName");
        	  String invitee1 = data.getStringExtra("invitee1");
        	  String invitee2 = data.getStringExtra("invitee2");
        	  SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
              SharedPreferences.Editor editor = settings.edit();
              editor.putString("groupName", groupName);
              editor.putString("invitee1", invitee1);
              editor.putString("invitee2", invitee2);
              if(DEBUG) Toast.makeText(Settings.this, settings.getString("groupName", "?")+ " " + settings.getString("invitee1", "??")+ " " + settings.getString("invitee2", "???") , Toast.LENGTH_SHORT).show();
        	  editor.commit();
              groupEditImage.setVisibility(0);
              groupEdit.setText(groupName);
            }
          }
    }
}