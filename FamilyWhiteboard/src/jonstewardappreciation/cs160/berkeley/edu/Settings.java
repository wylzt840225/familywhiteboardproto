package jonstewardappreciation.cs160.berkeley.edu;

import android.app.Activity;
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
import android.widget.Toast;

public class Settings extends Activity {
    int SELECT_IMAGE_INTENT = 0;
    int GROUPS_INTENT = 1;
    
    public static final String PREFS_NAME = "MyPrefsFile";
    boolean DEBUG = true;
    public static String userName = "";
    EditText nameEdit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        
        // Listeners
        final Button profilePictureEdit = (Button) findViewById(R.id.profilePictureEdit);
        profilePictureEdit.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(DEBUG) Toast.makeText(Settings.this, "profilePictureEdit", Toast.LENGTH_SHORT).show();
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), SELECT_IMAGE_INTENT);
            }
        });
        
        final Button groupsEdit = (Button) findViewById(R.id.groupsEdit);
        groupsEdit.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	if(DEBUG) Toast.makeText(Settings.this, "groupsEdit", Toast.LENGTH_SHORT).show();
            	startActivity(new Intent(Settings.this, GroupSettings.class));
            }
        });
        
        final Button saveIcon = (Button) findViewById(R.id.saveIcon);
        saveIcon.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	if(DEBUG) Toast.makeText(Settings.this, "saveIcon", Toast.LENGTH_SHORT).show();
            	userName = nameEdit.getText().toString();
            	SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            	SharedPreferences.Editor editor = settings.edit();
            	editor.putString("name", userName);
            	editor.commit();
            	finish();
            }
        });
        
        final Button cancelIcon = (Button) findViewById(R.id.cancelIcon);
        cancelIcon.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	if(DEBUG) Toast.makeText(Settings.this, "cancelIcon", Toast.LENGTH_SHORT).show();
            	finish();
            }
        });
        
        nameEdit = (EditText) findViewById(R.id.nameEdit);
        nameEdit.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus) userName = nameEdit.getText().toString();
			}
		});
        
        // Default preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String name = settings.getString("name", userName);
        nameEdit.setText(name);
    }
    
    
    
    @Override
    protected void onStop(){
       super.onStop();

      // We need an Editor object to make preference changes.
      // All objects are from android.context.Context
      SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
      SharedPreferences.Editor editor = settings.edit();
      //editor.putBoolean("silentMode", mSilentMode);

      // Commit the edits!
      editor.commit();
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
        	  if(DEBUG) Toast.makeText(Settings.this, "on activity result with group", Toast.LENGTH_SHORT).show();
              // TODO Do something with the select image URI
            }
          }
    }
}