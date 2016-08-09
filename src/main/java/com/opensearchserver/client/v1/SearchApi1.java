/**
 * Copyright 2015 OpenSearchServer Inc.
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
package com.opensearchserver.client.v1;

import com.opensearchserver.client.JsonClient1;
import com.opensearchserver.client.common.AbstractApi;
import com.opensearchserver.client.common.search.query.SearchFieldQuery;
import com.opensearchserver.client.common.search.query.SearchPatternQuery;
import com.opensearchserver.client.common.search.query.SearchQueryBatch;
import com.opensearchserver.client.v1.search.SearchResult1;
import com.qwazr.utils.http.HttpRequest;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

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
	 * @param indexName The name of the index
	 * @param template  The name of the template
	 * @param query     The query
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public void createSearchFieldTemplate(String indexName, String template, SearchFieldQuery query)
			throws IOException, URISyntaxException {
		final URIBuilder uriBuilder = client.getBaseUrl("index/", indexName, "/search/field/", template);
		final HttpRequest request = HttpRequest.Put(uriBuilder.build());
		client.execute(request, query, null, validator_200_201);
	}

	/**
	 * Execute a search with a search field template
	 *
	 * @param indexName The name of the index
	 * @param template  The name of the template
	 * @param query     Any overriding query parameter
	 * @return the search result
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public SearchResult1 executeSearchFieldTemplate(String indexName, String template, SearchFieldQuery query)
			throws IOException, URISyntaxException {
		final URIBuilder uriBuilder = client.getBaseUrl("index/", indexName, "/search/field/", template);
		final HttpRequest request = HttpRequest.Post(uriBuilder.build());
		return client.executeJson(request, query, null, SearchResult1.class, validator_200);
	}

	/**
	 * Execute a search with a search field template
	 *
	 * @param indexName The name of the index
	 * @param template  The name of the template
	 * @param query     Any overriding query parameter
	 * @return the search result
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public SearchResult1 executeSearchPatternTemplate(String indexName, String template, SearchPatternQuery query)
			throws IOException, URISyntaxException {
		final URIBuilder uriBuilder = client.getBaseUrl("index/", indexName, "/search/pattern/", template);
		final HttpRequest request = HttpRequest.Post(uriBuilder.build());
		return client.executeJson(request, query, null, SearchResult1.class, validator_200);
	}

	/**
	 * Execute a search field
	 *
	 * @param indexName The name of the index
	 * @param query     Any overriding query parameter
	 * @return the search result
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public SearchResult1 executeSearchField(String indexName, SearchFieldQuery query)
			throws IOException, URISyntaxException {
		final URIBuilder uriBuilder = client.getBaseUrl("index/", indexName, "/search/field");
		final HttpRequest request = HttpRequest.Post(uriBuilder.build());
		return client.executeJson(request, query, null, SearchResult1.class, validator_200);
	}

	/**
	 * Execute a search pattern
	 *
	 * @param indexName The name of the index
	 * @param query     Any overriding query parameter
	 * @return the search result
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public SearchResult1 executeSearchPattern(String indexName, SearchPatternQuery query)
			throws IOException, URISyntaxException {
		final URIBuilder uriBuilder = client.getBaseUrl("index/", indexName, "/search/pattern");
		final HttpRequest request = HttpRequest.Post(uriBuilder.build());
		return client.executeJson(request, query, null, SearchResult1.class, validator_200);
	}

	/**
	 * Execute a batch of search
	 *
	 * @param indexName  The name of the index
	 * @param queryBatch The queries
	 * @return a list of results
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public List<SearchResult1> searchBatch(String indexName, SearchQueryBatch queryBatch)
			throws IOException, URISyntaxException {
		final URIBuilder uriBuilder = client.getBaseUrl("index/", indexName, "/search/batch");
		final HttpRequest request = HttpRequest.Post(uriBuilder.build());
		return client.executeJson(request, queryBatch, null, SearchResult1.LIST_TYPEREF, validator_200);
	}

}
