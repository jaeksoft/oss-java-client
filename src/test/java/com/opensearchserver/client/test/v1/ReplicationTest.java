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
package com.opensearchserver.client.test.v1;

import com.opensearchserver.client.common.index.TemplateEnum;
import com.opensearchserver.client.common.search.query.SearchFieldQuery;
import com.opensearchserver.client.v1.IndexApi1;
import com.opensearchserver.client.v1.ReplicationApi1;
import com.opensearchserver.client.v1.SearchApi1;
import com.opensearchserver.client.v1.replication.ReplicationItem;
import com.opensearchserver.client.v1.replication.ReplicationResult;
import com.opensearchserver.client.v1.replication.ReplicationType;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.net.URISyntaxException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReplicationTest extends AbstractTest {

	public static final String INDEX_NAME = "oss-java-client test replication";
	public static final String INDEX_NAME_REPL = "oss-java-client test replication repl";
	public static final String REPLICATION_ITEM_NAME = OSS_URL + "/" + INDEX_NAME_REPL;
	public static final Integer REPLICATION_TIME_OUT = 60;
	public static final String SEARCH_TEMPLATE_NAME = "search";

	public ReplicationTest() throws URISyntaxException {
		super();
	}

	@Test
	public void test001createIndexes() throws IOException, URISyntaxException {
		IndexApi1 indexApi = new IndexApi1(client);
		indexApi.createIndex(INDEX_NAME, TemplateEnum.WEB_CRAWLER);
		indexApi.createIndex(INDEX_NAME_REPL, TemplateEnum.EMPTY_INDEX);
	}

	@Test
	public void test010createReplication() throws IOException, URISyntaxException {
		ReplicationApi1 replicationApi = new ReplicationApi1(client);
		ReplicationItem replicationItem = new ReplicationItem().setReplicationType(ReplicationType.BACKUP_INDEX)
				.setRemoteUrl(OSS_URL)
				.setRemoteIndexName(INDEX_NAME_REPL)
				.setRemoteLogin(OSS_LOGIN)
				.setRemoteApiKey(OSS_APIKEY)
				.setSecTimeOut(REPLICATION_TIME_OUT);
		replicationApi.createUpdateReplication(INDEX_NAME, replicationItem);
	}

	@Test
	public void test020executeReplication() throws IOException, URISyntaxException {
		ReplicationApi1 replicationApi = new ReplicationApi1(client);
		replicationApi.executeReplication(INDEX_NAME, REPLICATION_ITEM_NAME);
	}

	@Test
	public void test030waitReplication() throws IOException, URISyntaxException, InterruptedException {
		ReplicationApi1 replicationApi = new ReplicationApi1(client);
		for (int i = 0; i < 60; i++) {
			ReplicationResult result = replicationApi.getReplication(INDEX_NAME, REPLICATION_ITEM_NAME);
			if (result.lastThread != null && !result.isActiveThread)
				return;
			Thread.sleep(2000);
		}
		Assert.fail("Time out");
	}

	@Test
	public void test040search() throws IOException, URISyntaxException {
		SearchApi1 searchApi1 = new SearchApi1(client);
		SearchFieldQuery query = (SearchFieldQuery) new SearchFieldQuery().setQuery("test");
		searchApi1.executeSearchFieldTemplate(INDEX_NAME_REPL, SEARCH_TEMPLATE_NAME, query);
	}

	@Test
	public void test500deleteReplication() throws IOException, URISyntaxException {
		ReplicationApi1 replicationApi = new ReplicationApi1(client);
		replicationApi.deleteReplication(INDEX_NAME, REPLICATION_ITEM_NAME);
	}

	@Test
	public void test999deleteIndexes() throws IOException, URISyntaxException {
		IndexApi1 indexApi = new IndexApi1(client);
		indexApi.deleteIndex(INDEX_NAME);
		indexApi.deleteIndex(INDEX_NAME_REPL);
	}
}
