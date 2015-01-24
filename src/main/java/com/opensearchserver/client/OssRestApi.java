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
package com.opensearchserver.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;

public class OssRestApi {

	private static final Logger LOGGER = Logger.getLogger(OssRestApi.class
			.getName());

	private final String oss_api_endpoint;
	private final String oss_api_login;
	private final String oss_api_key;
	private final int msTimeOut;

	public OssRestApi(String oss_api_endpoint, String oss_api_login,
			String oss_api_key, int msTimeOut) {
		this.oss_api_endpoint = oss_api_endpoint;
		this.oss_api_login = oss_api_login;
		this.oss_api_key = oss_api_key;
		this.msTimeOut = msTimeOut;
	}

	private final static String utf8encode(String text)
			throws UnsupportedEncodingException {
		return URLEncoder.encode(text, "UTF-8");
	}

	/**
	 * Extract the content of an HttpEntity
	 * 
	 * @param entity
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public final static String toString(HttpEntity entity)
			throws IllegalStateException, IOException {
		if (entity == null)
			return null;
		String encoding = null;
		Header header = entity.getContentEncoding();
		if (header != null)
			encoding = header.getValue();
		if (StringUtils.isEmpty(encoding))
			encoding = "UTF-8";
		InputStream inputStream = entity.getContent();
		if (inputStream == null)
			return null;
		return IOUtils.toString(inputStream, encoding);
	}

	/**
	 * Check that the HTTP response code match the given codes
	 * 
	 * @param response
	 *            The response returned by HttpClient
	 * @param codes
	 *            An array of codes. Can be empty.
	 * @return The HTTP result code
	 * @throws IOException
	 */
	public final static int checkHttpResponse(HttpResponse response,
			int... codes) throws IOException {
		if (response == null)
			throw new IOException("No response");
		StatusLine statusLine = response.getStatusLine();
		int resultCode = statusLine.getStatusCode();
		if (codes == null)
			return resultCode;
		for (int code : codes)
			if (resultCode == code)
				return resultCode;
		String content = toString(response.getEntity());
		if (content != null)
			LOGGER.severe("Http response:\n" + content);
		throw new IOException("Wrong status: " + statusLine.getStatusCode()
				+ ": " + statusLine.getReasonPhrase());
	}

	/**
	 * Check if the returned content match the give content types.
	 * 
	 * @param content
	 *            The returned content
	 * @param types
	 *            The allowed types
	 * @return The content type returned
	 * @throws IOException
	 */
	public static ContentType checkContentType(HttpEntity entity,
			ContentType... types) throws IOException {
		if (entity == null)
			throw new IOException("No content entity");
		ContentType contentType = ContentType.get(entity);
		for (ContentType type : types)
			if (contentType.getMimeType().equals(type.getMimeType()))
				return contentType;
		throw new IOException("Wrong content type: " + contentType);
	}

	public static class ResponseEntity implements ResponseHandler<HttpEntity> {

		@Override
		public HttpEntity handleResponse(HttpResponse response)
				throws ClientProtocolException, IOException {
			StatusLine statusLine = response.getStatusLine();
			HttpEntity entity = response.getEntity();
			if (statusLine.getStatusCode() >= 300) {
				throw new HttpResponseException(statusLine.getStatusCode(),
						statusLine.getReasonPhrase());
			}
			if (entity == null) {
				throw new ClientProtocolException(
						"Response contains no content");
			}
			return entity;
		}
	}

	private final URIBuilder baseUrl(String... path) throws URISyntaxException {
		StringBuilder sb = new StringBuilder(oss_api_endpoint);
		if (path != null)
			for (String p : path)
				if (p != null)
					sb.append(p);
		URIBuilder uriBuilder = new URIBuilder(sb.toString());
		if (!StringUtils.isEmpty(oss_api_login))
			uriBuilder.addParameter("login", oss_api_login);
		if (!StringUtils.isEmpty(oss_api_key))
			uriBuilder.addParameter("key", oss_api_key);
		return uriBuilder;
	}

