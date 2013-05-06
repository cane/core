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

/**
 * 
 * @author Yat-ton
 * @version 1.00.000 2011-5-27
 */
public class StringUtils {
	final static char[] digits = {
		'0' , '1' , '2' , '3' , '4' , '5' ,
		'6' , '7' , '8' , '9' , 'a' , 'b' ,
		'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
		'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
		'o' , 'p' , 'q' , 'r' , 's' , 't' ,
		'u' , 'v' , 'w' , 'x' , 'y' , 'z' ,
		'A' , 'B' , 'C' , 'D' , 'E' , 'F' , 
		'G' , 'H' , 'I' , 'J' , 'K' , 'L' ,
		'M' , 'N' , 'O' , 'P' , 'Q' , 'R' ,
		'S' , 'T' , 'U' , 'V' , 'W' , 'X' ,
		'Y' , 'Z'
	    };
	
	public static boolean isBlank(String target){
		if(null == target) return true;
		
		return target.matches("\\s*");
	}
	//boolean isNumber
	public static String random(int length) {
		StringBuffer sb = new StringBuffer(length);
		
		//33 ~ 126
		int radix = 126;

		for (int i = 0; i < length; i++) {
			int c = new Double(Math.random() % radix * radix).intValue();
			if(c < 33) continue;
			sb.append((char)c);
		}
		
		return sb.toString();
	}

	public static String random(int length, char[] specimen) {
		StringBuffer sb = new StringBuffer(length);

		int radix = specimen.length - 1;

		for (int i = 0; i < length; i++) {
			int a = (int)Math.round(Math.random() % radix * radix);
			sb.append(specimen[a]);
		}
		
		return sb.toString();
	}
	
	public static String toHex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			// if (n<b.length-1) hs=hs+":";
		}
		return hs.toUpperCase();
	}
	
	public static String toString(long l, int radix)
    {
        if(radix < 2 || radix > 62)
        	radix = 10;
        if(radix == 10)
            return Long.toString(l);
        
        char ac[] = new char[65];
        int j = 64;
        boolean flag = l < 0L;
        if(!flag)
            l = -l;
        for(; l <= (long)(-radix); l /= radix)
            ac[j--] = digits[(int)(-(l % (long)radix))];

        ac[j] = digits[(int)(-l)];
        if(flag)
            ac[--j] = '-';
        return new String(ac, j, 65 - j);
    }
}
