package edu.chalmers.dat255.group09.Alarmed.test.modules.mathModule.model;

import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.AdditionOperator;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathOperator;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathProblemGenerator;

public class MathProblemGeneratorTest extends AndroidTestCase {

	private MathProblemGenerator generator;
	private int upperLimit = 10;
	private int lowerLimit = 0;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		generator = new MathProblemGenerator();
	}

	public void testGetProblem() {

		MathProblem problem = generator.generateProblem();

		int[] nbrs = problem.getNumbers();

		for (int i = 0; i < nbrs.length; i++) {
			assertTrue(nbrs[i] <= upperLimit);
			assertTrue(nbrs[i] >= lowerLimit);
		}

		MathOperator operator = problem.getOperator();

		assertTrue(operator instanceof MathOperator);

	}

	public void testOperatorProbability() {
		int iterations = 1000;
		int numberOfAdditionOperators = 0;
		int expectedNumberOfAdditionOperators = 500;
		int delta = 50;

		MathProblem problem = null;

		for (int i = 0; i < iterations; i++) {
			problem = generator.generateProblem();
			if (problem.getOperator() instanceof AdditionOperator) {
				numberOfAdditionOperators++;
			}
		}

		assertEquals(expectedNumberOfAdditionOperators,
				numberOfAdditionOperators, delta);
	}

	public void testNumbersInterval() {
		int iterations = 1000;

		MathProblem problem = null;

		for (int i = 0; i < iterations; i++) {
			problem = generator.generateProblem();
			int[] nbrs = problem.getNumbers();
			for (int j = 0; j < nbrs.length; j++) {
				if (nbrs[j] > upperLimit || nbrs[j] < lowerLimit) {
					fail("Number out of limit");
				}
			}
		}

		// at this point no number is out of interval
		// TODO reevaluate end condition
		assertTrue(true);

	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		generator = null;
	}
}
