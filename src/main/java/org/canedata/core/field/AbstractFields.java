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

import org.canedata.cache.Cacheable;
import org.canedata.core.logging.LoggerFactory;
import org.canedata.core.util.ByteUtil;
import org.canedata.field.Fields;
import org.canedata.logging.Logger;

/**
 * 
 * @author Yat-ton
 * @version 1.00.000 2011-6-3
 */
public abstract class AbstractFields extends Cacheable.Adapter implements Fields {
	protected static final Logger logger = LoggerFactory
	.getLogger(AbstractFields.class);
	

	/* (non-Javadoc)
	 * @see org.jlue.cane.field.Fields#get(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(String field) {
		if(null == getField(field))
			return null;
		
		return (T) getField(field).get();
	}

	/* (non-Javadoc)
	 * @see org.jlue.cane.field.Fields#getChar(java.lang.String)
	 */
	public char getChar(String field) {
		return ByteUtil.getChar(get(field));
	}

	/* (non-Javadoc)
	 * @see org.jlue.cane.field.Fields#getInt(java.lang.String)
	 */
	public int getInt(String field) {
		if(null == getField(field))
			return 0;
		
		return ByteUtil.getInt(get(field));
	}

	/* (non-Javadoc)
	 * @see org.jlue.cane.field.Fields#getBoolean(java.lang.String)
	 */
	public boolean getBoolean(String field) {
		if(null == getField(field))
			return false;
		
		return ByteUtil.getBoolean(get(field));
	}

	/* (non-Javadoc)
	 * @see org.jlue.cane.field.Fields#getDouble(java.lang.String)
	 */
	public double getDouble(String field) {
		if(null == getField(field))
			return 0;
		
		return ByteUtil.getDouble(get(field));
	}

	/* (non-Javadoc)
	 * @see org.jlue.cane.field.Fields#getFloat(java.lang.String)
	 */
	public float getFloat(String field) {
		if(null == getField(field))
			return 0.0f;
		
		return ByteUtil.getFloat(get(field));
	}

	/* (non-Javadoc)
	 * @see org.jlue.cane.field.Fields#getByte(java.lang.String)
	 */
	public byte getByte(String field) {
		if(null == getField(field))
			return 0;
		
		return ByteUtil.getByte(get(field));
	}

	/* (non-Javadoc)
	 * @see org.jlue.cane.field.Fields#getBytes(java.lang.String)
	 */
	public byte[] getBytes(String field) {
		if(null == getField(field))
			return null;
		
		return ByteUtil.getBytes(get(field));
	}

	/* (non-Javadoc)
	 * @see org.jlue.cane.field.Fields#getString(java.lang.String)
	 */
	public String getString(String field) {
		if(null == getField(field))
			return null;
		
		return ByteUtil.getString(get(field));
	}

	/* (non-Javadoc)
	 * @see org.jlue.cane.field.Fields#getLong(java.lang.String)
	 */
	public long getLong(String field) {
		if(null == getField(field))
			return 0;
		
		return ByteUtil.getLong(get(field));
	}

	/* (non-Javadoc)
	 * @see org.jlue.cane.field.Fields#getShort(java.lang.String)
	 */
	public short getShort(String field) {
		if(null == getField(field))
			return 0;
		
		return ByteUtil.getShort(get(field));
	}

	/* (non-Javadoc)
	 * @see org.jlue.cane.field.Fields#getDate(java.lang.String)
	 */
	public Date getDate(String field) {
		if(null == getField(field))
			return null;
		
		Object t = get(field);
		if(t instanceof Date)
			return (Date)t;
		
		return new Date(ByteUtil.getLong(get(field)));
	}

	/* (non-Javadoc)
	 * @see org.jlue.cane.field.Fields#getInputStream(java.lang.String)
	 */
	public InputStream getInputStream(String field) {
		if(null == getField(field))
			return null;
		
		return new ByteArrayInputStream(ByteUtil.getBytes(get(field)));
	}

	/* (non-Javadoc)
	 * @see org.jlue.cane.field.Fields#getReader(java.lang.String)
	 */
	public Reader getReader(String field) {
		return new InputStreamReader(getInputStream(field));
	}

	/* (non-Javadoc)
	 * @see org.jlue.cane.field.Fields#getChannel(java.lang.String)
	 */
	public ReadableByteChannel getChannel(String field) {
		return Channels.newChannel(getInputStream(field));
	}

}
