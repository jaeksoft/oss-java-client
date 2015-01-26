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
package com.opensearchserver.client.v1.search;

import java.util.List;

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
public class DocumentResult1 extends AbstractDocumentResult {

	@XmlElement(name = "field")
	public List<FieldValueList1> fields;

	@XmlElement(name = "snippet")
	public List<FieldValueList1> snippets;

	@XmlElement(name = "join")
	public List<DocumentResult1> joins;

	public DocumentResult1() {
		fields = null;
		snippets = null;
		joins = null;
	}

	/**
	 * @param fields
	 *            the fields to set
	 */
	public DocumentResult1 setFields(List<FieldValueList1> fields) {
		this.fields = fields;
		return this;
	}

	/**
	 * @param snippets
	 *            the snippets to set
	 */
	public DocumentResult1 setSnippets(List<FieldValueList1> snippets) {
		this.snippets = snippets;
		return this;
	}

	/**
	 * @param joins
	 *            the joins to set
	 */
	public void setJoins(List<DocumentResult1> joins) {
		this.joins = joins;
	}
}
