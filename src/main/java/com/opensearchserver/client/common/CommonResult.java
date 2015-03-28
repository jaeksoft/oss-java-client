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
package com.opensearchserver.client.common;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "result")
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonResult {

	@XmlElement
	public String info;

	@XmlElement
	public Map<String, String> details;

	public CommonResult() {
		info = null;
		details = null;
	}

	public CommonResult(String info) {
		this.info = info;
	}

	public CommonResult setInfo(String info) {
		this.info = info;
		return this;
	}

	@XmlTransient
	@JsonIgnore
	/**
	 * Add a detail to the current list
	 * @param key the key information
	 * @param value the value information
	 * @return
	 */
	public CommonResult addDetail(String key, Object value) {
		if (value == null)
			return this;
		if (details == null)
			details = new LinkedHashMap<String, String>();
		details.put(key.intern(), value.toString());
		return this;
	}

	/**
	 * @param details
	 *            the details to set
	 * @return the current instance
	 */
	public CommonResult setDetails(Map<String, String> details) {
		this.details = details;
		return this;
	}

}
