package edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model;

public final class AdditionOperator implements MathOperator {

	private final static String OPERATOR = "+";

	public int getResult(int[] numbers) {
		int sum = 0;
		for (int i = 0; i < numbers.length; i++) {
			sum += numbers[i];
		}
		return sum;

	}

	@Override
	public String toString() {
		return OPERATOR;
	}

}