	/**
	 * Delete index
	 * 
	 * @param indexName
	 *            The name of the index
	 * @throws ClientProtocolException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void deleteIndex(String indexName) throws ClientProtocolException,
			UnsupportedEncodingException, IOException, URISyntaxException {
		URIBuilder uriBuilder = baseUrl("index/", utf8encode(indexName));
		HttpResponse response = (HttpResponse) Request
				.Delete(uriBuilder.build())
				.addHeader("accept", ContentType.APPLICATION_JSON.toString())
				.connectTimeout(msTimeOut).socketTimeout(msTimeOut).execute()
				.returnResponse();
		checkHttpResponse(response, 200);
	}

	/**
	 * Create a new index
	 * 
	 * @param indexName
	 *            The name of the index
	 * @param templateName
	 *            The template
	 * @throws ClientProtocolException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void createIndex(String indexName, String templateName)
			throws ClientProtocolException, UnsupportedEncodingException,
			IOException, URISyntaxException {
		URIBuilder uriBuilder = baseUrl("index/", utf8encode(indexName),
				"/template/", utf8encode(templateName));
		HttpResponse response = Request.Post(uriBuilder.build())
				.addHeader("accept", ContentType.APPLICATION_JSON.toString())
				.connectTimeout(msTimeOut).socketTimeout(msTimeOut).execute()
				.returnResponse();
		checkHttpResponse(response, 200);
	}

	/**
	 * Create the fields
	 * 
	 * @param indexName
	 *            The name of the index
	 * @param json
	 *            A JSON structure defining the fields
	 * @throws ClientProtocolException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void createFields(String indexName, String json)
			throws ClientProtocolException, UnsupportedEncodingException,
			IOException, URISyntaxException {
		URIBuilder uriBuilder = baseUrl("index/", utf8encode(indexName),
				"/field");
		HttpResponse response = Request.Put(uriBuilder.build())
				.addHeader("accept", ContentType.APPLICATION_JSON.toString())
				.connectTimeout(msTimeOut).socketTimeout(msTimeOut)
				.bodyString(json, ContentType.APPLICATION_JSON).execute()
				.returnResponse();
		checkHttpResponse(response, 200);
	}

	/**
	 * Delete a field from a schema
	 * 
	 * @param indexName
	 *            The name of the index
	 * @param fieldName
	 *            The name of the field to delete
	 * @throws ClientProtocolException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void deleteField(String indexName, String fieldName)
			throws ClientProtocolException, UnsupportedEncodingException,
			IOException, URISyntaxException {
		URIBuilder uriBuilder = baseUrl("index/", utf8encode(indexName),
				"/field/", utf8encode(fieldName));
		HttpResponse response = Request.Delete(uriBuilder.build())
				.addHeader("accept", ContentType.APPLICATION_JSON.toString())
				.connectTimeout(msTimeOut).socketTimeout(msTimeOut).execute()
				.returnResponse();
		checkHttpResponse(response, 200, 404);
	}

	/**
	 * Set the default field of the index
	 * 
	 * @param indexName
	 * @param field
	 * @throws ClientProtocolException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void setDefaultField(String indexName, String field)
			throws ClientProtocolException, UnsupportedEncodingException,
			IOException, URISyntaxException {
		URIBuilder uriBuilder = baseUrl("index/", utf8encode(indexName),
				"/field").addParameter("default", field);
		HttpResponse response = Request
				.Post(uriBuilder.build())
				.addHeader("accept", ContentType.APPLICATION_JSON.toString())
				.addHeader("Content-Type",
						ContentType.APPLICATION_JSON.toString())
				.connectTimeout(msTimeOut).socketTimeout(msTimeOut).execute()
				.returnResponse();
		checkHttpResponse(response, 200);
	}

	/**
	 * Create an analyzer
	 * 
	 * @param indexName
	 *            The name of the index
	 * @param json
	 *            A JSON structure defining the analyzer
	 * @param analyzerName
	 *            Name of the analyzer to create
	 * @param analyzerLang
	 *            Lang of the analyzer to create
	 * @throws ClientProtocolException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void createAnalyzer(String indexName, String json,
			String analyzerName, String analyzerLang)
			throws ClientProtocolException, UnsupportedEncodingException,
			IOException, URISyntaxException {
		URIBuilder uriBuilder = baseUrl("index/", utf8encode(indexName),
				"/analyzer/", utf8encode(analyzerName), "/lang/",
				utf8encode(analyzerLang));
		HttpResponse response = Request.Put(uriBuilder.build())
				.addHeader("accept", ContentType.APPLICATION_JSON.toString())
				.connectTimeout(msTimeOut).socketTimeout(msTimeOut)
				.bodyString(json, ContentType.APPLICATION_JSON).execute()
				.returnResponse();
		checkHttpResponse(response, 200);
	}

	/**
	 * Create a list of synonyms
	 * 
	 * @param indexName
	 *            The name of the index
	 * @param listSynonyms
	 *            List of synonyms, one set of synonyms by line, raw text
	 * @param listName
	 *            Name of the list
	 * @throws ClientProtocolException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void createListSynonyms(String indexName, String listSynonyms,
			String listName) throws ClientProtocolException,
			UnsupportedEncodingException, IOException, URISyntaxException {
		URIBuilder uriBuilder = baseUrl("index/", utf8encode(indexName),
				"/synonyms/", utf8encode(listName));
		HttpResponse response = Request.Put(uriBuilder.build())
				.addHeader("accept", ContentType.APPLICATION_JSON.toString())
				.connectTimeout(msTimeOut).socketTimeout(msTimeOut)
				.bodyString(listSynonyms, ContentType.TEXT_PLAIN).execute()
				.returnResponse();
		checkHttpResponse(response, 200);
	}

	/**
	 * Put document(s) in the index
	 * 
	 * @param indexName
	 *            The name of the inde
	 * @param json
	 *            The JSON structure contaning documents
	 * @throws ClientProtocolException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void indexDocuments(String indexName, String json)
			throws ClientProtocolException, UnsupportedEncodingException,
			IOException, URISyntaxException {
		URIBuilder uriBuilder = baseUrl("index/", utf8encode(indexName),
				"/document");
		HttpResponse response = Request.Put(uriBuilder.build())
				.addHeader("accept", ContentType.APPLICATION_JSON.toString())
				.bodyString(json, ContentType.APPLICATION_JSON)
				.connectTimeout(msTimeOut).socketTimeout(msTimeOut).execute()
				.returnResponse();
		checkHttpResponse(response, 200);
	}

	/**
	 * Check if the index exists.
	 * 
	 * @param indexName
	 *            The name of the index.
	 * @return true if the index exists, false if not.
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public boolean checkIndexExists(String indexName) throws IOException,
			URISyntaxException {
		URIBuilder uriBuilder = baseUrl("index/", utf8encode(indexName));
		HttpResponse response = Request.Get(uriBuilder.build())
				.addHeader("accept", ContentType.APPLICATION_JSON.toString())
				.connectTimeout(msTimeOut).socketTimeout(msTimeOut).execute()
				.returnResponse();
		switch (checkHttpResponse(response, 200, 404)) {
		case 200:
			return true;
		case 404:
			return false;
		}
		return false;
	}

	/**
	 * Check if an analyzer exists in an index.
	 * 
	 * @param indexName
	 *            The name of the index.
	 * @param analyzerName
	 *            The name of the analyzer.
	 * @param analyzerLang
	 *            Lang of the analyzer.
	 * @return true if the analyzer exists, false if not.
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public boolean checkAnalyzerExists(String indexName, String analyzerName,
			String analyzerLang) throws IOException, URISyntaxException {
		URIBuilder uriBuilder = baseUrl("index/", utf8encode(indexName),
				"/analyzer/", utf8encode(analyzerName), "/lang/",
				utf8encode(analyzerLang));
		HttpResponse response = Request.Get(uriBuilder.build())
				.addHeader("accept", ContentType.APPLICATION_JSON.toString())
				.connectTimeout(msTimeOut).socketTimeout(msTimeOut).execute()
				.returnResponse();
		switch (checkHttpResponse(response, 200, 404)) {
		case 200:
			return true;
		case 404:
			return false;
		}
		return false;
	}

	/**
	 * Check if a list of synonyms exists in an index.
	 * 
	 * @param indexName
	 *            The name of the index.
	 * @param listName
	 *            The name of the list.
	 * @return true if the analyzer exists, false if not.
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public boolean checkListSynonymsExists(String indexName, String listName)
			throws IOException, URISyntaxException {
		URIBuilder uriBuilder = baseUrl("index/", utf8encode(indexName),
				"/synonyms/", utf8encode(listName));
		HttpResponse response = Request.Head(uriBuilder.build())
				.addHeader("accept", ContentType.APPLICATION_JSON.toString())
				.connectTimeout(msTimeOut).socketTimeout(msTimeOut).execute()
				.returnResponse();
		switch (checkHttpResponse(response, 200, 404)) {
		case 200:
			return true;
		case 404:
			return false;
		}
		return false;
	}

	/**
	 * Delete documents where the given field contains any of the given values
	 * 
	 * @param indexName
	 * @param field
	 * @param values
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void deleteDocumentsByFieldValues(String indexName, String field,
			Object... values) throws ClientProtocolException, IOException,
			URISyntaxException {
		StringBuilder encodedValues = new StringBuilder("/");
		for (Object value : values) {
			encodedValues.append(utf8encode(value.toString()));
			encodedValues.append('/');
		}
		URIBuilder uriBuilder = baseUrl("index/", utf8encode(indexName),
				"/document/", utf8encode(field), encodedValues.toString());
		HttpResponse response = Request.Delete(uriBuilder.build())
				.addHeader("accept", ContentType.APPLICATION_JSON.toString())
				.connectTimeout(msTimeOut).socketTimeout(msTimeOut).execute()
				.returnResponse();
		checkHttpResponse(response, 200);
	}

}
