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
package edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes;

import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.constants.Difficulty;

/**
 * An abstract class for the math problem types used in the math problem module.
 * 
 * @author Joakim Persson
 * 
 */
public abstract class MathProblemType {

	/**
	 * For these numbers calculate the correct answer to the problem.
	 * 
	 * @param numbers
	 *            An array containing the numbers to evaluate
	 * @return The result of the numbers in the array.
	 */
	public abstract int getResult(int[] numbers);

	/**
	 * Generate random numbers customized for the problem based on the
	 * difficulty.
	 * 
	 * @param difficulty
	 *            The difficulty of the problem
	 * @return An array of random numbers
	 */
	public int[] generateNumbers(Difficulty difficulty) {
		int[] nbrs = null;

		switch (difficulty) {
		case EASY:
			nbrs = generateEasyProblem();
			break;
		case MEDIUM:
			nbrs = generateMediumProblem();
			break;
		case HARD:
			nbrs = generateHardProblem();
			break;
		default:
			break;
		}

		return nbrs;
	}

	/**
	 * Generate an array of random numbers, with respect to being an easy
	 * problem.
	 * 
	 * @return An random array of numbers, with respect to being an easy
	 *         problem.
	 */
	protected abstract int[] generateEasyProblem();

	/**
	 * Generate an array of random numbers, with respect to being an medium
	 * problem.
	 * 
	 * @return An random array of numbers, with respect to being an easy
	 *         problem.
	 */
	protected abstract int[] generateMediumProblem();

	/**
	 * Generate an array of random numbers, with respect to being an medium
	 * problem.
	 * 
	 * @return An random array of numbers, with respect to being an medium
	 *         problem.
	 */
	protected abstract int[] generateHardProblem();

	/**
	 * Get The Header/Title for the problem.
	 * 
	 * @return The Title of the problem
	 */
	public abstract String getProblemHeader();

	/**
	 * Get a formatted output of the current problem.
	 * 
	 * @param nbrs
	 *            The numbers in the current problem
	 * @return A formatted String
	 */
	public abstract String getFormattedProblem(int[] nbrs);

}
