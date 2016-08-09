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
import com.opensearchserver.client.common.LanguageEnum;
import com.opensearchserver.client.common.analyzer.AnalyzerItem;
import com.qwazr.utils.http.HttpRequest;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;

public class AnalyzerApi1 extends AbstractApi<JsonClient1> {

	public AnalyzerApi1(JsonClient1 client) {
		super(client);
	}

	/**
	 * Check if an analyzer exists in an index.
	 *
	 * @param indexName    The name of the index.
	 * @param analyzerName The name of the analyzer.
	 * @param analyzerLang Lang of the analyzer.
	 * @return true if the analyzer exists, false if not.
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public boolean checkAnalyzerExists(String indexName, String analyzerName, LanguageEnum analyzerLang)
			throws IOException, URISyntaxException {
		final URIBuilder uriBuilder =
				client.getBaseUrl("index/", indexName, "/analyzer/", analyzerName, "/lang/", analyzerLang.toString());
		final HttpRequest request = HttpRequest.Get(uriBuilder.build());
		return client.execute200True404False(request, null, null, validator_200_404);
	}

	/**
	 * Create an analyzer
	 *
	 * @param indexName    The name of the index
	 * @param analyzer     The analyzer to create
	 * @param analyzerName Name of the analyzer to create
	 * @param analyzerLang Lang of the analyzer to create
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public void createAnalyzer(String indexName, AnalyzerItem analyzer, String analyzerName, LanguageEnum analyzerLang)
			throws IOException, URISyntaxException {
		final URIBuilder uriBuilder =
				client.getBaseUrl("index/", indexName, "/analyzer/", analyzerName, "/lang/", analyzerLang.toString());
		final HttpRequest request = HttpRequest.Put(uriBuilder.build());
		client.execute(request, analyzer, null, validator_200);
	}

}
