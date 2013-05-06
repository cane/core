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


/**
 * 
 * @author Yat-ton
 * @version 1.00.000 2011-5-5
 */
public interface Step {
	public int step();
	
	public String getName();

	public String getPurpose();

	public Object[] getScalar();
	
	public abstract class AbstractStep implements Step {
		int step = 0;
		String purpose = null;
		Object[] scalar = null;

		public AbstractStep(int step, String pur, Object[] scalar) {
			this.step = step;
			this.purpose = pur;
			this.scalar = scalar;
		}

		public int step() {
			return step;
		}

		public String getPurpose() {
			return purpose;
		}

		public Object[] getScalar() {
			return scalar;
		}

		@Override
		public int hashCode() {
			return super.hashCode();
		}
		
		public boolean equals(Object obj) {
			if (null == obj || !(obj instanceof Step))
				return false;
			return ((Step) obj).step() == step();
		}
	}
}
