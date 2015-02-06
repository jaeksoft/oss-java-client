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
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.opensearchserver.client.common.CommonResult;
import com.opensearchserver.client.common.ThreadResult;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "result")
@JsonInclude(Include.NON_EMPTY)
public class ReplicationResult extends CommonResult {

	public final String name;
	public final ReplicationType replicationType;
	public final String remoteUrl;
	public final String remoteLogin;
	public final String remoteApiKey;
	public final String remoteIndexName;
	public final Integer secTimeOut;
	public final Boolean isActiveThread;
	public final ThreadResult lastThread;

	public ReplicationResult() {
		name = null;
		replicationType = null;
		remoteUrl = null;
		remoteLogin = null;
		remoteApiKey = null;
		remoteIndexName = null;
		secTimeOut = null;
		isActiveThread = null;
		lastThread = null;
	}

}
