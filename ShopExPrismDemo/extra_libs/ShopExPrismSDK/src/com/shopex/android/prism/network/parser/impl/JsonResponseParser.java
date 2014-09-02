
package com.shopex.android.prism.network.parser.impl;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.shopex.android.prism.network.parser.IResponseParser;


/**
 * @ClassName: HttpParser
 * @Description: TODO
 * @author bluestome
 * @date 2014-7-7 下午4:04:40
 */
public class JsonResponseParser implements IResponseParser {
    private static final long serialVersionUID = 1L;
    private Gson mGson = new Gson();

    @Override
    public <T> T fromJson(String json, Class<?> clz) {
        try {
            return mGson.fromJson(json, (Type) clz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
