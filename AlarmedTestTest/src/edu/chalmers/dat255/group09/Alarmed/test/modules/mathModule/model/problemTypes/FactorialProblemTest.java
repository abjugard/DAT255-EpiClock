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
package edu.chalmers.dat255.group09.Alarmed.test.modules.mathModule.model.problemTypes;

import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.constants.Difficulty;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.FactorialProblem;

/**
 * 
 * @author Joakim Persson
 * 
 */
public class FactorialProblemTest extends AndroidTestCase {

	private static final int ITERATIONS = 100;
	private FactorialProblem factorialProblem;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		factorialProblem = new FactorialProblem();
	}

	public void testGetPromlemHeader() {
		String expected = "What is the sum?";
		String actual = factorialProblem.getProblemHeader();
		assertEquals(expected, actual);
	}

	public void testGetFormattedString() {
		int[] nbrs = { 3, 4 };
		String expected = "3! + 4! = ?";
		String actual = factorialProblem.getFormattedProblem(nbrs);
		assertEquals(expected, actual);
	}

	public void testGetResult() {

		int actualResult = factorialProblem.getResult(new int[] { 6, 2, 3 });
		int expectedResult = 728;
		assertEquals(expectedResult, actualResult);

		actualResult = factorialProblem.getResult(new int[] { 0, 1 });
		expectedResult = 2;
		assertEquals(expectedResult, actualResult);

		actualResult = factorialProblem.getResult(new int[] { 4, 3 });
		expectedResult = 30;
		assertEquals(expectedResult, actualResult);
	}

	public void testGenerateEasyProblem() {
		int lowerLimit = 0;
		int upperLimit = 6;
		int expectedNbrOfNumbers = 2;

		testFactorialProblems(Difficulty.EASY, lowerLimit, upperLimit,
				expectedNbrOfNumbers);
	}

	public void testGenerateMediumProblems() {
		int lowerLimit = 0;
		int upperLimit = 30;
		int expectedNbrOfNumbers = 3;

		testFactorialProblems(Difficulty.MEDIUM, lowerLimit, upperLimit,
				expectedNbrOfNumbers);
	}

	public void testGenerateHardProblems() {
		int lowerLimit = 0;
		int upperLimit = 8;
		int expectedNbrOfNumbers = 2;

		testFactorialProblems(Difficulty.HARD, lowerLimit, upperLimit,
				expectedNbrOfNumbers);
	}

	private void testFactorialProblems(Difficulty difficulty, int lowerLimit,
			int upperLimit, int expectedNbrOfNumbers) {
		int[] nbrs;
		for (int i = 0; i < ITERATIONS; i++) {
			nbrs = factorialProblem.generateNumbers(difficulty);
			assertTrue(isCorrectNbrOfNumbers(expectedNbrOfNumbers, nbrs));
			assertTrue(isNumbersInRange(nbrs, lowerLimit, upperLimit));
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

	@Override
	protected void tearDown() throws Exception {

		super.tearDown();
		factorialProblem = null;
	}
}
