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
package com.opensearchserver.client.v2;

import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;

import com.opensearchserver.client.JsonClientAbstract;
import com.opensearchserver.utils.StringUtils;

/**
 * This client is for OpenSearchServer v2.x *
 */
public class JsonClient2 extends JsonClientAbstract {

	public JsonClient2(String url, String login, String key)
			throws URISyntaxException {
		super(url, login, key);
	}

	/**
	 * Build an URL for OpenSearchServer 2.x
	 * 
	 * @param paths
	 * @return a prepared URIBuilder
	 */
	@Override
	final public URIBuilder getBaseUrl(String... paths) {
		URIBuilder uriBuilder = new URIBuilder().setScheme(uri.getScheme())
				.setHost(uri.getHost())
				.setPath(StringUtils.fastConcat(uri.getPath(), paths));
		if (!StringUtils.isEmpty(login) && !StringUtils.isEmpty(key))
			uriBuilder.setUserInfo(login, key);
		return uriBuilder;
	}
}
