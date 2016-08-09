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
import com.opensearchserver.client.common.index.TemplateEnum;
import com.qwazr.utils.http.HttpRequest;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;

public class IndexApi1 extends AbstractApi<JsonClient1> {

	public IndexApi1(JsonClient1 client) {
		super(client);
	}

	/**
	 * Create a new index
	 *
	 * @param indexName The name of the index
	 * @param template  The template
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public void createIndex(String indexName, TemplateEnum template) throws IOException, URISyntaxException {
		if (indexName == null || indexName.isEmpty())
			throw new IllegalArgumentException("The index name is missing.");
		final URIBuilder uriBuilder = client.getBaseUrl("index/", indexName, "/template/", template.name());
		final HttpRequest request = HttpRequest.Post(uriBuilder.build());
		client.execute(request, null, null, validator_200);
	}

	/**
	 * Check if the index exists.
	 *
	 * @param indexName The name of the index.
	 * @return true if the index exists, false if not.
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public boolean indexExists(String indexName) throws URISyntaxException, IOException {
		if (indexName == null || indexName.isEmpty())
			throw new IllegalArgumentException("The index name is missing.");
		final URIBuilder uriBuilder = client.getBaseUrl("index/", indexName);
		final HttpRequest request = HttpRequest.Get(uriBuilder.build());
		return client.execute200True404False(request, null, null, validator_200_404);
	}

	/**
	 * Delete index
	 *
	 * @param indexName The name of the index
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public void deleteIndex(String indexName) throws IOException, URISyntaxException {
		if (indexName == null || indexName.isEmpty())
			throw new IllegalArgumentException("The index name is missing.");
		final URIBuilder uriBuilder = client.getBaseUrl("index/", indexName);
		final HttpRequest request = HttpRequest.Delete(uriBuilder.build());
		client.execute(request, null, null, validator_200);
	}

}
