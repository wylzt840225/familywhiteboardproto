package jonstewardappreciation.cs160.berkeley.edu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Settings extends Activity {
    int SELECT_IMAGE = 0;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        
        Button profilePictureEdit = (Button) findViewById(R.id.profilePictureEdit);
        Button groupsEdit = (Button) findViewById(R.id.groupsEdit);
        Button save = (Button) findViewById(R.id.saveIcon);
        Button cancel = (Button) findViewById(R.id.cancelIcon);
        
        profilePictureEdit.setOnClickListener(pictureListen);
        groupsEdit.setOnClickListener(groupsListen);
        save.setOnClickListener(saveListen);
        cancel.setOnClickListener(cancelListen);
    }
    
    private OnClickListener pictureListen = new OnClickListener(){
    	public void onClick(View v)
		{
    		startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), SELECT_IMAGE);
		}
    };
    
    private OnClickListener groupsListen = new OnClickListener(){
    	public void onClick(View v)
		{
    		//
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
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      if (requestCode == SELECT_IMAGE)
        if (resultCode == Activity.RESULT_OK) {
          Uri selectedImage = data.getData();
          // TODO Do something with the select image URI
        } 
    }
}