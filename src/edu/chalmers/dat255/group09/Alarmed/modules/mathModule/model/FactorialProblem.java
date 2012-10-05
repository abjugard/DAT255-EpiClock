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

import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.constants.Difficulty;

/**
 * 
 * @author Joakim Persson
 * 
 */
// TODO add better documentation
public class FactorialProblem implements MathProblemType {

	private final static String PROBLEM_HEADER = "What is the sum?";

	// They have to summarize two or three factorial problems

	@Override
	public int getResult(int[] numbers) {
		int sum = 0;

		for (int number : numbers) {
			sum += factorial(number);
		}

		return sum;
	}

	private int factorial(int number) {
		if (number <= 1) {
			return 1;
		} else {
			return (number * factorial(number - 1));
		}
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
		int upperLimit = 6;
		int nbrOfFactors = 2;

		return generateRandomNumbers(nbrOfFactors, lowerLimit, upperLimit);
	}

	private int[] generateMediumProblem() {
		int lowerLimit = 0;
		int upperLimit = 6;
		int nbrOfFactors = 3;

		return generateRandomNumbers(nbrOfFactors, lowerLimit, upperLimit);
	}

	private int[] generateHardProblem() {
		int lowerLimit = 0;
		int upperLimit = 8;
		int nbrOfFactors = 2;
		return generateRandomNumbers(nbrOfFactors, lowerLimit, upperLimit);
	}

	private int[] generateRandomNumbers(int nbrOfFactors, int lowerLimit,
			int upperLimit) {
		int[] nbrs = new int[nbrOfFactors];

		for (int i = 1; i < nbrOfFactors; i++) {
			nbrs[i] = getRandomNumber(lowerLimit, upperLimit);
		}

		return nbrs;
	}

	private int getRandomNumber(int lowerLimit, int upperLimit) {
		int diff = upperLimit - lowerLimit;
		return (int) (lowerLimit + diff * Math.random());
	}

	@Override
	public String getProblemHeader() {
		return PROBLEM_HEADER;
	}

	@Override
	public String getFormattedProblem(int[] nbrs) {
		StringBuilder builder = new StringBuilder();
		String operatorSign = "+";

		for (int i = 0; i < nbrs.length; i++) {
			builder.append(nbrs[i] + "! ");
			if ((i + 1) != nbrs.length) {
				builder.append(operatorSign + " ");
			}
		}

		builder.append("= ?");
		return builder.toString();
	}

}
