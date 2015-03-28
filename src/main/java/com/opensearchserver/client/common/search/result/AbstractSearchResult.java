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
package com.opensearchserver.client.common.search.result;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.opensearchserver.client.common.CommonResult;

@XmlRootElement(name = "result")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(Include.NON_EMPTY)
public abstract class AbstractSearchResult extends CommonResult {

	@XmlElement
	public String query;

	@XmlAttribute
	public Integer rows;

	@XmlAttribute
	public Long start;

	@XmlAttribute
	public Long numFound;

	@XmlAttribute
	public Long time;

	@XmlAttribute
	public Long collapsedDocCount;

	@XmlAttribute
	public Float maxScore;

	public AbstractSearchResult() {
		query = null;
		rows = null;
		start = null;
		numFound = null;
		time = null;
		collapsedDocCount = null;
		maxScore = null;
	}

	/**
	 * @param query
	 *            the query to set
	 * @return this instance
	 */
	public AbstractSearchResult setQuery(String query) {
		this.query = query;
		return this;
	}

	/**
	 * @param rows
	 *            the rows to set
	 * @return this instance
	 */
	public AbstractSearchResult setRows(Integer rows) {
		this.rows = rows;
		return this;
	}

	/**
	 * @param start
	 *            the start to set
	 * @return this instance
	 */
	public AbstractSearchResult setStart(Long start) {
		this.start = start;
		return this;
	}

	/**
	 * @param numFound
	 *            the numFound to set
	 * @return this instance
	 */
	public AbstractSearchResult setNumFound(Long numFound) {
		this.numFound = numFound;
		return this;
	}

	/**
	 * @param time
	 *            the time to set
	 * @return this instance
	 */
	public AbstractSearchResult setTime(Long time) {
		this.time = time;
		return this;
	}

	/**
	 * @param collapsedDocCount
	 *            the collapsedDocCount to set
	 * @return this instance
	 */
	public AbstractSearchResult setCollapsedDocCount(Long collapsedDocCount) {
		this.collapsedDocCount = collapsedDocCount;
		return this;
	}

	/**
	 * @param maxScore
	 *            the maxScore to set
	 * @return this instance
	 */
	public AbstractSearchResult setMaxScore(Float maxScore) {
		this.maxScore = maxScore;
		return this;
	}

}
