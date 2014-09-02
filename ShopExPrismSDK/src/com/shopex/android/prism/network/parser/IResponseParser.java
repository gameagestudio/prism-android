
package com.shopex.android.prism.network.parser;

import java.io.Serializable;

/**
 * @ClassName: IResponseParser
 * @Description: TODO
 * @author bluestome
 * @date 2014-7-7 下午5:00:08
 */
public interface IResponseParser extends Serializable {

    /**
     * 解析器
     * 
     * @param content
     * @param clz
     * @return
     */
    <T> T fromJson(String content, Class<?> clz);
}
