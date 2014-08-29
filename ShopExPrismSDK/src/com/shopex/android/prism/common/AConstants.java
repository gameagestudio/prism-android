
package com.shopex.android.prism.common;


public class AConstants {
    // 项目名称
    public static final String PROJECT = "prism";

    // 是否处于调试模式
    public static final boolean DEBUG = true;

    // 调试标签
    public static final String TAG = PROJECT;

    // 文件根目录名
    public static final String ROOT_DIR = ".shopex";

    // 包名
    public static final String PACKAGE_NAME = "com.shopex.android.prism";

    

    // 图片�?��的宽�?    public static final int PRODUCT_IMAGE_MAX_WIDTH = 800;

    // 店铺图片�?��宽度
    public static final int SHOP_IMAGE_MAX_WIDTH = 500;

    // 店招�?��宽度
    public static final int SHOP_BANNER_MAX_WIDTH = 1000;

    // HTTP连接端口
    public static final int HTTP_PORT = 80;

    // HTTP安全连接端口
    public static final int HTTPS_PORT = 443;



    // 现网域名
    public static final String ONLINE_DOMAIN = "http://dilbmtcv.apihub.cn" ;

    // 测试环境域名
    public static final String TEST_DOMAIN = "http://dilbmtcv.apihub.cn" + ":" + HTTP_PORT;

   

    /**
     * 正式环境
     */
    public static final String ONLINE_SERVER = ONLINE_DOMAIN ;

    /**
     * 测试环境
     */
    public static final String TEST_SERVER = TEST_DOMAIN;

    /**
     * 服务端调用API的前�?     */

    public static String API_URL = TEST_SERVER;

    /**
     * 请求接口API集合
     * 
     * 
     */
    public static class REQUEST_API {
    	
    	
    	
    	/**
    	 * 安全机制
    	 */
    	public static class SECURITY{
    		public static final String SECURITY_URL = ONLINE_DOMAIN + "/api/platform/notify/status";
    		public static final String WRITE_URL = ONLINE_DOMAIN + "/api/platform/notify/write";
    		public static final String AUTHORIZE_URL = ONLINE_DOMAIN + "/oauth/authorize";
    		public static final String TOKEN_URL = ONLINE_DOMAIN + "/oauth/token";
    		public static final String CHECKSESSION_URL = ONLINE_DOMAIN + "/";
    	
    	}

  
    	/**
    	 * WebSocket
    	 */
    	public static class WEBSOCKET{
    		public static final String NOTIFY_METHOD = "/platform/notify";
    		public static final String NOTIFY_URL = ONLINE_DOMAIN + "/api" + NOTIFY_METHOD;
    	}
    }

    

  

    /**
     * key键定 
     * 
     */
    public static class KEY {
    	public static class AUTH{
    		 public static final String CLIENT_ID = "client_id";
    		 public static final String REDIRECT_URI = "redirect_uri";
    		 public static final String RESPONSE_TYPE = "response_type";
    		 public static final String VIEW = "VIEW";
    		 public static final String TTL = "ttl";
    	}
      
    }

   

 

  

  
}
