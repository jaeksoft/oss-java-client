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
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.opensearchserver.utils.json.JsonHttpResponseHandler;
import com.opensearchserver.utils.json.JsonMapper;

public abstract class JsonClientAbstract {

	protected final URI uri;
	protected final String login;
	protected final String key;
	public final int msTimeOut;

	public JsonClientAbstract(String url, String login, String key,
			int msTimeOut) throws URISyntaxException {
		URI u = new URI(url);
		String path = u.getPath();
		if (path.endsWith("/"))
			uri = new URI(u.getScheme(), u.getHost(), path.substring(0,
					path.length() - 1), u.getFragment());
		else
			uri = u;
		this.login = login;
		this.key = key;
		this.msTimeOut = msTimeOut;
	}

	/**
	 * Build an URL
	 * 
	 * @param paths
	 * @return a prepared URIBuilder
	 * @throws URISyntaxException
	 */
	public abstract URIBuilder getBaseUrl(String... paths)
			throws URISyntaxException;

	/**
	 * Execute an HTTP request returning the expected object.
	 * 
	 * @param request
	 * @param msTimeOut
	 * @param bodyObject
	 * @param jsonResultClass
	 * @param expectedCodes
	 * @return An instance of the expected class
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	final public <T> T execute(Request request, Object bodyObject,
			Integer msTimeOut, Class<T> jsonResultClass, int... expectedCodes)
			throws ClientProtocolException, IOException {
		if (msTimeOut == null)
			msTimeOut = this.msTimeOut;
		if (bodyObject != null)
			request = request.bodyString(
					JsonMapper.MAPPER.writeValueAsString(bodyObject),
					ContentType.APPLICATION_JSON);
		return request
				.connectTimeout(msTimeOut)
				.socketTimeout(msTimeOut)
				.addHeader("accept", ContentType.APPLICATION_JSON.toString())
				.execute()
				.handleResponse(
						new JsonHttpResponseHandler.JsonValueResponse<T>(
								ContentType.APPLICATION_JSON, jsonResultClass,
								expectedCodes));
	}

	/**
	 * Execute an HTTP request returning an objet of the expected type
	 * reference.
	 * 
	 * @param request
	 * @param msTimeOut
	 * @param bodyObject
	 * @param typeRef
	 * @param expectedCodes
	 * @return An instance of the expected type
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	final public <T> T execute(Request request, Object bodyObject,
			Integer msTimeOut, TypeReference<T> typeRef, int... expectedCodes)
			throws ClientProtocolException, IOException {
		if (msTimeOut == null)
			msTimeOut = this.msTimeOut;
		if (bodyObject != null)
			request = request.bodyString(
					JsonMapper.MAPPER.writeValueAsString(bodyObject),
					ContentType.APPLICATION_JSON);
		return request
				.connectTimeout(msTimeOut)
				.socketTimeout(msTimeOut)
				.addHeader("accept", ContentType.APPLICATION_JSON.toString())
				.execute()
				.handleResponse(
						new JsonHttpResponseHandler.JsonValueTypeRefResponse<T>(
								ContentType.APPLICATION_JSON, typeRef,
								expectedCodes));
	}

	/**
	 * Execute an HTTP request. If a bodyObject is passed as parameter, it is
	 * serialized in JSON format and send in the body (payload).
	 * 
	 * @param request
	 * @param msTimeOut
	 * @param bodyObject
	 * @return the HTTP response
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	final public HttpResponse execute(Request request, Object bodyObject,
			Integer msTimeOut) throws ClientProtocolException, IOException {
		if (msTimeOut == null)
			msTimeOut = this.msTimeOut;
		if (bodyObject != null)
			request = request.bodyString(
					JsonMapper.MAPPER.writeValueAsString(bodyObject),
					ContentType.APPLICATION_JSON);
		return request.connectTimeout(msTimeOut).socketTimeout(msTimeOut)
				.execute().returnResponse();
	}
}
