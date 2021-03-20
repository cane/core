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
package org.canedata.core.intent;

import java.text.MessageFormat;

/**
 * 
 * @author Yat-ton
 * @version 1.00.000 2011-5-13
 */
public interface Limiter {
	public Limiter limit(long offset, int count);
	public Limiter offset(long offset);
	public Limiter count(int count);
	
	public long offset();
	public int count();
	public boolean isLimit();
	
	public Limiter reset();
	
	public class Default implements Limiter{
		long offset = 0;
		int count = -1;
		
		public Default limit(long offset, int count){
			this.offset = offset;
			this.count = count;
			
			return this;
		}

		public boolean isLimit(){
			return offset >= 0 && count > 0;
		}

		public Limiter offset(long offset) {
			this.offset = offset;
			
			return this;
		}

		public Limiter count(int count) {
			this.count = count;
			
			return this;
		}

		public long offset() {
			return offset;
		}

		public int count() {
			return count;
		}
		
		public Default reset(){
			offset = 0;
			count = -1;
			
			return this;
		}

		@Override
		public String toString() {
			return MessageFormat.format("Limiter: [{0}, {1}], isLimit: {2}", offset, count, isLimit());
		}
	}
}
