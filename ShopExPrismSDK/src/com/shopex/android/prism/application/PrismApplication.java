package com.shopex.android.prism.application;

import android.app.Application;

import com.shopex.android.prism.network.NetworkClient;
import com.shopex.android.prism.utils.AustriaUtil;

public class PrismApplication extends Application {

	protected NetworkClient client;
	private static PrismApplication instance = null;

	@Override
	public void onCreate() {

		super.onCreate();
		String processName = AustriaUtil.getProcessName(this);
		if (processName != null && processName.equals(getPackageName())) {
			instance = this;
			init();
		}
	}
	
	 

	private void init() {
		// 优化内存分配机制,处理OOM问题
		AustriaUtil.optimizeDalvikVM(getClassLoader());

		

	}

	/**
	 * 单例方法
	 * 
	 * @return
	 */
	public static PrismApplication getInstance() {
		return instance;
	}

	public NetworkClient getClient() {
		return client;
	}
}
