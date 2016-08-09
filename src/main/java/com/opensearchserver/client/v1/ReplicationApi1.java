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
import com.opensearchserver.client.common.CommonResult;
import com.opensearchserver.client.v1.replication.ReplicationItem;
import com.opensearchserver.client.v1.replication.ReplicationResult;
import com.qwazr.utils.http.HttpRequest;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;

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
	 * @param indexName       the name of the index
	 * @param replicationName the name of the replication item
	 * @return a replication item
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public ReplicationResult getReplication(String indexName, String replicationName)
			throws IOException, URISyntaxException {
		final URIBuilder uriBuilder =
				client.getBaseUrl("index/", indexName, "/replication").addParameter("name", replicationName);
		final HttpRequest request = HttpRequest.Get(uriBuilder.build());
		return client.executeJson(request, null, null, ReplicationResult.class, validator_200);
	}

	/**
	 * Create or update a replication item
	 *
	 * @param indexName       the name of the index
	 * @param replicationItem the replication item
	 * @return the status
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public CommonResult createUpdateReplication(String indexName, ReplicationItem replicationItem)
			throws IOException, URISyntaxException {
		final URIBuilder uriBuilder = client.getBaseUrl("index/", indexName, "/replication");
		final HttpRequest request = HttpRequest.Put(uriBuilder.build());
		return client.executeJson(request, replicationItem, null, CommonResult.class, validator_200_201);
	}

	/**
	 * Start a replication process
	 *
	 * @param indexName       the name of the index
	 * @param replicationName the name of the replication item
	 * @return the status
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public CommonResult executeReplication(String indexName, String replicationName)
			throws IOException, URISyntaxException {
		final URIBuilder uriBuilder =
				client.getBaseUrl("index/", indexName, "/replication/run").addParameter("name", replicationName);
		final HttpRequest request = HttpRequest.Put(uriBuilder.build());
		return client.executeJson(request, null, null, CommonResult.class, validator_200);
	}

	/**
	 * Delete a replication item
	 *
	 * @param indexName       the name of the index
	 * @param replicationName the name of the replication item
	 * @return the status
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public CommonResult deleteReplication(String indexName, String replicationName)
			throws IOException, URISyntaxException {
		final URIBuilder uriBuilder =
				client.getBaseUrl("index/", indexName, "/replication").addParameter("name", replicationName);
		final HttpRequest request = HttpRequest.Delete(uriBuilder.build());
		return client.executeJson(request, null, null, CommonResult.class, validator_200);
	}

}
