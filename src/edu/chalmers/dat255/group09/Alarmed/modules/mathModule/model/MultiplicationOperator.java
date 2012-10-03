package edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model;

public final class MultiplicationOperator implements MathOperator {

	private final static String OPERATOR = "*";

	
	public int getResult(int[] numbers) {
		int product = 1;
		for (int i = 0; i < numbers.length; i++) {
			product *= numbers[i];
		}
		return product;
	}

	@Override
	public String toString() {
		return OPERATOR;
	}
}
