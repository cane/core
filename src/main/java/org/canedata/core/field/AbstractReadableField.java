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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jlue.cane.field.ReadableField#getChar()
	 */
	public char getChar() {
		return getFields().getChar(getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jlue.cane.field.ReadableField#getInt()
	 */
	public int getInt() {
		return getFields().getInt(getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jlue.cane.field.ReadableField#getBoolean()
	 */
	public boolean getBoolean() {
		return getFields().getBoolean(getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jlue.cane.field.ReadableField#getDouble()
	 */
	public double getDouble() {
		return getFields().getDouble(getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jlue.cane.field.ReadableField#getFloat()
	 */
	public float getFloat() {
		return getFields().getFloat(getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jlue.cane.field.ReadableField#getByte()
	 */
	public byte getByte() {
		return getFields().getByte(getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jlue.cane.field.ReadableField#getBytes()
	 */
	public byte[] getBytes() {
		return getFields().getBytes(getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jlue.cane.field.ReadableField#getString()
	 */
	public String getString() {
		return getFields().getString(getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jlue.cane.field.ReadableField#getLong()
	 */
	public long getLong() {
		return getFields().getLong(getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jlue.cane.field.ReadableField#getShort()
	 */
	public short getShort() {
		return getFields().getShort(getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jlue.cane.field.ReadableField#getDate()
	 */
	public Date getDate() {
		return getFields().getDate(getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jlue.cane.field.ReadableField#getInputStream()
	 */
	public InputStream getInputStream() {
		return getFields().getInputStream(getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jlue.cane.field.ReadableField#getReader()
	 */
	public Reader getReader() {
		return getFields().getReader(getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jlue.cane.field.ReadableField#getChannel()
	 */
	public ReadableByteChannel getChannel() {
		return getFields().getChannel(getName());
	}

}
