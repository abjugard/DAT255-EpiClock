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

import java.util.ArrayList;
import java.util.List;

import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.constants.Difficulty;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.FibonacciProblem;

/**
 * A test class for the FibonacciProblem class.
 * 
 * @author Joakim Persson
 * 
 */
public class FibonacciProblemTest extends AndroidTestCase {

	private static final int ITERATIONS = 100;
	private FibonacciProblem fibonacciProblem;
	private List<Integer> fibonacciNumbers;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		fibonacciProblem = new FibonacciProblem();
		fibonacciNumbers = initFibonacciList();
	}

	public void testGetPromlemHeader() {
		String expected = "What is the next term?";
		String actual = fibonacciProblem.getProblemHeader();
		assertEquals(expected, actual);
	}

	public void testGetFormattedString() {
		int[] nbrs = { 3, 4, 5 };
		String expected = "3, 4, 5, ?";
		String actual = fibonacciProblem.getFormattedProblem(nbrs);
		assertEquals(expected, actual);
	}

	public void testGetResult() {

		int actualResult = fibonacciProblem.getResult(new int[] { 0, 1, 1 });
		int expectedResult = 2;
		assertEquals(expectedResult, actualResult);

		actualResult = fibonacciProblem.getResult(new int[] { 3, 5, 8 });
		expectedResult = 13;
		assertEquals(expectedResult, actualResult);

		actualResult = fibonacciProblem.getResult(new int[] { 8, 13 });
		expectedResult = 21;
		assertEquals(expectedResult, actualResult);
	}

	public void testGenerateEasyProblem() {
		int lowerLimit = 0;
		int upperLimit = 10;

		testFibonacciProblemsGenerateNumbers(Difficulty.EASY, lowerLimit,
				upperLimit);
	}

	public void testGenerateMediumProblems() {
		int lowerLimit = 8;
		int upperLimit = 30;

		testFibonacciProblemsGenerateNumbers(Difficulty.MEDIUM, lowerLimit,
				upperLimit);
	}

	public void testGenerateHardProblems() {
		int lowerLimit = 30;
		int upperLimit = 100;

		testFibonacciProblemsGenerateNumbers(Difficulty.HARD, lowerLimit,
				upperLimit);
	}

	/**
	 * Runs several tests for the fibonacciproblems generateNumbers function.
	 * For example the tests checks that the numbers only are fibonacci numbers.
	 * The methods runs the same tests several times on different random
	 * numbers. The method also takes into consideration the difficulty of the
	 * problem.
	 * 
	 * @param difficulty
	 *            The difficulty of the problem.
	 * @param lowerLimit
	 *            The lower limit for the starting number in the sequence.
	 * @param upperLimit
	 *            The upper limit for the starting number in the sequence.
	 */
	private void testFibonacciProblemsGenerateNumbers(Difficulty difficulty,
			int lowerLimit, int upperLimit) {
		int[] nbrs = null;
		for (int i = 0; i < ITERATIONS; i++) {
			nbrs = fibonacciProblem.generateNumbers(difficulty);
			assertTrue(isOnlyFibonacciNumbers(nbrs));
			assertTrue(isNumbersInIncreasingOrder(nbrs));
			assertTrue(isStartingNbrWithinLimits(nbrs, lowerLimit, upperLimit));
		}
	}

	/**
	 * Performs a check on an array that it only consists of fibonacci numbers.
	 * 
	 * @param numbers
	 *            An array of numbers.
	 * @return If the array only consists of fibonacci numbers.
	 */
	private boolean isOnlyFibonacciNumbers(int[] numbers) {
		for (int number : numbers) {
			if (!fibonacciNumbers.contains(number)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if the numbers in an array is increasing in size. Ex: 2, 3 if ok.
	 * 
	 * @param nbrs
	 *            An array of numbers.
	 * @return If the numbers is increasing in size.
	 */
	private boolean isNumbersInIncreasingOrder(int[] nbrs) {
		for (int i = 1; i < nbrs.length; i++) {
			if (nbrs[i] < nbrs[i - 1]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if the first number in the array is within two limits.
	 * [lowerLimit, upperLimit);
	 * 
	 * @param nbrs
	 *            The array of numbers.
	 * @param lowerLimit
	 *            The lower limit for the starting number.
	 * @param upperLimit
	 *            The upper limit for the starting number.
	 * @return If the starting number is within the limit.
	 */
	private boolean isStartingNbrWithinLimits(int[] nbrs, int lowerLimit,
			int upperLimit) {
		int startingNumber = nbrs[0];
		return (startingNumber < upperLimit || startingNumber >= lowerLimit);
	}

	/**
	 * Creates a new list containing only of the first 20 elements in the
	 * fibonacci sequence.
	 * 
	 * @return An list of the first twenty elements in the fibonacci sequence.
	 */
	private List<Integer> initFibonacciList() {
		List<Integer> fibNumbers = new ArrayList<Integer>();
		String numbs = "0,1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597,2584,4181,6765";

		String[] stringArray = numbs.replaceAll(" ", "").split(",");

		for (String string : stringArray) {
			fibNumbers.add(Integer.parseInt(string));
		}

		return fibNumbers;
	}

	@Override
	protected void tearDown() throws Exception {

		super.tearDown();
		fibonacciProblem = null;
	}

}
