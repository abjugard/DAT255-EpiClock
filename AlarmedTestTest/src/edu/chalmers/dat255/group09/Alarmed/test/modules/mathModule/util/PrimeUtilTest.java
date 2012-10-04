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
package edu.chalmers.dat255.group09.Alarmed.test.modules.mathModule.util;

import java.util.ArrayList;
import java.util.List;

import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.util.PrimeUtil;

/**
 * 
 * @author Joakim Persson
 *
 */
public class PrimeUtilTest extends AndroidTestCase {

	private List<Integer> primeNumbers;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		primeNumbers = initPrimeList();
	}

	public void testIsPrime() {

		for (int number : primeNumbers) {
			assertTrue(PrimeUtil.isPrime(number));
		}

		List<Integer> actualPrimes = new ArrayList<Integer>();

		for (int i = 2; i <= 100; i++) {
			if (PrimeUtil.isPrime(i)) {
				actualPrimes.add(i);
			}
		}

		List<Integer> expectedPrimes = PrimeUtil.getPrimeList(0, 100);

		assertEquals(expectedPrimes.size(), actualPrimes.size());
		assertEquals(expectedPrimes, actualPrimes);
	}

	public void testGeneratePrimeList() {
		int lowerLimit = 0;
		int upperLimit = 100;
		int nbrOfPrimes = primeNumbers.size();

		List<Integer> primes = PrimeUtil.getPrimeList(lowerLimit, upperLimit);

		assertEquals(nbrOfPrimes, primes.size());
		assertEquals(getPrimeList(lowerLimit, upperLimit), primes);

		lowerLimit = 50;
		upperLimit = 100;

		primes = PrimeUtil.getPrimeList(lowerLimit, upperLimit);
		nbrOfPrimes = getPrimeList(lowerLimit, upperLimit).size();

		assertEquals(nbrOfPrimes, primes.size());
		assertEquals(getPrimeList(lowerLimit, upperLimit), primes);

		lowerLimit = 30;
		upperLimit = 60;

		primes = PrimeUtil.getPrimeList(lowerLimit, upperLimit);
		nbrOfPrimes = getPrimeList(lowerLimit, upperLimit).size();

		assertEquals(nbrOfPrimes, primes.size());
		assertEquals(getPrimeList(lowerLimit, upperLimit), primes);

	}

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

	private List<Integer> initPrimeList() {
		List<Integer> primeList = new ArrayList<Integer>();
		String primeNumbers = "2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97";

		String[] primes = primeNumbers.replaceAll(" ", "").split(",");
		for (String string : primes) {
			primeList.add(Integer.parseInt(string));
		}
		return primeList;
	}
}
