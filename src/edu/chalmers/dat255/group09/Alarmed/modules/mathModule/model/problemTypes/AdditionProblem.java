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
 * A class representing an AdditionProblem. The problem generates two or more
 * numbers. The user solves the problem by calculating the sum of the factors.
 * 
 * @author Joakim Persson
 * 
 */
public final class AdditionProblem extends MathProblemType {

	private static final String PROBLEM_HEADER = "What Is The Sum?";
	private static final String OPERATOR = "+";

	@Override
	public String getProblemHeader() {
		return PROBLEM_HEADER;
	}

	@Override
	public int getResult(int[] numbers) {
		int sum = 0;
		for (int i = 0; i < numbers.length; i++) {
			sum += numbers[i];
		}
		return sum;

	}

	/**
	 * Generate an easy addition problem with respect to being an easy problem.
	 * 
	 * @return An array of random numbers, with respect to being an easy
	 *         problem.
	 */
	@Override
	protected int[] generateEasyProblem() {
		int numberOfNumbers = 2;
		int upperLimit = 10;
		int lowerLimit = 0;

		return RandomUtil.generateRandomNumbers(numberOfNumbers, lowerLimit,
				upperLimit);

	}

	/**
	 * Generate an medium addition problem with respect to being an easy
	 * problem.
	 * 
	 * @return An array of random numbers, with respect to being an medium level
	 *         problem.
	 */
	@Override
	protected int[] generateMediumProblem() {
		int numberOfNumbers = 3;
		int upperLimit = 10;
		int lowerLimit = 0;

		return RandomUtil.generateRandomNumbers(numberOfNumbers, lowerLimit,
				upperLimit);
	}

	/**
	 * Generate an easy addition problem with respect to being an hard problem.
	 * 
	 * @return An array of random numbers, with respect to being an hard
	 *         problem.
	 */
	@Override
	protected int[] generateHardProblem() {
		int numberOfNumbers = 3;
		int upperLimit = 50;
		int lowerLimit = 10;

		return RandomUtil.generateRandomNumbers(numberOfNumbers, lowerLimit,
				upperLimit);
	}

	@Override
	public String getFormattedProblem(int[] nbrs) {
		StringBuilder builder = new StringBuilder();
		String operatorSign = OPERATOR;

		for (int i = 0; i < nbrs.length; i++) {
			builder.append(nbrs[i] + " ");
			if ((i + 1) != nbrs.length) {
				builder.append(operatorSign + " ");
			}
		}

		builder.append("= ?");
		return builder.toString();

	}
}
