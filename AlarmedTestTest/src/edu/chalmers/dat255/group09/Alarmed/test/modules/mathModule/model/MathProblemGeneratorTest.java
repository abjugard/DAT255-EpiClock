package edu.chalmers.dat255.group09.Alarmed.test.modules.mathModule.model;

import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.AdditionProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathProblemType;
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

		int nbrOfNumbers = 2;

		MathProblem problem = generator.generateProblem(nbrOfNumbers);

		int[] nbrs = problem.getNumbers();

		assertEquals(nbrOfNumbers, nbrs.length);

		for (int i = 0; i < nbrs.length; i++) {
			assertTrue(nbrs[i] <= upperLimit);
			assertTrue(nbrs[i] >= lowerLimit);
		}

		MathProblemType operator = problem.getOperator();

		assertTrue(operator instanceof MathProblemType);

		nbrs = problem.getNumbers();

		assertEquals(nbrOfNumbers, nbrs.length);

		for (int i = 0; i < nbrs.length; i++) {
			assertTrue(nbrs[i] <= upperLimit);
			assertTrue(nbrs[i] >= lowerLimit);
		}

		operator = problem.getOperator();

		assertTrue(operator instanceof MathProblemType);
	}

	public void testOperatorProbability() {
		int iterations = 1000;
		int nbrOfNumbers = 2;
		int numberOfAdditionOperators = 0;
		int expectedNumberOfAdditionOperators = 500;
		int delta = 50;

		MathProblem problem = null;

		for (int i = 0; i < iterations; i++) {
			problem = generator.generateProblem(nbrOfNumbers);
			if (problem.getOperator() instanceof AdditionProblem) {
				numberOfAdditionOperators++;
			}
		}

		assertEquals(expectedNumberOfAdditionOperators,
				numberOfAdditionOperators, delta);
	}

	public void testNumbersInterval() {
		int iterations = 1000;
		int nbrOfNumbers = 2;

		MathProblem problem = null;

		for (int i = 0; i < iterations; i++) {
			problem = generator.generateProblem(nbrOfNumbers);
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
