/**
 * Copyright 2014-2015 OpenSearchServer Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.opensearchserver.client;

import com.qwazr.utils.LinkUtils;
import com.qwazr.utils.StringUtils;
import com.qwazr.utils.UBuilder;
import com.qwazr.utils.http.HttpRequest;
import com.qwazr.utils.http.ResponseValidator;
import com.qwazr.utils.json.client.JsonClientAbstract;
import com.qwazr.utils.server.RemoteService;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * This client is for OpenSearchServer v1.5.x
 */
public class JsonClient1 extends JsonClientAbstract {

	private final String baseUrl;
	private final String login;
	private final String key;

	public JsonClient1(final String baseUrl, final String login, final String key, int msTimeout)
			throws URISyntaxException {
		super(new RemoteService(new URI(baseUrl), msTimeout));
		this.baseUrl = baseUrl + "/services/rest/";
		this.login = login;
		this.key = key;
	}

	public JsonClient1(final ServerResource serverResource) throws URISyntaxException {
		this(serverResource.url, serverResource.login, serverResource.api_key, serverResource.time_out);
	}

	/**
	 * Build an URL for OpenSearchServer 1.5
	 *
	 * @param paths an array of path which will be concatened
	 * @return a prepared URIBuilder
	 * @throws URISyntaxException if the builded URI is not valid
	 */
	final public UBuilder getBaseUrl(final String... paths) throws URISyntaxException, UnsupportedEncodingException {
		final StringBuilder sb = new StringBuilder(baseUrl);
		if (paths != null)
			for (String path : paths) {
				if (path.indexOf('/') == -1)
					sb.append(LinkUtils.UTF8_URL_Encode(path));
				else
					sb.append(path);
			}
		final UBuilder uriBuilder = new UBuilder(sb.toString());
		if (!StringUtils.isEmpty(login))
			uriBuilder.addParameter("login", login);
		if (!StringUtils.isEmpty(key))
			uriBuilder.addParameter("key", key);
		return uriBuilder;
	}

	final public boolean execute200True404False(final HttpRequest request, final Object bodyObject,
			final Integer msTimeOut, final ResponseValidator validator) {
		switch (executeStatusCode(request, bodyObject, msTimeOut, validator)) {
		case 200:
			return true;
		case 404:
			return false;
		}
		return false;
	}
}
