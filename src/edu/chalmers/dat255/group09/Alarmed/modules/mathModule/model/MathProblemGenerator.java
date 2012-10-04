package edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model;

public class MathProblemGenerator {

	private final static int UPPER_LIMIT = 10;
	private final static int LOWER_LIMIT = 0;
	private final static int NBR_OF_OPERATORS = 2;

	public MathProblem generateProblem(int nbrOfNumbers) {
		MathProblemType operator = generateOperator();
		int[] numbers = generateNumbers(nbrOfNumbers);

		return new MathProblem(numbers, operator);
	}

	private MathProblemType generateOperator() {

		MathProblemType operator = null;
		int rand = (int) Math.floor((Math.random() * NBR_OF_OPERATORS));

		if (rand == 0) {
			operator = new AdditionProblem();
		}

		if (rand == 1) {
			operator = new MultiplicationProblem();
		}

		return operator;
	}

	private int[] generateNumbers(int numberOfNumbers) {
		int[] numbers = new int[numberOfNumbers];

		for (int i = 0; i < numberOfNumbers; i++) {
			numbers[i] = generateRandomNumber();
		}

		return numbers;
	}

	private int generateRandomNumber() {
		int diff = UPPER_LIMIT - LOWER_LIMIT;

		return (int) (LOWER_LIMIT + diff * Math.random());
	}
}
