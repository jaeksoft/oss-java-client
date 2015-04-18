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
package com.opensearchserver.client.test.v1;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;

import com.opensearchserver.client.JsonClient1;
import com.opensearchserver.client.common.search.query.SearchField;
import com.opensearchserver.client.common.search.query.SearchField.SearchFieldMode;
import com.opensearchserver.client.common.search.query.SearchFieldQuery;
import com.opensearchserver.client.common.search.query.SearchQueryAbstract.OperatorEnum;
import com.opensearchserver.client.v1.SearchApi1;
import com.opensearchserver.client.v1.search.SearchResult1;
import com.qwazr.utils.json.JsonMapper;

public class SearchFieldTest {

	@Test
	public void testSearchBooleanGroup() throws IOException, URISyntaxException {
		JsonClient1 client = new JsonClient1("http://localhost:8080", null,
				null, 50000);
		String queryGroup1 = "open search server";
		String queryGroup2 = "en";
		SearchApi1 searchApi = new SearchApi1(client);
		SearchFieldQuery searchFieldQuery = (SearchFieldQuery) new SearchFieldQuery()
				.setEmptyReturnsAll(false).setOperator(OperatorEnum.AND);

		// If the queryGroup1 is not empty, we add the required fields
		if (queryGroup1 != null && !queryGroup1.isEmpty()) {

			// Create the search fields, affected to the first boolean group
			searchFieldQuery.addSearchField(new SearchField()
					.setField("content").setMode(SearchFieldMode.PATTERN)
					.setBooleanGroup(1));

			searchFieldQuery.addSearchField(new SearchField()
					.setField("contentExact").setMode(SearchFieldMode.PATTERN)
					.setBooleanGroup(1));

			// We set the text query
			searchFieldQuery.setQueryStringMap("content", queryGroup1);
			searchFieldQuery.setQueryStringMap("contentExact", queryGroup1);

		}

		// If the queryGroup2 is not empty, we add the required fields
		if (queryGroup2 != null && !queryGroup2.isEmpty()) {

			// Create the search fields, affected to the first boolean group
			searchFieldQuery.addSearchField(new SearchField().setField("lang")
					.setMode(SearchFieldMode.PATTERN).setBooleanGroup(2));

			searchFieldQuery.setQueryStringMap("lang", queryGroup2);
		}

		System.out.println(JsonMapper.MAPPER
				.writeValueAsString(searchFieldQuery));
		SearchResult1 searchResult = searchApi.executeSearchField("web",
				searchFieldQuery);

		System.out.println(JsonMapper.MAPPER.writeValueAsString(searchResult));
	}
}
