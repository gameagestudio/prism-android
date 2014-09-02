package com.shopex.android.prism.auth;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;

import java.util.Set;

import com.shopex.android.prism.utils.LogUtil;

import android.graphics.Bitmap;
import android.text.TextUtils;

public class PrismOauthParameter {

	private static final String DEFAULT_CHARSET = "UTF-8";

	private LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

	public LinkedHashMap<String, Object> getParams() {
		return params;
	}

	public void setParams(LinkedHashMap<String, Object> params) {
		this.params = params;
	}

	public void put(String key, String val) {
		this.params.put(key, val);
	}

	public void put(String key, int value) {
		this.params.put(key, String.valueOf(value));
	}

	public void put(String key, long value) {
		this.params.put(key, String.valueOf(value));
	}

	public void put(String key, Bitmap bitmap) {
		this.params.put(key, bitmap);
	}

	public void put(String key, Object val) {
		this.params.put(key, val.toString());
	}

	public Object get(String key) {
		return this.params.get(key);
	}

	public void remove(String key) {
		if (this.params.containsKey(key)) {
			this.params.remove(key);
			this.params.remove(this.params.get(key));
		}
	}

	public Set<String> keySet() {
		return this.params.keySet();
	}

	public boolean containsKey(String key) {
		return this.params.containsKey(key);
	}

	public boolean containsValue(String value) {
		return this.params.containsValue(value);
	}

	public int size() {
		return this.params.size();
	}


	  public String encodeUrl()
	  {
	    StringBuilder sb = new StringBuilder();
	    boolean first = true;
	    for (String key : this.params.keySet()) {
	      if (first)
	        first = false;
	      else {
	        sb.append("&");
	      }

	      Object value = this.params.get(key);
	      if ((value instanceof String)) {
	        String param = (String)value;
	        if (!TextUtils.isEmpty(param)) {
	          try {
	            sb.append(URLEncoder.encode(key, DEFAULT_CHARSET) + "=" + 
	              URLEncoder.encode(param, DEFAULT_CHARSET));
	          } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	          }
	        }
	        LogUtil.i("encodeUrl", sb.toString());
	      }
	    }

	    return sb.toString();
	  }
}
