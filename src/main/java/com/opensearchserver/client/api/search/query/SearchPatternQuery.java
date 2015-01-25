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
package com.opensearchserver.client.api.search.query;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(Include.NON_EMPTY)
public class SearchPatternQuery extends SearchQueryAbstract {

	public String patternSearchQuery;
	public String patternSnippetQuery;

	public SearchPatternQuery() {
		patternSearchQuery = null;
		patternSnippetQuery = null;
	}

	/**
	 * @param patternSearchQuery
	 *            the patternSearchQuery to set
	 */
	public SearchPatternQuery setPatternSearchQuery(String patternSearchQuery) {
		this.patternSearchQuery = patternSearchQuery;
		return this;
	}

	/**
	 * @param patternSnippetQuery
	 *            the patternSnippetQuery to set
	 */
	public SearchPatternQuery setPatternSnippetQuery(String patternSnippetQuery) {
		this.patternSnippetQuery = patternSnippetQuery;
		return this;
	}

}
