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
package org.canedata.core.field.expr;

import org.canedata.core.intent.Intent;
import org.canedata.core.logging.LoggerFactory;
import org.canedata.expression.Operator;
import org.canedata.expression.shield.ExpressionA;
import org.canedata.expression.shield.ExpressionB;
import org.canedata.logging.Logger;

/**
 * 
 * @author Yat-ton
 * @version 1.00.000 2011-5-4
 */
public abstract class AbstractExpressionB implements ExpressionB {
	protected static final Logger logger = LoggerFactory.getLogger(AbstractExpressionB.class);
	
	protected abstract ExpressionA getExpressionA();
	
	protected abstract <T extends Intent> T getIntent();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jlue.cane.expression.shield.ExpressionB#equals(java.lang.String,
	 * java.lang.Object)
	 */
	public ExpressionA equals(String field, Object value) {
		if(logger.isDebug())
			logger.debug("Building expression, operator is [equals], field is [{0}], value is [{1}].", field, value);
		
		getIntent().step(Operator.EQUALS, field, value);

		return getExpressionA();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jlue.cane.expression.shield.ExpressionB#notEquals(java.lang.String,
	 * java.lang.Object)
	 */
	public ExpressionA notEquals(String field, Object value) {
		getIntent().step(Operator.NOT_EQUALS, field, value);

		return getExpressionA();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jlue.cane.expression.shield.ExpressionB#lessThan(java.lang.String,
	 * java.lang.Object)
	 */
	public ExpressionA lessThan(String field, Object value) {
		getIntent().step(Operator.LESSTHAN, field, value);

		return getExpressionA();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jlue.cane.expression.shield.ExpressionB#lessThanOrEqual(java.lang
	 * .String, java.lang.Object)
	 */
	public ExpressionA lessThanOrEqual(String field, Object value) {
		getIntent().step(Operator.LESSTHAN_OR_EQUAL, field, value);

		return getExpressionA();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jlue.cane.expression.shield.ExpressionB#greaterThan(java.lang.String,
	 * java.lang.Object)
	 */
	public ExpressionA greaterThan(String field, Object value) {
		getIntent().step(Operator.GREATERTHAN, field, value);

		return getExpressionA();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jlue.cane.expression.shield.ExpressionB#greaterThanOrEqual(java.lang
	 * .String, java.lang.Object)
	 */
	public ExpressionA greaterThanOrEqual(String field, Object value) {
		getIntent().step(Operator.GREATERTHAN_OR_EQUAL, field, value);

		return getExpressionA();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jlue.cane.expression.shield.ExpressionB#between(java.lang.String,
	 * java.lang.Object, java.lang.Object)
	 */
	public ExpressionA between(String field, Object lo, Object hi) {
		getIntent().step(Operator.BETWEEN, field, lo, hi);

		return getExpressionA();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jlue.cane.expression.shield.ExpressionB#notBetween(java.lang.String,
	 * java.lang.Object, java.lang.Object)
	 */
	public ExpressionA notBetween(String field, Object lo, Object hi) {
		getIntent().step(Operator.NOT_BETWEEN, field, lo, hi);

		return getExpressionA();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jlue.cane.expression.shield.ExpressionB#like(java.lang.String,
	 * java.lang.Object)
	 */
	public ExpressionA like(String field, Object value) {
		getIntent().step(Operator.LIKE, field, value);

		return getExpressionA();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jlue.cane.expression.shield.ExpressionB#notLike(java.lang.String,
	 * java.lang.Object)
	 */
	public ExpressionA notLike(String field, Object value) {
		getIntent().step(Operator.NOT_LIKE, field, value);

		return getExpressionA();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jlue.cane.expression.shield.ExpressionB#in(java.lang.String,
	 * java.lang.Object[])
	 */
	public ExpressionA in(String field, Object... values) {
		getIntent().step(Operator.IN, field, values);

		return getExpressionA();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jlue.cane.expression.shield.ExpressionB#notIn(java.lang.String,
	 * java.lang.Object[])
	 */
	public ExpressionA notIn(String field, Object... values) {
		getIntent().step(Operator.NOT_IN, field, values);

		return getExpressionA();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jlue.cane.expression.shield.ExpressionB#isEmpty(java.lang.String)
	 */
	public ExpressionA isEmpty(String field) {
		getIntent().step(Operator.EMPTY, field);

		return getExpressionA();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jlue.cane.expression.shield.ExpressionB#isNotEmpty(java.lang.String)
	 */
	public ExpressionA isNotEmpty(String field) {
		getIntent().step(Operator.NOT_EMPTY, field);

		return getExpressionA();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jlue.cane.expression.shield.ExpressionB#isNull(java.lang.String)
	 */
	public ExpressionA isNull(String field) {
		getIntent().step(Operator.NULL, field);

		return getExpressionA();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jlue.cane.expression.shield.ExpressionB#isNotNull(java.lang.String)
	 */
	public ExpressionA isNotNull(String field) {
		getIntent().step(Operator.NOT_NULL, field);

		return getExpressionA();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jlue.cane.expression.shield.ExpressionB#match(java.lang.String,
	 * java.lang.String)
	 */
	public ExpressionA match(String field, String regex) {
		getIntent().step(Operator.MATCH, field, regex);

		return getExpressionA();
	}

	public ExpressionA notMatch(String field, String regex) {
		getIntent().step(Operator.NOT_MATCH, field, regex);

		return getExpressionA();
	}


}
