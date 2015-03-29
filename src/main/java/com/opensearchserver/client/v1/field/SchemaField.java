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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(Include.NON_EMPTY)
public class SchemaField {

	public static enum Indexed {
		YES, NO;
	}

	public static enum Stored {
		YES, NO, COMPRESS;
	}

	public static enum TermVector {
		POSITIONS_OFFSETS, YES, NO;
	}

	public String name;

	public String analyzer;

	public Indexed indexed;

	public Stored stored;

	public TermVector termVector;

	public List<String> copyOf;

	public SchemaField() {
		name = null;
		analyzer = null;
		indexed = null;
		stored = null;
		termVector = null;
		copyOf = null;
	}

	/**
	 * @param name
	 *            the name to set
	 * @return this instance
	 */
	public SchemaField setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * @param analyzer
	 *            the analyzer to set
	 * @return this instance
	 */
	public SchemaField setAnalyzer(String analyzer) {
		this.analyzer = analyzer;
		return this;
	}

	/**
	 * @param indexed
	 *            the indexed to set
	 * @return this instance
	 */
	public SchemaField setIndexed(Indexed indexed) {
		this.indexed = indexed;
		return this;
	}

	/**
	 * @param stored
	 *            the stored to set
	 * @return this instance
	 */
	public SchemaField setStored(Stored stored) {
		this.stored = stored;
		return this;
	}

	/**
	 * @param termVector
	 *            the termVector to set
	 * @return this instance
	 */
	public SchemaField setTermVector(TermVector termVector) {
		this.termVector = termVector;
		return this;
	}

	/**
	 * @param copyOf
	 *            the copyOf to set
	 * @return this instance
	 */
	public SchemaField setCopyOf(List<String> copyOf) {
		this.copyOf = copyOf;
		return this;
	}

	/**
	 * @param copyOf
	 *            the copyOf to add
	 * @return this instance
	 */
	public SchemaField addCopyOf(String copyOf) {
		if (this.copyOf == null)
			this.copyOf = new ArrayList<String>(1);
		this.copyOf.add(copyOf);
		return this;
	}

}
