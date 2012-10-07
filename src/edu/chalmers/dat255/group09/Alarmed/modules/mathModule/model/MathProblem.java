package edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model;

import java.util.Arrays;

public class MathProblem {

	private int[] numbers;
	private MathProblemType mathProblemType;

	/**
	 * Create a new mathproblem instance
	 * 
	 * @param numbers
	 *            The numbers to be used in this problem
	 * @param problemType
	 *            The MathProblemType to be used is this problem
	 */
	public MathProblem(int[] numbers, MathProblemType problemType) {
		this.numbers = new int[numbers.length];
		System.arraycopy(numbers, 0, this.numbers, 0, numbers.length);
		this.mathProblemType = problemType;
	}

	/**
	 * Get a copy of the numbers used in the problem
	 * 
	 * @return An array of the numbers
	 */
	public int[] getNumbers() {
		return Arrays.copyOf(numbers, numbers.length);
	}

	/**
	 * Get the problemtype
	 * 
	 * @return A MathOperator used in the problem
	 */
	public MathProblemType getProblemType() {
		return mathProblemType;
	}
}
