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

import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.constants.Difficulty;

/**
 * A class representing a Difference Of Two Squares Problem. The problem is on
 * the form (a-b)(a+b), where the user is asked to calculate the product.
 * 
 * @author Joakim Persson
 * 
 */
// TODO figure out how to handle more than two numbers
public final class DifferenceOfTwoSquaresProblem implements MathProblemType {

	private static final String PROBLEM_HEADER = "What is the product?";
	private static final int NBR_OF_NUMBERS = 2;

	@Override
	public int getResult(int[] numbers) {
		// calculate a^2-b^2
		int a = numbers[0];
		int b = numbers[1];
		int power = 2;

		return (int) (Math.pow(a, power) - Math.pow(b, power));
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
		default:
			break;
		}

		return nbrs;
	}

	private int[] generateEasyProblem() {
		int lowerLimit = 1;
		int upperLimit = 5;
		return generateRandomNumbers(lowerLimit, upperLimit);
	}

	private int[] generateMediumProblem() {
		int lowerLimit = 4;
		int upperLimit = 8;

		return generateRandomNumbers(lowerLimit, upperLimit);
	}

	private int[] generateHardProblem() {
		int lowerLimit = 7;
		int upperLimit = 11;

		return generateRandomNumbers(lowerLimit, upperLimit);
	}

	private int[] generateRandomNumbers(int lowerLimit, int upperLimit) {
		int[] nbrs = new int[NBR_OF_NUMBERS];

		for (int i = 0; i < NBR_OF_NUMBERS; i++) {
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
		int a = nbrs[0];
		int b = nbrs[1];

		StringBuilder builder = new StringBuilder();

		builder.append("(" + a + "-" + b + ")");
		builder.append("(" + a + "+" + b + ")");

		return builder.toString();
	}

}
