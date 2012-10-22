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
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.BaseSwitchProblem;

/**
 * A test class for the BaseSwitchProblem class.
 * 
 * @author Joakim Persson
 * 
 */
public class BaseSwitchProblemTest extends TestCase {

	private static final int ITERATIONS = 100;
	private BaseSwitchProblem problem;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		problem = new BaseSwitchProblem();
	}

	/**
	 * Testing that the problem header in BaseSwitchProblem is working as
	 * expected an return a correct problem description.
	 */
	public void testGetProblemHeader() {
		String expected = "Convert to base ten";
		String actual = problem.getProblemHeader();
		assertEquals(expected, actual);
	}

	/**
	 * Testing that the getFormattedProblem string is working as expected and
	 * returning a valid string.
	 */
	public void testGetFormattedProblem() {
		int[] nbrs = { 0, 1, 1, 0 };
		String expected = "0 1 1 0";
		String actual = problem.getFormattedProblem(nbrs);
		assertEquals(expected, actual);
	}

	/**
	 * Testing that the getResult method is working correctly for two digits.
	 */
	public void testGetResultForTwoDigits() {
		int[] nbrs = { 1, 1 };
		int expected = 3;
		int actual = problem.getResult(nbrs);
		assertEquals(expected, actual);
	}

	/**
	 * Testing that the getResult method is working correctly for three digits.
	 */
	public void testGetResultForThreeDigits() {
		int[] nbrs = { 1, 1, 0 };
		int expected = 6;
		int actual = problem.getResult(nbrs);
		assertEquals(expected, actual);
	}

	/**
	 * Testing that the getResult method is working correctly for nine digits.
	 */
	public void testGetResultForNineDigits() {
		int[] nbrs = { 1, 1, 0, 1, 0, 1, 0, 1, 0 };
		int expected = 426;
		int actual = problem.getResult(nbrs);
		assertEquals(expected, actual);
	}

	/**
	 * Testing that the generateEasyProblem method is working as expected by
	 * running multiple tests on the generated random number.
	 */
	public void testGenerateEasyProblem() {
		int lowerLimit = 1;
		int upperLimit = 30;

		testProblemGenerateNumbers(Difficulty.EASY, lowerLimit, upperLimit);
	}

	/**
	 * Testing that the generateMediumProblem method is working as expected by
	 * running multiple tests on the generated random number.
	 */
	public void testGenerateMediumProblems() {
		int lowerLimit = 30;
		int upperLimit = 80;

		testProblemGenerateNumbers(Difficulty.MEDIUM, lowerLimit, upperLimit);
	}

	/**
	 * Testing that the generateHardProblem method is working as expected by
	 * running multiple tests on the generated random number.
	 */
	public void testGenerateHardProblems() {
		int lowerLimit = 80;
		int upperLimit = 120;

		testProblemGenerateNumbers(Difficulty.HARD, lowerLimit, upperLimit);
	}

	/**
	 * Runs several tests on different sets of the problems generate numbers
	 * method, with regard to the problems difficulty.
	 * 
	 * @param difficulty
	 *            The problems difficulty.
	 * @param lowerLimit
	 *            The lower limit for the problem.
	 * @param upperLimit
	 *            The upper limit for the problem.F
	 */
	private void testProblemGenerateNumbers(Difficulty difficulty,
			int lowerLimit, int upperLimit) {

		for (int i = 0; i < ITERATIONS; i++) {
			int[] nbrs = problem.generateNumbers(difficulty);
			assertTrue(isBaseTwoNumber(nbrs));
			assertTrue(isNumbersWithInRange(nbrs, lowerLimit, upperLimit));
		}

	}

	/**
	 * Checks that the array represents an base two number.
	 * 
	 * @param nbrs
	 *            An array of numbers.
	 * @return If it is an base two number or not.
	 */
	private boolean isBaseTwoNumber(int[] nbrs) {
		for (int number : nbrs) {
			if (number != 1 && number != 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if the generated number is within two limits. [lowerLimit,
	 * upperLimit).
	 * 
	 * @param nbrs
	 *            An array of numbers representing an base two number.
	 * @param lowerLimit
	 *            The lower limit for the number.
	 * @param upperLimit
	 *            The upper limit for the number.
	 * @return If the number is within the limits.
	 */
	private boolean isNumbersWithInRange(int[] nbrs, int lowerLimit,
			int upperLimit) {

		int number = problem.getResult(nbrs);
		return number >= lowerLimit || number < upperLimit;
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		problem = null;
	}

}
