package com.iapp.data;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.ListIterator;

import org.apache.http.util.EncodingUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.iapp.event.EventDispatcher;
import com.iapp.view.BaseActivity;

public class DataProvider extends EventDispatcher {

	private ArrayList<DataProviderCompleteListener> mArrListeners;
	private DataState mCurrentDataState = DataState.STATE_INIT;
	private DataWapper mDataWapper;
	private BaseActivity mTargetActivity;

	public DataProvider(BaseActivity aTargetActivity) {
		mTargetActivity = aTargetActivity;
		mArrListeners = new ArrayList<DataProviderCompleteListener>();
	}

	public void setOnDataCompleteListener(DataProviderCompleteListener aListener) {
		if (mArrListeners.indexOf(aListener) < 0) {
			mArrListeners.add(aListener);
		}
	}

	// public JSONObject getTestData() {
	// try {
	// return new JSONObject("{" + "\"employees\": ["
	// + "{ \"firstName\":\"Bill\" , \"lastName\":\"Gates\" },"
	// + "{ \"firstName\":\"George\" , \"lastName\":\"Bush\" },"
	// + "{ \"firstName\":\"Thomas\" , \"lastName\":\"Carter\" }"
	// + "]}");
	// } catch (JSONException e) {
	// e.printStackTrace();
	// }
	// return null;
	// }
	public JSONObject getTestData() {
		try {
			String fileName = "testdata.txt"; // 文件名字
			String res = "";
			try {
				// 得到资源中的asset数据流
				InputStream in = mTargetActivity.getResources().getAssets()
						.open(fileName);

				int length = in.available();
				byte[] buffer = new byte[length];

				in.read(buffer);
				in.close();
				res = EncodingUtils.getString(buffer, "UTF-8");
				return new JSONObject(res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void queryData() {
		setCurrentDataState(DataState.STATE_PENDING);
		String url = "http://www.google.com/uds/GnewsSearch?q=Obama&v=1.0";
		// mTargetActivity.$.ajax(url, JSONObject.class, this, "jsonCallback");
		jsonCallback("", getTestData(), new AjaxStatus());
	}

	public void jsonCallback(String url, JSONObject json, AjaxStatus status) {

		if (json != null) {
			mDataWapper = new DataWapper();
			mDataWapper.fromJSONObject(json);
			setCurrentDataState(DataState.STATE_COMPLETE);
			// checkCompleteListener
			ListIterator<DataProviderCompleteListener> iter = mArrListeners
					.listIterator();
			while (iter.hasNext()) {
				DataProviderCompleteListener listener = iter.next();
				listener.onDataComplete(mDataWapper);
			}
		} else {
			setCurrentDataState(DataState.STATE_ERROR);
		}
	}

	/**
	 * @return the mCurrentDataState
	 */
	public DataState getCurrentDataState() {
		return mCurrentDataState;
	}

	/**
	 * @param mCurrentDataState
	 *            the mCurrentDataState to set
	 */
	public void setCurrentDataState(DataState mCurrentDataState) {
		this.mCurrentDataState = mCurrentDataState;
	}

	public interface DataProviderCompleteListener {
		public void onDataComplete(DataWapper aDataWapper);
	}

	public enum DataState {
		STATE_INIT, STATE_PENDING, STATE_COMPLETE, STATE_ERROR
	};
}
