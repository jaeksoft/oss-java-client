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

public enum ReplicationType {

	/**
	 * Backup everything except the Scheduler Jobs
	 */
	BACKUP_INDEX,

	/**
	 * Replicate only the main index (schema and data)
	 */
	MAIN_INDEX,

	/**
	 * Replicate the Web Crawler URL database
	 */
	WEB_CRAWLER_URL_DATABASE,

	/**
	 * Replication the File Crawler URI database
	 */
	FILE_CRAWLER_URI_DATABASE,

	/**
	 * Replicate only the schema
	 */
	SCHEMA_ONLY,

	/**
	 * Replication only the data
	 */
	MAIN_DATA_COPY;

}
