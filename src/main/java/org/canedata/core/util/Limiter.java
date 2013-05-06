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
 * @version 1.00.000 2011-5-13
 */
public interface Limiter {
	public Limiter limit(int offset, int count);
	public Limiter offset(int offset);
	public Limiter count(int count);
	
	public int offset();
	public int count();
	
	public Limiter reset();
	
	public class Default implements Limiter{
		int offset = -1;
		int count = -1;
		
		public Default limit(int offset, int count){
			this.offset = offset;
			this.count = count;
			
			return this;
		}
		
		public Limiter offset(int offset) {
			this.offset = offset;
			
			return this;
		}

		public Limiter count(int count) {
			this.count = count;
			
			return this;
		}

		public int offset() {
			return offset;
		}

		public int count() {
			return count;
		}
		
		public Default reset(){
			offset = -1;
			count = -1;
			
			return this;
		}
	}
}
