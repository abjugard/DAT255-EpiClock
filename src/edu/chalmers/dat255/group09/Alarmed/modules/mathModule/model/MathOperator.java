package edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model;

public interface MathOperator {

	/**
	 * Evalutate the entered numbers using the decided operator
	 * 
	 * @param numbers
	 *            An array containing the numbers to evaluate
	 * @return The evaluate result of the two numbers
	 */
	public int getResult(int[] numbers);

}
