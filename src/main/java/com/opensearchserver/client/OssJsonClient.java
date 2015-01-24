/**
 * Copyright 2015 OpenSearchServer Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.opensearchserver.client;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import com.opensearchserver.utils.StringUtils;
import com.opensearchserver.utils.json.JsonHttpResponseHandler;

public class OssJsonClient {

	private final String oss_api_endpoint;
	private final String oss_api_login;
	private final String oss_api_key;

	public OssJsonClient(String oss_api_endpoint, String oss_api_login,
			String oss_api_key) {
		this.oss_api_endpoint = oss_api_endpoint;
		this.oss_api_login = oss_api_login;
		this.oss_api_key = oss_api_key;
	}

	final URIBuilder baseUrl(String... path) throws URISyntaxException {
		StringBuilder sb = new StringBuilder(oss_api_endpoint);
		if (path != null)
			for (String p : path)
				if (p != null)
					sb.append(p);
		URIBuilder uriBuilder = new URIBuilder(sb.toString());
		if (!StringUtils.isEmpty(oss_api_login))
			uriBuilder.addParameter("login", oss_api_login);
		if (!StringUtils.isEmpty(oss_api_key))
			uriBuilder.addParameter("key", oss_api_key);
		return uriBuilder;
	}

	public <T> T execute(Request request, int msTimeOut,
			Class<T> jsonResultClass) throws ClientProtocolException,
			IOException {
		return request
				.connectTimeout(msTimeOut)
				.socketTimeout(msTimeOut)
				.execute()
				.handleResponse(
						new JsonHttpResponseHandler.JsonValueResponse<T>(
								jsonResultClass));
	}
}
