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
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.FactorialProblem;

/**
 * A test class for the FactorialProblem.
 * 
 * @author Joakim Persson
 * 
 */
public class FactorialProblemTest extends TestCase {

	private static final int ITERATIONS = 100;
	private FactorialProblem factorialProblem;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		factorialProblem = new FactorialProblem();
	}

	/**
	 * Tests that the factorial problem is returning a correct problem
	 * header/description.
	 */
	public void testGetPromlemHeader() {
		String expected = "What is the sum?";
		String actual = factorialProblem.getProblemHeader();
		assertEquals(expected, actual);
	}

	/**
	 * Tests that the getFormattedString is working as expected and that the
	 * returned formatted problem description is correct.
	 */
	public void testGetFormattedString() {
		int[] nbrs = { 1, 0 };
		String expected = "1! + 0! = ?";
		String actual = factorialProblem.getFormattedProblem(nbrs);
		assertEquals(expected, actual);
	}

	/**
	 * Testing that the getResult method is working as expected for three
	 * numbers.
	 */
	public void testGetResultForThreeNumbers() {
		int actualResult = factorialProblem.getResult(new int[] { 0, 1, 2 });
		int expectedResult = 4;
		assertEquals(expectedResult, actualResult);
	}

	/**
	 * Testing that the getResult method is working correctly when the input is
	 * two zeros. Remember that by definition 0! =1.
	 */
	public void testGetResultForZeroAndZero() {
		int actualResult = factorialProblem.getResult(new int[] { 0, 0 });
		int expectedResult = 2;
		assertEquals(expectedResult, actualResult);
	}

	/**
	 * Tests the getResult method for "normal" input and validates that it works
	 * as expected.
	 */
	public void testGetResultForFourAndThree() {
		int actualResult = factorialProblem.getResult(new int[] { 4, 3 });
		int expectedResult = 30;
		assertEquals(expectedResult, actualResult);
	}

	/**
	 * Tests the generateEasyProblem method by running several different tests
	 * on the randomly generated numbers.
	 */
	public void testGenerateEasyProblem() {
		int lowerLimit = 0;
		int upperLimit = 6;
		int expectedNbrOfNumbers = 2;

		testFactorialProblemsGenerateNumbers(Difficulty.EASY, lowerLimit,
				upperLimit, expectedNbrOfNumbers);
	}

	/**
	 * Tests the generateMediumProblem method by running several different tests
	 * on the randomly generated numbers.
	 */
	public void testGenerateMediumProblems() {
		int lowerLimit = 0;
		int upperLimit = 30;
		int expectedNbrOfNumbers = 3;

		testFactorialProblemsGenerateNumbers(Difficulty.MEDIUM, lowerLimit,
				upperLimit, expectedNbrOfNumbers);
	}

	/**
	 * Tests the generateHardProblem method by running several different tests
	 * on the randomly generated numbers.
	 */
	public void testGenerateHardProblems() {
		int lowerLimit = 0;
		int upperLimit = 8;
		int expectedNbrOfNumbers = 2;

		testFactorialProblemsGenerateNumbers(Difficulty.HARD, lowerLimit,
				upperLimit, expectedNbrOfNumbers);
	}

	/**
	 * This method runs several tests on different random sets of number
	 * generated by the problem, with regard to the difficulty of the problem.
	 * 
	 * @param difficulty
	 *            The difficulty of the problem.
	 * @param lowerLimit
	 *            The lower limit for the numbers in the problem.
	 * @param upperLimit
	 *            The upper limit for the numbers in the problem.
	 * @param expectedNbrOfNumbers
	 *            The expected number of numbers to be generated by the problem.
	 */
	private void testFactorialProblemsGenerateNumbers(Difficulty difficulty,
			int lowerLimit, int upperLimit, int expectedNbrOfNumbers) {
		int[] nbrs;
		for (int i = 0; i < ITERATIONS; i++) {
			nbrs = factorialProblem.generateNumbers(difficulty);
			assertTrue(isCorrectNbrOfNumbers(expectedNbrOfNumbers, nbrs));
			assertTrue(isNumbersInRange(nbrs, lowerLimit, upperLimit));
		}
	}

	/**
	 * Checks that the array consists of the expected number of numbers.
	 * 
	 * @param expectedNbrOfNumbers
	 *            The expected number of numbers.
	 * @param nbrs
	 *            The actual array of numbers.
	 * @return If the array consists of correct number of numbers.
	 */
	private boolean isCorrectNbrOfNumbers(int expectedNbrOfNumbers, int[] nbrs) {
		return expectedNbrOfNumbers == nbrs.length;
	}

	/**
	 * Checks that the numbers in an array is within two limits: [lowerLimit,
	 * upperLimit).
	 * 
	 * @param nbrs
	 *            The array of numbers.
	 * @param lowerLimit
	 *            The lower limit for the numbers.
	 * @param upperLimit
	 *            The upper limit for the numbers.
	 * @return If the numbers in the array is within the limits.
	 */
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
