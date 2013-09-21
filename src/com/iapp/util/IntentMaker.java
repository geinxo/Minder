package com.iapp.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class IntentMaker {

	private Activity mTarActivity;

	public IntentMaker(Activity act) {
		mTarActivity = act;
	}
	
	public void gotoAct(Class<?> class1)
	{
		Intent intent = new Intent();
		intent.setClass(mTarActivity, class1);
		mTarActivity.startActivity(intent);
	}
	

	public void gotoActParamStr(Class<?> class1,String param1)
	{
		Intent intent = new Intent();
		intent.setClass(mTarActivity, class1);
		intent.putExtra("str1", param1);
		mTarActivity.startActivity(intent);
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		mTarActivity = null;
	}
}
