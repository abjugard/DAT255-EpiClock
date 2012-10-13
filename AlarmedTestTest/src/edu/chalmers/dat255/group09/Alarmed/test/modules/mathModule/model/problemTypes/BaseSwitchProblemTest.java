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
package edu.chalmers.dat255.group09.Alarmed.test.modules.mathModule.model.problemTypes;

import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.BaseSwitchProblem;

/**
 * 
 * @author Joakim Persson
 *
 */
public class BaseSwitchProblemTest extends AndroidTestCase {

	private final static int ITERATIONS = 100;
	private BaseSwitchProblem problem;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		problem = new BaseSwitchProblem();
	}

	public void testGetProblemHeader() {
		String expected = "Convert to base ten";
		String actual = problem.getProblemHeader();
		assertEquals(expected, actual);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		problem = null;
	}

}
