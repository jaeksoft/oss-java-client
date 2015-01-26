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
package com.opensearchserver.client.common.analyzer;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class ClassFactoryItem {

	public String name;
	public Map<String, Object> properties;
	public FilterScope scope;

	public ClassFactoryItem() {
		name = null;
		properties = null;
		scope = null;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public ClassFactoryItem setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * @param properties
	 *            the properties to set
	 */
	public ClassFactoryItem setProperties(Map<String, Object> properties) {
		this.properties = properties;
		return this;
	}

	/**
	 * @param name
	 * @param value
	 *            the property to add
	 */
	@XmlTransient
	@JsonIgnore
	public ClassFactoryItem addProperty(String name, String value) {
		if (properties == null)
			properties = new LinkedHashMap<String, Object>();
		properties.put(name.intern(), value);
		return this;
	}

	/**
	 * @param scope
	 *            the scope to set
	 */
	public ClassFactoryItem setScope(FilterScope scope) {
		this.scope = scope;
		return this;
	}

}