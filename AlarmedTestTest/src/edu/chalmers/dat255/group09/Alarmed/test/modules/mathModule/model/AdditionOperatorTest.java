package edu.chalmers.dat255.group09.Alarmed.test.modules.mathModule.model;

import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.AdditionOperator;
import android.test.AndroidTestCase;

public class AdditionOperatorTest extends AndroidTestCase {

	private AdditionOperator additionOperator;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		additionOperator = new AdditionOperator();
	}

	public void testToString() {
		String expected = "+";
		String actual = additionOperator.toString();
		assertEquals(expected, actual);
	}

	public void testGetResult() {
		int actualResult = additionOperator.getResult(new int[] { 6, 6 });
		int expectedResult = 12;
		assertEquals(expectedResult, actualResult);

		actualResult = additionOperator.getResult(new int[] { 2, -1 });
		expectedResult = 1;
		assertEquals(expectedResult, actualResult);

		actualResult = additionOperator.getResult(new int[] { 5, -5, 1 });
		expectedResult = 1;
		assertEquals(expectedResult, actualResult);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		additionOperator = null;
	}
}
