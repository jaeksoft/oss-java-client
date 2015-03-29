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
public class FacetField {

	public String field;
	public Integer minCount;
	public Boolean multivalued;
	public Boolean postCollapsing;

	public FacetField() {
		field = null;
		minCount = null;
		multivalued = null;
		postCollapsing = null;
	}

	/**
	 * @param field
	 *            the field to set
	 * @return this instance
	 */
	public FacetField setField(String field) {
		this.field = field;
		return this;
	}

	/**
	 * @param minCount
	 *            the minCount to set
	 * @return this instance
	 */
	public FacetField setMinCount(Integer minCount) {
		this.minCount = minCount;
		return this;
	}

	/**
	 * @param multivalued
	 *            the multivalued to set
	 * @return this instance
	 */
	public FacetField setMultivalued(Boolean multivalued) {
		this.multivalued = multivalued;
		return this;
	}

	/**
	 * @param postCollapsing
	 *            the postCollapsing to set
	 * @return this instance
	 */
	public FacetField setPostCollapsing(Boolean postCollapsing) {
		this.postCollapsing = postCollapsing;
		return this;
	}

}
