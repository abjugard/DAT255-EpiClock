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
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.ModularProblem;

/**
 * 
 * @author Joakim Persson
 * 
 */
public class ModularProblemTest extends AndroidTestCase {

	private ModularProblem modularProblem;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		modularProblem = new ModularProblem();
	}

	public void testGetPromlemHeader() {
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
}
