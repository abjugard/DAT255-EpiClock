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
package edu.chalmers.dat255.group09.Alarmed.test.modules.mathModule.util;

import java.util.ArrayList;
import java.util.List;

import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.util.PrimeUtil;

/**
 * A test class for the PrimeUtil class.
 * 
 * @author Joakim Persson
 * 
 */
public class PrimeUtilTest extends AndroidTestCase {

	private List<Integer> primeNumbers;
	private static final int MAX_LIMIT = 100;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		primeNumbers = initPrimeList();
	}

	/**
	 * Tests the isPrime method for numbers between two and MAX_LIMIT and then
	 * checks with a pre generated list if the method works correctly.
	 */
	public void testIsPrime() {

		for (int number : primeNumbers) {
			assertTrue(PrimeUtil.isPrime(number));
		}

		List<Integer> actualPrimes = new ArrayList<Integer>();

		for (int i = 2; i <= MAX_LIMIT; i++) {
			if (PrimeUtil.isPrime(i)) {
				actualPrimes.add(i);
			}
		}

		List<Integer> expectedPrimes = PrimeUtil.getPrimeList(0, MAX_LIMIT);

		assertEquals(expectedPrimes.size(), actualPrimes.size());
		assertEquals(expectedPrimes, actualPrimes);
	}

	/**
	 * Tests against a pre generated list of known prime numbers that the
	 * generatePrimeList method is working correctly with numbers between zero
	 * and MAX_SIZE.
	 */
	public void testGeneratePrimeListBetweenZeroAndMaxLimit() {
		int lowerLimit = 0;
		int nbrOfPrimes = primeNumbers.size();

		List<Integer> primes = PrimeUtil.getPrimeList(lowerLimit, MAX_LIMIT);

		assertEquals(nbrOfPrimes, primes.size());
		assertEquals(getPrimeList(lowerLimit, MAX_LIMIT), primes);
	}

	/**
	 * Tests against a pre generated list of known prime numbers that the
	 * generatePrimeList method is working correctly with numbers between fifty
	 * and MAX_SIZE.
	 */
	public void testGeneratePrimeListBetweenFiftyAndMaxLimit() {
		int lowerLimit = 50;

		List<Integer> primes = PrimeUtil.getPrimeList(lowerLimit, MAX_LIMIT);
		int nbrOfPrimes = getPrimeList(lowerLimit, MAX_LIMIT).size();

		assertEquals(nbrOfPrimes, primes.size());
		assertEquals(getPrimeList(lowerLimit, MAX_LIMIT), primes);
	}

	/**
	 * Tests against a pre generated list of known prime numbers that the
	 * generatePrimeList method is working correctly with numbers between thiry
	 * and sixty.
	 */
	public void testGeneratePrimeListBetweenThiryAndSixty() {

		int lowerLimit = 30;
		int upperLimit = 60;

		List<Integer> primes = PrimeUtil.getPrimeList(lowerLimit, upperLimit);
		int nbrOfPrimes = getPrimeList(lowerLimit, upperLimit).size();

		assertEquals(nbrOfPrimes, primes.size());
		assertEquals(getPrimeList(lowerLimit, upperLimit), primes);

	}

	/**
	 * Creates a list of all primes between two limits. [lowerLimit,
	 * upperLimit).
	 * 
	 * @param lowerLimit
	 *            The lower limit for the smallest prime.
	 * @param upperLimit
	 *            The upper limit for the biggest prime.
	 * @return A list of all primes in the interval.
	 */
	private List<Integer> getPrimeList(int lowerLimit, int upperLimit) {
		List<Integer> primes = new ArrayList<Integer>();

		for (int integer : primeNumbers) {
			if (integer >= lowerLimit && integer < upperLimit) {
				primes.add(integer);

				if (integer > upperLimit) {
					break;
				}

			}
		}

		return primes;
	}

	/***
	 * Creates a new list containing all the prime numbers between 2 and 100,
	 * from an predefinded string of prime numbers.
	 * 
	 * @return A list off all prime numbers between 2 and 100
	 */
	private List<Integer> initPrimeList() {
		List<Integer> primeList = new ArrayList<Integer>();
		String primeNbrs = "2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97";

		String[] primes = primeNbrs.replaceAll(" ", "").split(",");
		for (String string : primes) {
			primeList.add(Integer.parseInt(string));
		}
		return primeList;
	}
}
