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
package com.opensearchserver.client.api.index.result;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.opensearchserver.client.api.CommonResult;
import com.opensearchserver.client.api.index.SchemaField;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "result")
public class ResultField extends CommonResult {

	public SchemaField field;

	public ResultField() {
		field = null;
	}

	/**
	 * @param field
	 *            the field to set
	 */
	public ResultField setField(SchemaField field) {
		this.field = field;
		return this;
	}
}
