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
package edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes;

import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.constants.Difficulty;

//Convert from binary 01010101

/**
 * 
 * @author Joakim Persson
 * 
 */
public class BaseSwitchProblem implements MathProblemType {

	private final static String PROBLEM_HEADER = "Convert to base ten";

	@Override
	public int getResult(int[] numbers) {
		// TODO Auto-generated method stub
		return 0;
	}

	private int convertBaseTwoToTen(int[] nbrs) {
		// reverse the array
		int[] reversedNbrs = reverseArray(nbrs);
		int base = 2;
		int sum = 0;
		// then sum every position:
		// the the number at the position * 2^i
		for (int i = 0; i < reversedNbrs.length; i++) {
			sum += reversedNbrs[i] * Math.pow(base, i);
		}

		return sum;
	}

	@Override
	public int[] generateNumbers(Difficulty difficulty) {
		// TODO Auto-generated method stub
		return null;
	}

	private String converBaseTenToTwo(int number) {

		int base = 2;

		if (number < 0) {
			return "-" + converBaseTenToTwo(-number);
		}
		if (number < base) {
			return Integer.toString(number);
		} else {
			return converBaseTenToTwo(number / 2) + number % 2;
		}
	}

	private static int[] reverseArray(int[] nbrs) {
		int size = nbrs.length;
		int mid = size / 2;
		int temp;

		for (int i = 0; i < mid; i++) {
			temp = nbrs[i];
			nbrs[i] = nbrs[size - i - 1];
			nbrs[size - i - 1] = temp;
		}

		return nbrs;
	}

	@Override
	public String getProblemHeader() {
		return PROBLEM_HEADER;
	}

	@Override
	public String getFormattedProblem(int[] nbrs) {
		// TODO Auto-generated method stub
		return null;
	}

}
