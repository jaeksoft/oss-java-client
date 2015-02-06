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
import com.opensearchserver.client.common.CommonResult;
import com.opensearchserver.client.v1.replication.ReplicationItem;
import com.opensearchserver.client.v1.replication.ReplicationResult;

/**
 * @version For OpenSearchServer v1.5.x
 */
public class ReplicationApi1 extends AbstractApi<JsonClient1> {

	public ReplicationApi1(JsonClient1 client) {
		super(client);
	}

	/**
	 * Retrieve a replication item
	 * 
	 * @param indexName
	 * @param replicationName
	 * @return a replication item
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public ReplicationResult getReplication(String indexName,
			String replicationName) throws IOException, URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl("index/", indexName,
				"/replication").addParameter("name", replicationName);
		Request request = Request.Get(uriBuilder.build());
		return client
				.execute(request, null, null, ReplicationResult.class, 200);
	}

	/**
	 * Create or update a replication item
	 * 
	 * @param indexName
	 * @param replicationItem
	 * @return the status
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public CommonResult createUpdateReplication(String indexName,
			ReplicationItem replicationItem) throws IOException,
			URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl("index/", indexName,
				"/replication");
		Request request = Request.Put(uriBuilder.build());
		return client.execute(request, replicationItem, null,
				CommonResult.class, 200, 201);
	}

	/**
	 * Start a replication process
	 * 
	 * @param indexName
	 * @param replicationName
	 * @return the status
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public CommonResult executeReplication(String indexName,
			String replicationName) throws IOException, URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl("index/", indexName,
				"/replication/run").addParameter("name", replicationName);
		Request request = Request.Put(uriBuilder.build());
		return client.execute(request, null, null, CommonResult.class, 200);
	}

	/**
	 * Delete a replication item
	 * 
	 * @param indexName
	 * @param replicationName
	 * @return the status
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public CommonResult deleteReplication(String indexName,
			String replicationName) throws IOException, URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl("index/", indexName,
				"/replication").addParameter("name", replicationName);
		Request request = Request.Delete(uriBuilder.build());
		return client.execute(request, null, null, CommonResult.class, 200);
	}

}
