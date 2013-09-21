package com.iapp.global;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.androidquery.util.Common;

public class ACommon extends Common implements OnEditorActionListener{

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		invoke(v);
		return false;
	}
}
