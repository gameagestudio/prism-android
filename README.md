Android
===============================================

用途
-----------------------------------------------
实现shopex Prism 的Android版SDK供第三方使用

功能
-----------------------------------------------

- 提供oauth认证

要求
-----------------------------------------------
Android 1.6+ JDK6以上版本


提供oauth认证
--------------------------------------------------
- 创建PrismOauth实例对象,并且将上下文Context,clientId和redirectUrl传入

```java
String redirectUrl = "http://buwb2lii.com/oauth-adapter?action=callback";
String clientId = "buwb2lii";
PrismClient prismClient = new PrismClient(context,clientId,redirectUrl);
```

- 进行认证

```java
			oauth.authorize(new PrismOauthListener() {
					
					@Override
					public void onSuccess(OAuth data) {
						System.out.println("登录成功:"+data.getAccess_token());
						UIUtils.showAlert(MainActivity.this, "成功","登录成功:"+data.getAccess_token());
						//用户需将OAuth对象保存本地，以后再次发送请求的时候将access_token带入头部
					}
					
					@Override
					public void onFaliure(int code, String result) {
						UIUtils.showAlert(MainActivity.this, "失败",result+":"+code);
						
					}
					
					@Override
					public void onException(PrismException exception) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onCancel() {
						UIUtils.showAlert(MainActivity.this, "失败","用户取消");
						
					}
				});
```


