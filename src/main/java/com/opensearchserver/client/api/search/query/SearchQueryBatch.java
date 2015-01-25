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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonInclude(Include.NON_EMPTY)
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchQueryBatch {

	public static enum QueryModeEnum {
		all, first
	}

	public QueryModeEnum mode;

	@XmlElements({
			@XmlElement(name = "SearchField", type = SearchFieldQuery.class),
			@XmlElement(name = "SearchPattern", type = SearchPatternQuery.class),
			@XmlElement(name = "SearchFieldTemplate", type = SearchFieldTemplateQuery.class),
			@XmlElement(name = "SearchPatternTemplate", type = SearchPatternTemplateQuery.class) })
	@JsonTypeInfo(use = Id.NAME, property = "type")
	@JsonSubTypes({
			@JsonSubTypes.Type(value = SearchFieldQuery.class, name = "SearchField"),
			@JsonSubTypes.Type(value = SearchPatternQuery.class, name = "SearchPattern"),
			@JsonSubTypes.Type(value = SearchFieldTemplateQuery.class, name = "SearchFieldTemplate"),
			@JsonSubTypes.Type(value = SearchPatternTemplateQuery.class, name = "SearchPatternTemplate") })
	public List<SearchQueryAbstract> queries;

	public SearchQueryBatch() {
		queries = null;
		mode = null;
	}

	@JsonIgnore
	@XmlTransient
	public SearchQueryBatch addQuery(SearchQueryAbstract query) {
		if (queries == null)
			queries = new ArrayList<SearchQueryAbstract>(1);
		queries.add(query);
		return this;
	}

	public SearchQueryBatch addQuery(List<SearchQueryAbstract> queries) {
		this.queries = queries;
		return this;
	}

	public SearchQueryBatch seMode(QueryModeEnum mode) {
		this.mode = mode;
		return this;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@JsonInclude(Include.NON_EMPTY)
	public static class SearchFieldTemplateQuery extends SearchFieldQuery {

		public String template;

		public SearchFieldTemplateQuery() {
			template = null;
		}

		public SearchFieldTemplateQuery setTemplate(String template) {
			this.template = template;
			return this;
		}
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@JsonInclude(Include.NON_EMPTY)
	public static class SearchPatternTemplateQuery extends SearchPatternQuery {

		public String template;

		public SearchPatternTemplateQuery() {
			template = null;
		}

		public SearchPatternTemplateQuery setTemplate(String template) {
			this.template = template;
			return this;
		}
	}

}
