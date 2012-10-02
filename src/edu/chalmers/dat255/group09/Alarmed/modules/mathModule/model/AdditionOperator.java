package edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model;

public final class AdditionOperator implements MathOperator {

	private final static String OPERATOR = "+";

	@Override
	public int getResult(int numberOne, int numberTwo) {
		return numberOne + numberTwo;
	}

	@Override
	public String toString() {
		return OPERATOR;
	}

}
