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
package edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model;

import java.util.List;

import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.constants.Difficulty;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.util.PrimeUtil;

/**
 * 
 * @author Joakim Persson
 * 
 *         the user gets four numbers of which one is prime number and should
 *         pick out the prime number
 */
// TODO add better documentation
public class PrimeProblem implements MathProblemType {
	private final static String PROBLEM_HEADER = "Which Number Is Prime?";
	private final static int NBR_OF_NUMBERS = 4;

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

	@Override
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
		}

		return nbrs;
	}

	private int[] generateEasyProblem() {
		int lowerLimit = 0;
		int upperLimit = 30;
		int deltaToPrime = 10;

		return generateRandomNumbersAndPrime(lowerLimit, upperLimit,
				deltaToPrime);
	}

	private int[] generateMediumProblem() {
		int lowerLimit = 30;
		int upperLimit = 50;
		int deltaToPrime = 15;

		return generateRandomNumbersAndPrime(lowerLimit, upperLimit,
				deltaToPrime);
	}

	private int[] generateHardProblem() {
		int lowerLimit = 50;
		int upperLimit = 100;
		int deltaToPrime = 20;

		return generateRandomNumbersAndPrime(lowerLimit, upperLimit,
				deltaToPrime);
	}

	private int[] generateRandomNumbersAndPrime(int lowerLimit, int upperLimit,
			int delta) {
		List<Integer> primeList = PrimeUtil
				.getPrimeList(lowerLimit, upperLimit);

		int primeNumberIndex = getRandomIndex(primeList.size());

		int primeNumber = primeList.get(primeNumberIndex);

		return getRandomNumbers(primeNumber, delta);

	}

	private int getRandomIndex(int size) {
		int lowerLimit = 0;
		int diff = size - lowerLimit;

		return getRandomNumberWithInRange(lowerLimit, diff);
	}

	private int[] getRandomNumbers(int primeNumber, int delta) {

		int[] nbrs = new int[NBR_OF_NUMBERS];

		nbrs[0] = primeNumber;

		for (int i = 1; i < NBR_OF_NUMBERS; i++) {
			nbrs[i] = getRandomNumber(primeNumber, nbrs, delta);
		}

		return randomizeOrder(nbrs);
	}

	private int getRandomNumber(int primeNumber, int[] previousNumbers,
			int delta) {
		int lowerLimit = primeNumber - delta;
		int upperLimit = primeNumber + delta;

		int number = 0;

		do {
			number = getRandomNumberWithInRange(lowerLimit, upperLimit);
		} while (!isValidNumber(previousNumbers, number));

		return number;
	}

	private int getRandomNumberWithInRange(int lowerLimit, int upperLimit) {
		int diff = upperLimit - lowerLimit;
		return (int) (lowerLimit + diff * Math.random());
	}

	private boolean isValidNumber(int[] previousNumbers, int number) {

		for (int i : previousNumbers) {
			if (i == number) {
				return false;
			}
		}

		return !(PrimeUtil.isPrime(number));
	}

	private int[] randomizeOrder(int[] nbrs) {
		int[] randomizedArray = initEmptyArray(nbrs);
		int index = 0;
		int nbrsToPlace = nbrs.length;

		while (index < nbrsToPlace) {

			int newIndex = getRandomNumberWithInRange(0, nbrs.length);

			if (randomizedArray[newIndex] == -1) {
				randomizedArray[newIndex] = nbrs[index];
				index++;
			}

		}

		return randomizedArray;
	}

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
