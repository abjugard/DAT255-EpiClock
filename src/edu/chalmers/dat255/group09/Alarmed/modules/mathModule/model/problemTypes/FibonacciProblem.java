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

import java.util.ArrayList;
import java.util.List;

import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.util.RandomUtil;

/**
 * A class representing a FibonacciProblem. The problem generates a subset of
 * the fibonacci sequence. The user solves the problem calculating the next term
 * in the sequence.
 * 
 * @author Joakim Persson
 * 
 */
public class FibonacciProblem extends MathProblemType {

	private static final String PROBLEM_HEADER = "What is the next term?";
	private static final int NBR_OF_NUMBERS = 4;

	@Override
	public int getResult(int[] numbers) {
		int index = numbers.length - 1;
		return numbers[index] + numbers[index - 1];
	}

	/**
	 * Create an array of random numbers generated with respect to being an easy
	 * problem. The numbers are a subset from the fibonacci sequence.
	 * 
	 * @return An array of random numbers, with respect to being an easy
	 *         problem.
	 */
	@Override
	protected int[] generateEasyProblem() {
		int lowerLimit = 0;
		int upperLimit = 10;

		return generateRandomSequence(lowerLimit, upperLimit);
	}

	/**
	 * Create an array of random numbers generated with respect to being an
	 * medium level problem. The numbers are a subset from the fibonacci
	 * sequence.
	 * 
	 * @return An array of random numbers, with respect to being an medium
	 *         problem.
	 */
	@Override
	protected int[] generateMediumProblem() {
		int lowerLimit = 8;
		int upperLimit = 30;

		return generateRandomSequence(lowerLimit, upperLimit);
	}

	/**
	 * Create an array of random numbers generated with respect to being an hard
	 * problem. The numbers are a subset from the fibonacci sequence.
	 * 
	 * @return An array of random numbers, with respect to being an hard
	 *         problem.
	 */
	@Override
	protected int[] generateHardProblem() {
		int lowerLimit = 30;
		int upperLimit = 100;

		return generateRandomSequence(lowerLimit, upperLimit);
	}

	/**
	 * Generates an random subset of the fibonacci sequence. The sequence is
	 * staring with an random number form the sequence in the interval
	 * [lowerLimit,upperLimit).
	 * 
	 * @param lowerLimit
	 *            The lower limit for the starting number.
	 * @param upperLimit
	 *            The upper limit for starting number.
	 * @return An array with numbers from an subset of the fibonacci sequence.
	 */
	private int[] generateRandomSequence(int lowerLimit, int upperLimit) {

		List<Integer> fibonacciNumbers = getFibonacciSequence(lowerLimit,
				upperLimit);

		int fibonacciNumberIndex = getRandomIndex(fibonacciNumbers.size());

		int startingNumber = fibonacciNumbers.get(fibonacciNumberIndex);

		int surrondingNumberIndex = fibonacciNumberIndex + 1;

		if (surrondingNumberIndex == fibonacciNumbers.size()) {
			surrondingNumberIndex = fibonacciNumberIndex - 1;
		}

		int surrondingNumber = fibonacciNumbers.get(surrondingNumberIndex);

		return getSequence(startingNumber, surrondingNumber);
	}

	/**
	 * Get an random index of a list.
	 * 
	 * @param size
	 *            The length of the list
	 * @return An random index within the size of an list
	 */
	private int getRandomIndex(int size) {
		int lowerLimit = 0;
		int diff = size - lowerLimit;

		return RandomUtil.generateRandomNumber(lowerLimit, diff);
	}

	/**
	 * Create a subset of the fibonacci sequence around two starting numbers.
	 * 
	 * @param startingNumber
	 *            The first term in the sequence
	 * @param surrondingNumber
	 *            A term either greather than or less than startingNumber
	 * @return A subset of the fibonacci sequence
	 */
	private int[] getSequence(int startingNumber, int surrondingNumber) {
		int[] nbrs = new int[NBR_OF_NUMBERS];

		if (startingNumber > surrondingNumber) {
			nbrs[0] = surrondingNumber;
			nbrs[1] = startingNumber;
		} else {
			nbrs[0] = startingNumber;
			nbrs[1] = surrondingNumber;
		}

		for (int i = 2; i < NBR_OF_NUMBERS; i++) {
			nbrs[i] = nbrs[i - 1] + nbrs[i - 2];
		}

		return nbrs;
	}

	/**
	 * Get a list of the terms in the fibonacci sequence between two limits.
	 * 
	 * @param lowerLimit
	 *            The lower limit
	 * @param upperLimit
	 *            The upper limit
	 * @return A list of terms in the fibonacci sequence
	 */
	private List<Integer> getFibonacciSequence(int lowerLimit, int upperLimit) {
		List<Integer> fibonacciSequence = new ArrayList<Integer>();

		fibonacciSequence.add(1);
		fibonacciSequence.add(1);

		int index = 2;
		while (true) {

			int nextTerm = fibonacciSequence.get(index - 1)
					+ fibonacciSequence.get(index - 2);

			if (nextTerm > upperLimit) {
				break;
			}

			fibonacciSequence.add(nextTerm);
			index++;
		}

		return removeTermsBelowLowerLimit(fibonacciSequence, lowerLimit);
	}

	/**
	 * Removes any term in an list which is less than the lower limit.
	 * 
	 * @param sequence
	 *            An list of integers
	 * @param lowerLimit
	 *            The lower limit
	 * @return A list of integers with terms greater than or equal to lower
	 *         limit
	 */
	private List<Integer> removeTermsBelowLowerLimit(List<Integer> sequence,
			int lowerLimit) {
		List<Integer> fibonacciSequence = new ArrayList<Integer>();

		for (int number : sequence) {
			if (number >= lowerLimit) {
				fibonacciSequence.add(number);
			}
		}

		return fibonacciSequence;
	}

	@Override
	public String getFormattedProblem(int[] nbrs) {
		StringBuilder builder = new StringBuilder();
		String seperatorSign = ", ";

		for (int i = 0; i < nbrs.length; i++) {
			builder.append(nbrs[i] + "");
			if ((i + 1) != nbrs.length) {
				builder.append(seperatorSign);
			}
		}

		builder.append(", ?");
		return builder.toString();
	}

	@Override
	public String getProblemHeader() {
		return PROBLEM_HEADER;
	}

}
