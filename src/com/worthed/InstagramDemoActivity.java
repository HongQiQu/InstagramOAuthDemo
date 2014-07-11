package com.worthed;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class InstagramDemoActivity extends Activity {

	// private final String TAG = InstagramDemoActivity.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instagram_demo);
	}

	public void onClick(View view) {
		Intent intent = null;
		switch (view.getId()) {
		case R.id.btn_implicit:
			intent = new Intent(InstagramDemoActivity.this, ImplicitApiActivity.class);
			break;
		case R.id.btn_explicit:
			intent = new Intent(InstagramDemoActivity.this, ExplicitApiActivity.class);
			break;
		default:
			break;
		}
		if (intent != null) {
			startActivity(intent);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.instagram_demo, menu);
		return true;
	}

}
