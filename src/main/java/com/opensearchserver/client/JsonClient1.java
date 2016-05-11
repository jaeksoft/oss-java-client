/**
 * Copyright 2014-2015 OpenSearchServer Inc.
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
import java.net.URI;
import com.qwazr.utils.StringUtils;
import com.qwazr.utils.json.client.JsonClientAbstract;
import com.qwazr.utils.server.RemoteService;

/**
 * This client is for OpenSearchServer v1.5.x
 *
 */
public class JsonClient1 extends JsonClientAbstract {

	private final String login;
	private final String key;

	public JsonClient1(String url, String login, String key, int msTimeout)
			throws URISyntaxException {
		super(new RemoteService(new URI(url), msTimeout));
		this.login = login;
		this.key = key;
	}

	public JsonClient1(ServerResource serverResource) throws URISyntaxException {
		this(serverResource.url, serverResource.login, serverResource.api_key,
				serverResource.time_out);
	}

	/**
	 * Build an URL for OpenSearchServer 1.5
	 * 
	 * @param paths
	 *            an array of path which will be concatened
	 * @return a prepared URIBuilder
	 * @throws URISyntaxException
	 *             if the builded URI is not valid
	 */
	final public UBuilder getBaseUrl(String... paths) throws URISyntaxException {
		UBuilder uriBuilder = new UBuilder(StringUtils.fastConcat(
				"/services/rest/", paths));
		if (!StringUtils.isEmpty(login))
			uriBuilder.addParameter("login", login);
		if (!StringUtils.isEmpty(key))
			uriBuilder.addParameter("key", key);
		return uriBuilder;
	}
}
