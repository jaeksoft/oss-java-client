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
package com.opensearchserver.client.v2.search;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.opensearchserver.client.common.search.result.AbstractDocumentResult;

@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class DocumentResult2 extends AbstractDocumentResult {

	@XmlElement(name = "field")
	public Map<String, List<String>> fields;

	@XmlElement(name = "snippet")
	public Map<String, SnippetField2> snippets;

	@XmlElement(name = "join")
	public List<DocumentResult2> joins;

	public DocumentResult2() {
		fields = null;
		snippets = null;
		joins = null;
	}

	/**
	 * @param fields
	 *            the fields to set
	 */
	public DocumentResult2 setFields(Map<String, List<String>> fields) {
		this.fields = fields;
		return this;
	}

	/**
	 * @param snippets
	 *            the snippets to set
	 */
	public DocumentResult2 setSnippets(Map<String, SnippetField2> snippets) {
		this.snippets = snippets;
		return this;
	}

	/**
	 * @param joins
	 *            the joins to set
	 */
	public void setJoins(List<DocumentResult2> joins) {
		this.joins = joins;
	}

}
