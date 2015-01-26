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
public class SnippetField {

	public static enum FragmenterEnum {
		NO, SENTENCE;
	}

	public String field;
	public String tag;
	public String separator;
	public Integer maxSize;
	public Integer maxNumber;
	public FragmenterEnum fragmenter;

	public SnippetField() {
		field = null;
		tag = null;
		separator = null;
		maxSize = null;
		maxNumber = null;
		fragmenter = null;
	}

	/**
	 * @param field
	 *            the field to set
	 */
	public SnippetField setField(String field) {
		this.field = field;
		return this;
	}

	/**
	 * @param tag
	 *            the tag to set
	 */
	public SnippetField setTag(String tag) {
		this.tag = tag;
		return this;
	}

	/**
	 * @param separator
	 *            the separator to set
	 */
	public SnippetField setSeparator(String separator) {
		this.separator = separator;
		return this;
	}

	/**
	 * @param maxSize
	 *            the maxSize to set
	 */
	public SnippetField setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
		return this;
	}

	/**
	 * @param maxNumber
	 *            the maxNumber to set
	 */
	public SnippetField setMaxNumber(Integer maxNumber) {
		this.maxNumber = maxNumber;
		return this;
	}

	/**
	 * @param fragmenter
	 *            the fragmenter to set
	 */
	public SnippetField setFragmenter(FragmenterEnum fragmenter) {
		this.fragmenter = fragmenter;
		return this;
	}

}
