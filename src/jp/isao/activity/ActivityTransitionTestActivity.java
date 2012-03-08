package jp.isao.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class ActivityTransitionTestActivity extends Activity {
    private final int REQUEST_GET_CONTENT = 1;
	private final int REQUEST_CROP_PICK = 2;
	private ImageView imageView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        imageView = (ImageView)findViewById(R.id.image_view);
        
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_GET_CONTENT);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	
    	if(resultCode == RESULT_OK) {
    		if(requestCode == REQUEST_GET_CONTENT) {
    			Uri uri = data.getData();
    			Intent intent = new Intent("com.android.camera.action.CROP");
    			intent.setData(uri);
    			intent.putExtra("outputX", 400);
    			intent.putExtra("outputY", 400);
    			intent.putExtra("aspectX", 1);
    			intent.putExtra("aspectY", 1);
    			intent.putExtra("scale", false);
    			intent.putExtra("return-data", true);
    			startActivityForResult(intent, REQUEST_CROP_PICK);
    		} else if(requestCode == REQUEST_CROP_PICK) {
    			Bitmap bitmap = data.getExtras().getParcelable("data");
    			imageView.setImageBitmap(bitmap);
    			Intent i = new Intent(ActivityTransitionTestActivity.this, ActivityB.class);
    			startActivity(i);
    			//ActivityB遷移した後、メインのActivityを終了してキャッシュに残らないようにする
    			finish();
    		}
    	}
    	
    	
    	super.onActivityResult(requestCode, resultCode, data);
    }
    
}