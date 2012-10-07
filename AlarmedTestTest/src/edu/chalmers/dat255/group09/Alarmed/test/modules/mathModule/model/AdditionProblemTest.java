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
package edu.chalmers.dat255.group09.Alarmed.test.modules.mathModule.model;

import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.AdditionProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.constants.Difficulty;

/**
 * 
 * @author Joakim Persson
 * 
 */
public class AdditionProblemTest extends AndroidTestCase {

	private final static int ITERATIONS = 100;
	private AdditionProblem additionProblem;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		additionProblem = new AdditionProblem();
	}

	public void testGetPromlemHeader() {
		String expected = "What Is The Sum?";
		String actual = additionProblem.getProblemHeader();
		assertEquals(expected, actual);
	}

	public void testGetFormattedString() {
		int[] nbrs = { 3, 4, 5 };
		String expected = "3 + 4 + 5 = ?";
		String actual = additionProblem.getFormattedProblem(nbrs);
		assertEquals(expected, actual);
	}

	public void testGetResult() {
		int actualResult = additionProblem.getResult(new int[] { 6, 6 });
		int expectedResult = 12;
		assertEquals(expectedResult, actualResult);

		actualResult = additionProblem.getResult(new int[] { 2, -1 });
		expectedResult = 1;
		assertEquals(expectedResult, actualResult);

		actualResult = additionProblem.getResult(new int[] { 5, -5, 1 });
		expectedResult = 1;
		assertEquals(expectedResult, actualResult);
	}

	public void testGenerateEasyProblem() {
		int lowerLimit = 0;
		int upperLimit = 10;
		int expectedNbrOfNumbers = 2;

		testAdditionProblem(Difficulty.EASY, lowerLimit, upperLimit,
				expectedNbrOfNumbers);
	}

	public void testGenerateMediumProblems() {
		int lowerLimit = 0;
		int upperLimit = 10;
		int expectedNbrOfNumbers = 3;

		testAdditionProblem(Difficulty.MEDIUM, lowerLimit, upperLimit,
				expectedNbrOfNumbers);
	}

	public void testGenerateHardProblems() {
		int lowerLimit = 10;
		int upperLimit = 500;
		int expectedNbrOfNumbers = 3;

		testAdditionProblem(Difficulty.HARD, lowerLimit, upperLimit,
				expectedNbrOfNumbers);
	}

	private void testAdditionProblem(Difficulty difficulty, int lowerLimit,
			int upperLimit, int expectedNbrOfNumbers) {
		int[] nbrs = null;

		for (int i = 0; i < ITERATIONS; i++) {
			nbrs = additionProblem.generateNumbers(difficulty);
			assertTrue(isCorrectNbrOfNumbers(expectedNbrOfNumbers, nbrs));
			assertTrue(isNumbersInRange(nbrs, lowerLimit, upperLimit));
			assertTrue(isNumbersNonNegative(nbrs));
		}

	}

	private boolean isCorrectNbrOfNumbers(int expectedNbrOfNumbers, int[] nbrs) {
		return expectedNbrOfNumbers == nbrs.length;
	}

	private boolean isNumbersInRange(int[] nbrs, int lowerLimit, int upperLimit) {
		for (int number : nbrs) {
			if (number < lowerLimit || number >= upperLimit) {
				return false;
			}
		}

		return true;
	}

	private boolean isNumbersNonNegative(int[] numbers) {
		for (int number : numbers) {
			if (number < 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		additionProblem = null;
	}
}
