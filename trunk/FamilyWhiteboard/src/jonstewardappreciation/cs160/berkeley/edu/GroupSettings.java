package jonstewardappreciation.cs160.berkeley.edu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GroupSettings extends Activity {
	EditText groupEdit;
	public static String groupName = "";
    
    EditText inviteEdit;
    public static String inviteeName = "";
    
    ImageView newMemberIcon1;
    TextView newMemberName1;
    ImageView newMemberIcon2;
    TextView newMemberName2;
    boolean firstMember = true;
    
    boolean DEBUG = false;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groupsettings);
        
        //String groupNameFromBefore = savedInstanceState.getString("groupName");
        //if(DEBUG) Toast.makeText(GroupSettings.this, groupNameFromBefore, Toast.LENGTH_SHORT).show();
    	
        final Button saveIcon = (Button) findViewById(R.id.saveIcon);
        saveIcon.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            //	if(DEBUG) Toast.makeText(GroupSettings.this, "saveIcon2", Toast.LENGTH_SHORT).show();
            	groupName = groupEdit.getText().toString();
            	Intent returnIntent = new Intent();
                returnIntent.putExtra("groupName",groupName);
                returnIntent.putExtra("invitee1",newMemberName1.toString());
                returnIntent.putExtra("invitee2",newMemberName2.toString());
                setResult(RESULT_OK,returnIntent);        
            	finish();
            }
        });
        
        final Button cancelIcon = (Button) findViewById(R.id.cancelIcon);
        cancelIcon.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            //	if(DEBUG) Toast.makeText(GroupSettings.this, "cancelIcon", Toast.LENGTH_SHORT).show();
            	finish();
            }
        });
        
     // Help
        final Button helpIcon = (Button) findViewById(R.id.helpIcon);
        helpIcon.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	// prepare the alert box
                AlertDialog.Builder alertbox = new AlertDialog.Builder(GroupSettings.this);
                // set the message to display
                alertbox.setMessage("Add new members to your group by entering their name and clicking 'Invite'");
	            // show it
                alertbox.show();

            }
        });
        
        final Button invite = (Button) findViewById(R.id.invite);
        invite.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	//if(DEBUG) Toast.makeText(GroupSettings.this, "invite", Toast.LENGTH_SHORT).show();
            	if(firstMember) {
            		newMemberIcon1.setVisibility(0);
                	newMemberName1.setText(inviteEdit.getText().toString());
                	firstMember = false;
                	inviteEdit.setText("");
            	}
            	else {
            		newMemberIcon2.setVisibility(0);
            		newMemberName2.setText(inviteEdit.getText().toString());
            		inviteEdit.setText("");
            	}
            }
        });
        
        newMemberIcon1 = (ImageView) findViewById(R.id.groupsIcon1);
        newMemberName1 = (TextView) findViewById(R.id.groupsEdit1);
        newMemberIcon2 = (ImageView) findViewById(R.id.groupsIcon2);
        newMemberName2 = (TextView) findViewById(R.id.groupsEdit2);
        
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
				if(!hasFocus) inviteeName = inviteEdit.getText().toString();
			}
		});
        
	}
    
}