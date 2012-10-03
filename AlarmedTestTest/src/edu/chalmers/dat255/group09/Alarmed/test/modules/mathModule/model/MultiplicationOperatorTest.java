package edu.chalmers.dat255.group09.Alarmed.test.modules.mathModule.model;

import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MultiplicationOperator;

public class MultiplicationOperatorTest extends AndroidTestCase {

	private MultiplicationOperator multiOperator;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		multiOperator = new MultiplicationOperator();
	}

	public void testToString() {

		String expected = "*";
		String actual = multiOperator.toString();
		assertEquals(expected, actual);
	}

	public void testGetResult() {
		int actualResult = multiOperator.getResult(new int[] { 6, 6 });
		int expectedResult = 36;
		assertEquals(expectedResult, actualResult);

		actualResult = multiOperator.getResult(new int[] { 2, -1 });
		expectedResult = -2;
		assertEquals(expectedResult, actualResult);

		actualResult = multiOperator.getResult(new int[] { 5, -5, -1 });
		expectedResult = 25;
		assertEquals(expectedResult, actualResult);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		multiOperator = null;
	}
}
