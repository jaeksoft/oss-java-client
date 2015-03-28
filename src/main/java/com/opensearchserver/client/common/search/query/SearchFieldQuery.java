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
package com.opensearchserver.client.common.search.query;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(Include.NON_EMPTY)
public class SearchFieldQuery extends SearchQueryAbstract {

	public List<SearchField> searchFields;
	public Map<String, String> queryStringMap;

	public SearchFieldQuery() {
		searchFields = null;
		queryStringMap = null;
	}

	/**
	 * @param searchField
	 *            the SearchField to add
	 * @return this instance
	 */
	@JsonIgnore
	@XmlTransient
	public SearchFieldQuery addSearchField(SearchField searchField) {
		if (searchFields == null)
			searchFields = new ArrayList<SearchField>(1);
		searchFields.add(searchField);
		return this;
	}

	/**
	 * @param field
	 *            the name of the field
	 * @param value
	 *            the query value for this field
	 * @return this instance
	 */
	@JsonIgnore
	@XmlTransient
	public SearchFieldQuery setQueryStringMap(String field, String value) {
		if (queryStringMap == null)
			queryStringMap = new LinkedHashMap<String, String>();
		queryStringMap.put(field.intern(), value);
		return this;
	}

	/**
	 * @param searchFields
	 *            the searchFields to set
	 * @return this instance
	 */
	public SearchFieldQuery setSearchFields(List<SearchField> searchFields) {
		this.searchFields = searchFields;
		return this;
	}

	/**
	 * @param queryStringMap
	 *            the queryStringMap to set
	 * @return this instance
	 */
	public SearchFieldQuery setQueryStringMap(Map<String, String> queryStringMap) {
		this.queryStringMap = queryStringMap;
		return this;
	}
}
