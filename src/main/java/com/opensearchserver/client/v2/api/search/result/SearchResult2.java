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
package com.opensearchserver.client.v2.api.search.result;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.opensearchserver.client.api.search.result.AbstractDocumentResult;

@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class SearchResult2 extends AbstractDocumentResult {

	@XmlTransient
	@JsonIgnore
	public final static TypeReference<ArrayList<SearchResult2>> LIST_TYPEREF = new TypeReference<ArrayList<SearchResult2>>() {
	};

	@XmlElement(name = "document")
	public List<DocumentResult2> documents;

	public SearchResult2() {
		documents = null;
	}

	/**
	 * @param documents
	 *            the documents to set
	 */
	public SearchResult2 setDocuments(List<DocumentResult2> documents) {
		this.documents = documents;
		return this;
	}

	@XmlTransient
	@JsonIgnore
	public SearchResult2 addDocument(DocumentResult2 document) {
		if (documents == null)
			documents = new ArrayList<DocumentResult2>();
		documents.add(document);
		return this;
	}

}
