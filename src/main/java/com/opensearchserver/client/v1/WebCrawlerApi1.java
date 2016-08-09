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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.opensearchserver.client.JsonClient1;
import com.opensearchserver.client.common.AbstractApi;
import com.opensearchserver.client.common.CommonListResult;
import com.opensearchserver.client.common.CommonResult;
import com.opensearchserver.client.v1.search.FieldValueList1;
import com.qwazr.utils.http.HttpRequest;
import org.apache.http.client.utils.URIBuilder;

import javax.xml.bind.annotation.XmlTransient;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class WebCrawlerApi1 extends AbstractApi<JsonClient1> {

	public WebCrawlerApi1(JsonClient1 client) {
		super(client);
	}

	@XmlTransient
	@JsonIgnore
	public final static TypeReference<CommonListResult<List<FieldValueList1>>> LISTCRAWL_TYPEREF =
			new TypeReference<CommonListResult<List<FieldValueList1>>>() {
			};

	/**
	 * Crawl an URL and return data
	 *
	 * @param indexName The name of the index
	 * @param url       The URL to crawl
	 * @param msTimeOut The timeout in milliseconds *
	 * @return the result of the crawl
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public CommonListResult<List<FieldValueList1>> crawlWithData(String indexName, String url, Integer msTimeOut)
			throws IOException, URISyntaxException {
		final URIBuilder uriBuilder = client.getBaseUrl("index/", indexName, "/crawler/web/crawl")
				.addParameter("url", url)
				.addParameter("returnData", "true");
		final HttpRequest request = HttpRequest.Get(uriBuilder.build());
		return client.executeJson(request, null, msTimeOut, LISTCRAWL_TYPEREF, validator_200);
	}

	/**
	 * Crawl an URL
	 *
	 * @param indexName The name of the index
	 * @param url       The URL to crawl
	 * @param msTimeOut The timeout in milliseconds
	 * @return the status of the crawl
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public CommonResult crawl(String indexName, String url, Integer msTimeOut) throws IOException, URISyntaxException {
		final URIBuilder uriBuilder = client.getBaseUrl("index/", indexName, "/crawler/web/crawl")
				.addParameter("url", url)
				.addParameter("returnData", "false");
		final HttpRequest request = HttpRequest.Get(uriBuilder.build());
		return client.executeJson(request, null, msTimeOut, CommonResult.class, validator_200);
	}

	/**
	 * Enable or disable pattern inclusion and exclusion
	 *
	 * @param indexName       The name of the index
	 * @param inclusionStatus Enable or disable inclusion list
	 * @param exclusionStatus Enable or disable inclusion list
	 * @return the result of the call
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public CommonResult setPatternStatus(String indexName, Boolean inclusionStatus, Boolean exclusionStatus)
			throws IOException, URISyntaxException {
		final URIBuilder uriBuilder = client.getBaseUrl("index/", indexName, "/crawler/web/patterns/status");
		if (inclusionStatus != null)
			uriBuilder.addParameter("inclusion", inclusionStatus.toString());
		if (exclusionStatus != null)
			uriBuilder.addParameter("exclusion", exclusionStatus.toString());
		final HttpRequest request = HttpRequest.Put(uriBuilder.build());
		return client.executeJson(request, null, null, CommonResult.class, validator_200);
	}
}
