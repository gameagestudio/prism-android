
package com.shopex.android.prism.common;

/**
 *  
 * @author bluestome
 */
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
    public static final String TEST_DOMAIN = "http://122.224.88.221" + ":" + HTTP_PORT;

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
    // 正式环境: http://122.225.68.107
    // 测试环境: http://122.224.88.221:8888
    public static String API_URL = TEST_SERVER;

    /**
     * 请求接口API集合
     * 
     * @author bluestome
     */
    public static class REQUEST_API {
    	
    	
    	
    	/**
    	 * 安全机制
    	 */
    	public static class SECURITY{
    		public static final String SECURITY_URL = ONLINE_DOMAIN + "/api/platform/notify/status";
    		public static final String WRITE_URL = ONLINE_DOMAIN + "/api/platform/notify/write";
    	}

        /**
         * 帮助与关于中的地�?         * 
         * @author bluestome
         */
        public static class ABOUT {
            // 免责条款
            public static final String TERM_URL = ONLINE_DOMAIN + "/about/disclaimer.html";

            // 关于我们
            public static final String ABOUT_US_URL = ONLINE_DOMAIN + "/about/us.html";

            // 版权说明
            public static final String COPYRIGHT_URL = ONLINE_DOMAIN + "/about/copyright.html";

            // 帮助说明
            public static final String HELP_URL = ONLINE_DOMAIN + "/about/kk_start.html";

            // 担保协议
            public static final String DANBAO = ONLINE_DOMAIN + "/about/danbao.html";
        }

        /**
         * ua接口
         */
        public static final String UA = API_URL + "";
        /**
         * �?��更新
         */
        public static final String CHECK_UPDATE = API_URL + "/update/check";

        /**
         * 出错了的意见反馈
         */
        public static final String FEEDBACK_ERROR = API_URL + "/feedback/reportError";

        /**
         * 使用过程的意见反�?         */
        public static final String FEEDBACK_USE = API_URL + "/feedback/save";

        /**
         * 获取�?��省份
         */
        public static final String PROVICE_LIST = API_URL + "/zone/roots";

        /**
         * 获取指定ID下的子地�?         */
        public static final String CITY_LIST = API_URL + "/zone/{id}/children ";

        /**
         * 文件上传接口
         * 
         * @author bluestome
         */
        public static class FILE_UPLOAD_API {
            /**
             * 图片上传地址
             */
            public static final String UPLOAD_IMAGE = API_URL + "/_f/u";

            /**
             * 日志上传地址
             */
            public static final String UPLOAD_LOG = API_URL + "/_f/u/log";

            /**
             * 图片下载地址
             */
            public static final String DOWNLOAD_IMAGE = API_URL + "/_f/";
        }

        /**
         * 用户接口
         * 
         * @author bluestome
         */
        public static class USR_API {

            /**
             * �?��是否登陆
             */
            public static final String CHECK_LOGIN = API_URL + "/signined";

            /**
             * 登陆 接收参数[u:用户�?p:密码]
             */
            public static final String LOGIN = API_URL + "/signin_check";

            /**
             * �?��
             */
            public static final String LOGOUT = API_URL + "/logout";

            /**
             * 注册 接收参数[mobile:手机号码 smsCode: 短信验证码]
             */
            public static final String REGISTER = API_URL + "/register";

            /**
             * 注册手机号码�?�� 接收参数[mobile:手机号码]
             */
            public static final String REGISTER_CHECK = API_URL + "/registered";

            /**
             * 发�?短信验证�?接收参数[mobile:手机号码]
             */
            public static final String SMS_AUTH = API_URL + "/send-sms-code";

            /**
             * 发�?修改密码短信验证�?接收参数[mobile:手机号码]
             */
            public static final String FINDPWD_SMS_AUTH = API_URL
                    + "/forget-pwd";

            /**
             * 验证找回密码的短�?             */
            public static final String VALIDATE_FINDPWD_SMS = API_URL
                    + "/validate-forget-pwd";

            /**
             * 修改密码
             */
            public static final String RESET_PWD = API_URL
                    + "/validate-forget-pwd";

            /**
             * 修改密码 接受参数:[old:旧密�?newPwd:新密码]
             */
            public static final String MODIFY_PWD = API_URL
                    + "/user/change-pwd";
        }

        /**
         * 客户管理
         * 
         * @author bluestome
         */
        public static class CUSTOMER_API {

            private static final String TEMP_API_URL = "http://10.0.13.59:8080";

            /**
             * 获取客户列表
             */
            public static final String LIST = API_URL + "/customer/list";

            /**
             * 搜索客户
             */
            public static final String LISTBYKEY = API_URL + "/customer/listByKey";

            /**
             * 获取客户详情
             */
            public static final String DETAIL = API_URL + "/customer";
        }

        /**
         * 首页相关
         * 
         * @author zhenshui.xia
         */
        public static class HOME_API {

            /**
             * 首页�?��数据接口
             */
            public static final String HOME_ACTIVITY = API_URL + "/index/home-activity";

            /**
             * 首页�?��数据接口
             */
            public static final String HOME_PRODUCTS = API_URL + "/category/";

            /**
             * 首页活动，排序接�?             */
            public static final String HOME_ACTIVITY2 = API_URL + "/index/home-activity2";

            /**
             * 首页商品分页数据接口
             */
            public static final String HOME_PRODUCTS2 = API_URL + "/activity/one";

        }

        /**
         * 订单接口
         * 
         * @author bluestome
         */
        public static class ORDER_API {

            /**
             * 获取列表 /order/list/{status}
             */
            public static final String LIST = API_URL + "/order/list/";

            /**
             * 搜索订单 /order/listByKey/{status}
             */
            public static final String LISTBYKEY = API_URL + "/order/listByKey/";

            /**
             * 订单详情�?             */
            public static final String DETAIL = API_URL + "/order/";

            /**
             * 获取快�?公司列表
             */
            public static final String LOGISTICSES = API_URL + "/logistics/list";

            /**
             * 发货
             */
            public static final String SHIP = API_URL + "/order/shipped";

            /**
             * �?��
             */
            public static final String REFUND = API_URL + "/order/refund";
        }

        /**
         * 产品接口URL
         * 
         * @author bluestome
         */
        public static class PRODUCT_API {

            /**
             * 保存和更新产�?             */
            public static final String SAVE_UPDATE = API_URL + "/product/save";

            /**
             * 获取商品列表 /product/list
             */
            public static final String LIST = API_URL + "/product/list";

            /**
             * 获取商品详情 /product/{id}
             */
            public static final String DETAIL = API_URL + "/product/";

            /**
             * 删除商品 /product/delete/{id}
             */
            public static final String DELETE = API_URL + "/product/delete/";

            /**
             * 上架接口 /product/onsale/{id}
             */
            public static final String ON_SALE = API_URL + "/product/onsale/";

            /**
             * 下架接口 /product/instock/{id}
             */
            public static final String OUT_OF_SALE = API_URL
                    + "/product/instock/";

            /**
             * 店铺内的商品排序 /shop/productIndex/{shopId}
             */
            public static final String PRODUCT_INDEX = API_URL
                    + "/product/index/";

            /**
             * 获取计划发布商品列表 /product/list/forsale
             */
            public static final String PRODUCT_PLAN_LIST = API_URL + "/product/list/forsale";

        }

        /**
         * 店铺API
         * 
         * @author bluestome
         */
        public static class SHOP_API {

            /**
             * 店铺搬家
             */
            public static final String SHOP_MOVE = API_URL + "/shop/step1";
            /**
             * 保存店铺
             */
            public static final String SAVE = API_URL + "/shop/save";

            /**
             * 更新店铺
             */
            public static final String UPDATE = API_URL + "/shop/update";

            /**
             * 我的店铺
             */
            public static final String MY_SHOP = API_URL + "/shop/mine";

            /**
             * 更新店招地址 /shop/updateBanner/{id}
             */
            public static final String UPDATE_SHOP_BANNER = API_URL
                    + "/shop/updateBanner/";

            /**
             * 店铺里的TAG列表
             */
            public static final String TAG_LIST = API_URL + "/shop/tags";

            /**
             * 增加TAG
             */
            public static final String TAG_SAVE = API_URL + "/shop/tag/save";

            /**
             * 删除TAG
             */
            public static final String TAG_REMOVE = API_URL + "/shop/tag/remove";

            /**
             * 修改TAG
             */
            public static final String TAG_UPDATE = API_URL + "/shop/tag/update";

            /**
             * 店铺类目的goods列表
             */
            public static final String SHOP_GOODS_LIST = API_URL + "/shop/goods";

            /**
             * 增加店铺类目
             */
            public static final String SHOP_GOODS_SAVE = API_URL + "/shop/goods/save";

            /**
             * 更新店铺类目
             */
            public static final String SHOP_GOODS_UPDATE = API_URL + "/shop/goods/update";

            /**
             * 删除店铺类目
             */
            public static final String SHOP_GOODS_REMOVE = API_URL + "/shop/goods/remove";

            /**
             * �??店铺的担保交易请求URL
             */
            public static final String SHOP_DANBAO = API_URL + "/shop/openDanbao";

            // **** 店铺信息更新,拆分未部分接�?****
            /**
             * 更新店铺头像
             */
            public static final String SHOP_UPDATE_IMG = API_URL + "/shop/updateImg";
            /**
             * 更新店铺名称
             */
            public static final String SHOP_UPDATE_NAME = API_URL + "/shop/updateName";

            /**
             * 更新店铺微信
             */
            public static final String SHOP_UPDATE_WECHAT = API_URL + "/shop/updateWechat";
            /**
             * 更新店铺描述
             */
            public static final String SHOP_UPDATE_DESC = API_URL + "/shop/updateDesc";

            /**
             * 更新店铺公告
             */
            public static final String SHOP_UPDATE_BULLETIN = API_URL + "/shop/updateBulletin";

            /**
             * 更新店铺�?���?             */
            public static final String SHOP_UPDATE_LOCATION = API_URL + "/shop/updateLocal";

            /**
             * 设置邮费
             */
            public static final String SHOP_SET_POSTAGE = API_URL + "/shop/setPostage";
            
            /**
             * 获取店铺默认设置
             */
            public static final String SHOP_GET_DEFAULT = API_URL + "/shop/styles";
            
            /**
             * 保存店铺修改信息
             */
            public static final String SAVE_SHOP_SET = API_URL+"/shop/styles/update";
            
            /**
             * 获取默认店招图片
             */
            public static final String GET_DEFAULT_SHOP_PIC = API_URL+"/shop/styles/banners";
      
        }

        /**
         * 我的收入模块API
         * 
         * @ClassName: INCOME_API
         * @Description: TODO
         * @author bluestome
         * @date 2014-6-4 下午2:56:59
         */
        public static class INCOME_API {
            // 我的收入主界面数据请求URL
            public static final String INCOME_MAIN = API_URL + "/userBank/mineAccount";

            // �?��是否绑定了银行卡
            public static final String CHECK_BIND_BANK = API_URL + "/userBank/mine";

            // 保存绑定的银行卡
            public static final String SAVE_BANKCARD = API_URL + "/userBank/save";

            // 累计收入接口
            public static final String INCOME_TOTAL_LIST = API_URL + "/userBank/mineDealHistory";

        
        }

    }

    /**
     * 基于位置的接�?     * 
     * @author bluestome
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
     * key键定�?     * 
     * @author bluestome
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
