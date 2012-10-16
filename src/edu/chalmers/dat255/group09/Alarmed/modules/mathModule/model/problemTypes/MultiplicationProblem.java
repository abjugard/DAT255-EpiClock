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
package edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes;

import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.util.RandomUtil;

/**
 * A class representing an AdditionProblem. The problem generates two or more
 * numbers. The user solves the problem by calculating the product of the
 * factors.
 * 
 * @author Joakim Persson
 * 
 */
public final class MultiplicationProblem extends MathProblemType {

	private static final String OPERATOR = "*";
	private static final String PROBLEM_HEADER = "What Is The Product?";

	@Override
	public int getResult(int[] numbers) {
		int product = 1;
		for (int i = 0; i < numbers.length; i++) {
			product *= numbers[i];
		}
		return product;
	}

	@Override
	public String getProblemHeader() {
		return PROBLEM_HEADER;
	}

	/**
	 * Generate numbers for an easy multiplication problem.
	 * 
	 * @return An array with random numbers with respect to an easy problem.
	 */
	@Override
	protected int[] generateEasyProblem() {
		int numberOfNumbers = 2;
		int upperLimit = 10;
		int lowerLimit = 0;

		return RandomUtil.generateRandomNumbers(numberOfNumbers, upperLimit,
				lowerLimit);

	}

	/**
	 * Generate numbers for an medium multiplication problem.
	 * 
	 * @return An array with random numbers with respect to an medium problem.
	 */
	@Override
	protected int[] generateMediumProblem() {
		int numberOfNumbers = 3;
		int upperLimit = 10;
		int lowerLimit = 0;

		return RandomUtil.generateRandomNumbers(numberOfNumbers, upperLimit,
				lowerLimit);
	}

	/**
	 * Generate numbers for an hard multiplication problem.
	 * 
	 * @return An array with random numbers with respect to an hard problem.
	 */
	@Override
	protected int[] generateHardProblem() {
		int numberOfNumbers = 3;
		int upperLimit = 15;
		int lowerLimit = 10;

		return RandomUtil.generateRandomNumbers(numberOfNumbers, upperLimit,
				lowerLimit);
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
