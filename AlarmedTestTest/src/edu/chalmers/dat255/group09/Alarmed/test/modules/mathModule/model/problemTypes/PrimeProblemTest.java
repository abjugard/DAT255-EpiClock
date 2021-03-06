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

import junit.framework.TestCase;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.constants.Difficulty;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.PrimeProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.util.PrimeUtil;

/**
 * A test class for the PrimeProblem class.
 * 
 * @author Joakim Persson
 * 
 */
public class PrimeProblemTest extends TestCase {

	private PrimeProblem primeProblem;
	private static final int ITERATIONS = 100;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		primeProblem = new PrimeProblem();
	}

	/**
	 * Testing that the problem is returning a correct problem
	 * description/header.
	 */
	public void testGetPromlemHeader() {
		String expected = "Which Number Is Prime?";
		String actual = primeProblem.getProblemHeader();
		assertEquals(expected, actual);

	}

	/**
	 * Testing that the problem is formatting the problem descriptions strings
	 * correctly.
	 */
	public void testGetFormattedString() {
		int[] nbrs = { 0, 1, 2, 1 };
		String expected = "0, 1, 2, 1";
		String actual = primeProblem.getFormattedProblem(nbrs);
		assertEquals(expected, actual);
	}

	/**
	 * Testing that the getResult method is finding one prime numbers if the
	 * total number of numbers is four.
	 */
	public void tesetGetResultForFourNumbers() {
		int actualResult = primeProblem.getResult(new int[] { 6, 12, 36, 97 });
		int expectedResult = 97;
		assertEquals(expectedResult, actualResult);
	}

	/**
	 * Testing the getResult method is working as expected when using three
	 * numbers and returns the only prime.
	 */
	public void testGetResultForThreeNumbers() {
		int actualResult = primeProblem.getResult(new int[] { 34, 20, 29 });
		int expectedResult = 29;
		assertEquals(expectedResult, actualResult);
	}

	/**
	 * Testing the getResult method is returning the correct prime number when
	 * working with four numbers and two prime numbers, but one of them are
	 * negative.
	 */
	public void testGetResultForFourNumberWithAlsoOneNegativePimeNumber() {
		int actualResult = primeProblem.getResult(new int[] { 9, -5, 1, 5 });
		int expectedResult = 5;
		assertEquals(expectedResult, actualResult);
	}

	/**
	 * Tests the problems generateEasyNumbers method using several different
	 * tests, but is for example validating the the other numbers is within
	 * delta from the prime numbers etc.
	 */
	public void testGenerateEasyNumbers() {
		int lowerLimit = 0;
		int upperLimit = 30;
		int deltaToPrime = 10;

		testProblemGenerateNumbers(Difficulty.EASY, lowerLimit, upperLimit,
				deltaToPrime);

	}

	/**
	 * Tests the problems generateMediumNumbers method using several different
	 * tests, but is for example validating the the other numbers is within
	 * delta from the prime numbers etc.
	 */
	public void testGenerateMediumProblems() {
		int lowerLimit = 30;
		int upperLimit = 50;
		int deltaToPrime = 15;

		testProblemGenerateNumbers(Difficulty.MEDIUM, lowerLimit, upperLimit,
				deltaToPrime);
	}

	/**
	 * Tests the problems generateHardNumbers method using several different
	 * tests, but is for example validating the the other numbers is within
	 * delta from the prime numbers etc.
	 */
	public void testGenerateHardProblems() {
		int lowerLimit = 50;
		int upperLimit = 100;
		int deltaToPrime = 20;

		testProblemGenerateNumbers(Difficulty.HARD, lowerLimit, upperLimit,
				deltaToPrime);
	}

	/**
	 * This method runs several tests on generated numbers from an prime
	 * problem. For example this method tests that the array only contains one
	 * prime number, is only unique numbers.
	 * 
	 * @param difficulty
	 *            The difficulty of the problem that generates the numbers
	 * @param lowerLimit
	 *            The lower limit for the prime number in the array.
	 * @param upperLimit
	 *            The upper limit for the prime number in the array.
	 * @param deltaToPrime
	 *            The allowed delta for other numbers than the prime number v
	 */
	private void testProblemGenerateNumbers(Difficulty difficulty,
			int lowerLimit, int upperLimit, int deltaToPrime) {

		for (int i = 0; i < ITERATIONS; i++) {

			int[] nbrs = primeProblem.generateNumbers(difficulty);
			assertTrue(isOnlyOnePrimeNumber(nbrs));
			assertTrue(isOnlyUniqueNumbers(nbrs));
			assertTrue(isPrimeInRange(nbrs, lowerLimit, upperLimit));
			assertTrue(isNumbersWithInDelta(nbrs, deltaToPrime));
		}
	}

	/**
	 * This method tests that an array of numbers only contains one prime
	 * number.
	 * 
	 * @param nbrs
	 *            An array of numbers.
	 * @return If the array only contains one prime number.
	 */
	private boolean isOnlyOnePrimeNumber(int[] nbrs) {
		int expectedNbrOfPrimes = 1;
		int actualNbrOfPrimes = 1;

		for (int i = 0; i < ITERATIONS; i++) {
			actualNbrOfPrimes = getNbrsOfPrimes(nbrs);
		}
		return expectedNbrOfPrimes == actualNbrOfPrimes;
	}

	/**
	 * A utility method for calculating how many prime numbers that an array
	 * consist of.
	 * 
	 * @param nbrs
	 *            An array of numbers.
	 * @return How many prime numbers that exist in the array.
	 */
	private int getNbrsOfPrimes(int[] nbrs) {

		int nbrOfPrimes = 0;

		for (int number : nbrs) {

			if (PrimeUtil.isPrime(number)) {
				nbrOfPrimes++;
			}

		}
		return nbrOfPrimes;
	}

	/**
	 * Checks that the prime number is located within an specified limit:
	 * [lowerLimit, upperLimit).
	 * 
	 * @param nbrs
	 *            An array of number containing only one prime number.
	 * @param lowerLimit
	 *            The lower limit for the prime number
	 * @param upperLimit
	 *            The upper limit for the prime number.
	 * @return If the prime number is located within delta.
	 */
	private boolean isPrimeInRange(int[] nbrs, int lowerLimit, int upperLimit) {
		int primeNumber = getPrimeNumber(nbrs);
		return primeNumber >= lowerLimit && primeNumber < upperLimit;
	}

	/**
	 * Checks that an array of only one prime number, this method checks that
	 * the other numbers in the array is located around the prime number within
	 * an specified delta.
	 * 
	 * @param nbrs
	 *            An array of number, containing only one prime number.
	 * @param deltaToPrime
	 *            The allowed delta to the prime number
	 * @return If the other numbers is located within delta to the prime number
	 */
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

	/**
	 * Tests if the array only contains unique numbers. Which means that an
	 * number only can occur once.
	 * 
	 * @param nbrs
	 *            An array of numbers.
	 * @return If the array only contains unique numbers or not.
	 */
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

	/**
	 * From an array of random numbers containing one prime number, this method
	 * will find and return that number.
	 * 
	 * @param nbrs
	 *            An array of random numbers, containing one prime
	 * @return The prime number in the array
	 */
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
