package jonstewardappreciation.cs160.berkeley.edu;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GroupSettings extends Activity {
	public static final String PREFS_NAME = "MyPrefsFile";
    
	EditText groupEdit;
	public static String groupName = "";
    
    EditText inviteEdit;
    public static String inviteeName = "";
    boolean DEBUG = true;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groupsettings);
        
        final Button saveIcon = (Button) findViewById(R.id.saveIcon2);
        saveIcon.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	if(DEBUG) Toast.makeText(GroupSettings.this, "saveIcon2", Toast.LENGTH_SHORT).show();
            	groupName = groupEdit.getText().toString();
            	SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            	SharedPreferences.Editor editor = settings.edit();
            	editor.putString("groupName", groupName);
            	editor.commit();
            	finish();
            }
        });
        
        final Button cancelIcon = (Button) findViewById(R.id.cancelIcon2);
        cancelIcon.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	if(DEBUG) Toast.makeText(GroupSettings.this, "cancelIcon", Toast.LENGTH_SHORT).show();
            	finish();
            }
        });
        
        final Button invite = (Button) findViewById(R.id.invite);
        invite.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	if(DEBUG) Toast.makeText(GroupSettings.this, "invite", Toast.LENGTH_SHORT).show();
            	
            }
        });
        
        groupEdit = (EditText) findViewById(R.id.groupNameEdit);
        groupEdit.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus) groupName = groupEdit.getText().toString();
			}
		});
        
        inviteEdit = (EditText) findViewById(R.id.findPeople);
        inviteEdit.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus) groupName = groupEdit.getText().toString();
			}
		});
        
        // Default preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        groupEdit.setText(settings.getString("groupName", groupName));
    }
    
}