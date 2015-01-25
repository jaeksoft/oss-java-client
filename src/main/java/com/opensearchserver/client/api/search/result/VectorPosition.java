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
package com.opensearchserver.client.api.search.result;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(Include.NON_EMPTY)
public class VectorPosition {

	public Integer start;
	public Integer end;

	public VectorPosition() {
		start = 0;
		end = 0;
	}

	public VectorPosition(Integer start, Integer end) {
		this.start = start;
		this.end = end;
	}

	/**
	 * @param start
	 *            the start to set
	 */
	public VectorPosition setStart(Integer start) {
		this.start = start;
		return this;
	}

	/**
	 * @param end
	 *            the end to set
	 */
	public VectorPosition setEnd(Integer end) {
		this.end = end;
		return this;
	}

}