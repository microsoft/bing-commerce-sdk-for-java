/**
 * 
 */
package com.microsoft.bing.commerce.search.util;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AccessTokenInterceptor implements Interceptor {

	private final AccessTokenProvider accessTokenProvider;

	public AccessTokenInterceptor(final AccessTokenProvider accessTokenProvider) {
		this.accessTokenProvider = accessTokenProvider;
	}

	@Override
	public Response intercept(Chain chain) throws IOException {

		Request request = chain.request().newBuilder()
				.header("Authorization", "Bearer " + accessTokenProvider.getAccessToken())
				.build();
		return chain.proceed(request);
	}
}
