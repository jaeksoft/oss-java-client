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
package com.opensearchserver.client.v1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import com.opensearchserver.client.JsonClient1;
import com.opensearchserver.client.common.AbstractApi;
import com.opensearchserver.client.common.search.query.SearchFieldQuery;
import com.opensearchserver.client.common.search.query.SearchQueryBatch;
import com.opensearchserver.client.v1.search.SearchResult1;
import com.qwazr.utils.http.HttpUtils;

/**
 * @version For OpenSearchServer v1.5.x
 */
public class SearchApi1 extends AbstractApi<JsonClient1> {

	public SearchApi1(JsonClient1 client) {
		super(client);
	}

	/**
	 * Create or update a search field template
	 * 
	 * @param indexName
	 *            The name of the index
	 * @param template
	 *            The name of the template
	 * @param query
	 *            The query
	 * @throws IOException
	 *             if any IO error occurs
	 * @throws URISyntaxException
	 *             if the URI is not valid
	 */
	public void createSearchFieldTemplate(String indexName, String template,
			SearchFieldQuery query) throws IOException, URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl("index/", indexName,
				"/search/field/", template);
		Request request = Request.Put(uriBuilder.build());
		HttpResponse response = client.execute(request, query, null);
		HttpUtils.checkStatusCodes(response, 200, 201);
	}

	/**
	 * Execute a search with a search field template
	 * 
	 * @param indexName
	 *            The name of the index
	 * @param template
	 *            The name of the template
	 * @param query
	 *            Any overriding query parameter
	 * @return the search result
	 * @throws IOException
	 *             if any IO error occurs
	 * @throws URISyntaxException
	 *             if the URI is not valid
	 */
	public SearchResult1 executeSearchFieldTemplate(String indexName,
			String template, SearchFieldQuery query) throws IOException,
			URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl("index/", indexName,
				"/search/field/", template);
		Request request = Request.Post(uriBuilder.build());
		return client.execute(request, query, null, SearchResult1.class, 200);
	}

	/**
	 * Execute a search field
	 * 
	 * @param indexName
	 *            The name of the index
	 * @param query
	 *            Any overriding query parameter
	 * @return the search result
	 * @throws IOException
	 *             if any IO error occurs
	 * @throws URISyntaxException
	 *             if the URI is not valid
	 */
	public SearchResult1 executeSearchField(String indexName,
			SearchFieldQuery query) throws IOException, URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl("index/", indexName,
				"/search/field");
		Request request = Request.Post(uriBuilder.build());
		return client.execute(request, query, null, SearchResult1.class, 200);
	}

	/**
	 * Execute a batch of search
	 * 
	 * @param indexName
	 *            The name of the index
	 * @param queryBatch
	 *            The queries
	 * @return a list of results
	 * @throws IOException
	 *             if any IO error occurs
	 * @throws URISyntaxException
	 *             if the URI is not valid
	 */
	public List<SearchResult1> searchBatch(String indexName,
			SearchQueryBatch queryBatch) throws IOException, URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl("index/", indexName,
				"/search/batch");
		Request request = Request.Post(uriBuilder.build());
		return client.execute(request, queryBatch, null,
				SearchResult1.LIST_TYPEREF, 200);
	}

}
