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
package edu.chalmers.dat255.group09.Alarmed.test.modules.mathModule.controller;

import java.util.ArrayList;
import java.util.List;

import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.controller.MathController;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.AdditionProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathProblemGenerator;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MultiplicationProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.constants.Difficulty;

/**
 * 
 * @author Joakim Persson
 * 
 */
public class MathControllerTest extends AndroidTestCase {

	private MathController controller;
	private int[] correctAnswers = null;
	private int problemIndex = 0;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		MockMathProblemGenerator mockGenerator = new MockMathProblemGenerator();
		controller = new MathController(mockGenerator);
		correctAnswers = new int[] { 1, 4 };
		problemIndex = 0;
	}

	public void testcheckAnswer() {

		// a new problem is generated
		controller.generateNewProblem();

		int answer = 1;

		assertTrue(controller.checkAnswer(answer));

		// a new problem is generated
		controller.generateNewProblem();

		assertFalse(controller.checkAnswer(answer));

		// the correct answer is 4
		answer = 4;
		assertTrue(controller.checkAnswer(answer));

	}

	public void testFiveCorrectAnswerInARow() {

		int expectedCorrectAnswers = 0;
		int actualCorrectAnswers = 0;

		for (int i = 0; i < 5; i++) {
			assertFalse(controller.isComplete());
			simulateCorrectAnswers();
			expectedCorrectAnswers++;
			actualCorrectAnswers = controller.getCompletedProblems();
			assertEquals(expectedCorrectAnswers, actualCorrectAnswers);
		}

		assertTrue(controller.isComplete());
	}

	public void testGetCorrectAnswers() {

		int expectedCorrectAnswers = 0;
		int actualCorrectAnswers = 0;
		int answer = 0;

		/*
		 * Two correct answers in a row
		 */

		for (int i = 0; i < 5; i++) {
			simulateCorrectAnswers();
			expectedCorrectAnswers++;
			actualCorrectAnswers = controller.getCompletedProblems();
			assertEquals(expectedCorrectAnswers, actualCorrectAnswers);
		}

		/*
		 * Simulate one wrong answer
		 */
		controller.generateNewProblem();
		answer = -1;
		controller.checkAnswer(answer);

		expectedCorrectAnswers = 0;
		actualCorrectAnswers = controller.getCompletedProblems();

		assertEquals(expectedCorrectAnswers, actualCorrectAnswers);
	}

	
	public void testIncreaseDifficulty() {
		fail("Not Yet Implemented");
	}

	private void simulateCorrectAnswers() {
		int answer = getCorrectAnswer();
		controller.checkAnswer(answer);
	}

	private int getCorrectAnswer() {
		MathProblem problem = controller.generateNewProblem();
		int answer = 0;
		int nbrOfNumbers = problem.getNumbers().length;

		if (nbrOfNumbers == 2) {
			answer = correctAnswers[problemIndex];
		} else {
			answer = correctAnswers[2];
		}
		problemIndex = (problemIndex + 1) % 2;
		return answer;
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		controller = null;
		correctAnswers = null;
	}

	/**
	 * A mock implementation of the MathProblemGenerator class which now only
	 * returns two different problems every other time. Which results in that
	 * the first time controller.generateNewProblem() will return a problem with
	 * answer 1 and the seconds time 4 and the third 1 and so on
	 * 
	 */
	private class MockMathProblemGenerator extends MathProblemGenerator {

		private int index = 0;
		private List<MathProblem> problems;

		public MockMathProblemGenerator() {
			super();
			problems = new ArrayList<MathProblem>();
			problems.add(new MathProblem(new int[] { 1, 1 },
					new MultiplicationProblem()));
			problems.add(new MathProblem(new int[] { 2, 2 },
					new AdditionProblem()));
		}

		@Override
		public MathProblem generateProblem(Difficulty difficulty) {
			MathProblem problem = problems.get(index);

			index = (index + 1) % 2;

			return problem;
		}
	}
}
