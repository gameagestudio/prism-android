
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

    // UA头名�?    public static final String HTTP_HEADER_UA = "User-Agent";

    // UA�?    public static final String USER_AGENT = "Hotshop";

    // 现网域名
    public static final String ONLINE_DOMAIN = "http://dilbmtcv.apihub.cn" ;

    // 测试环境域名
    public static final String TEST_DOMAIN = "http://dilbmtcv.apihub.cn" + ":" + HTTP_PORT;

    // 接口版本�?    public static final String API_VERSION = "/v2";

    /**
     * 正式环境
     */
    public static final String ONLINE_SERVER = ONLINE_DOMAIN ;

    /**
     * 测试环境
     */
    public static final String TEST_SERVER = ONLINE_DOMAIN;

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
     * 基于位置的接�?     * 
     * 
     */
    public static class LBS_API {
        // 获取位置信息
        public static final String LOCATION = API_URL + "/location";
    }

    public static class DIALOG_KEY
    {
        public final static int DIALOG_LOADING = 0x00000030;
    }

    /**
     * key键定 
     * 
     */
    public static class KEY {
        // Cookie保存KEY
        public final static String COOKIE_SAVE_KEY = PACKAGE_NAME
                + ".COOKIE_SAVE_KEY";

        // 本地Account账号KEY
        public final static String ACCOUNT_KEY = PACKAGE_NAME + ".ACCOUNT_KEY";

        // 是否第一次启动AddActivity
        public final static String ADD_SHOP_FIRST_KEY = PACKAGE_NAME
                + ".ADD_SHOP_FIRST_KEY";

        public final static int REQUEST_SHOW_PRODUCT_DETAIL_KEY = 0x00000100;
        public final static int REQUEST_ADD_PRODUCT_KEY = 0x00000101;

        public final static String PRODUCT_DELETE_KEY = "PRODUCT_DELETE";
        public final static String PRODUCT_KEY = "PRODUCT";

        public final static String URL_KEY = "URL";
        public final static String TITLE_KEY = "TITLE";
        public final static String SHARE_KEY = "SHARE";
        public final static String VALUE_KEY = "VALUE";

        // 访客统计KEY
        public final static String VISITORS_STAT_KEY = "VISITORS_STAT";

        // 帮助信息KEY
        public final static String HELP_KEY = "HELP";

        // 发布记录KEY
        public final static String PUBLISH_RECORDS_KEY = "PUBLISH_RECORDS";

        // 主框架页默认选中片段KEY
        public final static String MAIN_TABID_KEY = "MAIN_TABID";

        // 消息体相关KEY
        public final static String MSG_TYPE_KEY = "type";
        public final static String MSG_TITLE_KEY = "title";
        public final static String MSG_DESC_KEY = "desc";
        public final static String MSG_IMAGEURL_KEY = "imageUrl";
        public final static String MSG_DETAILURL_KEY = "detailUrl";
        public final static String MSG_USERID_KEY = "userId";
        public final static String MSG_TIME_KEY = "createdAt";
        public final static String MSG_DATA0_KEY = "data0";
        public final static String MSG_DATA1_KEY = "data1";
        public final static String MSG_DATA2_KEY = "data2";
        public final static String MSG_DATA3_KEY = "data3";
        public final static String MSG_DATA4_KEY = "data4";
        public final static String MSG_DATA5_KEY = "data5";
        public final static String MSG_DATA6_KEY = "data6";
        public final static String MSG_DATA7_KEY = "data7";

        // 用户ID KEY
        public final static String USER_ID_KEY = "userId";

        // 是否禁用消息设置KEY
        public final static String SETTING_PUSH_KEY = "settingPush";

        // 是否已经自动�?��更新
        public final static String KEY_CHECK_UPGRADE = "checkUpgrade";

        public final static String KEY_PHONE = "phone";
        public final static String KEY_PASSWD = "passwd";

        public final static String KEY_EXIT_LOGIN = "exitLogin";

        public final static String KEY_ISLOGIN = "isLogin";

        public final static String KEY_USER_INFO = "userInfo";
        public final static String KEY_SHOP_INFO = "shopInfo";
        public final static String KEY_USER_BANK = "userBank";

        // 存放sina key的字�?        public final static String KEY_SINA_UID = "uid";

        public final static String KEY_SINA_ACCESS_TOKEN = "access_token";

        public final static String KEY_SINA_EXPIRES_IN = "expires_in";

        // 发货快�?公司信息
        public final static String KEY_EXPRESS_NAME = "express_name";

        public final static String KEY_HOME_ACTIVITY = "home_activity";
        public final static String KEY_HOME_PRODUCTS = "home_products";
        public final static String KEY_HOME_PCATEGORY = "home_pcategory";
        public final static String KEY_HOME_PSORT = "home_psort";
        public final static String KEY_PCATEGORY_INDEX = "pcategory_index";
        public final static String KEY_PSORT_INDEX = "psort_index";
    }

    /**
     * 广播ACTIONS集合
     * 
     * @author bluestome
     */
    public static class BROADCAST_ACTIONS {
        // �?��activity广播ACTION
        public static final String ACTIVITY_DESTORY_SELF_ACTION = "com.shopex.android.BROADCAST_ACTIONS.ACTIVITY_DESTORY_SELF_ACTION";
        // �?��广播
        public static final String EXIT_ACTION = "com.shopex.android.BROADCAST_ACTIONS.EXIT_ACTION";
        // 启动更新的广�?        public static final String CHECK_UPDATE_ACTION = "com.shopex.android.BROADCAST_ACTIONS.CHECK_UPDATE_ACTION";
        // 下载更新
        public static final String DOWNLOAD_UPDATE_ACTION = "com.shopex.android.BROADCAST_ACTIONS.DOWNLOAD_UPDATE_ACTION";
        // 消息广播
        public static final String MESSAGE_ACTION = "com.shopex.android.BROADCAST_ACTIONS.MESSAGE_ACTION";
        // 消息更新广播，针对当前用户在消息页面
        public static final String MESSAGE_UPDATE_ACTION = "com.shopex.android.BROADCAST_ACTIONS.MESSAGE_UPDATE_ACTION";
        // �?��登陆的ACTION
        public static final String NEED_LOGIN_ACTION = "com.shopex.android.BROADCAST_ACTIONS.NEED_LOGIN_ACTION";
        // 登录后发送更新广�?        public static final String LOGIN_UPDATE_ACTION = "com.shopex.android.BROADCAST_ACTIONS.LOGIN_UPDATE_ACTION";
        // 商品发布成功广播
        public static final String PRODUCT_RELEASE_SUCCESS_ACTION = "com.shopex.android.PRODUCT_RELEASE_SUCCESS_ACTION";
    }

    /**
     * 服务广播
     * 
     * @author bluestome
     */
    public static class SERVICES_ACTIONS {

    }

    /**
     * 状�?定义
     * 
     * @author bluestome
     */
    public static class STATUS_CODE {
        // 未登陆状�?        public static final int NO_LOGIN = 0x191;
    }

    public static class WECHAT {
        public static final int WECHAT_MOMENT = 1;
        public static final int WECHAT_FRIEND = 0;
    }

    public static class SINA {
        public static final String SINAWEIBO_APP_KEY = "3493668126";

        public static final String SINAWEIBO_REDIRECT_URL = "http://www.kkkd.com/";

        public static final String SINAWEIBO_SCOPE = "";
    }

    public static class FEEDBACK {
        public static final String ERROR = "ERROR";
        public static final String USE = "USE";

    }

  
}
