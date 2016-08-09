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
import com.opensearchserver.client.v1.field.ResultField;
import com.opensearchserver.client.v1.field.ResultFieldList;
import com.opensearchserver.client.v1.field.SchemaField;
import com.qwazr.utils.http.HttpRequest;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class FieldApi1 extends AbstractApi<JsonClient1> {

	public FieldApi1(JsonClient1 client) {
		super(client);
	}

	/**
	 * Get one field
	 *
	 * @param indexName The index name
	 * @param fieldName The field name
	 * @return The field details
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public ResultField getField(String indexName, String fieldName) throws IOException, URISyntaxException {
		final URIBuilder uriBuilder = client.getBaseUrl("index/", indexName, "/field/", fieldName);
		final HttpRequest request = HttpRequest.Get(uriBuilder.build());
		return client.executeJson(request, null, null, ResultField.class, validator_200_404);

	}

	/**
	 * Get the field list
	 *
	 * @param indexName The index name
	 * @return A list of fields
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public ResultFieldList getFields(String indexName) throws IOException, URISyntaxException {
		final URIBuilder uriBuilder = client.getBaseUrl("index/", indexName, "/field");
		final HttpRequest request = HttpRequest.Get(uriBuilder.build());
		return client.executeJson(request, null, null, ResultFieldList.class, validator_200);
	}

	/**
	 * Create or replace a field
	 *
	 * @param indexName The name of the index
	 * @param field     The field to create or update
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public void setField(String indexName, SchemaField field) throws IOException, URISyntaxException {
		final URIBuilder uriBuilder = client.getBaseUrl("index/", indexName, "/field/" + field.name);
		final HttpRequest request = HttpRequest.Put(uriBuilder.build());
		client.execute(request, field, null, validator_200);
	}

	/**
	 * Create or update a set of fields
	 *
	 * @param indexName The name of the index
	 * @param fields    A list of fields
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public void setFields(String indexName, List<SchemaField> fields) throws IOException, URISyntaxException {
		final URIBuilder uriBuilder = client.getBaseUrl("index/", indexName, "/field");
		final HttpRequest request = HttpRequest.Put(uriBuilder.build());
		client.execute(request, fields, null, validator_200);
	}

	/**
	 * Delete a field from a schema
	 *
	 * @param indexName The name of the index
	 * @param fieldName The name of the field to delete
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public void deleteField(String indexName, String fieldName) throws IOException, URISyntaxException {
		final URIBuilder uriBuilder = client.getBaseUrl("index/", indexName, "/field/", fieldName);
		final HttpRequest request = HttpRequest.Delete(uriBuilder.build());
		client.execute(request, null, null, validator_200_404);
	}

	/**
	 * Set the default and unique field
	 *
	 * @param indexName    The name of the index
	 * @param defaultField The name of the default field
	 * @param uniqueField  The name of the unique field
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public void setDefaultUniqueField(String indexName, String defaultField, String uniqueField)
			throws IOException, URISyntaxException {
		final URIBuilder uriBuilder = client.getBaseUrl("index/", indexName, "/field")
				.addParameter("default", defaultField)
				.addParameter("unique", uniqueField);
		final HttpRequest request = HttpRequest.Post(uriBuilder.build());
		client.execute(request, null, null, validator_200);
	}
}
