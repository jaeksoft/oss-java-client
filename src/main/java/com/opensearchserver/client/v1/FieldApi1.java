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
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import com.opensearchserver.client.JsonClient1;
import com.opensearchserver.client.common.AbstractApi;
import com.opensearchserver.client.common.JsonClientAbstract;
import com.opensearchserver.client.v1.field.ResultField;
import com.opensearchserver.client.v1.field.ResultFieldList;
import com.opensearchserver.client.v1.field.SchemaField;
import com.opensearchserver.utils.HttpUtils;
import com.opensearchserver.utils.LinkUtils;

public class FieldApi1 extends AbstractApi<JsonClientAbstract> {

	public FieldApi1(JsonClient1 client) {
		super(client);
	}

	/**
	 * Get one field
	 * 
	 * @param indexName
	 *            The index name
	 * @param fieldName
	 *            The field name
	 * @return The field details
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public ResultField getField(String indexName, String fieldName)
			throws IOException, URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl("index/",
				LinkUtils.UTF8_URL_Encode(indexName), "/field/",
				LinkUtils.UTF8_URL_Encode(fieldName));
		Request request = Request.Get(uriBuilder.build());
		return client.execute(request, null, null, ResultField.class, 200, 404);

	}

	/**
	 * Get the field list
	 * 
	 * @param indexName
	 *            The index name
	 * @return A list of fields
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public ResultFieldList getFields(String indexName) throws IOException,
			URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl("index/",
				LinkUtils.UTF8_URL_Encode(indexName), "/field");
		Request request = Request.Get(uriBuilder.build());
		return client.execute(request, null, null, ResultFieldList.class, 200);
	}

	/**
	 * Create or replace a field
	 * 
	 * @param indexName
	 *            The name of the index
	 * @param json
	 *            A JSON structure defining the fields
	 * @throws ClientProtocolException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void setField(String indexName, SchemaField field)
			throws IOException, URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl("index/",
				LinkUtils.UTF8_URL_Encode(indexName),
				"/field/" + LinkUtils.UTF8_URL_Encode(field.name));
		Request request = Request.Put(uriBuilder.build());
		HttpResponse response = client.execute(request, field, null);
		HttpUtils.checkStatusCodes(response.getStatusLine(), 200);
	}

	/**
	 * Create or update a set of fields
	 * 
	 * @param indexName
	 *            The name of the index
	 * @param fields
	 *            A list of fields
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void setFields(String indexName, List<SchemaField> fields)
			throws IOException, URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl("index/",
				LinkUtils.UTF8_URL_Encode(indexName), "/field");
		Request request = Request.Put(uriBuilder.build());
		HttpResponse response = client.execute(request, fields, null);
		HttpUtils.checkStatusCodes(response.getStatusLine(), 200);
	}

	/**
	 * Delete a field from a schema
	 * 
	 * @param indexName
	 *            The name of the index
	 * @param fieldName
	 *            The name of the field to delete
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void deleteField(String indexName, String fieldName)
			throws IOException, URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl("index/",
				LinkUtils.UTF8_URL_Encode(indexName), "/field/",
				LinkUtils.UTF8_URL_Encode(fieldName));
		Request request = Request.Delete(uriBuilder.build());
		HttpResponse response = client.execute(request, null, null);
		HttpUtils.checkStatusCodes(response.getStatusLine(), 200, 404);
	}

	/**
	 * Set the default and unique field
	 * 
	 * @param indexName
	 *            The name of the index
	 * @param defaultField
	 *            The name of the default field
	 * @param uniqueField
	 *            The name of the unique field
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void setDefaultUniqueField(String indexName, String defaultField,
			String uniqueField) throws IOException, URISyntaxException {
		URIBuilder uriBuilder = client
				.getBaseUrl("index/", LinkUtils.UTF8_URL_Encode(indexName),
						"/field").addParameter("default", defaultField)
				.addParameter("unique", uniqueField);
		Request request = Request.Post(uriBuilder.build());
		HttpResponse response = client.execute(request, null, null);
		HttpUtils.checkStatusCodes(response.getStatusLine(), 200);
	}
}
