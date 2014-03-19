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
package org.canedata.core.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Date;

/**
 * 
 * @author Yat-ton
 * @version 1.00.000 2011-4-27 01:15:34
 */
public class ByteUtil {
	public static byte[] objectToByte(Object target) {
		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(target);
			oo.flush();

			byte[] rlt = bo.toByteArray();
			bo.close();

			return rlt;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static ByteBuffer objectToByteBuffer(Object target) {
		return ByteBuffer.wrap(objectToByte(target));
	}

	public static Object byteToObject(byte[] source) {
		try {
			ByteArrayInputStream bai = new ByteArrayInputStream(source);
			ObjectInputStream bis = new ObjectInputStream(bai);

			return bis.readObject();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static Object byteToObject(ByteBuffer source) {
		return byteToObject(source.array());
	}

	public static ByteBuffer toByteBuffer(Object val) {
		ByteBuffer buffer = null;

		try {
			if (val instanceof Byte) {
				buffer = ByteBuffer.allocate(Byte.SIZE);

				buffer.put(0, (Byte) val);

				return buffer;
			}

			if (val instanceof byte[])
				return ByteBuffer.wrap((byte[]) val);

			if (val instanceof ByteBuffer)
				return (ByteBuffer) val;

			if (val instanceof Character) {
				buffer = ByteBuffer.allocate(Character.SIZE / Byte.SIZE);

				buffer.putChar(0, (Character) val);

				return buffer;
			}

			if (val instanceof Short) {
				buffer = ByteBuffer.allocate(Short.SIZE / Byte.SIZE);

				buffer.putShort(0, (Short) val);

				return buffer;
			}

			if (val instanceof Integer) {
				buffer = ByteBuffer.allocate(Integer.SIZE / Byte.SIZE);

				buffer.putInt(0, ((Integer) val).intValue());

				return buffer;
			}

			if (val instanceof Boolean) {
				byte[] v = new byte[1];
				if ((Boolean) val)
					v[0] = 1;
				else
					v[0] = 0;

				return ByteBuffer.wrap(v);
			}

			if (val instanceof Double) {
				buffer = ByteBuffer.allocate(Double.SIZE / Byte.SIZE);

				buffer.putDouble(0, (Double) val);

				return buffer;
			}

			if (val instanceof Float) {
				buffer = ByteBuffer.allocate(Float.SIZE / Byte.SIZE);

				buffer.putFloat(0, (Float) val);

				return buffer;
			}

			if (val instanceof Long) {
				buffer = ByteBuffer.allocate(Long.SIZE / Byte.SIZE);

				buffer.putLong(0, (Long) val);

				return buffer;
			}

			if (val instanceof String) {
				try {
					return ByteBuffer.wrap(((String) val).getBytes("UTF-8"));
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException(e);
				}
			}
			
			if(val instanceof Date){
				buffer = ByteBuffer.allocate(Long.SIZE / Byte.SIZE);

				buffer.putLong(0, ((Date)val).getTime());

				return buffer;
			}

			return objectToByteBuffer(val);

		} finally {
			if (null != buffer)
				buffer.flip();
		}

	}

	public static byte[] toByte(ByteBuffer val) {
		return val.array();
	}

	public static byte[] toByte(Object val) {
		return toByteBuffer(val).array();
	}

	public static byte[] sliceToByte(ByteBuffer val, int offset, int length) {
		byte[] rlt = new byte[length];
		val.get(rlt, offset, length);

		return rlt;
	}

	public static char getChar(Object source) {
        if(source instanceof Character)
            return ((Character)source).charValue();

        if(source instanceof Byte)
            return (char)((Byte)source).intValue();

        if(source instanceof Number)
            return (char)((Number)source).intValue();

        if(source instanceof CharSequence)
            return ((CharSequence)source).charAt(0);

		return toByteBuffer(source).getChar();
	}

	public static int getInt(Object source) {
        if ( source instanceof Number )
            return ((Number)source).intValue();

        if ( source instanceof Boolean )
            return ((Boolean)source) ? 1 : 0;

        if(source instanceof String && isNumber((String)source))
                return new Double((String)source).intValue();

        int l = Integer.SIZE/Byte.SIZE;

        ByteBuffer buf = ByteBuffer.allocate(l);
        byte[] b = toByte(source);
        int pos = l - b.length;

        //fill
        for(int i =0;i<=pos;i ++){
            buf.put(i, (byte)0);
        }

        buf.position(pos);
        buf.put(b);
        buf.flip();
        int rlt = buf.getInt();
        buf.clear();

		return rlt;
	}

	public static boolean getBoolean(Object source) {
		if (source == null)
			return false;
		
		if(source instanceof Boolean)
			return ((Boolean)source).booleanValue();
		
		if(source instanceof Number)
			return ((Number)source).intValue() > 0;
			
		if(source instanceof String)
				return Boolean.valueOf((String)source).booleanValue();
		
		byte[] v = toByteBuffer(source).array();
		if (v.length == 1)
			return v[0] == 1;

		if (v.length > 1)
			try {
				return Boolean.valueOf(new String(v, "UTF-8")).booleanValue();
			} catch (UnsupportedEncodingException e) {
			}

		return false;
	}

	public static double getDouble(Object source) {
        if ( source instanceof Number )
            return ((Number)source).doubleValue();

        if ( source instanceof Boolean )
            return ((Boolean)source) ? 1 : 0;

        if(source instanceof String && isNumber((String)source))
            return new Double((String)source).doubleValue();

        int l = Double.SIZE/Byte.SIZE;

        ByteBuffer buf = ByteBuffer.allocate(l);
        byte[] b = toByte(source);
        int pos = l - b.length;
        //fill
        for(int i =0;i<=pos;i ++){
            buf.put(i, (byte)0);
        }

        buf.position(pos);
        buf.flip();
        double rlt = buf.getDouble();
        buf.clear();

        return rlt;
	}

	public static float getFloat(Object source) {
        if ( source instanceof Number )
            return ((Number)source).floatValue();

        if ( source instanceof Boolean )
            return ((Boolean)source) ? 1 : 0;

        if(source instanceof String && isNumber((String)source))
            return new Double((String)source).floatValue();

        int l = Float.SIZE/Byte.SIZE;

        ByteBuffer buf = ByteBuffer.allocate(l);
        byte[] b = toByte(source);
        int pos = l - b.length;
        //fill
        for(int i =0;i<=pos;i ++){
            buf.put(i, (byte)0);
        }

        buf.position(pos);
        buf.flip();
        float rlt = buf.getFloat();
        buf.clear();

		return rlt;
	}

	public static byte getByte(Object source) {
        if(source instanceof Byte)
            return (Byte)source;

        if(source instanceof Number)
            return ((Number)source).byteValue();

        throw new IllegalArgumentException( "can't convert: " + source.getClass().getName() + " to byte" );
	}

	public static byte[] getBytes(Object source) {
		return toByteBuffer(source).array();
	}

	public static String getString(Object source) {
        if(null == source)
            return null;

        if(source instanceof byte[])
		try {
			return new String((byte[])source, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

        return String.valueOf(source);
	}

	public static long getLong(Object source) {
        if ( source instanceof Number )
            return ((Number)source).longValue();

        if ( source instanceof Boolean )
            return ((Boolean)source) ? 1 : 0;

        if(source instanceof String && isNumber((String)source))
            return new Double((String)source).longValue();

        int l = Long.SIZE/Byte.SIZE;

        ByteBuffer buf = ByteBuffer.allocate(l);
        byte[] b = toByte(source);
        int pos = l - b.length;
        //fill
        for(int i =0;i<=pos;i ++){
            buf.put(i, (byte)0);
        }

        buf.position(pos);
        buf.flip();
        long rlt = buf.getLong();
        buf.clear();

		return rlt;
	}

	public static short getShort(Object source) {
        if ( source instanceof Number )
            return ((Number)source).shortValue();

        if ( source instanceof Boolean )
            return (short)(((Boolean)source) ? 1 : 0);

        if(source instanceof String && isNumber((String)source))
            return new Double((String)source).shortValue();

        int l = Short.SIZE/Byte.SIZE;

        ByteBuffer buf = ByteBuffer.allocate(l);
        byte[] b = toByte(source);
        int pos = l - b.length;
        //fill
        for(int i =0;i<=pos;i ++){
            buf.put(i, (byte)0);
        }

        buf.position(pos);
        buf.flip();
        short rlt = buf.getShort();
        buf.clear();

        return rlt;
	}

    public static boolean isNumber(String s){
        if(null == s)
            return false;

        boolean dumPoint = false;
        for(char c : s.toCharArray()){
            if(c == '.' && dumPoint)
                return false;

            if(c == '.')
                dumPoint = true;

            if(!Character.isDigit(c) && c != '.')
                return false;
        }

        return true;
    }

    public static boolean isFloat(String s){
        if(null == s)
            return false;

        return s.indexOf('.') != -1;
    }
}
