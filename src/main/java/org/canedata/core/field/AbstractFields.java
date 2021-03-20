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

import org.canedata.cache.Cacheable;
import org.canedata.core.logging.LoggerFactory;
import org.canedata.core.util.ByteUtil;
import org.canedata.field.Fields;
import org.canedata.logging.Logger;

import java.io.InputStream;
import java.io.Reader;
import java.nio.channels.ReadableByteChannel;
import java.util.Date;

/**
 * 
 * @author Yat-ton
 * @version 1.00.000 2011-6-3
 */
public abstract class AbstractFields extends Cacheable.Adapter implements Fields {
	protected static final Logger logger = LoggerFactory
	.getLogger(AbstractFields.class);
	
	public char getChar(String field) {
		return getField(field).getChar();
	}

	public char getChar(String field, char defaultValue) {
		if(!this.exist(field)) return defaultValue;

		return getChar(field);
	}

	public int getInt(String field) {
		return getField(field).getInt();
	}

	@Override
	public int getInt(String field, int defaultValue) {
		if(!this.exist(field)) return defaultValue;

		return getInt(field);
	}

	public boolean getBoolean(String field) {
		return getField(field).getBoolean();
	}

	@Override
	public boolean getBoolean(String field, boolean defaultValue) {
		if (!exist(field))
			return defaultValue;

		return getBoolean(field);
	}

	public double getDouble(String field) {
		return getField(field).getDouble();
	}

	@Override
	public double getDouble(String field, double defaultValue) {
		if (!exist(field))
			return defaultValue;

		return getDouble(field);
	}

	public float getFloat(String field) {
		return getField(field).getFloat();
	}

	@Override
	public float getFloat(String field, float defaultValue) {
		if (!exist(field))
			return defaultValue;

		return getFloat(field);
	}

	public byte getByte(String field) {
		return getField(field).getByte();
	}

	@Override
	public byte getByte(String field, byte defaultValue) {
		if (!exist(field))
			return defaultValue;

		return getByte(field);
	}

	public byte[] getBytes(String field) {
		return getField(field).getBytes();
	}

	@Override
	public byte[] getBytes(String field, byte[] defaultValue) {
		if (!exist(field))
			return defaultValue;

		return getBytes(field);
	}

	public String getString(String field) {
		if(null == getField(field))
			return null;
		
		return ByteUtil.getString(get(field));
	}

	@Override
	public String getString(String field, String defaultValue) {
		if (!exist(field))
			return defaultValue;

		return getString(field);
	}

	public long getLong(String field) {
		return getField(field).getLong();
	}

	@Override
	public long getLong(String field, long defaultValue) {
		if(!exist(field))
			return defaultValue;

		return getLong(field);
	}

	public short getShort(String field) {
		return getField(field).getShort();
	}

	@Override
	public short getShort(String field, short defaultValue) {
		if (!exist(field))
			return defaultValue;

		return getShort(field);
	}

	public Date getDate(String field) {
		return getField(field).getDate();
	}

	@Override
	public Date getDate(String field, Date defaultValue) {
		Date d = getField(field).getDate();
		if(!exist(field))
			return defaultValue;

		return d;
	}

	public InputStream getInputStream(String field) {
		return getField(field).getInputStream();
	}

	public Reader getReader(String field) {
		return getField(field).getReader();
	}

	public ReadableByteChannel getChannel(String field) {
		return getField(field).getChannel();
	}

}
