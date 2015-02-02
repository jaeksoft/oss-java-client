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
public class SearchField {

	public static enum SearchFieldMode {
		PATTERN, TERM, PHRASE, TERM_AND_PHRASE;
	}

	public String field;
	public SearchFieldMode mode;
	public Double boost;
	public Double phraseBoost;
	public Integer phraseSlop;
	public Integer booleanGroup;

	public SearchField() {
		field = null;
		mode = null;
		boost = null;
		phraseBoost = null;
		phraseSlop = null;
		booleanGroup = null;
	}

	/**
	 * @param field
	 *            the field to set
	 */
	public SearchField setField(String field) {
		this.field = field;
		return this;
	}

	/**
	 * @param mode
	 *            the mode to set
	 */
	public SearchField setMode(SearchFieldMode mode) {
		this.mode = mode;
		return this;
	}

	/**
	 * @param boost
	 *            the boost to set
	 */
	public SearchField setBoost(Double boost) {
		this.boost = boost;
		return this;
	}

	/**
	 * @param phraseBoost
	 *            the phraseBoost to set
	 */
	public SearchField setPhraseBoost(Double phraseBoost) {
		this.phraseBoost = phraseBoost;
		return this;
	}

	/**
	 * @param phraseSlop
	 *            the phraseSlop to set
	 */
	public SearchField setPhraseSlop(Integer phraseSlop) {
		this.phraseSlop = phraseSlop;
		return this;
	}

	/**
	 * @param booleanGroup
	 *            the booleanGroup to set
	 */
	public SearchField setBooleanGroup(Integer booleanGroup) {
		this.booleanGroup = booleanGroup;
		return this;
	}
}