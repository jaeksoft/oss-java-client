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
package com.opensearchserver.client.api.search.query;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlAccessorType(XmlAccessType.FIELD)
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
	 */
	@JsonIgnore
	@XmlTransient
	public void setLatitudeField(String latitudeField) {
		this.latitudeField = latitudeField;
	}

	/**
	 * @param longitudeField
	 *            the longitudeField to set
	 */
	@JsonIgnore
	@XmlTransient
	public void setLongitudeField(String longitudeField) {
		this.longitudeField = longitudeField;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	@JsonIgnore
	@XmlTransient
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	@JsonIgnore
	@XmlTransient
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @param coordUnit
	 *            the coordUnit to set
	 */
	@JsonIgnore
	@XmlTransient
	public void setCoordUnit(CoordUnitEnum coordUnit) {
		this.coordUnit = coordUnit;
	}

	/**
	 * @param distanceReturn
	 *            the distanceReturn to set
	 */
	@JsonIgnore
	@XmlTransient
	public void setDistanceReturn(DistanceReturnEnum distanceReturn) {
		this.distanceReturn = distanceReturn;
	}

}