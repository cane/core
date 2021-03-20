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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Date;
import java.util.Objects;

import org.canedata.cache.Cacheable;
import org.canedata.core.logging.LoggerFactory;
import org.canedata.core.util.ByteUtil;
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

    //navigate to the caller
	protected abstract Fields getFields();
	
	
	public String getKey() {
		return null;
	}

	public char getChar() {
		return ByteUtil.getChar(get());
	}

	public int getInt() {
		return ByteUtil.getInt(get());
	}

	public boolean getBoolean() {
		return ByteUtil.getBoolean(get());
	}

	public double getDouble() {
		return ByteUtil.getDouble(get());
	}

	public float getFloat() {
		return ByteUtil.getFloat(get());
	}

	public byte getByte() {
		return ByteUtil.getByte(get());
	}

	public byte[] getBytes() {
		return ByteUtil.getBytes(get());
	}

	public String getString() {
		return ByteUtil.getString(get());
	}

	public long getLong() {
		return ByteUtil.getLong(get());
	}

	public short getShort() {
		return ByteUtil.getShort(get());
	}


	public Date getDate() {
        Object val = get();
        if(null == val)
            return null;

        if(val instanceof Date)
            return (Date)val;

		return new Date(getLong());
	}

	public InputStream getInputStream() {
		return new ByteArrayInputStream(getBytes());
	}

	public Reader getReader() {
		return new InputStreamReader(getInputStream());
	}

	public ReadableByteChannel getChannel() {
		return Channels.newChannel(getInputStream());
	}

}
