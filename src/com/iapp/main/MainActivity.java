package com.iapp.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.iapp.global.G;
import com.iapp.mytest.R;
import com.iapp.view.BaseActivity;

public class MainActivity extends BaseActivity {


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        $.id(R.id.btnEnter).clicked(this, "onBtnEnterClick");
        
        onBtnEnterClick(null);
    }

	public void onBtnEnterClick(View aBtn) {
        //G.i(this).gotoAct(LoginActivity.class);
        G.i(this).gotoAct(ContentActivity.class);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("MySettings");
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getTitle() == "MySettings")
		{
			 G.i(this).gotoAct(SettingsActivity.class);
		}
		return true;
	}
	
	@Override
	protected void onPause() {
		Toast.makeText(this, "once again", Toast.LENGTH_SHORT).show();
		super.onPause();
	}
}
