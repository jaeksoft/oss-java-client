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
package com.opensearchserver.client.v1.field;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(Include.NON_EMPTY)
public class FieldUpdate {

	@XmlAttribute
	public String name;

	@XmlAttribute
	public Float boost;

	@XmlValue
	public String value;

	public FieldUpdate() {
		name = null;
		boost = null;
		value = null;
	}

	public FieldUpdate(String name, String value, Float boost) {
		this.name = name;
		this.value = value;
		this.boost = boost;
	}

	/**
	 * @param name
	 *            the name to set
	 * @return this instance
	 */
	public FieldUpdate setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * @param boost
	 *            the boost to set
	 * @return this instance
	 */
	public FieldUpdate setBoost(Float boost) {
		this.boost = boost;
		return this;
	}

	/**
	 * @param value
	 *            the value to set
	 * @return this instance
	 */
	public FieldUpdate setValue(String value) {
		this.value = value;
		return this;
	}

}
