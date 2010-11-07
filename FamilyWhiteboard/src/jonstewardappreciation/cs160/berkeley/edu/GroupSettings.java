package jonstewardappreciation.cs160.berkeley.edu;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GroupSettings extends Activity {
    public static String groupName = "";
    public static final String PREFS_NAME = "MyPrefsFile";
    boolean DEBUG = true;
    EditText groupEdit;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groupsettings);
        
       /*final Button saveIcon = (Button) findViewById(R.id.saveIcon);
        saveIcon.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	if(DEBUG) Toast.makeText(GroupSettings.this, "saveIcon", Toast.LENGTH_SHORT).show();
            	groupName = groupEdit.getText().toString();
            	SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            	SharedPreferences.Editor editor = settings.edit();
            	editor.putString("groupName", groupName);
            	editor.commit();
            	finish();
            }
        });
        
        final Button cancelIcon = (Button) findViewById(R.id.cancelIcon);
        cancelIcon.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	if(DEBUG) Toast.makeText(GroupSettings.this, "cancelIcon", Toast.LENGTH_SHORT).show();
            	finish();
            }
        });
        groupEdit = (EditText) findViewById(R.id.groupsEdit);
        // Default preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String name = settings.getString("name", groupName);
        groupEdit.setText(name);*/
    }
    
}