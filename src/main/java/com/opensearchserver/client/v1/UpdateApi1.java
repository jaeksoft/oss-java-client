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
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import com.opensearchserver.client.JsonClient1;
import com.opensearchserver.client.common.AbstractApi;
import com.opensearchserver.client.common.LanguageEnum;
import com.opensearchserver.client.common.update.DocumentUpdate;
import com.qwazr.utils.http.HttpUtils;

public class UpdateApi1 extends AbstractApi<JsonClient1> {

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
	 *             if any IO error occurs
	 * @throws URISyntaxException
	 *             if the URI is not valid
	 */
	public void deleteDocumentsByFieldValue(String indexName, String fieldName,
			List<String> values) throws IOException, URISyntaxException {
		StringBuilder deleteValues = new StringBuilder();
		for (String value : values) {
			deleteValues.append('/');
			deleteValues.append(value);
		}
		URIBuilder uriBuilder = client.getBaseUrl("index/", indexName,
				"/document/" + fieldName + deleteValues.toString());
		Request request = Request.Delete(uriBuilder.build());
		HttpResponse response = client.execute(request, null, null);
		HttpUtils.checkStatusCodes(response, 200);
	}

	/**
	 * Create or update a set of documents
	 * 
	 * @param indexName
	 *            The name of the index
	 * @param documents
	 *            A list of documents
	 * @throws IOException
	 *             if any IO error occurs
	 * @throws URISyntaxException
	 *             if the URI is not valid
	 */
	public void updateDocuments(String indexName, List<DocumentUpdate> documents)
			throws IOException, URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl("index/", indexName,
				"/document");
		Request request = Request.Put(uriBuilder.build());
		HttpResponse response = client.execute(request, documents, null);
		HttpUtils.checkStatusCodes(response, 200);
	}

	/**
	 * @return a new empty list of DocumentUpdates
	 */
	public List<DocumentUpdate> getNewDocumentUpdateList() {
		return new ArrayList<DocumentUpdate>();
	}

	/**
	 * @param langNameOfCode
	 *            the language of the document
	 * @return a new DocumentUpdate record
	 */
	public DocumentUpdate getNewDocumentUpdate(String langNameOfCode) {
		return new DocumentUpdate().setLang(LanguageEnum
				.findByNameOrCode(langNameOfCode));
	}

}
