/**
 * Copyright 2010 CaneData.org
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
package org.canedata.core.logging;

import org.canedata.Wrapper;
import org.canedata.core.logging.impl.JdkLogging;
import org.canedata.logging.Logger;
import org.canedata.logging.WrappedLoggerFactory;

/**
 * 
 * @author Yat-ton
 * @version 1.00.000 Sep 2, 2010 5:07:45 PM
 */
public class LoggerFactory implements Wrapper{
	static WrappedLoggerFactory factory = null;
	static boolean registed = false;
	
	public LoggerFactory(){
		
	}
	
	public LoggerFactory(WrappedLoggerFactory fct){
		registe(fct);
	}
	
	static public Logger getLogger(String name){
		if(factory == null && JdkLogging.isAvailable())
			factory = JdkLogging.getInstance();
		
		if(factory != null)
			return factory.getLogger(name);
		
		return null;
	}
	
	static public Logger getLogger(Class<?> target){
		return getLogger(target.getName());
	}
	
	public void setWrappedLoggerFactory(WrappedLoggerFactory fct){
		registe(fct);
	}
	
	public static void registe(WrappedLoggerFactory fct){
		if(null == fct) return;
		
		registed = true;
		factory = fct;
	}
	
	public static boolean hasRegisted(){
		return registed;
	}

	public boolean isWrappedFor(Class<?> iface) {
		return iface.isInstance(factory);
	}

	public <T> T unwrap(Class<T> iface) {
		return iface.cast(factory);
	}
	
	
}
