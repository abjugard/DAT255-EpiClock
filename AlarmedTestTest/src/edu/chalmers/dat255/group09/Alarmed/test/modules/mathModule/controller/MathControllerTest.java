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
package edu.chalmers.dat255.group09.Alarmed.test.modules.mathModule.controller;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.controller.MathController;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.factory.MathProblemFactory;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.constants.Difficulty;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.AdditionProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.MultiplicationProblem;

/**
 * A test class for the MathController class.
 * 
 * @author Joakim Persson
 * 
 */
public class MathControllerTest extends TestCase {

	private MathController controller;
	private final int[] correctAnswers = new int[] { 1, 4 };
	private static final int CORRECT_ANSWERS_TO_COMPLETE = 5;
	private static final int CORRECT_ANSWERS_TO_HARD = 4;
	private static final int CORRECT_ANSWERS_TO_MEDIUM = 3;
	private int problemIndex = 0;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		MockMathProblemGenerator mockGenerator = new MockMathProblemGenerator();
		controller = new MathController(mockGenerator);
		problemIndex = 0;
	}

	/**
	 * Tests the controllers ability to verify that an aswer is correct or not.
	 */
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

	/**
	 * Simulate that the user has completed five problems in a row correctly and
	 * that the method MathController#isComplete() only returns true after five
	 * completed problems in a row.
	 */
	public void testFiveCorrectAnswerInARow() {

		int expectedCorrectAnswers = 0;
		int actualCorrectAnswers = 0;

		for (int i = 0; i < CORRECT_ANSWERS_TO_COMPLETE; i++) {
			assertFalse(controller.isComplete());
			simulateCorrectAnswer();
			expectedCorrectAnswers++;
			actualCorrectAnswers = controller.getCompletedProblems();
			assertEquals(expectedCorrectAnswers, actualCorrectAnswers);
		}

		assertTrue(controller.isComplete());
	}

	/**
	 * Tests the getCompletedProblems method works as expected and that the
	 * number of corrects answers increases every time there is an correct
	 * answer.
	 */
	public void testGetCorrectAnswersAfterTwoCorrectAnswers() {
		int expectedCorrectAnswers = 0;
		int actualCorrectAnswers = 0;

		for (int i = 0; i < 2; i++) {
			simulateCorrectAnswer();
			expectedCorrectAnswers++;
			actualCorrectAnswers = controller.getCompletedProblems();
			assertEquals(expectedCorrectAnswers, actualCorrectAnswers);
		}
	}

	/**
	 * Tests the getCompletedProblems method works as expected and that the
	 * number of corrects answers increases every time there is an correct
	 * answer. This method also tests that the count is reset after an incorrect
	 * answer.
	 */
	public void testGetCorrectAnswersAfterOneCorrectAndOneWrong() {
		int expectedCorrectAnswers = 1;
		int actualCorrectAnswers = 0;

		simulateCorrectAnswer();

		actualCorrectAnswers = controller.getCompletedProblems();
		assertEquals(expectedCorrectAnswers, actualCorrectAnswers);

		/*
		 * Enter a faulty answer
		 */
		controller.generateNewProblem();
		controller.checkAnswer(-1);

		expectedCorrectAnswers = 0;
		actualCorrectAnswers = controller.getCompletedProblems();

		assertEquals(expectedCorrectAnswers, actualCorrectAnswers);
	}

	/**
	 * Tests that the controllers starting difficulty is easy.
	 */
	public void testStartingDifficultyIsEasy() {
		Difficulty actual = controller.getDifficulty();
		Difficulty expected = Difficulty.EASY;

		assertEquals(expected, actual);
	}

	/**
	 * Tests that the controller increases the difficulty to medium after the
	 * users has completed enough answers in a row.
	 */
	public void testIncreaseDifficultyToMedium() {
		Difficulty actual = controller.getDifficulty();
		Difficulty expected = Difficulty.EASY;
		assertEquals(expected, actual);

		simulateCorrectAnswers(CORRECT_ANSWERS_TO_MEDIUM);

		expected = Difficulty.MEDIUM;
		actual = controller.getDifficulty();
		assertEquals(expected, actual);

	}

	/**
	 * Tests that the controller increases the difficulty to hard after the
	 * users has completed enough answers in a row.
	 */
	public void testIncreaseDifficultyToHard() {
		Difficulty actual;
		Difficulty expected = Difficulty.HARD;

		simulateCorrectAnswers(CORRECT_ANSWERS_TO_HARD);

		// Simulate difficulty to change
		controller.generateNewProblem();

		actual = controller.getDifficulty();
		assertEquals(expected, actual);
	}

	/**
	 * First simulates enough correct problems in a row to reach medium level
	 * and then simulates an incorrect answer to should set the current
	 * difficulty to easy.
	 */
	public void testDifficultyReset() {

		simulateCorrectAnswers(CORRECT_ANSWERS_TO_MEDIUM);

		Difficulty expected = Difficulty.MEDIUM;
		Difficulty actual = controller.getDifficulty();
		assertEquals(expected, actual);

		/*
		 * Simulate One Wrong Answer
		 */
		controller.generateNewProblem();
		controller.checkAnswer(-1);

		expected = Difficulty.EASY;
		actual = controller.getDifficulty();
		assertEquals(expected, actual);
	}

	/**
	 * Simulate that the user has completed a specified numbers of problems in a
	 * row.
	 * 
	 * @param nbrOfAnsers
	 *            The number of correct answers to simulate.
	 */
	private void simulateCorrectAnswers(int nbrOfAnsers) {
		for (int i = 0; i < nbrOfAnsers; i++) {
			simulateCorrectAnswer();
		}
	}

	/**
	 * Using the MockMathProblemgenerators implementation to simulate that the
	 * user answered one problem correctly.
	 */
	private void simulateCorrectAnswer() {
		int answer = getCorrectAnswer();
		controller.checkAnswer(answer);
	}

	/**
	 * Generate a new problem and get the answer to the same problem.
	 * 
	 * @return The correct answer to the newly generated problem.
	 */
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
	}

	/**
	 * A mock implementation of the MathProblemGenerator class which now only
	 * returns two different problems every other time. Which results in that
	 * the first time controller.generateNewProblem() will return a problem with
	 * answer 1 and the seconds time 4 and the third 1 and so on
	 * 
	 */
	private class MockMathProblemGenerator extends MathProblemFactory {

		private int index = 0;
		private List<MathProblem> problems;

		/**
		 * Create a new instance of the MockMathProblemGenerator and creates the
		 * pregenerated list of problems.
		 */
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
