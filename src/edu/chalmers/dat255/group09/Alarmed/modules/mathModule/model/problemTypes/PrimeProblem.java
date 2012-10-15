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

import java.util.List;

import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.util.PrimeUtil;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.util.RandomUtil;

/**
 * 
 * A class representing a PrimeProblem. The problem generates a list of number
 * and one of these numbers is a prime number and the other numbers is located
 * within a specified delta. The user solves the problem by picking out which
 * number is prime.
 * 
 * @author Joakim Persson
 * 
 */
public final class PrimeProblem extends MathProblemType {
	private static final String PROBLEM_HEADER = "Which Number Is Prime?";
	private static final int NBR_OF_NUMBERS = 4;

	@Override
	public int getResult(int[] numbers) {

		int primeNumber = 0;

		for (int number : numbers) {
			if (PrimeUtil.isPrime(number)) {
				primeNumber = number;
				break;
			}
		}

		return primeNumber;
	}

	/**
	 * Generates numbers based on the easy difficulty.
	 * 
	 * @return An array of number, including one prime number
	 */
	@Override
	protected int[] generateEasyProblem() {
		int lowerLimit = 0;
		int upperLimit = 30;
		int deltaToPrime = 10;

		return generateRandomNumbersAndPrime(lowerLimit, upperLimit,
				deltaToPrime);
	}

	/**
	 * Generates numbers based on the medium difficulty.
	 * 
	 * @return An array of number, including one prime number
	 */
	@Override
	protected int[] generateMediumProblem() {
		int lowerLimit = 30;
		int upperLimit = 50;
		int deltaToPrime = 15;

		return generateRandomNumbersAndPrime(lowerLimit, upperLimit,
				deltaToPrime);
	}

	/**
	 * Generates numbers based on the hard difficulty.
	 * 
	 * @return An array of number, including one prime number
	 */
	@Override
	protected int[] generateHardProblem() {
		int lowerLimit = 50;
		int upperLimit = 100;
		int deltaToPrime = 20;

		return generateRandomNumbersAndPrime(lowerLimit, upperLimit,
				deltaToPrime);
	}

	/**
	 * Generates an array of numbers containing only one prime number in the
	 * interval [lowerLimit, upperLimit) and the other numbers in in the
	 * interval [lowerLimit-delta, lowerLimit+delta).
	 * 
	 * @param lowerLimit
	 *            The lower limit for the prime number
	 * @param upperLimit
	 *            The upper limit for the prime number
	 * @param delta
	 *            The other numbers delta to the prime number
	 * @return An array of numbers
	 */
	private int[] generateRandomNumbersAndPrime(int lowerLimit, int upperLimit,
			int delta) {
		List<Integer> primeList = PrimeUtil
				.getPrimeList(lowerLimit, upperLimit);

		int primeNumberIndex = getRandomIndex(primeList.size());

		int primeNumber = primeList.get(primeNumberIndex);

		return getRandomNumbers(primeNumber, delta);

	}

	/**
	 * Get an random index of a list.
	 * 
	 * @param listLength
	 *            The length of the list
	 * @return An random index within the size of an list
	 */
	private int getRandomIndex(int listLength) {
		int lowerLimit = 0;
		int diff = listLength - lowerLimit;

		return RandomUtil.generateRandomNumber(lowerLimit, diff);
	}

	/**
	 * Get a random number within a delta to a prime number.
	 * 
	 * @param primeNumber
	 *            The prime number to generate numbers around
	 * @param delta
	 *            The delta to the prime
	 * @return An array of random numbers and the prime number
	 */
	private int[] getRandomNumbers(int primeNumber, int delta) {

		int[] nbrs = new int[NBR_OF_NUMBERS];

		nbrs[0] = primeNumber;

		for (int i = 1; i < NBR_OF_NUMBERS; i++) {
			nbrs[i] = getRandomNumber(primeNumber, nbrs, delta);
		}

		return randomizeOrder(nbrs);
	}

	/**
	 * Get a unique random number.
	 * 
	 * @param primeNumber
	 *            The prime number
	 * @param previousNumbers
	 *            The previous generated numbers
	 * @param delta
	 *            The delta to the prime number
	 * @return A unique random number
	 */
	private int getRandomNumber(int primeNumber, int[] previousNumbers,
			int delta) {
		int lowerLimit = primeNumber - delta;
		int upperLimit = primeNumber + delta;

		int number = 0;

		do {
			number = RandomUtil.generateRandomNumber(lowerLimit, upperLimit);
		} while (!isValidNumber(previousNumbers, number));

		return number;
	}

	/**
	 * Validates so a number is not already existing in an previous array and
	 * not is a prime number.
	 * 
	 * @param previousNumbers
	 *            The previous numbers in an array
	 * @param number
	 *            The new number
	 * @return If the newNumber exists or not
	 */
	private boolean isValidNumber(int[] previousNumbers, int number) {

		for (int i : previousNumbers) {
			if (i == number) {
				return false;
			}
		}

		return !(PrimeUtil.isPrime(number));
	}

	/**
	 * Randomize the order of the items in an array.
	 * 
	 * @param nbrs
	 *            The array to randomize
	 * @return An array with the items in random order
	 */
	private int[] randomizeOrder(int[] nbrs) {
		int[] randomizedArray = initEmptyArray(nbrs);
		int index = 0;
		int nbrsToPlace = nbrs.length;

		while (index < nbrsToPlace) {

			int newIndex = RandomUtil.generateRandomNumber(0, nbrs.length);

			if (randomizedArray[newIndex] == -1) {
				randomizedArray[newIndex] = nbrs[index];
				index++;
			}

		}

		return randomizedArray;
	}

	/**
	 * Create a new empty array, with the same length as another array. The
	 * array only contains -1.
	 * 
	 * @param nbrs
	 *            The array to have the same size as.
	 * @return A new array consisting of only -1.
	 */
	private int[] initEmptyArray(int[] nbrs) {
		int[] tmpArray = new int[nbrs.length];

		for (int i = 0; i < nbrs.length; i++) {
			tmpArray[i] = -1;
		}

		return tmpArray;
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

		return builder.toString();
	}

	@Override
	public String getProblemHeader() {
		return PROBLEM_HEADER;
	}

}
