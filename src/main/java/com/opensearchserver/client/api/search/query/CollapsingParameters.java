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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlAccessorType(XmlAccessType.FIELD)
public class CollapsingParameters {

	public static enum ModeEnum {
		OFF, ADJACENT, CLUSTER;
	}

	public static enum TypeEnum {
		FULL, OPTIMIZED;
	}

	public static enum FunctionEnum {
		MIN, MAX, CONCAT, COUNT;
	}

	public String field;
	public Integer max;
	public ModeEnum mode;
	public TypeEnum type;
	public List<FunctionField> functionFields;

	public CollapsingParameters() {
		field = null;
		max = null;
		mode = null;
		type = null;
		functionFields = null;
	}

	@JsonIgnore
	@XmlTransient
	public CollapsingParameters setField(String field) {
		this.field = field;
		return this;
	}

	@JsonIgnore
	@XmlTransient
	public CollapsingParameters setMax(Integer max) {
		this.max = max;
		return this;
	}

	@JsonIgnore
	@XmlTransient
	public CollapsingParameters setMode(ModeEnum mode) {
		this.mode = mode;
		return this;
	}

	@JsonIgnore
	@XmlTransient
	public CollapsingParameters setType(TypeEnum type) {
		this.type = type;
		return this;
	}

	@JsonIgnore
	@XmlTransient
	public FunctionField addFunctionField() {
		if (functionFields == null)
			functionFields = new ArrayList<FunctionField>(1);
		FunctionField functionField = new FunctionField();
		functionFields.add(functionField);
		return functionField;
	}

	@JsonInclude(Include.NON_EMPTY)
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class FunctionField {

		public FunctionEnum function;
		public String field;

		public FunctionField() {
			function = null;
			field = null;
		}

		@JsonIgnore
		@XmlTransient
		public FunctionField setFunction(FunctionEnum function) {
			this.function = function;
			return this;
		}

		@JsonIgnore
		@XmlTransient
		public FunctionField setField(String field) {
			this.field = field;
			return this;
		}
	}
}