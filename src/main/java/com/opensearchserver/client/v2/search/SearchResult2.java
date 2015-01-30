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
import com.opensearchserver.client.common.search.result.AbstractSearchResult;

@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class SearchResult2 extends AbstractSearchResult {

	@XmlTransient
	@JsonIgnore
	public final static TypeReference<ArrayList<SearchResult2>> LIST_TYPEREF = new TypeReference<ArrayList<SearchResult2>>() {
	};

	@XmlElement(name = "document")
	public List<DocumentResult2> documents;

	@XmlElement(name = "facet")
	public List<FacetResult2> facets;

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

	/**
	 * @param facet
	 *            the FacetResult to add
	 */
	@XmlTransient
	@JsonIgnore
	public SearchResult2 addFacet(FacetResult2 facet) {
		if (facets == null)
			facets = new ArrayList<FacetResult2>(1);
		facets.add(facet);
		return this;
	}

	/**
	 * @param facets
	 *            the facets to set
	 */
	public SearchResult2 setFacets(List<FacetResult2> facets) {
		this.facets = facets;
		return this;
	}

}
