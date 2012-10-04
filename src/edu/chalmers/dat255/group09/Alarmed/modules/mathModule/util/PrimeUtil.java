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

/**
 * 
 * @author Joakim Persson
 * 
 */
// TODO add better documentation
public class PrimeUtil {

	public static boolean isPrime(int number) {
		if (number < 2) {
			return false;
		}

		if (number == 2) {
			return true;
		}

		int d = (int) Math.sqrt((double) number);

		if (number % 2 == 0) {
			return false;
		}

		for (int j = 3; j <= d; j++) {
			if (number % j == 0) {
				return false;
			}
		}
		return true;
	}

	public static ArrayList<Integer> getPrimeList(int lowerLimit, int upperLimit) {
		Boolean[] tmpPrimeList = createArray(upperLimit);
		ArrayList<Integer> rtn = new ArrayList<Integer>();
		for (int i = 2; i < tmpPrimeList.length; i++) {
			if (tmpPrimeList[i] && i >= lowerLimit) {
				rtn.add(i);
			}
		}
		rtn.trimToSize();
		return rtn;
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
