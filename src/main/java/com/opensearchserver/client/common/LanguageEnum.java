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
package com.opensearchserver.client.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import com.opensearchserver.utils.IOUtils;
import com.opensearchserver.utils.LocaleUtils;
import com.opensearchserver.utils.StringUtils;

public enum LanguageEnum {

	UNDEFINED("Undefined", ""),

	ARABIC("Arabic", "ar"),

	CHINESE("Chinese", "zh", "zh-cn"),

	DANISH("Danish", "da"),

	CZECH("Czech", "cz", "cs"),

	DUTCH("Dutch", "nl"),

	ENGLISH("English", "en"),

	FINNISH("Finnish", "fi"),

	FRENCH("French", "fr"),

	GERMAN("German", "de"),

	HUNGARIAN("Hungarian", "hu"),

	ITALIAN("Italian", "it"),

	JAPANESE("Japanese", "ja"),

	KOREAN("Korean", "kr", "ko"),

	NORWEGIAN("Norwegian", "no"),

	POLISH("Polish", "pl"),

	PORTUGUESE("Portuguese", "pt"),

	ROMANIAN("Romanian", "ro"),

	RUSSIAN("Russian", "ru"),

	SPANISH("Spanish", "es"),

	SWEDISH("Swedish", "sv"),

	TURKISH("Turkish", "tr");

	private final String name;

	private final String code;

	private final String alternativeCode;

	private LanguageEnum(String name, String code, String alternativeCode) {
		this.name = name;
		this.code = code;
		this.alternativeCode = alternativeCode;
	}

	private LanguageEnum(String name, String code) {
		this(name, code, null);
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public String getAlternativeCode() {
		return alternativeCode;
	}

	public static LanguageEnum findByCode(String code) {
		if (code == null)
			return UNDEFINED;
		for (LanguageEnum lang : LanguageEnum.values())
			if (code.equalsIgnoreCase(lang.code))
				return lang;
		return UNDEFINED;
	}

	public static LanguageEnum findByName(String name) {
		if (name == null)
			return UNDEFINED;
		for (LanguageEnum lang : LanguageEnum.values())
			if (name.equalsIgnoreCase(lang.name))
				return lang;
		return UNDEFINED;
	}

	public static LanguageEnum findByNameOrCode(String nameOrCode) {
		if (nameOrCode == null)
			return UNDEFINED;
		LanguageEnum lang = findByName(nameOrCode);
		if (lang != UNDEFINED)
			return lang;
		return findByCode(nameOrCode);
	}

	public static String[] stringArray() {
		String[] array = new String[values().length];
		int i = 0;
		for (LanguageEnum lang : LanguageEnum.values())
			array[i++] = lang.name;
		return array;
	}

	static {
		try {
			List<String> profiles = new ArrayList<String>(0);
			for (LanguageEnum le : LanguageEnum.values()) {
				if (le == LanguageEnum.UNDEFINED)
					continue;
				InputStream is = com.cybozu.labs.langdetect.Detector.class
						.getResourceAsStream("/profiles/" + le.getCode());
				if (is == null && le.getAlternativeCode() != null)
					is = com.cybozu.labs.langdetect.Detector.class
							.getResourceAsStream("/profiles/"
									+ le.getAlternativeCode());
				if (is == null) {
					System.err.println("No profile for lang " + le.getName());
					continue;
				}
				profiles.add(IOUtils.toString(is));
				is.close();
			}
			DetectorFactory.loadProfile(profiles);
		} catch (LangDetectException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static final Locale langDetection(String text, int length)
			throws LangDetectException {
		if (StringUtils.isEmpty(text))
			return null;
		Detector detector = DetectorFactory.create();
		detector.setMaxTextLength(length);
		detector.append(text);
		String lang = detector.detect();
		return LocaleUtils.findLocaleDescription(lang);
	}

}
