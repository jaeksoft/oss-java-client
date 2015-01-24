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
package com.opensearchserver.client.api.search;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;

public class SearchApi {

	/**
	 * Create the Search Field request
	 * 
	 * @param indexName
	 *            The name of the index
	 * @param requestName
	 *            The name of the request
	 * @param json
	 *            The JSON structure of the request
	 * @throws ClientProtocolException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void createSearchFieldRequest(String indexName, String requestName,
			String json) throws ClientProtocolException,
			UnsupportedEncodingException, IOException, URISyntaxException {
		URIBuilder uriBuilder = baseUrl("index/", utf8encode(indexName),
				"/search/field/", utf8encode(requestName));
		HttpResponse response = Request.Put(uriBuilder.build())
				.addHeader("accept", ContentType.APPLICATION_JSON.toString())
				.bodyString(json, ContentType.APPLICATION_JSON)
				.connectTimeout(msTimeOut).socketTimeout(msTimeOut).execute()
				.returnResponse();
		checkHttpResponse(response, 200);
	}

	/**
	 * Make a search
	 * 
	 * @param indexName
	 *            The name of the index
	 * @param template
	 *            The name of the template
	 * @param json
	 *            Any overriding information in JSON format
	 * @return InputStream
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public InputStream searchFieldTemplate(String indexName, String template,
			String json) throws ClientProtocolException, IOException,
			URISyntaxException {
		URIBuilder uriBuilder = baseUrl("index/", utf8encode(indexName),
				"/search/field/", utf8encode(template));
		HttpResponse response = Request.Post(uriBuilder.build())
				.addHeader("accept", ContentType.APPLICATION_JSON.toString())
				.connectTimeout(msTimeOut).socketTimeout(msTimeOut)
				.bodyString(json, ContentType.APPLICATION_JSON).execute()
				.returnResponse();
		HttpEntity entity = response.getEntity();
		checkHttpResponse(response, 200);
		checkContentType(entity, ContentType.APPLICATION_JSON);
		return entity.getContent();
	}

	/**
	 * Make a search on fields
	 * 
	 * @param indexName
	 *            The name of the index
	 * @param json
	 *            Any overriding information in JSON format
	 * @return InputStream
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public InputStream searchField(String indexName, String json)
			throws ClientProtocolException, IOException, URISyntaxException {
		URIBuilder uriBuilder = baseUrl("index/", utf8encode(indexName),
				"/search/field");
		HttpResponse response = Request.Post(uriBuilder.build())
				.addHeader("accept", ContentType.APPLICATION_JSON.toString())
				.connectTimeout(msTimeOut).socketTimeout(msTimeOut)
				.bodyString(json, ContentType.APPLICATION_JSON).execute()
				.returnResponse();
		HttpEntity entity = response.getEntity();
		checkHttpResponse(response, 200);
		checkContentType(entity, ContentType.APPLICATION_JSON);
		return entity.getContent();
	}

	/**
	 * Make a batch search
	 * 
	 * @param indexName
	 *            The name of the index
	 * @param json
	 *            JSON queries
	 * @return A JSON structure with the documents found
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public InputStream searchBatch(String indexName, String json)
			throws ClientProtocolException, IOException, URISyntaxException {
		URIBuilder uriBuilder = baseUrl("index/", utf8encode(indexName),
				"/search/batch");
		HttpResponse response = Request.Post(uriBuilder.build())
				.addHeader("accept", ContentType.APPLICATION_JSON.toString())
				.connectTimeout(msTimeOut).socketTimeout(msTimeOut)
				.bodyString(json, ContentType.APPLICATION_JSON).execute()
				.returnResponse();
		checkHttpResponse(response, 200);
		HttpEntity entity = response.getEntity();
		checkContentType(entity, ContentType.APPLICATION_JSON);
		return entity.getContent();
	}

}
