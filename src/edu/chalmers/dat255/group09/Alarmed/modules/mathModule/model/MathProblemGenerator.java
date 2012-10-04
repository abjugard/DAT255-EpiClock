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

	private final static int NBR_OF_OPERATORS = 3;

	public MathProblem generateProblem(Difficulty difficulty) {
		MathProblemType operator = generateOperator();
		int[] numbers = operator.generateNumbers(difficulty);

		return new MathProblem(numbers, operator);
	}

	// TODO Add a mathoperator for deciding if a number is a multiple if
	// TODO use enum or final static int:s istället

	private MathProblemType generateOperator() {

		MathProblemType problemType = null;
		int rand = (int) Math.floor((Math.random() * NBR_OF_OPERATORS));

		if (rand == 0) {
			problemType = new AdditionProblem();
		}

		if (rand == 1) {
			problemType = new MultiplicationProblem();
		}

		if (rand == 2) {
			problemType = new PrimeProblem();
		}
		return problemType;
	}

}