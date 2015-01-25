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
package com.opensearchserver.client.api.search.query.filter;

import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@XmlTransient
@JsonTypeInfo(use = Id.NAME, property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = QueryFilter.class, name = "QueryFilter"),
		@JsonSubTypes.Type(value = TermFilter.class, name = "TermFilter"),
		@JsonSubTypes.Type(value = GeoFilter.class, name = "GeoFilter"),
		@JsonSubTypes.Type(value = RelativeDateFilter.class, name = "RelativeDateFilter"),
		@JsonSubTypes.Type(value = MirrorAndFilter.class, name = "MirrorAndFilter"),
		@JsonSubTypes.Type(value = RequestTemplateFilter.class, name = "RequestTemplateFilter") })
public abstract class AbstractFilter {

	public Boolean negative;
	final public String type;

	public AbstractFilter() {
		negative = null;
		type = getClass().getSimpleName();
	}

	public AbstractFilter setNegative(Boolean negative) {
		this.negative = negative;
		return this;
	}
}
