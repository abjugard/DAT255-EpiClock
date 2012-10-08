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

import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.constants.Difficulty;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.ModularProblem;

/**
 * 
 * @author Joakim Persson
 * 
 */
public class ModularProblemTest extends AndroidTestCase {

	private final static int ITERATIONS = 100;
	private ModularProblem modularProblem;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		modularProblem = new ModularProblem();
	}

	public void testGetProblemHeader() {
		String expected = "Solve for x!";
		String actual = modularProblem.getProblemHeader();
		assertEquals(expected, actual);
	}

	public void testGetFormattedString() {
		int[] nbrs = { 3, 4 };
		String expected = "x = 3 mod 4";
		String actual = modularProblem.getFormattedProblem(nbrs);
		assertEquals(expected, actual);
	}

	public void testZeroModOne() {
		int actualResult = modularProblem.getResult(new int[] { 0, 1 });
		int expectedResult = 0;
		assertEquals(expectedResult, actualResult);
	}

	public void testTenModTen() {
		int actualResult = modularProblem.getResult(new int[] { 10, 10 });
		int expectedResult = 0;
		assertEquals(expectedResult, actualResult);
	}

	public void testTwentyModTen() {
		int actualResult = modularProblem.getResult(new int[] { 20, 10 });
		int expectedResult = 0;
		assertEquals(expectedResult, actualResult);
	}

	public void testMinusTenModTen() {
		int actualResult = modularProblem.getResult(new int[] { -10, 10 });
		int expectedResult = 0;
		assertEquals(expectedResult, actualResult);
	}

	public void testFourteenModTen() {
		int actualResult = modularProblem.getResult(new int[] { 14, 10 });
		int expectedResult = 4;
		assertEquals(expectedResult, actualResult);
	}

	public void testMinusFourteenModTen() {
		int actualResult = modularProblem.getResult(new int[] { -14, 10 });
		int expectedResult = 6;
		assertEquals(expectedResult, actualResult);
	}

	public void testGenerateEasyProblem() {
		int lowerLimit = 0;
		int upperLimit = 30;

		testModulusProblems(Difficulty.EASY, lowerLimit, upperLimit);
	}

	public void testGenerateMediumProblems() {
		int lowerLimit = -30;
		int upperLimit = 30;

		testModulusProblems(Difficulty.MEDIUM, lowerLimit, upperLimit);
	}

	public void testGenerateHardProblems() {
		int lowerLimit = -50;
		int upperLimit = 50;

		testModulusProblems(Difficulty.HARD, lowerLimit, upperLimit);
	}

	private void testModulusProblems(Difficulty difficulty, int lowerLimit,
			int upperLimit) {
		int[] nbrs = null;
		for (int i = 0; i < ITERATIONS; i++) {
			nbrs = modularProblem.generateNumbers(difficulty);
			assertTrue(isNumbersWithInRange(nbrs, lowerLimit, upperLimit));
			assertTrue(isModulusPositive(nbrs[1]));
		}

	}

	private boolean isNumbersWithInRange(int[] nbrs, int lowerLimit,
			int upperLimit) {
		for (int number : nbrs) {
			if (number > upperLimit || number < lowerLimit) {
				return false;
			}
		}
		return true;
	}

	private boolean isModulusPositive(int number) {
		return number >= 0;
	}
}
