/*
 * Copyright (C) 2012 Joakim Persson, Daniel Augurell, Adrian Bjugqrd, Andreas Rolen
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
import android.util.Log;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.constants.Difficulty;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.ModularProblem;

/**
 * A test class for the ModularProblem class.
 * 
 * @author Joakim Persson
 * 
 */
public class ModularProblemTest extends AndroidTestCase {

	private static final int ITERATIONS = 100;
	private ModularProblem modularProblem;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		modularProblem = new ModularProblem();
	}

	public void testGetProblemHeader() {
		String expected = "Solve for x!";
		String actual = modularProblem.getProblemHeader();
		assertEquals(expected, actual);
	}

	public void testGetFormattedString() {
		int[] nbrs = { 3, 4 };
		String expected = "x = 3 mod 4";
		String actual = modularProblem.getFormattedProblem(nbrs);
		assertEquals(expected, actual);
	}

	public void testGetFormattedStringForMoreThanTwoNumbers() {
		int[] nbrs = { 3, 4, 8 };
		String expected = "x = 3 + 4 mod 8";
		String actual = modularProblem.getFormattedProblem(nbrs);
		Log.d("CreateAlarm", actual);
		assertEquals(expected, actual);
	}

	public void testZeroModOne() {
		int actualResult = modularProblem.getResult(new int[] { 0, 1 });
		int expectedResult = 0;
		assertEquals(expectedResult, actualResult);
	}

	public void testTenModTen() {
		int actualResult = modularProblem.getResult(new int[] { 10, 10 });
		int expectedResult = 0;
		assertEquals(expectedResult, actualResult);
	}

	public void testTwentyModTen() {
		int actualResult = modularProblem.getResult(new int[] { 20, 10 });
		int expectedResult = 0;
		assertEquals(expectedResult, actualResult);
	}

	public void testMinusTenModTen() {
		int actualResult = modularProblem.getResult(new int[] { -10, 10 });
		int expectedResult = 0;
		assertEquals(expectedResult, actualResult);
	}

	public void testFourteenModTen() {
		int actualResult = modularProblem.getResult(new int[] { 14, 10 });
		int expectedResult = 4;
		assertEquals(expectedResult, actualResult);
	}

	public void testMinusFourteenModTen() {
		int actualResult = modularProblem.getResult(new int[] { -14, 10 });
		int expectedResult = 6;
		assertEquals(expectedResult, actualResult);
	}

	public void testTreeNumbersGetResult() {
		int actualResult = modularProblem.getResult(new int[] { -14, -6, 10 });
		int expectedResult = 0;
		assertEquals(expectedResult, actualResult);
	}

	public void testGenerateEasyProblem() {
		int lowerLimit = 0;
		int upperLimit = 30;

		testModulusProblemsgenerateNumbers(Difficulty.EASY, lowerLimit,
				upperLimit);
	}

	public void testGenerateMediumProblems() {
		int lowerLimit = -30;
		int upperLimit = 30;

		testModulusProblemsgenerateNumbers(Difficulty.MEDIUM, lowerLimit,
				upperLimit);
	}

	public void testGenerateHardProblems() {
		int lowerLimit = -50;
		int upperLimit = 50;

		testModulusProblemsgenerateNumbers(Difficulty.HARD, lowerLimit,
				upperLimit);
	}

	/**
	 * A method that runs tests on the number generator method and performs
	 * checks on the returned numbers, based on which difficulty is selected.
	 * The test is running several iterations and testing for a selected set of
	 * random number sets.
	 * 
	 * @param difficulty
	 *            The problems difficulty
	 * @param lowerLimit
	 *            The lower limit for the numbers in the problem.
	 * @param upperLimit
	 *            The upper limit for the numbers in the problem.
	 */
	private void testModulusProblemsgenerateNumbers(Difficulty difficulty,
			int lowerLimit, int upperLimit) {
		int[] nbrs = null;
		for (int i = 0; i < ITERATIONS; i++) {
			nbrs = modularProblem.generateNumbers(difficulty);
			assertTrue(isNumbersWithInRange(nbrs, lowerLimit, upperLimit));
			assertTrue(isModulusPositive(nbrs[1]));
		}

	}

	/**
	 * Checks if the numbers in an array is with two limits. [lowerLimit,
	 * upperLimit).
	 * 
	 * @param nbrs
	 *            The array of numbers.
	 * @param lowerLimit
	 *            The lower limit for the numbers.
	 * @param upperLimit
	 *            The upper limit for the numbers.
	 * @return if the numbers is located within the limits or not.
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
	 * Checks if the n in a = b mod n, is positive.
	 * 
	 * @param number
	 *            The modulus number n.
	 * @return If it is positive or not.
	 */
	private boolean isModulusPositive(int number) {
		return number >= 0;
	}
}
