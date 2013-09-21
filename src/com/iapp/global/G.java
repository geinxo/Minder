package com.iapp.global;

import android.app.Activity;

import com.iapp.util.IntentMaker;

public class G {

	public static AQuery q(Activity act)
	{
		return new AQuery(act);
	}

	public static IntentMaker i(Activity act)
	{
		return new IntentMaker(act);
	}

}
