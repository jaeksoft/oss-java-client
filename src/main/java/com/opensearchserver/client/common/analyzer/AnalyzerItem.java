/**
 * Copyright 2014-2015 OpenSearchServer Inc.
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
package com.opensearchserver.client.common.analyzer;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class AnalyzerItem {

	public ClassFactoryItem queryTokenizer;
	public ClassFactoryItem indexTokenizer;
	public List<ClassFactoryItem> filters;

	public AnalyzerItem() {
		queryTokenizer = null;
		indexTokenizer = null;
		filters = null;
	}

	/**
	 * @param queryTokenizer
	 *            the queryTokenizer to set
	 */
	public AnalyzerItem setQueryTokenizer(ClassFactoryItem queryTokenizer) {
		this.queryTokenizer = queryTokenizer;
		return this;
	}

	/**
	 * @param indexTokenizer
	 *            the indexTokenizer to set
	 */
	public AnalyzerItem setIndexTokenizer(ClassFactoryItem indexTokenizer) {
		this.indexTokenizer = indexTokenizer;
		return this;
	}

	/**
	 * @param filters
	 *            the filters to set
	 */
	public AnalyzerItem setFilters(List<ClassFactoryItem> filters) {
		this.filters = filters;
		return this;
	}

	/**
	 * @param filter
	 *            the filter to add
	 * @return
	 */
	public AnalyzerItem addFilter(ClassFactoryItem filter) {
		if (filters == null)
			filters = new ArrayList<ClassFactoryItem>();
		return this;
	}

}
