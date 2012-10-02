package edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model;

public interface MathOperator {

	/**
	 * Evalutate the entered numbers using the decided operator
	 * 
	 * @param numberOne
	 *            A number
	 * @param numberTwo
	 *            A number
	 * @return The evaluate result of the two numbers
	 */
	public int getResult(int numberOne, int numberTwo);

}
