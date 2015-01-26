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
package com.opensearchserver.client.common.search.query.filter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonInclude(Include.NON_EMPTY)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RelativeDateFilter")
@XmlRootElement(name = "RelativeDateFilter")
@JsonTypeName("RelativeDateFilter")
public class RelativeDateFilter extends AbstractFilter {

	public static enum IntervalUnit {
		days, hours, minutes;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@JsonInclude(Include.NON_EMPTY)
	public static class TimeInterval {

		public IntervalUnit unit;
		public Long interval;

		public TimeInterval() {
			unit = null;
			interval = null;
		}

		public TimeInterval(IntervalUnit unit, Long interval) {
			setUnit(unit);
			setInterval(interval);
		}

		public TimeInterval setUnit(IntervalUnit unit) {
			this.unit = unit;
			return this;
		}

		public TimeInterval setInterval(Long interval) {
			this.interval = interval;
			return this;
		}
	}

	public TimeInterval from;
	public TimeInterval to;
	public String field;
	public String dateFormat;

	public RelativeDateFilter() {
		from = null;
		to = null;
		field = null;
		dateFormat = null;
	}

	public RelativeDateFilter setFrom(TimeInterval from) {
		this.from = from;
		return this;
	}

	public RelativeDateFilter setTo(TimeInterval to) {
		this.to = to;
		return this;
	}

	public RelativeDateFilter setField(String field) {
		this.field = field;
		return this;
	}

	public RelativeDateFilter setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
		return this;
	}

}
