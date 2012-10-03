package edu.chalmers.dat255.group09.Alarmed.modules.mathModule.controller;

import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathOperator;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathProblemGenerator;

public class MathController {

	private MathProblem currentProblem;
	private int completedProblemsInARow;
	private MathProblemGenerator problemGenerator;
	private final static int problemsThatMustBeCompleted = 5;
	private final static int completedProblemsToIncreaseDiffulcty = 3;

	/**
	 * Create a new MathController Object
	 * 
	 * @param generator
	 *            The ProblemGenerator to be used
	 */
	public MathController(MathProblemGenerator generator) {
		this.problemGenerator = generator;
		this.completedProblemsInARow = 0;
	}

	/**
	 * Checks if the user has answerd the current problem correctly
	 * 
	 * @param answer
	 *            The Users Answer
	 * @return If it was correct or not
	 */
	public boolean checkAnswer(int answer) {
		MathOperator operator = currentProblem.getOperator();
		int[] nbrs = currentProblem.getNumbers();

		boolean correctAnswer = answer == operator.getResult(nbrs);

		if (correctAnswer) {
			completedProblemsInARow++;
		} else {
			completedProblemsInARow = 0;
		}

		return correctAnswer;
	}

	/**
	 * Get a new MathProblem
	 * 
	 * @return A new MathProblem
	 */
	public MathProblem generateNewProblem() {

		int nbrOfNumbers = getNbrOfNumbers();

		currentProblem = problemGenerator.generateProblem(nbrOfNumbers);
		return currentProblem;
	}

	private int getNbrOfNumbers() {
		
		int nbrOfNumbers;
		
		if (completedProblemsInARow >= completedProblemsToIncreaseDiffulcty) {
			nbrOfNumbers = 3;
		} else {
			nbrOfNumbers = 2;
		}
		return nbrOfNumbers;
	}

	/**
	 * Returns true if the user has completed enough problems in a row and false
	 * otherwise
	 * 
	 * @return If the user has answered correctly on enough problems
	 */
	public boolean isComplete() {
		return completedProblemsInARow == problemsThatMustBeCompleted;
	}

	/**
	 * Returns how many problems the user has solved in a row
	 * 
	 * @return Numbers of problems solved in a row
	 */
	public int getCompletedProblems() {
		return completedProblemsInARow;
	}

}
