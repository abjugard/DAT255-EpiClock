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
 * A class representing a base switch problem. All the problems is of a base 2
 * number. The user solves the problem by converting the base two number into an
 * base 10 number.
 * 
 * @author Joakim Persson
 * 
 */
public final class BaseSwitchProblem extends MathProblemType {

	private static final String PROBLEM_HEADER = "Convert to base ten";
	private static final int BASE = 2;

	@Override
	public int getResult(int[] numbers) {
		return convertBaseTwoToTen(numbers);
	}

	/**
	 * Covert an array representing an base two number into an base ten number.
	 * 
	 * @param nbrs
	 *            The array representing a base two number
	 * @return The number in base ten
	 */
	private int convertBaseTwoToTen(int[] nbrs) {
		// reverse the array
		int[] reversedNbrs = reverseArray(nbrs);
		int sum = 0;
		// then sum every position:
		// the the number at the position * 2^i
		for (int i = 0; i < reversedNbrs.length; i++) {
			sum += reversedNbrs[i] * Math.pow(BASE, i);
		}

		return sum;
	}

	/**
	 * A util method for reversing the position of the numbers in an array.
	 * 
	 * @param nbrs
	 *            The array to be reversed
	 * @return The same array but in an reversed order
	 */
	private int[] reverseArray(int[] nbrs) {
		int size = nbrs.length;
		int mid = size / 2;
		int temp;

		for (int i = 0; i < mid; i++) {
			temp = nbrs[i];
			nbrs[i] = nbrs[size - i - 1];
			nbrs[size - i - 1] = temp;
		}

		return nbrs;
	}

	/**
	 * Generate an easy base switch problem.
	 * 
	 * @return An array of numbers in base 2 representing an easy random base 10
	 *         number.
	 */
	@Override
	protected int[] generateEasyProblem() {
		final int lowerLimit = 1;
		final int upperLimit = 30;
		return generateRandomNumbers(lowerLimit, upperLimit);
	}

	/**
	 * Generate an medium base switch problem.
	 * 
	 * @return An array of numbers in base 2 representing an medium level random
	 *         base 10 number.
	 */
	@Override
	protected int[] generateMediumProblem() {
		final int lowerLimit = 30;
		final int upperLimit = 80;
		return generateRandomNumbers(lowerLimit, upperLimit);
	}

	/**
	 * Generate an easy hard switch problem.
	 * 
	 * @return An array of numbers in base 2 representing an hard random base 10
	 *         number.
	 */
	@Override
	protected int[] generateHardProblem() {
		final int lowerLimit = 80;
		final int upperLimit = 120;
		return generateRandomNumbers(lowerLimit, upperLimit);
	}

	/**
	 * Generates an array representing a random base two numbers. Ex: if the
	 * array is {1,1} then the base two number is 11.
	 * 
	 * @param lowerLimit
	 *            The lower limit for the random number
	 * @param upperLimit
	 *            The upper limit for the random number
	 * @return An array representing an random base 2 number
	 */
	private int[] generateRandomNumbers(int lowerLimit, int upperLimit) {
		int randomNumber = RandomUtil.generateRandomNumber(lowerLimit,
				upperLimit);
		return convertNumberToBaseTwo(randomNumber);
	}

	/**
	 * Convert a base ten number into an base two or binary number.
	 * 
	 * @param number
	 *            The number to be converted into base 2
	 * @return An array representing an base two number
	 */
	private int[] convertNumberToBaseTwo(int number) {
		String baseTwoNumber = converBaseTenToTwo(number);

		String[] stringNumbers = baseTwoNumber.split(" ");

		int[] numbers = new int[stringNumbers.length];

		for (int i = 0; i < stringNumbers.length; i++) {
			numbers[i] = Integer.parseInt(stringNumbers[i]);
		}

		return numbers;
	}

	/**
	 * Converts a base ten number into an base two number and return it as a
	 * string containing a whitespace between the number. Ex: 23 = 1 0 1 1 1.
	 * 
	 * @param number
	 *            The number to be converted into base two
	 * @return A string of the base two number
	 */
	private String converBaseTenToTwo(int number) {

		if (number < BASE) {
			return Integer.toString(number) + " ";
		} else {
			return converBaseTenToTwo(number / 2) + number % 2 + " ";
		}
	}

	@Override
	public String getProblemHeader() {
		return PROBLEM_HEADER;
	}

	@Override
	public String getFormattedProblem(int[] nbrs) {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < nbrs.length; i++) {
			builder.append(nbrs[i]);
			if (i != nbrs.length - 1) {
				builder.append(" ");
			}
		}
		return builder.toString();
	}

}
