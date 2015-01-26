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

@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(Include.NON_EMPTY)
@XmlType(name = "GeoFilter")
@XmlRootElement(name = "GeoFilter")
@JsonTypeName("GeoFilter")
public class GeoFilter extends AbstractFilter {

	public static enum GeoUnit {
		METERS, KILOMETERS, MILES, FEETS;
	}

	@XmlType(name = "GeoType")
	public static enum GeoType {
		SQUARED, RADIUS;
	}

	public GeoUnit unit;
	public GeoType shape;
	public Double distance;

	public GeoFilter() {
		unit = null;
		shape = null;
		distance = null;
	}

	public GeoFilter(GeoUnit unit, GeoType shape, Double distance) {
		setUnit(unit);
		setType(shape);
		setDistance(distance);
	}

	public GeoFilter setUnit(GeoUnit unit) {
		this.unit = unit;
		return this;
	}

	public GeoFilter setType(GeoType shape) {
		this.shape = shape;
		return this;
	}

	public GeoFilter setDistance(Double distance) {
		this.distance = distance;
		return this;
	}
}