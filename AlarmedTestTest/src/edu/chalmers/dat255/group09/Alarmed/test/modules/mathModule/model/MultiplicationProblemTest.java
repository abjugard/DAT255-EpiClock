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

import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MultiplicationProblem;

/**
 * 
 * @author Joakim Persson
 * 
 */
public class MultiplicationProblemTest extends AndroidTestCase {

	private MultiplicationProblem multiProblem;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		multiProblem = new MultiplicationProblem();
	}

	public void testToString() {

		String expected = "*";
		String actual = multiProblem.toString();
		assertEquals(expected, actual);
	}

	public void testGetResult() {
		int actualResult = multiProblem.getResult(new int[] { 6, 6 });
		int expectedResult = 36;
		assertEquals(expectedResult, actualResult);

		actualResult = multiProblem.getResult(new int[] { 2, -1 });
		expectedResult = -2;
		assertEquals(expectedResult, actualResult);

		actualResult = multiProblem.getResult(new int[] { 5, -5, -1 });
		expectedResult = 25;
		assertEquals(expectedResult, actualResult);
	}

	// TODO add tests for getHeader and generateNumbers, formattedString

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		multiProblem = null;
	}
}
