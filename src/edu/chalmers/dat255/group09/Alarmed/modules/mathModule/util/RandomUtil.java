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
package edu.chalmers.dat255.group09.Alarmed.modules.mathModule.util;

/**
 * A util class for generating random numbers.
 * 
 * @author Joakim Persson
 * 
 */
public final class RandomUtil {

	/**
	 * A singleton and cannot be instantiated.
	 */
	private RandomUtil() {
	}

	/**
	 * Create an array of random numbers.
	 * 
	 * @param numberOfNumbers
	 *            The number of numbers in the array
	 * @param upperLimit
	 *            The maximum number to be generated
	 * @param lowerLimit
	 *            The smallest number to be created
	 * @return An array of random numbers
	 */
	public static int[] generateRandomNumbers(int numberOfNumbers,
			int upperLimit, int lowerLimit) {
		int[] numbers;
		numbers = new int[numberOfNumbers];

		for (int i = 0; i < numberOfNumbers; i++) {
			numbers[i] = generateRandomNumber(lowerLimit, upperLimit);
		}
		return numbers;
	}

	/**
	 * Get a random number within and interval [lowerLimit, upperLimit).
	 * 
	 * @param lowerLimit
	 *            The problems lowerlimit
	 * @param upperLimit
	 *            The problems upperlimit
	 * @return An random integer within the interval
	 */
	public static int generateRandomNumber(int lowerLimit, int upperLimit) {
		int diff = upperLimit - lowerLimit;

		return (int) (lowerLimit + diff * Math.random());
	}

}
