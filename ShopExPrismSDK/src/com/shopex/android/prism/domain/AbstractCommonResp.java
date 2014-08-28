
package com.shopex.android.prism.domain;

import java.io.Serializable;

import android.util.Log;

import com.google.gson.Gson;


public abstract class AbstractCommonResp<T> implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /**
	 * 
	 */
    protected Gson gson = new Gson();
    // 错误码
    protected int errorCode = 0;
    // 错误消息
    protected String error;
    // 更多信息
    protected String moreInfo;
    // 响应数据
    protected T data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

}
