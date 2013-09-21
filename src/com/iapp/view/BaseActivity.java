package com.iapp.view;

import android.app.Activity;

import com.iapp.global.AQuery;
import com.iapp.global.G;

public class BaseActivity extends Activity {
	public AQuery $;
	public BaseActivity() {
		$ = G.q(this);
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		$ = null;
	}
}
