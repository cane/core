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

import java.util.Arrays;

import org.canedata.core.intent.Intent;
import org.canedata.core.intent.Step;
import org.canedata.core.intent.Tracer;
import org.canedata.core.logging.LoggerFactory;
import org.canedata.exception.AnalyzeBehaviourException;
import org.canedata.expression.Expression;
import org.canedata.expression.Operator;
import org.canedata.expression.ParseExpressionException;
import org.canedata.expression.shield.ExpressionA;
import org.canedata.expression.shield.ExpressionB;
import org.canedata.logging.Logger;

/**
 * 
 * @author Yat-ton
 * @version 1.00.000 2011-5-4
 */
public abstract class AbstractExpression implements ExpressionA {
	protected static final Logger logger = LoggerFactory.getLogger(AbstractExpression.class);
	
	protected abstract ExpressionB getExpressionB();
	protected abstract <T extends Intent> T getIntent();
	
	
	public void parse(Parser parser) throws ParseExpressionException {
		try{
			parser.parse(getIntent());
		}catch(ParseExpressionException pee){
			logger.debug("");
			
			throw pee;
		}
	}
	
	public ExpressionA and(Expression expr) {
		getIntent().step(Operator.AND_EPR, null, expr);
		
		return this;
	}

	public ExpressionA or(Expression expr) {
		getIntent().step(Operator.OR_EPR, null, expr);
		
		return this;
	}

	public ExpressionB and() {
		getIntent().step(Operator.AND, null);
		
		return getExpressionB();
	}

	public ExpressionB or() {
		getIntent().step(Operator.OR, null);
		
		return getExpressionB();
	}
	
	public String toExprString() {
		final StringBuilder sb = new StringBuilder();

		try {
			getIntent().playback(new Tracer() {

				public Tracer trace(Step step)
						throws AnalyzeBehaviourException {
					logger.debug("Analyzing exprssion by toExprString, step is {0}, purpose is {1}, value is {2}.", step.getName(), step.getPurpose(), step.getScalar());
					
					switch (step.step()) {
					case Operator.EQUALS:
						sb.append(step.getPurpose()).append(" == ")
								.append(step.getScalar()[0]);

						break;
					case Operator.NOT_EQUALS:
						sb.append(step.getPurpose()).append(" != ")
								.append(step.getScalar()[0]);

						break;
					case Operator.LESSTHAN:
						sb.append(step.getPurpose()).append(" < ")
								.append(step.getScalar()[0]);

						break;
					case Operator.LESSTHAN_OR_EQUAL:
						sb.append(step.getPurpose()).append(" <= ")
								.append(step.getScalar()[0]);

						break;
					case Operator.GREATERTHAN:
						sb.append(step.getPurpose()).append(" > ")
								.append(step.getScalar()[0]);

						break;
					case Operator.GREATERTHAN_OR_EQUAL:
						sb.append(step.getPurpose()).append(" >= ")
								.append(step.getScalar()[0]);

						break;
					case Operator.BETWEEN:
						sb.append(step.getPurpose()).append(" between ")
								.append(step.getScalar()[0]).append(",")
								.append(step.getScalar()[1]);

						break;
					case Operator.NOT_BETWEEN:
						sb.append(step.getPurpose())
								.append(" not between ")
								.append(step.getScalar()[0]).append(",")
								.append(step.getScalar()[1]);

						break;
					case Operator.LIKE:
						sb.append(step.getPurpose()).append(" like ")
								.append(step.getScalar()[0]);

						break;
					case Operator.NOT_LIKE:
						sb.append(step.getPurpose()).append(" not like ")
								.append(step.getScalar()[0]);

						break;
					case Operator.IN:
						sb.append(step.getPurpose()).append(" in (")
								.append(Arrays.toString(step.getScalar())).append(")");

						break;
					case Operator.NOT_IN:
						sb.append(step.getPurpose()).append(" not in (")
								.append(Arrays.toString(step.getScalar())).append(")");

						break;
					case Operator.EMPTY:
						sb.append("isEmpty(").append(step.getPurpose())
								.append(")");

						break;
					case Operator.NOT_EMPTY:
						sb.append("isNotEmpty(").append(step.getPurpose())
								.append(")");

						break;
					case Operator.NULL:
						sb.append("isNull(").append(step.getPurpose())
								.append(")");

						break;
					case Operator.NOT_NULL:
						sb.append("isNotNull(").append(step.getPurpose())
								.append(")");

						break;
					case Operator.MATCH:
						sb.append("match(").append(step.getPurpose())
								.append(", ").append(step.getScalar()[0])
								.append(")");

						break;
					case Operator.NOT_MATCH:
						sb.append("notMatch(").append(step.getPurpose())
								.append(", ").append(step.getScalar()[0])
								.append(")");

						break;
					case Operator.AND:
						sb.append(" and ");

						break;
					case Operator.AND_EPR:
						sb.append(" and (")
								.append(((Expression) step.getScalar()[0])
										.toExprString()).append(")");

						break;
					case Operator.OR:
						sb.append(" or ");

						break;
					case Operator.OR_EPR:
						sb.append(" or (")
								.append(((Expression) step.getScalar()[0])
										.toExprString()).append(")");

						break;

					}

					return this;
				}

			});
		} catch (AnalyzeBehaviourException e) {
			return "Analyze expression failure, cause by: "
					+ e.getLocalizedMessage() + ". ";
		}
		return sb.toString();
	}

}
