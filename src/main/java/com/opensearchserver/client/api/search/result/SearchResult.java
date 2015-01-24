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
package com.opensearchserver.client.api.search.result;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.opensearchserver.client.api.CommonResult;
import com.opensearchserver.client.api.search.result.DocumentResult;
import com.opensearchserver.client.api.search.result.FacetResult;

@XmlRootElement(name = "result")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(Include.NON_EMPTY)
public class SearchResult extends CommonResult {

	@XmlElement(name = "document")
	public List<DocumentResult> documents;

	@XmlElement(name = "facet")
	public List<FacetResult> facets;

	@XmlElement
	public String query;

	@XmlAttribute
	public Integer rows;

	@XmlAttribute
	public Integer start;

	@XmlAttribute
	public Long numFound;

	@XmlAttribute
	public Long time;

	@XmlAttribute
	public Long collapsedDocCount;

	@XmlAttribute
	public Float maxScore;

	public SearchResult() {
		documents = null;
		query = null;
		facets = null;
		rows = null;
		start = null;
		numFound = null;
		time = null;
		collapsedDocCount = null;
		maxScore = null;
	}

	/**
	 * @param documents
	 *            the documents to set
	 */
	public SearchResult addDocument(DocumentResult document) {
		if (documents == null)
			documents = new ArrayList<DocumentResult>(1);
		documents.add(document);
		return this;
	}

	/**
	 * @param facets
	 *            the facets to set
	 */
	public SearchResult addFacet(FacetResult facet) {
		if (facets == null)
			facets = new ArrayList<FacetResult>(1);
		facets.add(facet);
		return this;
	}

	/**
	 * @param query
	 *            the query to set
	 */
	public SearchResult setQuery(String query) {
		this.query = query;
		return this;
	}

	/**
	 * @param rows
	 *            the rows to set
	 */
	public SearchResult setRows(Integer rows) {
		this.rows = rows;
		return this;
	}

	/**
	 * @param start
	 *            the start to set
	 */
	public SearchResult setStart(Integer start) {
		this.start = start;
		return this;
	}

	/**
	 * @param numFound
	 *            the numFound to set
	 */
	public SearchResult setNumFound(Long numFound) {
		this.numFound = numFound;
		return this;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public SearchResult setTime(Long time) {
		this.time = time;
		return this;
	}

	/**
	 * @param collapsedDocCount
	 *            the collapsedDocCount to set
	 */
	public SearchResult setCollapsedDocCount(Long collapsedDocCount) {
		this.collapsedDocCount = collapsedDocCount;
		return this;
	}

	/**
	 * @param maxScore
	 *            the maxScore to set
	 */
	public SearchResult setMaxScore(Float maxScore) {
		this.maxScore = maxScore;
		return this;
	}

}
