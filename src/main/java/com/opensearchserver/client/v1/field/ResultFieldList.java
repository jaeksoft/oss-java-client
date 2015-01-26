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

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opensearchserver.client.common.CommonResult;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "result")
public class ResultFieldList extends CommonResult {

	@XmlElement(name = "unique")
	@JsonProperty("unique")
	public String uniqueField;

	@XmlElement(name = "default")
	@JsonProperty("default")
	public String defaultField;

	public List<SchemaField> fields;

	public ResultFieldList() {
		fields = null;
		uniqueField = null;
		defaultField = null;
	}

	/**
	 * @param uniqueField
	 *            the uniqueField to set
	 */
	public ResultFieldList setUniqueField(String uniqueField) {
		this.uniqueField = uniqueField;
		return this;
	}

	/**
	 * @param defaultField
	 *            the defaultField to set
	 */
	public ResultFieldList setDefaultField(String defaultField) {
		this.defaultField = defaultField;
		return this;
	}

	/**
	 * @param fields
	 *            the fields to set
	 */
	public ResultFieldList setFields(List<SchemaField> fields) {
		this.fields = fields;
		return this;
	}

}
