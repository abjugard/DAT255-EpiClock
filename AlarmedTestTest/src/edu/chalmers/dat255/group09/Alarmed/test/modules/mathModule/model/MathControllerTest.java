package edu.chalmers.dat255.group09.Alarmed.test.modules.mathModule.model;

import java.util.ArrayList;
import java.util.List;

import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.controller.MathController;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.AdditionOperator;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathProblemGenerator;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MultiplicationOperator;

public class MathControllerTest extends AndroidTestCase {

	private MathController controller;
	private int[] correctAnswers = null;
	private int problemIndex = 0;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		MockMathProblemGenerator mockGenerator = new MockMathProblemGenerator();
		controller = new MathController(mockGenerator);
		correctAnswers = new int[] { 1, 4, 1 };
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

	public void testNbrOfNumbersIncrease() {

		int answersToComplete = 3;

		for (int i = 0; i < answersToComplete; i++) {
			simulateCorrectAnswers();
		}

		MathProblem problem = controller.generateNewProblem();

		int[] nbrs = problem.getNumbers();

		assertTrue(nbrs.length > 2);

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
	 * If the paramater nbrOfNumbers > 2 then the answer will be 3
	 * 
	 */
	private class MockMathProblemGenerator extends MathProblemGenerator {

		private int index = 0;
		private List<MathProblem> problems;

		public MockMathProblemGenerator() {
			super();
			problems = new ArrayList<MathProblem>();
			problems.add(new MathProblem(new int[] { 1, 1 },
					new MultiplicationOperator()));
			problems.add(new MathProblem(new int[] { 2, 2 },
					new AdditionOperator()));
		}

		@Override
		public MathProblem generateProblem(int nbrOfNumbers) {
			MathProblem problem = null;
			if (nbrOfNumbers == 2) {
				problem = problems.get(index);

			} else {
				problem = new MathProblem(new int[] { 1, 1, 1 },
						new MultiplicationOperator());
			}

			index = (index + 1) % 2;

			return problem;
		}
	}
}
