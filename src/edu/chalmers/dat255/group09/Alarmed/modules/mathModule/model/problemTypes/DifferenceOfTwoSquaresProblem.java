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
 * A class representing a Difference Of Two Squares Problem. The problem is on
 * the form (a-b)(a+b), where the user is asked to calculate the product.
 * 
 * @author Joakim Persson
 * 
 */
public final class DifferenceOfTwoSquaresProblem extends MathProblemType {

	private static final String PROBLEM_HEADER = "What is the product?";
	private static final int NBR_OF_NUMBERS = 2;

	@Override
	public int getResult(int[] numbers) {
		// calculates a^2-b^2
		int a = numbers[0];
		int b = numbers[1];
		int power = 2;

		return (int) (Math.pow(a, power) - Math.pow(b, power));
	}

	@Override
	protected int[] generateEasyProblem() {
		int lowerLimit = 1;
		int upperLimit = 5;
		return RandomUtil.generateRandomNumbers(NBR_OF_NUMBERS, lowerLimit,
				upperLimit);
	}

	@Override
	protected int[] generateMediumProblem() {
		int lowerLimit = 4;
		int upperLimit = 8;

		return RandomUtil.generateRandomNumbers(NBR_OF_NUMBERS, lowerLimit,
				upperLimit);
	}

	@Override
	protected int[] generateHardProblem() {
		int lowerLimit = 7;
		int upperLimit = 11;

		return RandomUtil.generateRandomNumbers(NBR_OF_NUMBERS, lowerLimit,
				upperLimit);
	}

	@Override
	public String getProblemHeader() {
		return PROBLEM_HEADER;
	}

	@Override
	public String getFormattedProblem(int[] nbrs) {
		int a = nbrs[0];
		int b = nbrs[1];

		StringBuilder builder = new StringBuilder();

		builder.append("(" + a + "-" + b + ")");
		builder.append("(" + a + "+" + b + ")");

		return builder.toString();
	}

}
