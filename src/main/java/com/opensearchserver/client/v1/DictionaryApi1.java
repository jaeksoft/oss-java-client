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
import com.qwazr.utils.http.HttpRequest;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;

public class DictionaryApi1 extends AbstractApi<JsonClient1> {

	public DictionaryApi1(JsonClient1 client) {
		super(client);
	}

	/**
	 * Create a list of synonyms
	 *
	 * @param indexName    The name of the index
	 * @param listName     Name of the list
	 * @param listSynonyms List of synonyms, one set of synonyms by line, raw text
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public void createSynonymsList(String indexName, String listName, String listSynonyms)
			throws IOException, URISyntaxException {
		final URIBuilder uriBuilder = client.getBaseUrl("index/", indexName, "/synonyms/", listName);
		final HttpRequest request = HttpRequest.Put(uriBuilder.build());
		client.execute(request, listSynonyms, null, validator_200);
	}

	/**
	 * Check if a list of synonyms exists.
	 *
	 * @param indexName The name of the index.
	 * @param listName  The name of the list.
	 * @return true if the analyzer exists, false if not.
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public boolean checkSynonymsListExists(String indexName, String listName) throws IOException, URISyntaxException {
		final URIBuilder uriBuilder = client.getBaseUrl("index/", indexName, "/synonyms/", listName);
		final HttpRequest request = HttpRequest.Head(uriBuilder.build());
		return client.execute200True404False(request, null, null, validator_200_404);
	}

	/**
	 * Delete a synonym list.
	 *
	 * @param indexName The name of the index.
	 * @param listName  The name of the list.
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public void deleteSynonymsList(String indexName, String listName) throws IOException, URISyntaxException {
		final URIBuilder uriBuilder = client.getBaseUrl("index/", indexName, "/synonyms/", listName);
		final HttpRequest request = HttpRequest.Delete(uriBuilder.build());
		client.execute(request, null, null, validator_200);
	}

	/**
	 * Create a list of stopwords
	 *
	 * @param indexName     The name of the index
	 * @param listName      Name of the list
	 * @param stopwordsList List of synonyms, one set of synonyms by line, raw text
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public void createStopWordsList(String indexName, String listName, String stopwordsList)
			throws IOException, URISyntaxException {
		final URIBuilder uriBuilder = client.getBaseUrl("index/", indexName, "/stopwords/", listName);
		final HttpRequest request = HttpRequest.Put(uriBuilder.build());
		client.execute(request, stopwordsList, null, validator_200);
	}

	/**
	 * Check if a list of stopwords exists.
	 *
	 * @param indexName The name of the index.
	 * @param listName  The name of the list.
	 * @return true if the analyzer exists, false if not.
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public boolean checkStopWordsListExists(String indexName, String listName) throws IOException, URISyntaxException {
		final URIBuilder uriBuilder = client.getBaseUrl("index/", indexName, "/stopwords/", listName);
		final HttpRequest request = HttpRequest.Head(uriBuilder.build());
		return client.execute200True404False(request, null, null, validator_200_404);
	}

	/**
	 * Delete a stopwords list.
	 *
	 * @param indexName The name of the index.
	 * @param listName  The name of the list.
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public void deleteStopWordsList(String indexName, String listName) throws IOException, URISyntaxException {
		final URIBuilder uriBuilder = client.getBaseUrl("index/", indexName, "/stopwords/", listName);
		final HttpRequest request = HttpRequest.Delete(uriBuilder.build());
		client.execute(request, null, null, validator_200);
	}
}
