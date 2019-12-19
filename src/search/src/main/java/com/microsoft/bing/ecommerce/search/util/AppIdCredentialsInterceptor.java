/**
 * 
 */
package com.microsoft.bing.ecommerce.search.util;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AppIdCredentialsInterceptor implements Interceptor
{
	private static final String APP_ID_PARAM_NAME = "appid";

	private final String appId;
	public AppIdCredentialsInterceptor(final String appId)
	{
		this.appId = appId;
	}
	
	public String appId()
	{
		return appId();
	}
	
	@Override
	public Response intercept(Interceptor.Chain chain) throws IOException {
		Request request = chain.request();
		
		HttpUrl modifiedUrl = request.url().newBuilder()
			.addQueryParameter(APP_ID_PARAM_NAME, appId)
			.build();
				
		Request modifiedRequest = request.newBuilder()
			.url(modifiedUrl)
			.build();
		
		return chain.proceed(modifiedRequest);
	}
}
