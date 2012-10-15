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

import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.util.RandomUtil;

/**
 * A class representing a FactorialProblem. The problem generates two or more
 * factorial factors. The user solves the problem by adding upp the sum of the
 * factorials
 * 
 * @author Joakim Persson
 * 
 */
public final class FactorialProblem extends MathProblemType {

	private static final String PROBLEM_HEADER = "What is the sum?";

	@Override
	public int getResult(int[] numbers) {
		int sum = 0;

		for (int number : numbers) {
			sum += factorial(number);
		}

		return sum;
	}

	/**
	 * Calculates the factorial of a specified number.
	 * 
	 * @param number
	 *            The factorial number
	 * @return The calculated factorial
	 */
	private int factorial(int number) {
		if (number <= 1) {
			return 1;
		} else {
			return (number * factorial(number - 1));
		}
	}

	/**
	 * Generate numbers for an easy factorial problem.
	 * 
	 * @return An array of random numbers, with respect to being an easy
	 *         factorial problem.
	 */
	@Override
	protected int[] generateEasyProblem() {
		int lowerLimit = 4;
		int upperLimit = 6;
		int nbrOfFactors = 2;

		return RandomUtil.generateRandomNumbers(nbrOfFactors, lowerLimit,
				upperLimit);
	}

	/**
	 * Generate numbers for an medium factorial problem.
	 * 
	 * @return An array of random numbers, with respect to being an medium
	 *         factorial problem.
	 */
	@Override
	protected int[] generateMediumProblem() {
		int lowerLimit = 4;
		int upperLimit = 6;
		int nbrOfFactors = 3;

		return RandomUtil.generateRandomNumbers(nbrOfFactors, lowerLimit,
				upperLimit);
	}

	/**
	 * Generate numbers for an hard factorial problem.
	 * 
	 * @return An array of random numbers, with respect to being an hard
	 *         factorial problem.
	 */
	@Override
	protected int[] generateHardProblem() {
		int lowerLimit = 6;
		int upperLimit = 8;
		int nbrOfFactors = 2;
		return RandomUtil.generateRandomNumbers(nbrOfFactors, lowerLimit,
				upperLimit);
	}

	@Override
	public String getProblemHeader() {
		return PROBLEM_HEADER;
	}

	@Override
	public String getFormattedProblem(int[] nbrs) {
		StringBuilder builder = new StringBuilder();
		String operatorSign = "+";

		for (int i = 0; i < nbrs.length; i++) {
			builder.append(nbrs[i] + "! ");
			if ((i + 1) != nbrs.length) {
				builder.append(operatorSign + " ");
			}
		}

		builder.append("= ?");
		return builder.toString();
	}

}
