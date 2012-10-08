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
 * @author Joakim Persson
 * 
 */
public class ModularProblem implements MathProblemType {

	private final static String PROBLEM_HEADER = "Solve for x!";
	private final static int NBR_OF_NUMBERS = 2;

	@Override
	public int getResult(int[] numbers) {
		// a = b mod n
		int b = numbers[0];
		int n = numbers[1];
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
		// TODO not so good...
		StringBuilder builder = new StringBuilder("");
		if (nbrs.length == 2) {
			builder.append("x = ");
			builder.append(nbrs[0] + " ");
			builder.append("mod ");
			builder.append(nbrs[1]);
		}
		return builder.toString();
	}

}
