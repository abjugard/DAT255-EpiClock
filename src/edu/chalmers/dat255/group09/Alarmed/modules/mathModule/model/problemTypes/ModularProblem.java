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

import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.constants.Difficulty;

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
// TODO add better method documentation
public class ModularProblem implements MathProblemType {

	private final static String PROBLEM_HEADER = "Solve for x!";
	private final static int NBR_OF_NUMBERS = 2;

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

	private int getModulus(int b, int n) {
		// a = b mod n
		int a = b % n;

		if (a < 0) {
			a += n;
		}
		return a;
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

		return generateRandomNumbers(lowerLimit, upperLimit);
	}

	private int[] generateMediumProblem() {
		int lowerLimit = -30;
		int upperLimit = 30;

		return generateRandomNumbers(lowerLimit, upperLimit);
	}

	private int[] generateHardProblem() {
		int lowerLimit = -50;
		int upperLimit = 50;

		return generateRandomNumbers(lowerLimit, upperLimit);
	}

	private int[] generateRandomNumbers(int lowerLimit, int upperLimit) {

		int[] nbrs = new int[NBR_OF_NUMBERS];

		nbrs[0] = getRandomNumberWithInRange(lowerLimit, upperLimit);
		nbrs[1] = getRandomModulusNumber(upperLimit);

		return nbrs;
	}

	private int getRandomModulusNumber(int upperLimit) {
		return getRandomNumberWithInRange(0, upperLimit);
	}

	private int getRandomNumberWithInRange(int lowerLimit, int upperLimit) {
		int diff = upperLimit - lowerLimit;
		return (int) (lowerLimit + diff * Math.random());
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

	private String getFormattedStringForTwoNumbers(int[] nbrs) {
		StringBuilder builder = new StringBuilder("");
		builder.append("x = ");
		builder.append(nbrs[0] + " ");
		builder.append("mod ");
		builder.append(nbrs[1]);
		return builder.toString();
	}

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
