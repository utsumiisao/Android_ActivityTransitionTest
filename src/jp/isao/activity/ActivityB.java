package jp.isao.activity;

import android.app.Activity;
import android.os.Bundle;

public class ActivityB extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.b);
		
		ActivityTransitionTestActivity a  = new ActivityTransitionTestActivity();
		a.finish();
		
		super.onCreate(savedInstanceState);
	}
}
