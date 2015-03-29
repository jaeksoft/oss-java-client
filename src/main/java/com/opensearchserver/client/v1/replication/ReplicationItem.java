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
package com.opensearchserver.client.v1.replication;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(Include.NON_EMPTY)
public class ReplicationItem {

	public ReplicationType replicationType;
	public String remoteUrl;
	public String remoteLogin;
	public String remoteApiKey;
	public String remoteIndexName;
	public Integer secTimeOut;

	public ReplicationItem() {
		replicationType = null;
		remoteUrl = null;
		remoteLogin = null;
		remoteApiKey = null;
		remoteIndexName = null;
		secTimeOut = null;
	}

	/**
	 * @param replicationType
	 *            the replicationType to set
	 * @return this instance
	 */
	public ReplicationItem setReplicationType(ReplicationType replicationType) {
		this.replicationType = replicationType;
		return this;
	}

	/**
	 * @param remoteUrl
	 *            the remoteUrl to set
	 * @return this instance
	 */
	public ReplicationItem setRemoteUrl(String remoteUrl) {
		this.remoteUrl = remoteUrl;
		return this;
	}

	/**
	 * @param remoteLogin
	 *            the remoteLogin to set
	 * @return this instance
	 */
	public ReplicationItem setRemoteLogin(String remoteLogin) {
		this.remoteLogin = remoteLogin;
		return this;
	}

	/**
	 * @param remoteApiKey
	 *            the remoteApiKey to set
	 * @return this instance
	 */
	public ReplicationItem setRemoteApiKey(String remoteApiKey) {
		this.remoteApiKey = remoteApiKey;
		return this;
	}

	/**
	 * @param remoteIndexName
	 *            the remoteIndexName to set
	 * @return this instance
	 */
	public ReplicationItem setRemoteIndexName(String remoteIndexName) {
		this.remoteIndexName = remoteIndexName;
		return this;
	}

	/**
	 * @param secTimeOut
	 *            the secTimeOut to set
	 * @return this instance
	 */
	public ReplicationItem setSecTimeOut(Integer secTimeOut) {
		this.secTimeOut = secTimeOut;
		return this;
	}

}
