package com.iapp.main;

import java.util.ArrayList;
import java.util.HashMap;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.iapp.data.DataItem;
import com.iapp.data.DataProvider;
import com.iapp.data.DataProvider.DataProviderCompleteListener;
import com.iapp.data.DataWapper;
import com.iapp.data.DataWapper.IDataHandler;
import com.iapp.global.AQuery;
import com.iapp.global.G;
import com.iapp.mytest.R;
import com.iapp.view.BaseActivity;

public class ContentActivity extends BaseActivity implements View.OnCreateContextMenuListener,DataProviderCompleteListener
{
	ListView mList;
	AQuery mListQuery;
	DataProvider mDataProvider;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content);
		mListQuery = $.id(R.id.listContent);
		mList = $.id(R.id.listContent).getListView();
		mDataProvider = new DataProvider(this);
		queryData();

		mListQuery.itemClicked(this, "onItemClick");
		//$.itemClicked(this,"onClick");
	}

//	public void onClick(AdapterView parent, View v, int pos, long id) {
//		queryData();
//	}

	@Override
	public void onDataComplete(DataWapper aDataWapper) {
		final ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
		aDataWapper.foreachDataItems(new IDataHandler() {
			@Override
			public void handleData(DataItem aDataItem) {
				HashMap<String, Object> aDataMap = new HashMap<String, Object>();
				aDataMap.put("title", aDataItem.getTitle());
				aDataMap.put("url", aDataItem.getUrl());
				aDataMap.put("tags", aDataItem.getTags());
				listItem.add( aDataMap );
			}
		});
		renderData(listItem);
	}
	
	private void queryData() {
		mDataProvider.setOnDataCompleteListener(this);
		mDataProvider.queryData();
	}

	private void renderData(ArrayList<HashMap<String, Object>> listItem) {
		SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,// 数据源
				R.layout.view_content_item, new String[] { "title", "url",
						"tags" }, new int[] { R.id.ItemImage, R.id.ItemTitle,
						R.id.ItemText });
		mList.setAdapter(listItemAdapter);
	}
//	private void queryData() {
//
//		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
//		for (int i = 0; i < 10; i++) {
//			HashMap<String, Object> map = new HashMap<String, Object>();
//			map.put("ItemImage", R.drawable.ic_launcher);// 图像资源的ID
//			map.put("ItemTitle", "Level " + i);
//			map.put("ItemText", "Finished in 1 Min 54 Secs, 70 Moves! ");
//			listItem.add(map);
//		}
//
//        //生成适配器的Item和动态数组对应的元素  
//        SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItem,//数据源   
//            R.layout.view_content_item,//ListItem的XML实现  
//            //动态数组与ImageItem对应的子项          
//            new String[] {"ItemImage","ItemTitle", "ItemText"},   
//            //ImageItem的XML文件里面的一个ImageView,两个TextView ID  
//            new int[] {R.id.ItemImage,R.id.ItemTitle,R.id.ItemText}  
//        );  
//         
//        //添加并且显示  
//        mList.setAdapter(listItemAdapter);  
//        mListQuery.itemClicked(this, "onItemClick");
//        
//        mList.setOnCreateContextMenuListener(this);
////        mList.setoncre
////        mList.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
////			
////			@Override
////			public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
////				 menu.setHeaderTitle("长按菜单-ContextMenu");     
////	                menu.add(0, 0, 0, "弹出长按菜单0");  
////	                menu.add(0, 1, 0, "弹出长按菜单1");     
////			}
////		});
//        
////      //添加长按点击  
////        mList.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {  
////              
////            @Override  
////            public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
////                menu.setHeaderTitle("长按菜单-ContextMenu");     
////                menu.add(0, 0, 0, "弹出长按菜单0");  
////                menu.add(0, 1, 0, "弹出长按菜单1");     
////            }  
////        });   
//	}

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		setTitle("点击第"+position+"个项目");  
		View curr = parent.getChildAt((int) id);
		G.i(this).gotoActParamStr(DetailActivity.class,(String)(((TextView)curr.findViewById( R.id.ItemTitle)).getText()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.content, menu);
		return true;
	}

}
