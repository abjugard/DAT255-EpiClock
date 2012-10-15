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

import java.util.ArrayList;
import java.util.List;

import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.constants.Difficulty;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.FibonacciProblem;

/**
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

		testFibonacciProblems(Difficulty.EASY, lowerLimit, upperLimit);
	}

	public void testGenerateMediumProblems() {
		int lowerLimit = 8;
		int upperLimit = 30;

		testFibonacciProblems(Difficulty.MEDIUM, lowerLimit, upperLimit);
	}

	public void testGenerateHardProblems() {
		int lowerLimit = 30;
		int upperLimit = 100;

		testFibonacciProblems(Difficulty.HARD, lowerLimit, upperLimit);
	}

	private void testFibonacciProblems(Difficulty difficulty, int lowerLimit,
			int upperLimit) {
		int[] nbrs = null;
		for (int i = 0; i < ITERATIONS; i++) {
			nbrs = fibonacciProblem.generateNumbers(difficulty);
			assertTrue(isOnlyFibonacciNumbers(nbrs));
			assertTrue(isNumbersInIncreasingOrder(nbrs));
			assertTrue(isStartingNbrWithinLimits(nbrs, lowerLimit, upperLimit));
		}
	}

	private boolean isOnlyFibonacciNumbers(int[] numbers) {
		for (int number : numbers) {
			if (!fibonacciNumbers.contains(number)) {
				return false;
			}
		}
		return true;
	}

	private boolean isNumbersInIncreasingOrder(int[] nbrs) {
		for (int i = 1; i < nbrs.length; i++) {
			if (nbrs[i] < nbrs[i - 1]) {
				return false;
			}
		}
		return true;
	}

	private boolean isStartingNbrWithinLimits(int[] nbrs, int lowerLimit,
			int upperLimit) {
		int startingNumber = nbrs[0];
		return (startingNumber < upperLimit || startingNumber >= lowerLimit);
	}

	private List<Integer> initFibonacciList() {
		List<Integer> fibonacciNumbers = new ArrayList<Integer>();
		String numbers = "0,1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597,2584,4181,6765";

		String[] stringArray = numbers.replaceAll(" ", "").split(",");

		for (String string : stringArray) {
			fibonacciNumbers.add(Integer.parseInt(string));
		}

		return fibonacciNumbers;
	}

	@Override
	protected void tearDown() throws Exception {

		super.tearDown();
		fibonacciProblem = null;
	}

}
