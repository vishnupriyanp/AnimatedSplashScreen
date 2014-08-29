package com.vishnu.animated.splashscreen;

/**
 * @author vishnupriyan 
 * 
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;

public class SplashScreen extends Activity {
	/** Called when the activity is first created. */

	final int color = Color.BLACK;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		WebView view = new WebView(this);
		view.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		view.setScrollbarFadingEnabled(true);
		view.setVerticalScrollBarEnabled(false);
		view.setHorizontalScrollBarEnabled(false);
		view.setBackgroundColor(color);
		view.loadDataWithBaseURL("file:///android_asset/",
				"<img src=\"boot.gif\" height=\"99%\" width=\"100%\"/>",
				"text/html", "utf-8", null);
		setContentView(view);

		view.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				return (arg1.getAction() == MotionEvent.ACTION_MOVE);
			}
		});

		Thread splashThread = new Thread() {
			@Override
			public void run() {
				try {
					int waited = 0;
					while (waited < 3000) {
						sleep(100);
						waited += 100;
					}
				} catch (InterruptedException e) {
					// do nothing
				} finally {
					Intent intentSecondActivity = new Intent(SplashScreen.this,
							SecondActivity.class);
					intentSecondActivity
							.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
					intentSecondActivity
							.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intentSecondActivity);
					finish();
				}
			}
		};
		splashThread.start();

	}
}