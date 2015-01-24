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
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class QueryAbstract {

	public String user;
	public List<String> groups;

	protected QueryAbstract() {
		user = null;
		groups = null;
	}

	@JsonIgnore
	@XmlTransient
	public QueryAbstract setUser(String user) {
		this.user = user;
		return this;
	}

	@JsonIgnore
	@XmlTransient
	public QueryAbstract addGroup(String group) {
		if (group == null)
			return this;
		if (this.groups == null)
			this.groups = new ArrayList<String>(1);
		this.groups.add(group);
		return this;
	}

	@JsonIgnore
	@XmlTransient
	public QueryAbstract addGroups(Collection<String> groups) {
		if (groups == null)
			return this;
		if (this.groups == null)
			this.groups = new ArrayList<String>(groups.size());
		this.groups.addAll(groups);
		return this;
	}
}
