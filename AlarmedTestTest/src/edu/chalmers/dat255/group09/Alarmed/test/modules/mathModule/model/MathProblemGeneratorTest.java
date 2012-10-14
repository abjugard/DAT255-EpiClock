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
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathProblemGenerator;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.constants.Difficulty;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.AdditionProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.MathProblemType;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.MultiplicationProblem;

/**
 * 
 * @author Joakim Persson
 * 
 */
public class MathProblemGeneratorTest extends AndroidTestCase {

	private MathProblemGenerator generator;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		generator = new MathProblemGenerator();
	}

	public void testGetProblem() {

		int delta = 2;
		int nbrOfNumbers = delta;

		MathProblem problem = generator.generateProblem(Difficulty.EASY);

		int[] nbrs = problem.getNumbers();

		assertEquals(nbrOfNumbers, nbrs.length, delta);

		MathProblemType operator = problem.getProblemType();

		assertTrue(operator instanceof MathProblemType);

		nbrs = problem.getNumbers();

		assertEquals(nbrOfNumbers, nbrs.length, delta);

		operator = problem.getProblemType();

		assertTrue(operator instanceof MathProblemType);
	}

	// TODO improve
	// test that every operator at least appear once as well
	public void testOperatorProbability() {
		int iterations = 100;
		int numberOfAdditionOperators = 0;
		int expectedNumberOfAdditionOperators = 10;
		int numberOfMultiplicationOperators = 0;
		int expectedNumberOfMultiplicationOperators = 10;
		int delta = 10;

		MathProblem problem = null;

		for (int i = 0; i < iterations; i++) {
			problem = generator.generateProblem(Difficulty.EASY);
			if (problem.getProblemType() instanceof AdditionProblem) {
				numberOfAdditionOperators++;
			}
			if (problem.getProblemType() instanceof MultiplicationProblem) {
				numberOfMultiplicationOperators++;
			}
		}

		assertEquals(expectedNumberOfAdditionOperators,
				numberOfAdditionOperators, delta);
		assertEquals(expectedNumberOfMultiplicationOperators,
				numberOfMultiplicationOperators, delta);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		generator = null;
	}
}
