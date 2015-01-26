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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(Include.NON_EMPTY)
public class JoinItem {

	public static enum JoinTypeEnum {
		INNER, OUTER;
	}

	public String indexName;
	public String queryTemplate;
	public String queryString;
	public String localField;
	public String foreignField;
	public JoinTypeEnum type;
	public Boolean returnFields;
	public Boolean returnScores;
	public Boolean returnFacets;

	public JoinItem() {
		indexName = null;
		queryTemplate = null;
		queryString = null;
		localField = null;
		foreignField = null;
		type = null;
		returnFields = null;
		returnScores = null;
		returnFacets = null;
	}

	/**
	 * @param indexName
	 *            the indexName to set
	 */
	public JoinItem setIndexName(String indexName) {
		this.indexName = indexName;
		return this;
	}

	/**
	 * @param queryTemplate
	 *            the queryTemplate to set
	 */
	public JoinItem setQueryTemplate(String queryTemplate) {
		this.queryTemplate = queryTemplate;
		return this;
	}

	/**
	 * @param queryString
	 *            the queryString to set
	 */
	public JoinItem setQueryString(String queryString) {
		this.queryString = queryString;
		return this;
	}

	/**
	 * @param localField
	 *            the localField to set
	 */
	public JoinItem setLocalField(String localField) {
		this.localField = localField;
		return this;
	}

	/**
	 * @param foreignField
	 *            the foreignField to set
	 */
	public JoinItem setForeignField(String foreignField) {
		this.foreignField = foreignField;
		return this;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public JoinItem setType(JoinTypeEnum type) {
		this.type = type;
		return this;
	}

	/**
	 * @param returnFields
	 *            the returnFields to set
	 */
	public JoinItem setReturnFields(Boolean returnFields) {
		this.returnFields = returnFields;
		return this;
	}

	/**
	 * @param returnScores
	 *            the returnScores to set
	 */
	public JoinItem setReturnScores(Boolean returnScores) {
		this.returnScores = returnScores;
		return this;
	}

	/**
	 * @param returnFacets
	 *            the returnFacets to set
	 */
	public JoinItem setReturnFacets(Boolean returnFacets) {
		this.returnFacets = returnFacets;
		return this;
	}

}
