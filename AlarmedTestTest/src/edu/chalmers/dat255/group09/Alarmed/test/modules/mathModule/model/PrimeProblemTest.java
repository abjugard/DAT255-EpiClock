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

import java.util.ArrayList;
import java.util.List;

import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.PrimeProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.constants.Difficulty;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.util.PrimeUtil;

/**
 * 
 * @author Joakim Persson
 * 
 */
public class PrimeProblemTest extends AndroidTestCase {

	private PrimeProblem primeProblem;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		primeProblem = new PrimeProblem();
	}

	public void testGetPromlemHeader() {
		String expected = "Which Number Is Prime?";
		String actual = primeProblem.getProblemHeader();
		assertEquals(expected, actual);
	}

	public void testGetFormattedString() {
		int[] nbrs = { 1, 2, 3, 4 };
		String expected = "1, 2, 3, 4";
		String actual = primeProblem.getFormattedProblem(nbrs);
		assertEquals(expected, actual);
	}

	public void testGetResult() {

		int actualResult = primeProblem.getResult(new int[] { 6, 12, 36, 97 });
		int expectedResult = 97;
		assertEquals(expectedResult, actualResult);

		actualResult = primeProblem.getResult(new int[] { 34, 20, 29, 8 });
		expectedResult = 29;
		assertEquals(expectedResult, actualResult);

		actualResult = primeProblem.getResult(new int[] { 9, 4, 1, 5 });
		expectedResult = 5;
		assertEquals(expectedResult, actualResult);
	}

	public void testGenerateEasyNumbers() {
		int lowerLimit = 0;
		int upperLimit = 30;
		int deltaToPrime = 10;
		int iterations = 1000;
		int[] nbrs = null;

		for (int i = 0; i < iterations; i++) {
			nbrs = primeProblem.generateNumbers(Difficulty.EASY);
			assertTrue(isOnlyOnePrimeNumber(nbrs));
			assertTrue(isOnlyUniqueNumbers(nbrs));
			assertTrue(isPrimeInRange(nbrs, lowerLimit, upperLimit));
			assertTrue(isNumbersWithInDelta(nbrs, deltaToPrime));
		}

	}

	public void testGenerateMediumProblems() {
		int lowerLimit = 30;
		int upperLimit = 50;
		int deltaToPrime = 15;
		int iterations = 1000;
		int[] nbrs = null;

		for (int i = 0; i < iterations; i++) {
			nbrs = primeProblem.generateNumbers(Difficulty.MEDIUM);
			assertTrue(isOnlyOnePrimeNumber(nbrs));
			assertTrue(isOnlyUniqueNumbers(nbrs));
			assertTrue(isPrimeInRange(nbrs, lowerLimit, upperLimit));
			assertTrue(isNumbersWithInDelta(nbrs, deltaToPrime));
		}
	}

	public void testGenerateHardProblems() {
		int lowerLimit = 50;
		int upperLimit = 100;
		int deltaToPrime = 20;
		int iterations = 1000;
		int[] nbrs = null;

		for (int i = 0; i < iterations; i++) {
			nbrs = primeProblem.generateNumbers(Difficulty.HARD);
			assertTrue(isOnlyOnePrimeNumber(nbrs));
			assertTrue(isOnlyUniqueNumbers(nbrs));
			assertTrue(isPrimeInRange(nbrs, lowerLimit, upperLimit));
			assertTrue(isNumbersWithInDelta(nbrs, deltaToPrime));
		}
	}

	private boolean isOnlyOnePrimeNumber(int[] nbrs) {
		int iterations = 1000;
		int expectedNbrOfPrimes = 1;
		int actualNbrOfPrimes = 1;

		for (int i = 0; i < iterations; i++) {
			actualNbrOfPrimes = getNbrsOfPrimes(nbrs);
		}
		return expectedNbrOfPrimes == actualNbrOfPrimes;
	}

	private int getNbrsOfPrimes(int[] nbrs) {

		int nbrOfPrimes = 0;

		for (int number : nbrs) {

			if (PrimeUtil.isPrime(number)) {
				nbrOfPrimes++;
			}

		}
		return nbrOfPrimes;
	}

	private boolean isPrimeInRange(int[] nbrs, int lowerLimit, int upperLimit) {
		int primeNumber = getPrimeNumber(nbrs);
		return primeNumber >= lowerLimit && primeNumber < upperLimit;
	}

	private boolean isNumbersWithInDelta(int[] nbrs, int deltaToPrime) {
		int primeNumber = getPrimeNumber(nbrs);
		int upperLimit = primeNumber + deltaToPrime;
		int lowerLimit = primeNumber - deltaToPrime;

		for (int number : nbrs) {

			if (number > upperLimit || number < lowerLimit) {
				return false;
			}

		}
		return true;
	}

	private boolean isOnlyUniqueNumbers(int[] nbrs) {
		List<Integer> previousNumbers = new ArrayList<Integer>();

		for (int number : nbrs) {
			if (previousNumbers.contains(number)) {
				return false;
			} else {
				previousNumbers.add(number);
			}
		}

		return true;
	}

	private int getPrimeNumber(int[] nbrs) {
		int primeNumber = 0;

		for (int number : nbrs) {

			if (PrimeUtil.isPrime(number)) {
				primeNumber = number;
			}

		}

		return primeNumber;
	}

	@Override
	protected void tearDown() throws Exception {

		super.tearDown();
		primeProblem = null;
	}

}
