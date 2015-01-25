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
package com.opensearchserver.client.v1;

import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;

import com.opensearchserver.client.JsonClientAbstract;
import com.opensearchserver.utils.StringUtils;

/**
 * This client is for OpenSearchServer v1.5.x
 *
 */
public class JsonClient1 extends JsonClientAbstract {

	public JsonClient1(String url, String login, String key, int msTimeout)
			throws URISyntaxException {
		super(url, login, key, msTimeout);
	}

	/**
	 * Build an URL for OpenSearchServer 1.5
	 * 
	 * @param paths
	 * @return a prepared URIBuilder
	 * @throws URISyntaxException
	 */
	@Override
	final public URIBuilder getBaseUrl(String... paths)
			throws URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder()
				.setScheme(uri.getScheme())
				.setHost(uri.getHost())
				.setPath(
						StringUtils.fastConcat(uri.getPath(),
								"/services/rest/", paths));
		if (!StringUtils.isEmpty(login))
			uriBuilder.addParameter("login", login);
		if (!StringUtils.isEmpty(key))
			uriBuilder.addParameter("key", key);
		return uriBuilder;
	}
}
