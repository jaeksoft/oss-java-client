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

@JsonInclude(Include.NON_EMPTY)
@XmlAccessorType(XmlAccessType.FIELD)
public class Scoring {

	public static enum ScoringTypeEnum {
		DISTANCE, FIELD_ORDER;
	}

	public Boolean ascending;
	public String fieldName;
	public Double weight;
	public ScoringTypeEnum type;

	public Scoring() {
		ascending = null;
		fieldName = null;
		weight = null;
		type = null;
	}

	/**
	 * @param ascending
	 *            the ascending to set
	 */
	public Scoring setAscending(Boolean ascending) {
		this.ascending = ascending;
		return this;
	}

	/**
	 * @param fieldName
	 *            the fieldName to set
	 */
	public Scoring setFieldName(String fieldName) {
		this.fieldName = fieldName;
		return this;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public Scoring setWeight(Double weight) {
		this.weight = weight;
		return this;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public Scoring setType(ScoringTypeEnum type) {
		this.type = type;
		return this;
	}

}
