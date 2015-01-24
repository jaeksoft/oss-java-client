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
import com.opensearchserver.client.api.LanguageEnum;
import com.opensearchserver.client.api.search.query.filter.AbstractFilter;
import com.opensearchserver.client.api.search.query.filter.GeoFilter;
import com.opensearchserver.client.api.search.query.filter.MirrorAndFilter;
import com.opensearchserver.client.api.search.query.filter.QueryFilter;
import com.opensearchserver.client.api.search.query.filter.RelativeDateFilter;
import com.opensearchserver.client.api.search.query.filter.RequestTemplateFilter;
import com.opensearchserver.client.api.search.query.filter.TermFilter;

@JsonInclude(Include.NON_EMPTY)
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class SearchQueryAbstract extends QueryAbstract {

	public static enum OperatorEnum {
		AND, OR;
	}

	public String query;
	public Integer start;
	public Integer rows;
	public LanguageEnum lang;
	public OperatorEnum operator;
	public CollapsingParameters collapsing;
	public GeoParameters geo;
	public Boolean emptyReturnsAll;

	@XmlElements({
			@XmlElement(name = "QueryFilter", type = QueryFilter.class),
			@XmlElement(name = "TermFilter", type = TermFilter.class),
			@XmlElement(name = "GeoFilter", type = GeoFilter.class),
			@XmlElement(name = "RelativeDateFilter", type = RelativeDateFilter.class),
			@XmlElement(name = "MirrorAndFilter", type = MirrorAndFilter.class),
			@XmlElement(name = "RequestTemplateFilter", type = RequestTemplateFilter.class) })
	public List<AbstractFilter> filters;
	public OperatorEnum filterOperator;

	public List<SortField> sorts;
	public List<String> returnedFields;
	public List<SnippetField> snippets;
	public List<FacetField> facets;
	public List<JoinItem> joins;
	public List<Scoring> scorings;
	public List<BoostingQuery> boostingQueries;
	public Boolean enableLog;
	public List<String> customLogs;

	public SearchQueryAbstract() {
		query = null;
		start = null;
		rows = null;
		lang = null;
		operator = null;
		collapsing = null;
		geo = null;
		filters = null;
		filterOperator = null;
		sorts = null;
		returnedFields = null;
		snippets = null;
		facets = null;
		joins = null;
		scorings = null;
		boostingQueries = null;
		enableLog = null;
		customLogs = null;
		emptyReturnsAll = null;
	}

	/**
	 * @param query
	 *            the query to set
	 */
	@JsonIgnore
	@XmlTransient
	SearchQueryAbstract setQuery(String query) {
		this.query = query;
		return this;
	}

	/**
	 * @param start
	 *            the start to set
	 */
	@JsonIgnore
	@XmlTransient
	public SearchQueryAbstract setStart(Integer start) {
		this.start = start;
		return this;
	}

	/**
	 * @param rows
	 *            the rows to set
	 */
	@JsonIgnore
	@XmlTransient
	public SearchQueryAbstract setRows(Integer rows) {
		this.rows = rows;
		return this;
	}

	/**
	 * @param lang
	 *            the lang to set
	 */
	@JsonIgnore
	@XmlTransient
	public SearchQueryAbstract setLang(LanguageEnum lang) {
		this.lang = lang;
		return this;
	}

	/**
	 * @param operator
	 *            the operator to set
	 */
	@JsonIgnore
	@XmlTransient
	public SearchQueryAbstract setOperator(OperatorEnum operator) {
		this.operator = operator;
		return this;
	}

	/**
	 * @param collapsing
	 *            the collapsing to set
	 */
	@JsonIgnore
	@XmlTransient
	public SearchQueryAbstract setCollapsing(CollapsingParameters collapsing) {
		this.collapsing = collapsing;
		return this;
	}

	/**
	 * @param geo
	 *            the geo to set
	 */
	@JsonIgnore
	@XmlTransient
	public SearchQueryAbstract setGeoParameters(GeoParameters geo) {
		this.geo = geo;
		return this;
	}

	/**
	 * @param emptyReturnsAll
	 *            the emptyReturnsAll to set
	 */
	@JsonIgnore
	@XmlTransient
	public SearchQueryAbstract setEmptyReturnsAll(Boolean emptyReturnsAll) {
		this.emptyReturnsAll = emptyReturnsAll;
		return this;
	}

	/**
	 * @param filters
	 *            the filters to set
	 */
	@JsonIgnore
	@XmlTransient
	public SearchQueryAbstract addFilter(AbstractFilter filter) {
		if (filters == null)
			filters = new ArrayList<AbstractFilter>(1);
		filters.add(filter);
		return this;
	}

	/**
	 * @param filterOperator
	 *            the filterOperator to set
	 */
	@JsonIgnore
	@XmlTransient
	public SearchQueryAbstract setFilterOperator(OperatorEnum filterOperator) {
		this.filterOperator = filterOperator;
		return this;
	}

	/**
	 * @param sorts
	 *            the sorts to set
	 */
	@JsonIgnore
	@XmlTransient
	public SearchQueryAbstract addSort(SortField sortField) {
		if (sorts == null)
			sorts = new ArrayList<SortField>(1);
		sorts.add(sortField);
		return this;
	}

	/**
	 * @param returnedFields
	 *            the returnedFields to set
	 */
	@JsonIgnore
	@XmlTransient
	public SearchQueryAbstract setReturnedFields(List<String> returnedFields) {
		this.returnedFields = returnedFields;
		return this;
	}

	/**
	 * @param snippets
	 *            the snippets to set
	 */
	@JsonIgnore
	@XmlTransient
	public SearchQueryAbstract addSnippet(SnippetField snippet) {
		if (snippets == null)
			snippets = new ArrayList<SnippetField>(1);
		snippets.add(snippet);
		return this;
	}

	/**
	 * @param facets
	 *            the facets to set
	 */
	@JsonIgnore
	@XmlTransient
	public SearchQueryAbstract setFacets(FacetField facet) {
		if (facets == null)
			facets = new ArrayList<FacetField>(1);
		facets.add(facet);
		return this;
	}

	/**
	 * @param joins
	 *            the joins to set
	 */
	@JsonIgnore
	@XmlTransient
	public SearchQueryAbstract addJoin(JoinItem join) {
		if (joins == null)
			joins = new ArrayList<JoinItem>(1);
		joins.add(join);
		return this;
	}

	/**
	 * @param scorings
	 *            the scorings to set
	 */
	@JsonIgnore
	@XmlTransient
	public SearchQueryAbstract addScoring(Scoring scoring) {
		if (scorings == null)
			scorings = new ArrayList<Scoring>(1);
		scorings.add(scoring);
		return this;
	}

	/**
	 * @param boostingQueries
	 *            the boostingQueries to set
	 */
	@JsonIgnore
	@XmlTransient
	public SearchQueryAbstract addBoostingQuery(BoostingQuery boostingQuery) {
		if (boostingQueries == null)
			boostingQueries = new ArrayList<BoostingQuery>(1);
		boostingQueries.add(boostingQuery);
		return this;
	}

	/**
	 * @param enableLog
	 *            the enableLog to set
	 */
	@JsonIgnore
	@XmlTransient
	public SearchQueryAbstract setEnableLog(Boolean enableLog) {
		this.enableLog = enableLog;
		return this;
	}

	/**
	 * @param customLogs
	 *            the customLogs to set
	 */
	@JsonIgnore
	@XmlTransient
	public SearchQueryAbstract addCustomLog(String customLog) {
		if (customLogs == null)
			customLogs = new ArrayList<String>(1);
		customLogs.add(customLog);
		return this;
	}

}
