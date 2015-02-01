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

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import com.opensearchserver.client.JsonClient1;
import com.opensearchserver.client.common.AbstractApi;
import com.opensearchserver.client.common.search.query.DocumentsQuery;
import com.opensearchserver.client.v1.search.DocumentsResult1;
import com.opensearchserver.utils.LinkUtils;

/**
 * @version For OpenSearchServer v1.5.x
 */
public class DocumentApi1 extends AbstractApi<JsonClient1> {

	public DocumentApi1(JsonClient1 client) {
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
	 * @throws URISyntaxException
	 */
	public DocumentsResult1 documentsSearch(String indexName,
			DocumentsQuery query) throws IOException, URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl("index/",
				LinkUtils.UTF8_URL_Encode(indexName), "/documents");
		Request request = Request.Post(uriBuilder.build());
		return client
				.execute(request, query, null, DocumentsResult1.class, 200);
	}

}
