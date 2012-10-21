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
package edu.chalmers.dat255.group09.Alarmed.test.modules.mathModule.model;

import java.util.Arrays;

import junit.framework.TestCase;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.AdditionProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.MathProblemType;

/**
 * A test class for the MathProblem class.
 * 
 * @author Joakim Persson
 * 
 */
public class MathProblemTest extends TestCase {

	private MathProblem problem = null;

	/**
	 * 
	 */
	public void testGetNumbers() {
		int[] expected = { 1, 2 };
		MathProblemType operator = new AdditionProblem();
		problem = new MathProblem(expected, operator);

		int[] actual = problem.getNumbers();
		assertTrue(Arrays.equals(expected, actual));

		expected = new int[] { 1, 2, 1, 2, 1 };

		operator = new AdditionProblem();
		problem = new MathProblem(expected, operator);

		actual = problem.getNumbers();
		assertTrue(Arrays.equals(expected, actual));
	}

	/**
	 * 
	 */
	public void testGetOperator() {
		int[] numbers = { 1, 2 };
		MathProblemType expectedOperator = new AdditionProblem();

		problem = new MathProblem(numbers, expectedOperator);

		MathProblemType actualOperator = problem.getProblemType();

		assertEquals(expectedOperator, actualOperator);

	}
}
