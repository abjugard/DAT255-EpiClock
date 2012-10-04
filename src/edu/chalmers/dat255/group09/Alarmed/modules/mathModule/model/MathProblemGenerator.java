/*
 * Copyright (C) 2012 Joakim Persson, Daniel Augurell, Adrian Bjugård, Andreas Rolén
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model;

import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.constants.Difficulty;

/**
 * 
 * @author Joakim Persson
 * 
 */
public class MathProblemGenerator {

	private final static int NBR_OF_OPERATORS = 2;

	public MathProblem generateProblem(Difficulty difficulty) {
		MathProblemType operator = generateOperator();
		int[] numbers = operator.generateNumbers(difficulty);

		return new MathProblem(numbers, operator);
	}

	// Add a mathoperator for deciding if a number is a multiple if

	private MathProblemType generateOperator() {

		MathProblemType operator = null;
		int rand = (int) Math.floor((Math.random() * NBR_OF_OPERATORS));

		if (rand == 0) {
			operator = new AdditionProblem();
		}

		if (rand == 1) {
			operator = new MultiplicationProblem();
		}

		return operator;
	}

}