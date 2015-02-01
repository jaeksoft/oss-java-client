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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(Include.NON_EMPTY)
public class DocumentsQuery extends QueryAbstract {

	public String field;

	public List<String> values;

	public List<String> returnedFields;

	public Boolean reverse;

	public DocumentsQuery() {
		field = null;
		values = null;
		returnedFields = null;
		reverse = null;
	}

	public DocumentsQuery setField(String field) {
		this.field = field;
		return this;
	}

	@JsonIgnore
	@XmlTransient
	public DocumentsQuery addValue(String value) {
		if (values == null)
			values = new ArrayList<String>(1);
		values.add(value);
		return this;
	}

	/**
	 * @param returnedField
	 *            the returnedField to add
	 */
	@JsonIgnore
	@XmlTransient
	public DocumentsQuery addReturnedField(String returnedField) {
		if (returnedFields == null)
			returnedFields = new ArrayList<String>(1);
		returnedFields.add(returnedField);
		return this;
	}

	public DocumentsQuery setReverse(Boolean reverse) {
		this.reverse = reverse;
		return this;
	}

}
