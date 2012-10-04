package edu.chalmers.dat255.group09.Alarmed.test.modules.mathModule.model;

import java.util.Arrays;

import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.AdditionProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathProblemType;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathProblem;

public class MathProblemTest extends AndroidTestCase {

	private MathProblem problem = null;

	public void testGetNumbers() {
		int[] expected = { 2, 3 };
		MathProblemType operator = new AdditionProblem();
		problem = new MathProblem(expected, operator);

		int[] actual = problem.getNumbers();
		assertTrue(Arrays.equals(expected, actual));

		expected = new int[] { 2, 3, 4, 5 };

		operator = new AdditionProblem();
		problem = new MathProblem(expected, operator);

		actual = problem.getNumbers();
		assertTrue(Arrays.equals(expected, actual));
	}

	public void testGetOperator() {
		int[] numbers = { 1, 2 };
		MathProblemType expectedOperator = new AdditionProblem();

		problem = new MathProblem(numbers, expectedOperator);

		MathProblemType actualOperator = problem.getProblemType();

		assertEquals(expectedOperator, actualOperator);

	}
}
