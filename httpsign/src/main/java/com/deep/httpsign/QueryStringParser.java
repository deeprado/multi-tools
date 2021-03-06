/*
 * Copyright (c) 2016-2100, fastquery.org and/or its affiliates. All rights reserved.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * For more information, please see http://www.fastquery.org/.
 * 
 */

package com.deep.httpsign;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * HTTP Query字符串处理工具
 * @author mei.sir@aliyun.cn
 */
class QueryStringParser {
	
	private Map<String, String> paramMap;

	QueryStringParser(String queryString) {
		if (queryString == null) {
			paramMap = null;
		} else {
			paramMap = new HashMap<>();
			StringTokenizer st = new StringTokenizer(queryString, "&");
			while (st.hasMoreTokens()) {
				String pairs = st.nextToken();
				String key = pairs.substring(0, pairs.indexOf('='));
				String value = pairs.substring(pairs.indexOf('=') + 1);
				paramMap.put(key, value);
			}
		}
	
	}

	String get(String key) {
		if(paramMap==null) {
			return null;
		} else {
			return paramMap.get(key);	
		}
	}
}
