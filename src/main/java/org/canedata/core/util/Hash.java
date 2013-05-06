package org.canedata.core.util;

public final class Hash {

	public static final long hash(byte[] s) {
		if (null == s)
			return 0;

		long hash = 1;
		for (byte element : s)
			hash = hash * 63 + element;

		return hash;
	}

	public static final long trans(byte[] s) {
		if (null == s)
			return 0;

		long hash = 1;
		for (int i = s.length - 1; i > -1; i--)
			hash = hash * 63 + s[i];

		return hash;
	}
	
	public static final long mirror(byte[] s) {
		if (null == s)
			return 0;

		long hash = 1;
		for (byte element : s)
			hash = hash * 63 + (0 - element);

		return hash;
	}
	
	public static final long transMirror(byte[] s) {
		if (null == s)
			return 0;

		long hash = 1;
		for (int i = s.length - 1; i > -1; i--)
			hash = hash * 63 + (0 - s[i]);

		return hash;
	}
	
	static final long _longHashConstant = 4095;

	/**
	 * 64-bit hash, using longs, in stead of ints, for less collisions, for when
	 * it matters. Calls longHash( s , 0 , s.length() )
	 * 
	 * @param s
	 *            The source to hash.
	 * @return the hash code
	 */
	public static final long longHash(byte[] s) {
		return longHash(s, 0, s.length);
	}

	/**
	 * 64-bit hash using longs, starting on index 'start' and including
	 * everything before 'end'.
	 * 
	 * @param s
	 *            The source to hash.
	 * @param start
	 *            Where to start the hash.
	 * @param end
	 *            Where to end the hash.
	 * @return the hash code
	 */
	public static final long longHash(byte[] s, int start, int end) {
		if (null == s)
			return 0;

		long hash = 1;
		for (; start < end; start++)
			hash = _longHashConstant * hash + s[start];

		return hash;
	}

}