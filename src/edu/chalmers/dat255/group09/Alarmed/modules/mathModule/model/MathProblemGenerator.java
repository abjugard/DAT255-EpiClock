/*
 * Copyright (C) 2012 Joakim Persson, Daniel Augurell, Adrian Bjugard, Andreas Rolen
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
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.AdditionProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.BaseSwitchProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.DifferenceOfTwoSquaresProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.FactorialProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.FibonacciProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.MathProblemType;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.ModularProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.MultiplicationProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.PrimeProblem;

/**
 * A class that generates new math problems.
 * 
 * @author Joakim Persson
 * 
 */
public class MathProblemGenerator {

	private static final int NBR_OF_OPERATORS = 8;

	/**
	 * Generate a new math problem with an specified difficulty.
	 * 
	 * @param difficulty
	 *            The difficulty to use in the problem.
	 * @return A new mathproblem.
	 */
	public MathProblem generateProblem(Difficulty difficulty) {
		MathProblemType operator = generateProblemType();
		int[] numbers = operator.generateNumbers(difficulty);

		return new MathProblem(numbers, operator);
	}

	/**
	 * Generate a random math problem type.
	 * 
	 * @return A random math problem type.
	 */
	private MathProblemType generateProblemType() {

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

		if (rand == 3) {
			problemType = new FactorialProblem();
		}

		if (rand == 4) {
			problemType = new FibonacciProblem();
		}

		if (rand == 5) {
			problemType = new ModularProblem();
		}

		if (rand == 6) {
			problemType = new DifferenceOfTwoSquaresProblem();
		}

		if (rand == 7) {
			problemType = new BaseSwitchProblem();
		}
		return problemType;
	}
}
