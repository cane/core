/**
 * Copyright 2010 CaneData.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.canedata.core.cache;

import org.canedata.cache.Cacheable;

/**
 * String wrapped to Cacheable.
 * 
 * @author Yat-ton
 * @version 1.00.000 Sep 4, 2010 11:05:47 PM
 */
public class StringCacheableWrapped implements Cacheable {
	protected String key = null;
	protected boolean isRestored = false;
	protected long cacheTime = System.currentTimeMillis();
	protected String content = null;

	public StringCacheableWrapped(String key, boolean restored, String content) {
		this.key = key;
		isRestored = restored;
		this.content = content;
	}

	public String getKey() {
		return key;
	}

	public boolean isRestored() {
		return isRestored;
	}

	public long getCacheTime() {
		return cacheTime;
	}

	public String getContent() {
		return content;
	}

	public Cacheable onCaching() {
		cacheTime = System.currentTimeMillis();
		isRestored = false;

		return this;
	}

	public Cacheable onRestoring() {
		isRestored = true;

		return this;
	}

	public Cacheable onRestored() {
		isRestored = true;

		return this;
	}
}
