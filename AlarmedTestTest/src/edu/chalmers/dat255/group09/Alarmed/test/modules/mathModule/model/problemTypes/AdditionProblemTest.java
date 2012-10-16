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
package edu.chalmers.dat255.group09.Alarmed.test.modules.mathModule.model.problemTypes;

import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.constants.Difficulty;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.AdditionProblem;

/**
 * An test class for the AdditionProblem class.
 * 
 * @author Joakim Persson
 * 
 */
public class AdditionProblemTest extends AndroidTestCase {

	private static final int ITERATIONS = 100;
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

	public void testGetResultForThreeNumbers() {
		int actualResult = additionProblem.getResult(new int[] { 5, -5, 1 });
		int expectedResult = 1;
		assertEquals(expectedResult, actualResult);
	}

	public void testGetResultForSixPlusSixe() {
		int actualResult = additionProblem.getResult(new int[] { 6, 6 });
		int expectedResult = 12;
		assertEquals(expectedResult, actualResult);
	}

	public void testGetResultForTwoPlusMinusOne() {
		int actualResult = additionProblem.getResult(new int[] { 2, -1 });
		int expectedResult = 1;
		assertEquals(expectedResult, actualResult);
	}

	public void testGenerateEasyProblem() {
		int lowerLimit = 0;
		int upperLimit = 10;
		int expectedNbrOfNumbers = 2;

		testAdditionProblemGenerateNumbers(Difficulty.EASY, lowerLimit,
				upperLimit, expectedNbrOfNumbers);
	}

	public void testGenerateMediumProblems() {
		int lowerLimit = 0;
		int upperLimit = 10;
		int expectedNbrOfNumbers = 3;

		testAdditionProblemGenerateNumbers(Difficulty.MEDIUM, lowerLimit,
				upperLimit, expectedNbrOfNumbers);
	}

	public void testGenerateHardProblems() {
		int lowerLimit = 10;
		int upperLimit = 500;
		int expectedNbrOfNumbers = 3;

		testAdditionProblemGenerateNumbers(Difficulty.HARD, lowerLimit,
				upperLimit, expectedNbrOfNumbers);
	}

	/**
	 * A method that runs several tests on the problems generateNumbers method.
	 * For example it checks if the problem returns the expected number of
	 * number, if they are in range or if the numbers are non negative. The
	 * method carries out the test by running the same tests on different set of
	 * random numbers.
	 * 
	 * @param difficulty
	 *            The problems difficulty
	 * @param lowerLimit
	 *            The lower limit for the numbers in the problem.
	 * @param upperLimit
	 *            The upper limit for the numbers in the problem.
	 * @param expectedNbrOfNumbers
	 *            The expected numbers of numbers to be generated.
	 */
	private void testAdditionProblemGenerateNumbers(Difficulty difficulty,
			int lowerLimit, int upperLimit, int expectedNbrOfNumbers) {
		int[] nbrs = null;

		for (int i = 0; i < ITERATIONS; i++) {
			nbrs = additionProblem.generateNumbers(difficulty);
			assertTrue(isCorrectNbrOfNumbers(expectedNbrOfNumbers, nbrs));
			assertTrue(isNumbersInRange(nbrs, lowerLimit, upperLimit));
			assertTrue(isNumbersNonNegative(nbrs));
		}

	}

	/**
	 * Checks that the number of numbers in an array is correct.
	 * 
	 * @param expectedNbrOfNumbers
	 *            The expected number of numbers.
	 * @param nbrs
	 *            An array with number.
	 * @return If array contains the expected number of numbers.
	 */
	private boolean isCorrectNbrOfNumbers(int expectedNbrOfNumbers, int[] nbrs) {
		return expectedNbrOfNumbers == nbrs.length;
	}

	/**
	 * Checks to see if the numbers in an array is within the specified limits.
	 * [lowerLimit, upperLimit).
	 * 
	 * @param nbrs
	 *            An array of numbers.
	 * @param lowerLimit
	 *            The lower limit for the numbers in the array.
	 * @param upperLimit
	 *            The upper limit for the numbers in the array.
	 * @return If the numbers in the array is within the limit.
	 */
	private boolean isNumbersInRange(int[] nbrs, int lowerLimit, int upperLimit) {
		for (int number : nbrs) {
			if (number < lowerLimit || number >= upperLimit) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Checks to see if numbers in the array is non negative.
	 * 
	 * @param numbers
	 *            An array of numbers.
	 * @return If the numbers in the array is non negative.
	 */
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
