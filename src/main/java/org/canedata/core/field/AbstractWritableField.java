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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;

import org.canedata.cache.Cacheable;
import org.canedata.core.intent.Intent;
import org.canedata.core.logging.LoggerFactory;
import org.canedata.core.util.ByteUtil;
import org.canedata.entity.Entity;
import org.canedata.field.WritableField;
import org.canedata.logging.Logger;

/**
 * 
 * @author Yat-ton
 * @version 1.00.000 2011-5-4
 */
public abstract class AbstractWritableField extends Cacheable.Adapter implements WritableField {
	protected static final Logger logger = LoggerFactory
			.getLogger(AbstractWritableField.class);
	
	protected Object value = null;

	/**
	 * put value to entity.
	 *
	 * @param field
	 * @param value field value.
	 * @return return the instance of WritableField.
	 */
	protected abstract AbstractWritableField put(String field, Object value);

	protected abstract Entity getEntity();

	protected abstract <I extends Intent> I getIntent();

	
	public String getKey() {
		return null;
	}

	protected Object getValue(){
		return value;
	}

	public Entity set(Object val) {
		value = val;
		
		put(getName(), ByteUtil.objectToByteBuffer(val));

		return getEntity();
	}

	public Entity set(char val) {
		value = val;
		
		put(getName(), val);

		return getEntity();
	}

	public Entity set(String val) {
		value = val;
		
		put(getName(), val);

		return getEntity();
	}

	public Entity set(int val) {
		value = val;
		
		put(getName(), val);

		return getEntity();
	}

	public Entity set(double val) {
		value = val;
		
		put(getName(), val);

		return getEntity();
	}

	public Entity set(float val) {
		value = val;
		
		put(getName(), val);

		return getEntity();
	}

	public Entity set(boolean val) {
		value = val;
		
		put(getName(), val);

		return getEntity();
	}

	public Entity set(byte val) {
		value = val;
		
		put(getName(), val);

		return getEntity();
	}

	public Entity set(byte[] vals) {
		value = vals;
		
		put(getName(), vals);

		return getEntity();
	}

	public Entity set(long val) {
		value = val;
		
		put(getName(), val);

		return getEntity();
	}

	public Entity set(short val) {
		value = val;
		
		put(getName(), val);

		return getEntity();
	}

	public OutputStream getOutputStream() {
		OutputStream out = new ByteArrayOutputStream() {
			boolean hasFlashed = false;

			@Override
			public void close() throws IOException {
				if (!hasFlashed)
					flush();
			}

			@Override
			public void flush() throws IOException {
				hasFlashed = true;

				put(getName(), ByteBuffer.wrap(toByteArray()));
			}

		};
		
		value = out;
		
		return out;
	}

	public Writer getWriter() {
		return new OutputStreamWriter(getOutputStream());
	}

	public WritableByteChannel getChannel() {
		return Channels.newChannel(getOutputStream());
	}

}
