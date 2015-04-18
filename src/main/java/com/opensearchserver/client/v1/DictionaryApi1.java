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

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import com.opensearchserver.client.JsonClient1;
import com.opensearchserver.client.common.AbstractApi;
import com.qwazr.utils.http.HttpUtils;
import com.qwazr.utils.json.client.JsonClientAbstract;

public class DictionaryApi1 extends AbstractApi<JsonClientAbstract> {

	public DictionaryApi1(JsonClient1 client) {
		super(client);
	}

	/**
	 * Create a list of synonyms
	 * 
	 * @param indexName
	 *            The name of the index
	 * @param listName
	 *            Name of the list
	 * @param listSynonyms
	 *            List of synonyms, one set of synonyms by line, raw text
	 * @throws IOException
	 *             if any IO error occurs
	 * @throws URISyntaxException
	 *             if the URI is not valid
	 */
	public void createSynonymsList(String indexName, String listName,
			String listSynonyms) throws IOException, URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl("index/", indexName,
				"/synonyms/", listName);
		Request request = Request.Put(uriBuilder.build());
		HttpResponse response = client.execute(request, listSynonyms, null);
		HttpUtils.checkStatusCodes(response, 200);
	}

	/**
	 * Check if a list of synonyms exists.
	 * 
	 * @param indexName
	 *            The name of the index.
	 * @param listName
	 *            The name of the list.
	 * @return true if the analyzer exists, false if not.
	 * @throws IOException
	 *             if any IO error occurs
	 * @throws URISyntaxException
	 *             if the URI is not valid
	 */
	public boolean checkSynonymsListExists(String indexName, String listName)
			throws IOException, URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl("index/", indexName,
				"/synonyms/", listName);
		Request request = Request.Head(uriBuilder.build());
		HttpResponse response = client.execute(request, null, null);
		switch (HttpUtils.checkStatusCodes(response, 200, 404)) {
		case 200:
			return true;
		case 404:
			return false;
		}
		return false;
	}

	/**
	 * Delete a synonym list.
	 * 
	 * @param indexName
	 *            The name of the index.
	 * @param listName
	 *            The name of the list.
	 * @throws IOException
	 *             if any IO error occurs
	 * @throws URISyntaxException
	 *             if the URI is not valid
	 */
	public void deleteSynonymsList(String indexName, String listName)
			throws IOException, URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl("index/", indexName,
				"/synonyms/", listName);
		Request request = Request.Delete(uriBuilder.build());
		HttpResponse response = client.execute(request, null, null);
		HttpUtils.checkStatusCodes(response, 200);
	}

	/**
	 * Create a list of stopwords
	 * 
	 * @param indexName
	 *            The name of the index
	 * @param listName
	 *            Name of the list
	 * @param stopwordsList
	 *            List of synonyms, one set of synonyms by line, raw text
	 * @throws IOException
	 *             if any IO error occurs
	 * @throws URISyntaxException
	 *             if the URI is not valid
	 */
	public void createStopWordsList(String indexName, String listName,
			String stopwordsList) throws IOException, URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl("index/", indexName,
				"/stopwords/", listName);
		Request request = Request.Put(uriBuilder.build());
		HttpResponse response = client.execute(request, stopwordsList, null);
		HttpUtils.checkStatusCodes(response, 200);
	}

	/**
	 * Check if a list of stopwords exists.
	 * 
	 * @param indexName
	 *            The name of the index.
	 * @param listName
	 *            The name of the list.
	 * @return true if the analyzer exists, false if not.
	 * @throws IOException
	 *             if any IO error occurs
	 * @throws URISyntaxException
	 *             if the URI is not valid
	 */
	public boolean checkStopWordsListExists(String indexName, String listName)
			throws IOException, URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl("index/", indexName,
				"/stopwords/", listName);
		Request request = Request.Head(uriBuilder.build());
		HttpResponse response = client.execute(request, null, null);
		switch (HttpUtils.checkStatusCodes(response, 200, 404)) {
		case 200:
			return true;
		case 404:
			return false;
		}
		return false;
	}

	/**
	 * Delete a stopwords list.
	 * 
	 * @param indexName
	 *            The name of the index.
	 * @param listName
	 *            The name of the list.
	 * @throws IOException
	 *             if any IO error occurs
	 * @throws URISyntaxException
	 *             if the URI is not valid
	 */
	public void deleteStopWordsList(String indexName, String listName)
			throws IOException, URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl("index/", indexName,
				"/stopwords/", listName);
		Request request = Request.Delete(uriBuilder.build());
		HttpResponse response = client.execute(request, null, null);
		HttpUtils.checkStatusCodes(response, 200);
	}
}
