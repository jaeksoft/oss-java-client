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
package com.opensearchserver.client.v1.api.crawler.web;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.opensearchserver.client.api.AbstractApi;
import com.opensearchserver.client.api.CommonListResult;
import com.opensearchserver.client.api.CommonResult;
import com.opensearchserver.client.v1.JsonClient1;
import com.opensearchserver.client.v1.api.search.result.FieldValueList;
import com.opensearchserver.utils.LinkUtils;

public class WebCrawlerApi1 extends AbstractApi<JsonClient1> {

	public WebCrawlerApi1(JsonClient1 client) {
		super(client);
	}

	@XmlTransient
	@JsonIgnore
	public final static TypeReference<CommonListResult<List<FieldValueList>>> LISTCRAWL_TYPEREF = new TypeReference<CommonListResult<List<FieldValueList>>>() {
	};

	/**
	 * Crawl an URL and return data
	 * 
	 * @param indexName
	 *            The name of the index
	 * @param url
	 *            The URL to crawl
	 * @param msTimeOut
	 *            The timeout in milliseconds * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public CommonListResult<List<FieldValueList>> crawlWithData(
			String indexName, String url, Integer msTimeOut)
			throws ClientProtocolException, IOException, URISyntaxException {
		URIBuilder uriBuilder = client
				.getBaseUrl("index/", LinkUtils.UTF8_URL_Encode(indexName),
						"/crawler/web/crawl").addParameter("url", url)
				.addParameter("returnData", "true");
		Request request = Request.Get(uriBuilder.build());
		return client.execute(request, null, msTimeOut, LISTCRAWL_TYPEREF, 200);
	}

	/**
	 * Crawl an URL
	 * 
	 * @param indexName
	 *            The name of the index
	 * @param url
	 *            The URL to crawl
	 * @param msTimeOut
	 *            The timeout in milliseconds
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public CommonResult crawl(String indexName, String url, Integer msTimeOut)
			throws ClientProtocolException, IOException, URISyntaxException {
		URIBuilder uriBuilder = client
				.getBaseUrl("index/", LinkUtils.UTF8_URL_Encode(indexName),
						"/crawler/web/crawl").addParameter("url", url)
				.addParameter("returnData", "false");
		Request request = Request.Get(uriBuilder.build());
		return client
				.execute(request, null, msTimeOut, CommonResult.class, 200);
	}

	/**
	 * Enable or disable pattern inclusion and exclusion
	 * 
	 * @param indexName
	 *            The name of the index
	 * @param inclusionStatus
	 *            Enable or disable inclusion list
	 * @param exclusionStatus
	 *            Enable or disable inclusion list
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public CommonResult setPatternStatus(String indexName,
			Boolean inclusionStatus, Boolean exclusionStatus)
			throws ClientProtocolException, IOException, URISyntaxException {
		URIBuilder uriBuilder = client.getBaseUrl("index/",
				LinkUtils.UTF8_URL_Encode(indexName),
				"/crawler/web/patterns/status");
		if (inclusionStatus != null)
			uriBuilder.addParameter("inclusion", inclusionStatus.toString());
		if (exclusionStatus != null)
			uriBuilder.addParameter("exclusion", exclusionStatus.toString());
		Request request = Request.Get(uriBuilder.build());
		return client.execute(request, null, null, CommonResult.class, 200);
	}
}
