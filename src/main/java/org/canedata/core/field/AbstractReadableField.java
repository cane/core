/**
 * Copyright 2011 CaneData.org
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
package org.canedata.core.field;

import java.io.InputStream;
import java.io.Reader;
import java.nio.channels.ReadableByteChannel;
import java.util.Date;

import org.canedata.cache.Cacheable;
import org.canedata.core.logging.LoggerFactory;
import org.canedata.field.Fields;
import org.canedata.field.ReadableField;
import org.canedata.logging.Logger;

/**
 * Default charset use utf-8.
 * 
 * @author Yat-ton
 * @version 1.00.000 2011-6-2
 */
public abstract class AbstractReadableField extends Cacheable.Adapter implements ReadableField {
	protected static final Logger logger = LoggerFactory
			.getLogger(AbstractReadableField.class);
	
	protected abstract Fields getFields();
	
	
	public String getKey() {
		return null;
	}

	public char getChar() {
		return getFields().getChar(getName());
	}

	public int getInt() {
		return getFields().getInt(getName());
	}

	public boolean getBoolean() {
		return getFields().getBoolean(getName());
	}

	public double getDouble() {
		return getFields().getDouble(getName());
	}

	public float getFloat() {
		return getFields().getFloat(getName());
	}

	public byte getByte() {
		return getFields().getByte(getName());
	}

	public byte[] getBytes() {
		return getFields().getBytes(getName());
	}

	public String getString() {
		return getFields().getString(getName());
	}

	public long getLong() {
		return getFields().getLong(getName());
	}

	public short getShort() {
		return getFields().getShort(getName());
	}

	public Date getDate() {
		return getFields().getDate(getName());
	}

	public InputStream getInputStream() {
		return getFields().getInputStream(getName());
	}

	public Reader getReader() {
		return getFields().getReader(getName());
	}

	public ReadableByteChannel getChannel() {
		return getFields().getChannel(getName());
	}

}
