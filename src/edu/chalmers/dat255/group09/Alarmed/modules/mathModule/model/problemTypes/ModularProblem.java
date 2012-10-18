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
 * A class representing a ModularProblem. The problem generates a problem on the
 * form: a = b mod n. The user is supplied with b and n. The user solves the
 * problem by calculating what b mod n is equal or just a.
 * 
 * The problem it optimated for two problems but if someone enter more than two
 * numbers, the last number in the array will be equal two n and b will be equal
 * to the sum of the numbers
 * 
 * @author Joakim Persson
 * 
 */
public final class ModularProblem extends MathProblemType {

	private static final String PROBLEM_HEADER = "Solve for x!";
	private static final int NBR_OF_NUMBERS = 2;

	@Override
	public int getResult(int[] numbers) {
		int result = 0;
		if (numbers.length == 2) {
			result = getModulus(numbers[0], numbers[1]);
		} else {
			int sum = 0;
			for (int i = 0; i < numbers.length - 1; i++) {
				sum += numbers[i];
			}
			result = getModulus(sum, numbers[numbers.length - 1]);
		}
		return result;
	}

	/**
	 * Calculate modulus on the form a = b mod n. This method calculates and
	 * returns a.
	 * 
	 * @param b
	 *            The b integer in the form a = b mod n.
	 * @param n
	 *            The n integer in the form a = b mod n.
	 * @return The calculated modulus.
	 */
	private int getModulus(int b, int n) {
		// a = b mod n
		int a = b % n;

		if (a < 0) {
			a += n;
		}
		return a;
	}

	@Override
	protected int[] generateEasyProblem() {
		final int lowerLimit = 0;
		final int upperLimit = 30;

		return generateRandomNumbers(lowerLimit, upperLimit);
	}

	@Override
	protected int[] generateMediumProblem() {
		final int lowerLimit = -30;
		final int upperLimit = 30;

		return generateRandomNumbers(lowerLimit, upperLimit);
	}

	@Override
	protected int[] generateHardProblem() {
		final int lowerLimit = -50;
		final int upperLimit = 50;

		return generateRandomNumbers(lowerLimit, upperLimit);
	}

	/**
	 * Generate random numbers for a modulus problem between a lower limit and
	 * an upper limit.
	 * 
	 * @param lowerLimit
	 *            The lower limit for the numbers
	 * @param upperLimit
	 *            The upper limit for the numbers
	 * @return An array with random numbers, with respect to an modulus problem.
	 */
	private int[] generateRandomNumbers(int lowerLimit, int upperLimit) {

		int[] nbrs = new int[NBR_OF_NUMBERS];

		nbrs[0] = RandomUtil.generateRandomNumber(lowerLimit, upperLimit);
		nbrs[1] = getRandomModulusNumber(upperLimit);

		return nbrs;
	}

	/**
	 * Generate the random modulus number. The modulus problem is on the form: a
	 * = b mon n. This method generates a random n, between 0 and upper limit.
	 * 
	 * @param upperLimit
	 *            The upper limit for the problem.
	 * @return An random modulus number.
	 */
	private int getRandomModulusNumber(int upperLimit) {
		return RandomUtil.generateRandomNumber(0, upperLimit);
	}

	@Override
	public String getProblemHeader() {
		return PROBLEM_HEADER;
	}

	@Override
	public String getFormattedProblem(int[] nbrs) {
		if (nbrs.length == 2) {
			return getFormattedStringForTwoNumbers(nbrs);
		} else {
			return getFormattedStringForMorThanTwoNumbers(nbrs);
		}

	}

	/**
	 * Generates a problem description string suited for two numbers.
	 * 
	 * @param nbrs
	 *            An array of two numbers.
	 * @return A string describing the current problem.
	 */
	private String getFormattedStringForTwoNumbers(int[] nbrs) {
		StringBuilder builder = new StringBuilder("");
		builder.append("x = ");
		builder.append(nbrs[0] + " ");
		builder.append("mod ");
		builder.append(nbrs[1]);
		return builder.toString();
	}

	/**
	 * Generates a problem description string suited for problems with more than
	 * two numbers.
	 * 
	 * @param nbrs
	 *            An array of more than two numbers.
	 * @return A string describing the current problem.
	 */
	private String getFormattedStringForMorThanTwoNumbers(int[] nbrs) {
		StringBuilder builder = new StringBuilder("");
		String operatorSign = "+";
		builder.append("x = ");

		for (int i = 0; i < nbrs.length - 1; i++) {
			builder.append(nbrs[i] + " ");
			if ((i + 1) != nbrs.length - 1) {
				builder.append(operatorSign + " ");
			}
		}

		builder.append("mod ");
		builder.append(nbrs[nbrs.length - 1]);
		return builder.toString();
	}

}
