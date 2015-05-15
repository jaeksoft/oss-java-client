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
package com.opensearchserver.client;

import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServerResource {

	/**
	 * The URL of the remote OpenSearchServer instance. Example:
	 * http://myserver:9090
	 */
	public String url;

	/**
	 * The user login (optional).
	 */
	public String login;

	/**
	 * Users's API key (optional).
	 */
	public String api_key;

	/**
	 * The name of the index for OpenSearchServer v1.5.x. The name of the
	 * resource for OpenSearchServer v2.x.
	 */
	public String name;

	/**
	 * The default time out in milliseconds
	 */
	public int time_out;

	@XmlTransient
	@JsonIgnore
	/**
	 * Server resources are the same if both the URL and the name match.
	 * Null are never identical.
	 * 
	 * @param serverResouce1
	 * @param serverResource2
	 * @return if the server resources are identical
	 */
	public static boolean sameResource(ServerResource serverResource1,
			ServerResource serverResource2) {
		if (serverResource1 == null || serverResource2 == null)
			return false;
		if (!StringUtils.equals(serverResource1.url, serverResource2.url))
			return false;
		return StringUtils.equals(serverResource1.name, serverResource2.name);
	}

}
