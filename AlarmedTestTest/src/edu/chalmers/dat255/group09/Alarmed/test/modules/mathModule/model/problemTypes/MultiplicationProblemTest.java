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

import junit.framework.TestCase;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.constants.Difficulty;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.MultiplicationProblem;

/**
 * A test class for the MultiplicationProblem class.
 * 
 * @author Joakim Persson
 * 
 */
public class MultiplicationProblemTest extends TestCase {

	private static final int ITERATIONS = 100;
	private MultiplicationProblem multiProblem;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		multiProblem = new MultiplicationProblem();
	}

	public void testGetPromlemHeader() {
		String expected = "What Is The Product?";
		String actual = multiProblem.getProblemHeader();
		assertEquals(expected, actual);
	}

	public void testGetFormattedString() {
		int[] nbrs = { 3, 4, 5 };
		String expected = "3 * 4 * 5 = ?";
		String actual = multiProblem.getFormattedProblem(nbrs);
		assertEquals(expected, actual);
	}

	public void testGetResultForSixTimesSix() {
		int actualResult = multiProblem.getResult(new int[] { 6, 6 });
		int expectedResult = 36;
		assertEquals(expectedResult, actualResult);
	}

	public void testGetResultForTwoTimesMinusOne() {
		int actualResult = multiProblem.getResult(new int[] { 2, -1 });
		int expectedResult = -2;
		assertEquals(expectedResult, actualResult);

	}

	public void testGetResultForThreeNumbers() {
		int actualResult = multiProblem.getResult(new int[] { 5, -5, -1 });
		int expectedResult = 25;
		assertEquals(expectedResult, actualResult);
	}

	public void testGenerateEasyProblem() {
		int lowerLimit = 0;
		int upperLimit = 10;
		int expectedNbrOfNumbers = 2;

		testGenerateNumbersMultiplicationProblem(Difficulty.EASY, lowerLimit,
				upperLimit, expectedNbrOfNumbers);
	}

	public void testGenerateMediumProblems() {
		int lowerLimit = 0;
		int upperLimit = 10;
		int expectedNbrOfNumbers = 3;

		testGenerateNumbersMultiplicationProblem(Difficulty.MEDIUM, lowerLimit,
				upperLimit, expectedNbrOfNumbers);
	}

	public void testGenerateHardProblems() {
		int lowerLimit = 10;
		int upperLimit = 15;
		int expectedNbrOfNumbers = 3;

		testGenerateNumbersMultiplicationProblem(Difficulty.HARD, lowerLimit,
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
	private void testGenerateNumbersMultiplicationProblem(
			Difficulty difficulty, int lowerLimit, int upperLimit,
			int expectedNbrOfNumbers) {
		int[] nbrs = null;

		for (int i = 0; i < ITERATIONS; i++) {
			nbrs = multiProblem.generateNumbers(difficulty);
			assertTrue(isCorrectNbrOfNumbers(expectedNbrOfNumbers, nbrs));
			assertTrue(isNumbersInRange(nbrs, lowerLimit, upperLimit));
			assertTrue(isNumbersNonNegative(nbrs));
		}

	}

	/**
	 * Checks if an array contains the correct number of numbers.
	 * 
	 * @param expectedNbrOfNumbers
	 *            The expected number of numbers.
	 * @param nbrs
	 *            The array to check.
	 * @return If the array contains the expected number of numbers.
	 */
	private boolean isCorrectNbrOfNumbers(int expectedNbrOfNumbers, int[] nbrs) {
		return expectedNbrOfNumbers == nbrs.length;
	}

	/**
	 * If the numbers in an array is within an specified limit. [lowerLimit,
	 * upperLimit).
	 * 
	 * @param nbrs
	 *            The array of numbers.
	 * @param lowerLimit
	 *            The lower limit for the numbers.
	 * @param upperLimit
	 *            The upper limit for the numbers.
	 * @return If the numbers is located with the limits.
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
	 * Checks that the numbers in array is not negative.
	 * 
	 * @param numbers
	 *            The array of numbers.
	 * @return If the array contains negative numbers.
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
		multiProblem = null;
	}
}
