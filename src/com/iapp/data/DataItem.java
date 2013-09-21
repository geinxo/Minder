package com.iapp.data;

import java.util.ArrayList;
import java.util.ListIterator;

import org.json.JSONException;
import org.json.JSONObject;

public class DataItem {
	private String title;
	private String content;
	private String url;
	private String thumb;
	private ArrayList<String> tags = new ArrayList<String>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public String getTags() {
		String destResult = "";
		ListIterator<String > iter = tags.listIterator();
		while(iter.hasNext())
		{
			destResult += iter.next();
		}
		return destResult;
	}

	public void addTags(String tag) {
		tags.add(tag);
	}

	public DataItem() {
	}

	public DataItem(String title, String url, String thumb) {
		super();
		this.title = title;
		this.url = url;
		this.thumb = thumb;
	}

	public void readFromObject(JSONObject aJsonObject) {
		try {
			// setTitle(aJsonObject.get("title").toString());
			// setUrl(aJsonObject.get("url").toString());
			// setThumb(aJsonObject.get("thumb").toString());
			// addTags(aJsonObject.get("tags").toString());
			setTitle(aJsonObject.get("title").toString());
			setUrl(aJsonObject.get("unescapedUrl").toString());
			setContent(aJsonObject.get("content").toString());
			setThumb("this is thumb");
			addTags(aJsonObject.get("publisher").toString());
			addTags(aJsonObject.get("publishedDate").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

}
