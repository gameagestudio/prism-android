
package com.shopex.android.prism.domain;

import com.loopj.android.http.RequestParams;

import java.io.Serializable;

/**
 * 请求通用头抽象类
 * 
 * @author bluestome
 */
public abstract class AbstractCommonReq extends RequestParams implements
        Serializable {

    /**
	 * 
	 */
    protected final String TAG = getClass().getSimpleName();
    private static final long serialVersionUID = 1L;
    /*
     * 分页参数-页码
     */
    protected int page;
    /*
     * 分页参数-每页显示的数量
     */
    protected int size;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
        add("page", String.valueOf(page));
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        add("size", String.valueOf(size));
    }

}
