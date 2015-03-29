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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(Include.NON_EMPTY)
public class GeoParameters {

	public static enum CoordUnitEnum {
		DEGREES, RADIANS;
	}

	public static enum DistanceReturnEnum {
		NO_DISTANCE, DISTANCE_KM, DISTANCE_MILES;
	}

	public String latitudeField;
	public String longitudeField;
	public Double latitude;
	public Double longitude;
	public CoordUnitEnum coordUnit;
	public DistanceReturnEnum distanceReturn;

	public GeoParameters() {
		latitudeField = null;
		longitudeField = null;
		latitude = null;
		longitude = null;
		coordUnit = null;
		distanceReturn = null;
	}

	/**
	 * @param latitudeField
	 *            the latitudeField to set
	 * @return this instance
	 */
	public GeoParameters setLatitudeField(String latitudeField) {
		this.latitudeField = latitudeField;
		return this;
	}

	/**
	 * @param longitudeField
	 *            the longitudeField to set
	 * @return this instance
	 */
	public GeoParameters setLongitudeField(String longitudeField) {
		this.longitudeField = longitudeField;
		return this;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 * @return this instance
	 */
	public GeoParameters setLatitude(Double latitude) {
		this.latitude = latitude;
		return this;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 * @return this instance
	 */
	public GeoParameters setLongitude(Double longitude) {
		this.longitude = longitude;
		return this;
	}

	/**
	 * @param coordUnit
	 *            the coordUnit to set
	 * @return this instance
	 */
	public GeoParameters setCoordUnit(CoordUnitEnum coordUnit) {
		this.coordUnit = coordUnit;
		return this;
	}

	/**
	 * @param distanceReturn
	 *            the distanceReturn to set
	 * @return this instance
	 */
	public GeoParameters setDistanceReturn(DistanceReturnEnum distanceReturn) {
		this.distanceReturn = distanceReturn;
		return this;
	}

}