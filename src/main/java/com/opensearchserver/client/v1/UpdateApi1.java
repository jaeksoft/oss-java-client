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
import com.opensearchserver.client.common.JsonClientAbstract;
import com.opensearchserver.client.common.update.DocumentUpdate;
import com.opensearchserver.utils.HttpUtils;
import com.opensearchserver.utils.LinkUtils;

public class UpdateApi1 extends AbstractApi<JsonClientAbstract> {

	public UpdateApi1(JsonClient1 client) {
		super(client);
	}

	/**
	 * Delete the documents which contains the value in the specified field.
	 * 
	 * @param indexName
	 *            The name of the index
	 * @param fieldName
	 *            The name of the field
	 * @param values
	 *            A list of value
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void deleteDocumentsByFieldValue(String indexName, String fieldName,
			List<String> values) throws IOException, URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl("index/",
				LinkUtils.UTF8_URL_Encode(indexName),
				"/document/" + LinkUtils.UTF8_URL_Encode(fieldName));
		Request request = Request.Delete(uriBuilder.build());
		HttpResponse response = client.execute(request, values, null);
		HttpUtils.checkStatusCodes(response.getStatusLine(), 200);
	}

	/**
	 * Create or update a set of documents
	 * 
	 * @param indexName
	 *            The name of the index
	 * @param documents
	 *            A list of documents
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void updateDocuments(String indexName, List<DocumentUpdate> documents)
			throws IOException, URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl("index/",
				LinkUtils.UTF8_URL_Encode(indexName), "/document");
		Request request = Request.Put(uriBuilder.build());
		HttpResponse response = client.execute(request, documents, null);
		HttpUtils.checkStatusCodes(response.getStatusLine(), 200);
	}

}
