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

import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;

import com.opensearchserver.utils.StringUtils;
import com.opensearchserver.utils.json.JsonClientAbstract;
import com.opensearchserver.utils.json.ServerResource;

/**
 * This client is for OpenSearchServer v2.x *
 */
public class JsonClient2 extends JsonClientAbstract {

	private final String login;
	private final String key;

	public JsonClient2(String url, String login, String key, int msTimeOut)
			throws URISyntaxException {
		super(url, msTimeOut);
		this.login = login;
		this.key = key;
	}

	public JsonClient2(ServerResource serverResource) throws URISyntaxException {
		this(serverResource.url, serverResource.login, serverResource.api_key,
				serverResource.time_out);
	}

	/**
	 * Build an URL for OpenSearchServer 2.x
	 * 
	 * @param paths
	 *            an array of paths which will be concatened
	 * @return a prepared URIBuilder
	 * @throws URISyntaxException
	 *             if the builded URI is not valid
	 */
	@Override
	final public URIBuilder getBaseUrl(String... paths)
			throws URISyntaxException {
		URIBuilder uriBuilder = super.getBaseUrl(paths);
		if (!StringUtils.isEmpty(login) && !StringUtils.isEmpty(key))
			uriBuilder.setUserInfo(login, key);
		return uriBuilder;
	}
}
