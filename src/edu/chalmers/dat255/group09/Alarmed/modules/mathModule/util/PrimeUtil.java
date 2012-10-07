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
package edu.chalmers.dat255.group09.Alarmed.modules.mathModule.util;

import java.util.ArrayList;
import java.util.List;

/**
 * A utility class for doing prime related actions. For example finding all
 * prime number between and interval or deciding if a number is a prime number
 * or not
 * 
 * @author Joakim Persson
 * 
 */
public class PrimeUtil {

	/**
	 * Finds out if a number is a prime number or not
	 * 
	 * @param number
	 *            The possible prime number
	 * @return If it is a prime number or not
	 */
	public static boolean isPrime(int number) {

		if (number < 2) {
			return false;
		}

		if (number == 2) {
			return true;
		}

		if (number % 2 == 0) {
			return false;
		}

		int root = (int) Math.sqrt((double) number);

		for (int j = 3; j <= root; j++) {
			if (number % j == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Get a list of prime numbers between a lower and upper limit [lower,upper)
	 * 
	 * @param lowerLimit
	 *            The lower limit
	 * @param upperLimit
	 *            The upper limit
	 * @return A list of primenumbers
	 */
	public static List<Integer> getPrimeList(int lowerLimit, int upperLimit) {
		Boolean[] tmpPrimeList = createArray(upperLimit);
		List<Integer> primes = new ArrayList<Integer>();
		for (int i = 2; i < tmpPrimeList.length; i++) {
			if (tmpPrimeList[i] && i >= lowerLimit) {
				primes.add(i);
			}
		}
		return primes;
	}

	private static Boolean[] createArray(int numbers) {
		Boolean[] prime = new Boolean[numbers + 1];

		prime[0] = true;
		prime[1] = true;

		for (int i = 2; i <= numbers; i++) {
			prime[i] = true;
		}
		prime = removeMultiples(prime);
		return prime;
	}

	private static Boolean[] removeMultiples(Boolean[] integers) {
		Boolean[] prime = integers;

		int end = (int) Math.sqrt(prime.length);

		for (int i = 2; i <= end; i++) {
			for (int j = i + i; j < prime.length; j += i) {
				if (j % i == 0) {
					prime[j] = false;
				}
			}
		}

		return prime;
	}

}
