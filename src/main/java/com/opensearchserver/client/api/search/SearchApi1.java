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
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import com.opensearchserver.client.JsonClient;
import com.opensearchserver.client.api.search.query.SearchFieldQuery;
import com.opensearchserver.client.api.search.query.SearchQueryBatch;
import com.opensearchserver.client.api.search.result.SearchResult;
import com.opensearchserver.utils.HttpUtils;
import com.opensearchserver.utils.LinkUtils;

/**
 * @version For OpenSearchServer v1.5.x
 */
public class SearchApi1 {

	/**
	 * Create or update a search field template
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
	public void createSearchFieldTemplate(JsonClient client, String indexName,
			String requestName, SearchFieldQuery query, int msTimeOut)
			throws ClientProtocolException, UnsupportedEncodingException,
			IOException, URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl1("index/",
				LinkUtils.UTF8_URL_Encode(indexName), "/search/field/",
				LinkUtils.UTF8_URL_Encode(requestName));
		Request request = Request.Put(uriBuilder.build());
		HttpResponse response = client.execute(request, msTimeOut, query);
		HttpUtils.checkStatusCodes(response.getStatusLine(), 200, 201);
	}

	/**
	 * Make a search with a search field template
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
	public SearchResult searchFieldTemplate(JsonClient client,
			String indexName, String requestName, SearchFieldQuery query,
			int msTimeOut) throws ClientProtocolException, IOException,
			URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl1("index/",
				LinkUtils.UTF8_URL_Encode(indexName), "/search/field/",
				LinkUtils.UTF8_URL_Encode(requestName));
		Request request = Request.Post(uriBuilder.build());
		return client.execute(request, msTimeOut, query, SearchResult.class,
				200);
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
	public SearchResult searchField(JsonClient client, String indexName,
			SearchFieldQuery query, int msTimeOut)
			throws ClientProtocolException, IOException, URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl1("index/",
				LinkUtils.UTF8_URL_Encode(indexName), "/search/field");
		Request request = Request.Post(uriBuilder.build());
		return client.execute(request, msTimeOut, query, SearchResult.class,
				200);
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
	public List<SearchResult> searchBatch(JsonClient client, String indexName,
			SearchQueryBatch queryBatch, int msTimeOut)
			throws ClientProtocolException, IOException, URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl1("index/",
				LinkUtils.UTF8_URL_Encode(indexName), "/search/batch");
		Request request = Request.Post(uriBuilder.build());
		return client.execute(request, msTimeOut, queryBatch,
				SearchResult.LIST_TYPEREF, 200);
	}
}
