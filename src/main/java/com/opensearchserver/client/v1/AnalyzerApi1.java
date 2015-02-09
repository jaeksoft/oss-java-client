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
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import com.opensearchserver.client.JsonClient1;
import com.opensearchserver.client.common.AbstractApi;
import com.opensearchserver.client.common.JsonClientAbstract;
import com.opensearchserver.client.common.LanguageEnum;
import com.opensearchserver.client.common.analyzer.AnalyzerItem;
import com.opensearchserver.utils.HttpUtils;

public class AnalyzerApi1 extends AbstractApi<JsonClientAbstract> {

	public AnalyzerApi1(JsonClient1 client) {
		super(client);
	}

	/**
	 * Check if an analyzer exists in an index.
	 * 
	 * @param indexName
	 *            The name of the index.
	 * @param analyzerName
	 *            The name of the analyzer.
	 * @param analyzerLang
	 *            Lang of the analyzer.
	 * @return true if the analyzer exists, false if not.
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public boolean checkAnalyzerExists(String indexName, String analyzerName,
			LanguageEnum analyzerLang) throws IOException, URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl("index/", indexName,
				"/analyzer/", analyzerName, "/lang/", analyzerLang.toString());
		Request request = Request.Get(uriBuilder.build());
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
	 * Create an analyzer
	 * 
	 * @param indexName
	 *            The name of the index
	 * @param analyzer
	 *            The analyzer to create
	 * @param analyzerName
	 *            Name of the analyzer to create
	 * @param analyzerLang
	 *            Lang of the analyzer to create
	 * @throws ClientProtocolException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void createAnalyzer(String indexName, AnalyzerItem analyzer,
			String analyzerName, LanguageEnum analyzerLang)
			throws ClientProtocolException, UnsupportedEncodingException,
			IOException, URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl("index/", indexName,
				"/analyzer/", analyzerName, "/lang/", analyzerLang.toString());
		Request request = Request.Put(uriBuilder.build());
		HttpResponse response = client.execute(request, analyzer, null);
		HttpUtils.checkStatusCodes(response, 200);
	}

}
