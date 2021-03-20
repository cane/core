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
package org.canedata.core.logging.impl;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.canedata.logging.Logger;
import org.canedata.logging.WrappedLoggerFactory;
import org.canedata.module.Module;

/**
 * Wrapped JDK Logging.
 * 
 * @author Yat-ton
 * @version 1.00.000 Sep 2, 2010 3:55:16 PM
 */
public class JdkLogging implements WrappedLoggerFactory {
	private static final String NAME = "JdkLogging";
	private static final String VENDOR = "Cane";
	private static final int VERSION = 1;
	private static final Map<String, Object> EXTRAS = new HashMap<String, Object>();
	
	private static JdkLogging instance;

	@Override
	public JdkLogging setExtra(String key, Object val) {
		EXTRAS.put(key, val);

		return this;
	}

	private JdkLogging() {
		// java.util.logging.
	}

	public static synchronized WrappedLoggerFactory getInstance() {
		if (null == instance)
			instance = new JdkLogging();

		return instance;
	}

	public static boolean isAvailable() {
		try {
			Class.forName("java.util.logging.Logger");
		} catch (ClassNotFoundException e) {
			return false;
		}

		return true;
	}

	public Logger getLogger(String name) {
		return new WrappedLogger(java.util.logging.Logger.getLogger(name), true);
	}

	public Logger getLogger(Class<?> target) {
		return getLogger(target.getClass().getName());
	}
	
	public boolean isWrappedFor(Class<?> iface) {
		return JdkLogging.class.isInstance(iface);
	}

	public <T> T unwrap(Class<T> iface) {
		return iface.cast(instance);
	}

	static class WrappedLogger implements Logger {
		private java.util.logging.Logger wrapped;
		String sourceClassName, sourceMethodName;
		private transient boolean needToInferCaller;

		public WrappedLogger(java.util.logging.Logger logger,
				boolean needToInferCaller) {
			wrapped = logger;
			this.needToInferCaller = needToInferCaller;
		}

		public boolean isDebug() {
			return wrapped.isLoggable(java.util.logging.Level.FINER);
		}

		public boolean isTrace() {
			return wrapped.isLoggable(java.util.logging.Level.FINEST);
		}

		public void debug(String msg) {
			if (isDebug())
				logw(java.util.logging.Level.FINER, msg);
		}

		public void debug(String msg, Object... args) {
			if (isDebug())
				logw(java.util.logging.Level.FINER, msg, args);
		}

		public void debug(Throwable t, String msg, Object... args) {
			if (isDebug())
				logw(java.util.logging.Level.FINER, t, msg, args);
		}

		public void info(String msg) {
			logw(java.util.logging.Level.INFO, msg);
		}

		public void info(String msg, Object... args) {
			logw(java.util.logging.Level.INFO, msg, args);
		}

		public void info(Throwable t, String format, Object... args) {
			logw(java.util.logging.Level.INFO, format, t, args);
		}

		public void warn(String msg) {
			logw(java.util.logging.Level.WARNING, msg);
		}

		public void warn(String msg, Object... args) {
			logw(java.util.logging.Level.WARNING, msg, args);
		}

		public void warn(Throwable t, String msg, Object... args) {
			logw(java.util.logging.Level.WARNING, t, msg, args);
		}

		public void error(String msg) {
			logw(java.util.logging.Level.SEVERE, msg);
		}

		public void error(String msg, Object... args) {
			logw(java.util.logging.Level.SEVERE, msg, args);
		}

		public void error(Throwable t, String msg, Object... args) {
			logw(java.util.logging.Level.SEVERE, t, msg, args);
		}

		public void trace(String msg) {
			if (isTrace())
				logw(java.util.logging.Level.FINEST, msg);
		}

		public void trace(String msg, Object... args) {
			if (isTrace())
				logw(java.util.logging.Level.FINEST, msg, args);
		}

		public void trace(Throwable t, String msg, Object... args) {
			if (isTrace())
				logw(java.util.logging.Level.FINEST, t, msg, args);
		}

		private void logw(java.util.logging.Level level, String msg,
				Object... args) {
			if (needToInferCaller) {
				// needToInferCaller = false;
				inferCaller();
			}

			wrapped.logp(level,
					needToInferCaller ? sourceClassName : wrapped.getName(),
					sourceMethodName, msg, args);
		}

		private void logw(java.util.logging.Level level, Throwable t,
				String msg, Object... args) {
			if (needToInferCaller) {
				needToInferCaller = false;
				inferCaller();
			}

			MessageFormat mf = new MessageFormat(msg);

			wrapped.logp(level,
					needToInferCaller ? sourceClassName : wrapped.getName(),
					sourceMethodName, mf.format(args), t);
		}

		private void setSourceClassName(String name) {
			this.sourceClassName = name;
		}

		private void setSourceMethodName(String name) {
			this.sourceMethodName = name;
		}

		String cur_name = WrappedLogger.class.getName();
		// Private method to infer the caller's class and method names
		private void inferCaller() {
			// needToInferCaller = false;
			// Get the stack trace.
			StackTraceElement stack[] = (new Throwable()).getStackTrace();
			// First, search back to a method in the Logger class.
			int ix = 0;
			while (ix < stack.length) {
				StackTraceElement frame = stack[ix];
				String cname = frame.getClassName();
				if (cname.equals(cur_name)) {
					break;
				}
				ix++;
			}
			// Now search for the first frame before the "Logger" class.
			while (ix < stack.length) {
				StackTraceElement frame = stack[ix];
				String cname = frame.getClassName();
				if (!cname.equals(cur_name)) {
					// We've found the relevant frame.
					setSourceClassName(cname);
					setSourceMethodName(frame.getMethodName());
					return;
				}
				ix++;
			}
		}

	}

	public String getName() {
		return NAME;
	}

	public String getVendor() {
		return VENDOR;
	}

	public int getVersion() {
		return VERSION;
	}

	public Map<String, Object> getExtras() {
		return EXTRAS;
	}

	public Object getExtra(String key) {
		return EXTRAS.get(key);
	}

}
