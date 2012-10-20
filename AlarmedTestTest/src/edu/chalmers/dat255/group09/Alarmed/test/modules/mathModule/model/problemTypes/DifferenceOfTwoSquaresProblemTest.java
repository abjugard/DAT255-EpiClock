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
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.DifferenceOfTwoSquaresProblem;

/**
 * A test class for the DifferenceOfTwoSquaresProblem class.
 * 
 * @author Joakim Persson
 * 
 */
public class DifferenceOfTwoSquaresProblemTest extends AndroidTestCase {

	private static final int ITERATIONS = 100;
	private static final int NBR_OF_NUMBERS = 2;
	private DifferenceOfTwoSquaresProblem problem;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		problem = new DifferenceOfTwoSquaresProblem();
	}

	public void testGetProblemHeader() {
		String expected = "What is the product?";
		String actual = problem.getProblemHeader();
		assertEquals(expected, actual);
	}

	public void testGetFormattedString() {
		int[] nbrs = { 3, 4 };
		String expected = "(3-4)(3+4)";
		String actual = problem.getFormattedProblem(nbrs);
		assertEquals(expected, actual);
	}

	public void testGetResultForTenZero() {
		int actualResult = problem.getResult(new int[] { 10, 0 });
		int expectedResult = 100;
		assertEquals(expectedResult, actualResult);
	}

	public void testGetResultForZeroTen() {
		int actualResult = problem.getResult(new int[] { 0, 10 });
		int expectedResult = -100;
		assertEquals(expectedResult, actualResult);
	}

	public void testGetResultForSameNumbers() {
		int actualResult = problem.getResult(new int[] { 5, 5 });
		int expectedResult = 0;
		assertEquals(expectedResult, actualResult);
	}

	public void testGetResultSameNumbersOnePosOneNeg() {
		int actualResult = problem.getResult(new int[] { -5, 5 });
		int expectedResult = 0;
		assertEquals(expectedResult, actualResult);
	}

	public void testGetResultSameNumbersBothNegative() {
		int actualResult = problem.getResult(new int[] { -5, -5 });
		int expectedResult = 0;
		assertEquals(expectedResult, actualResult);
	}

	public void testGetResultTwoNegativeNumbers() {
		int actualResult = problem.getResult(new int[] { -3, -2 });
		int expectedResult = 5;
		assertEquals(expectedResult, actualResult);
	}

	public void testGetResultTwoPositiveNumbers() {
		int actualResult = problem.getResult(new int[] { 6, 7 });
		int expectedResult = -13;
		assertEquals(expectedResult, actualResult);
	}

	public void testGetResultOnePosOneNeg() {
		int actualResult = problem.getResult(new int[] { -6, 7 });
		int expectedResult = -13;
		assertEquals(expectedResult, actualResult);
	}

	public void testGenerateEasyProblem() {
		int lowerLimit = 1;
		int upperLimit = 5;

		testDifferenceOfTwoSquaresProblems(Difficulty.EASY, lowerLimit,
				upperLimit);
	}

	public void testGenerateMediumProblems() {
		int lowerLimit = 4;
		int upperLimit = 8;

		testDifferenceOfTwoSquaresProblems(Difficulty.MEDIUM, lowerLimit,
				upperLimit);
	}

	public void testGenerateHardProblems() {
		int lowerLimit = 7;
		int upperLimit = 11;

		testDifferenceOfTwoSquaresProblems(Difficulty.HARD, lowerLimit,
				upperLimit);
	}

	/**
	 * Runs several tests several times for the generateNumbers method on the
	 * problem and performs various checks on the random numbers, with regard to
	 * the problems difficulty.
	 * 
	 * @param difficulty
	 *            The problems difficulty.
	 * @param lowerLimit
	 *            The lower limit for the generated numbers.
	 * @param upperLimit
	 *            The upper limit for the generated numbers.
	 */
	private void testDifferenceOfTwoSquaresProblems(Difficulty difficulty,
			int lowerLimit, int upperLimit) {
		int[] nbrs = null;
		for (int i = 0; i < ITERATIONS; i++) {
			nbrs = problem.generateNumbers(difficulty);
			assertTrue(isNumbersWithInRange(nbrs, lowerLimit, upperLimit));
			assertTrue(isExpectedNbrOfNumbers(nbrs));
		}

	}

	/**
	 * Checks that the numbers in an array is within two specified limits.
	 * 
	 * @param nbrs
	 *            An array of numbers.
	 * @param lowerLimit
	 *            The lower limit for the numbers.
	 * @param upperLimit
	 *            The upper limit for the numbers.
	 * @return If the numbers in the array is within the limits.
	 */
	private boolean isNumbersWithInRange(int[] nbrs, int lowerLimit,
			int upperLimit) {
		for (int number : nbrs) {
			if (number > upperLimit || number < lowerLimit) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks that the array consist of the expected number of numbers.
	 * 
	 * @param nbrs
	 *            The array of numbers.
	 * @return If the array consists of the expected number of numbers.
	 */
	private boolean isExpectedNbrOfNumbers(int[] nbrs) {
		return nbrs.length == NBR_OF_NUMBERS;
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		problem = null;
	}
}
