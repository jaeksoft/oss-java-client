/**
 * Copyright 2015 OpenSearchServer Inc.
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

import java.lang.Thread.State;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(Include.NON_EMPTY)
public class ThreadResult {

	public String info;
	public State state;
	public Long durationMs;
	public Date startDate;

	public ThreadResult() {
		info = null;
		state = null;
		durationMs = null;
		startDate = null;
	}

	/**
	 * @param info
	 *            the info to set
	 */
	public ThreadResult setInfo(String info) {
		this.info = info;
		return this;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public ThreadResult setState(State state) {
		this.state = state;
		return this;
	}

	/**
	 * @param durationMs
	 *            the durationMs to set
	 */
	public ThreadResult setDurationMs(Long durationMs) {
		this.durationMs = durationMs;
		return this;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public ThreadResult setStartDate(Date startDate) {
		this.startDate = startDate;
		return this;
	}
}
