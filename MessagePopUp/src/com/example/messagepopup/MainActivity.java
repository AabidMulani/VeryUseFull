package com.example.messagepopup;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	Button btn;
	TextView txt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn = (Button) findViewById(R.id.button1);
		txt = (TextView) findViewById(R.id.textView1);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				appear(txt);
			}
		});
	}

	public boolean appear(final View view) {
		Animation slideout = AnimationUtils.loadAnimation(MainActivity.this,
				R.anim.appear);
		slideout.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {
			}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {
			}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				disaappear(view);
			}
		});
		view.startAnimation(slideout);
		view.setVisibility(View.VISIBLE);
		return true;
	}

	public boolean disaappear(View view) {
		Animation slideIn = AnimationUtils.loadAnimation(MainActivity.this,
				R.anim.disappear);
		view.startAnimation(slideIn);
		view.setVisibility(View.GONE);
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
