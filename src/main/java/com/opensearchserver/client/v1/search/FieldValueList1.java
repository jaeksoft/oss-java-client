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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(Include.NON_EMPTY)
public class FieldValueList1 {

	@XmlElement(name = "name")
	public String fieldName;

	@XmlElement(name = "value")
	public List<String> values;

	public Boolean highlighted;

	public FieldValueList1() {
		fieldName = null;
		values = null;
	}

	public FieldValueList1(String fieldName) {
		this.fieldName = fieldName;
		values = new ArrayList<String>(1);
	}

	/**
	 * @param fieldName
	 *            the fieldName to set
	 */
	public FieldValueList1 setFieldName(String fieldName) {
		this.fieldName = fieldName;
		return this;
	}

	/**
	 * @param values
	 *            the values to set
	 */
	public FieldValueList1 setValues(List<String> values) {
		this.values = values;
		return this;
	}

	/**
	 * @return the highlighted
	 */
	public Boolean getHighlighted() {
		return highlighted;
	}

	/**
	 * @param highlighted
	 *            the highlighted to set
	 */
	public FieldValueList1 setHighlighted(Boolean highlighted) {
		this.highlighted = highlighted;
		return this;
	}
}
