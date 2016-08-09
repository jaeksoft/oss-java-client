/**
 * Copyright 2015 OpenSearchServer Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.opensearchserver.client.common;

import com.qwazr.utils.http.ResponseValidator;
import com.qwazr.utils.json.client.JsonClientAbstract;

public class AbstractApi<T extends JsonClientAbstract> {

	protected final static ResponseValidator validator_200 = ResponseValidator.create().status(200);
	protected final static ResponseValidator validator_200_201 = ResponseValidator.create().status(200, 201);
	protected final static ResponseValidator validator_200_404 = ResponseValidator.create().status(200, 404);

	protected final T client;

	public AbstractApi(T client) {
		this.client = client;
	}

}
