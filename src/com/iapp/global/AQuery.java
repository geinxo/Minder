/*
 * Copyright 2011 - AndroidQuery.com (tinyeeliu@gmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.iapp.global;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView.OnEditorActionListener;

import com.androidquery.AbstractAQuery;

/**
 * The main class of AQuery. All methods are actually inherited from AbstractAQuery.
 *
 */
public class AQuery extends AbstractAQuery<AQuery>{

	
	public AQuery(Activity act) {
		super(act);
	}
	
	public AQuery(View view) {
		super(view);
	}
	
	public AQuery(Context context) {
		super(context);
	}
	
	public AQuery(Activity act, View root){
		super(act, root);
	}
	@Override
	public AQuery clicked(Object handler, String method) {
		// TODO Auto-generated method stub
		return super.clicked(handler, method);
	}

	private static final Class<?>[] A_ON_CLICK_SIG = {View.class};
	public AQuery edited(Object handler, String method) {
		ACommon common = (ACommon) (new ACommon().forward(handler, method, true, A_ON_CLICK_SIG));
		return edited(common);
	}
	
	public AQuery edited(OnEditorActionListener listener){
		
		if(view != null && view instanceof EditText ){						
			((EditText)view).setOnEditorActionListener(listener);
		}
		return this;
	}

	public View v(int id) {
		return ((Activity)getContext()).findViewById(id);
	}
}


