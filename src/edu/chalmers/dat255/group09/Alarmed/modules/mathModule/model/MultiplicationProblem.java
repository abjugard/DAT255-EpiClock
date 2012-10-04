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
public final class MultiplicationProblem implements MathProblemType {

	private final static String OPERATOR = "*";
	private final static String PROBLEM_HEADER = "What Is The Product?";

	public int getResult(int[] numbers) {
		int product = 1;
		for (int i = 0; i < numbers.length; i++) {
			product *= numbers[i];
		}
		return product;
	}

	@Override
	public String getProblemHeader() {
		return PROBLEM_HEADER;
	}

	@Override
	public String toString() {
		return OPERATOR;
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
		int numberOfNumbers = 2;
		int upperLimit = 10;
		int lowerLimit = 0;

		return generateRandomNumbers(numberOfNumbers, upperLimit, lowerLimit);

	}

	private int[] generateMediumProblem() {
		int numberOfNumbers = 3;
		int upperLimit = 10;
		int lowerLimit = 0;

		return generateRandomNumbers(numberOfNumbers, upperLimit, lowerLimit);
	}

	private int[] generateHardProblem() {
		int numberOfNumbers = 3;
		int upperLimit = 15;
		int lowerLimit = 10;

		return generateRandomNumbers(numberOfNumbers, upperLimit, lowerLimit);
	}

	private int[] generateRandomNumbers(int numberOfNumbers, int upperLimit,
			int lowerLimit) {
		int[] numbers;
		numbers = new int[numberOfNumbers];

		for (int i = 0; i < numberOfNumbers; i++) {
			numbers[i] = generateRandomNumber(lowerLimit, upperLimit);
		}
		return numbers;
	}

	private int generateRandomNumber(int lowerLimit, int upperLimit) {
		int diff = upperLimit - lowerLimit;

		return (int) (lowerLimit + diff * Math.random());
	}

}
