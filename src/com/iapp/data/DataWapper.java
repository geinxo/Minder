package com.iapp.data;

import java.util.ArrayList;
import java.util.ListIterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataWapper {
	private ArrayList<DataItem> mArrItems;
	public DataWapper() {
		mArrItems = new ArrayList<DataItem>();
	}
	/**
	 * @return the mArrItems
	 */
	public void foreachDataItems(IDataHandler dataHandler) {
		ListIterator<DataItem> iter = mArrItems.listIterator();
		while(iter.hasNext())
		{
			dataHandler.handleData(iter.next());
		}
	}
	/**
	 * @return the mArrItems
	 */
	public ArrayList<DataItem> getArrItems() {
		return mArrItems;
	}

	/**
	 * @param mArrItems the mArrItems to set
	 */
	public void setmArrItems(ArrayList<DataItem> mArrItems) {
		this.mArrItems = mArrItems;
	}
	
	/**
	 * @return the mArrItems
	 */
	public DataItem getArrItem(int aIndex) {
		return mArrItems.get(aIndex);
	}

	/**
	 * @param mArrItems the mArrItems to set
	 */
	public void setmArrItem(int aIndex, DataItem aArrItem) {
		mArrItems.set(aIndex, aArrItem);
	}
	
	public void fromJSONObject(JSONObject aJson) {
		try {
			JSONArray jsonAry = aJson.getJSONObject("responseData").getJSONArray("results");
			for (int i = 0; i < jsonAry.length(); i++) {
				JSONObject jsonObj = (JSONObject)jsonAry.get(i);
				DataItem dataItem = new DataItem();
				dataItem.readFromObject( jsonObj );
				mArrItems.add(dataItem);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public interface IDataHandler
	{
		public void handleData(DataItem aDataItem);
	};
}
