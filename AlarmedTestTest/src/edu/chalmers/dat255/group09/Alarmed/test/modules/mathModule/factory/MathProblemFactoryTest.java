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
package edu.chalmers.dat255.group09.Alarmed.test.modules.mathModule.factory;

import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.factory.MathProblemFactory;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.constants.Difficulty;

/**
 * 
 * @author Joakim Persson
 * 
 */
public class MathProblemFactoryTest extends AndroidTestCase {

	private MathProblemFactory generator;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		generator = new MathProblemFactory();
	}

	/**
	 * Tests the get problem method. By testing that it returns mathproblems
	 * types.
	 */
	public void testGetProblem() {

		final int delta = 3;
		int nbrOfNumbers = delta;

		MathProblem problem = generator.generateProblem(Difficulty.EASY);

		int[] nbrs = problem.getNumbers();

		assertEquals(nbrOfNumbers, nbrs.length, delta);

		nbrs = problem.getNumbers();

		assertEquals(nbrOfNumbers, nbrs.length, delta);

	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		generator = null;
	}
}
