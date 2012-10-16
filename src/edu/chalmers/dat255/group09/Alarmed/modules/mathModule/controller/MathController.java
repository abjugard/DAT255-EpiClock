/*
 * Copyright (C) 2012 Joakim Persson, Daniel Augurell, Adrian Bjugard, Andreas Rolen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.chalmers.dat255.group09.Alarmed.modules.mathModule.controller;

import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathProblemGenerator;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.constants.Difficulty;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.MathProblemType;

/**
 * A controller for an mathproblem. It keeps tracks of the users progress and is
 * also responsible for generating new math problems and validate if the user
 * has answerd correctly.
 * 
 * @author Joakim Persson
 * 
 */
public class MathController {

	private MathProblem currentProblem;
	private int completedProblemsInARow;
	private MathProblemGenerator problemGenerator;
	private Difficulty currentDifficulty;
	private static final int PROBLEMS_TO_COMPLETE = 5;
	private static final int MEDIUM_DIFFICULTY_LEVEL = 3;
	private static final int HARD_DIFFICULTY_LEVEL = 4;

	/**
	 * Create a new MathController Object.
	 * 
	 * @param generator
	 *            The ProblemGenerator to be used
	 */
	public MathController(MathProblemGenerator generator) {
		this.problemGenerator = generator;
		this.completedProblemsInARow = 0;
		this.currentDifficulty = Difficulty.EASY;
	}

	/**
	 * Checks if the user has answered the current problem correctly.
	 * 
	 * @param answer
	 *            The Users Answer
	 * @return If it was correct or not
	 */
	public boolean checkAnswer(int answer) {
		MathProblemType operator = currentProblem.getProblemType();
		int[] nbrs = currentProblem.getNumbers();

		boolean correctAnswer = answer == operator.getResult(nbrs);

		if (correctAnswer) {
			completedProblemsInARow++;
		} else {
			completedProblemsInARow = 0;
		}

		setDifficulty();

		return correctAnswer;
	}

	/**
	 * Generate a new math problem.
	 * 
	 * @return A new mathproblem.
	 */
	public MathProblem generateNewProblem() {

		currentProblem = problemGenerator.generateProblem(currentDifficulty);
		return currentProblem;
	}

	/**
	 * Set the next problems difficulty based on how many problems in a row the
	 * users has completed.
	 */
	private void setDifficulty() {
		if (completedProblemsInARow >= HARD_DIFFICULTY_LEVEL) {
			currentDifficulty = Difficulty.HARD;
		} else if (completedProblemsInARow >= MEDIUM_DIFFICULTY_LEVEL) {
			currentDifficulty = Difficulty.MEDIUM;

		} else {
			currentDifficulty = Difficulty.EASY;
		}
	}

	/**
	 * Get the current difficulty of the active problem.
	 * 
	 * @return The current difficulty
	 */
	public Difficulty getDifficulty() {
		return currentDifficulty;
	}

	/**
	 * Returns true if the user has completed enough problems in a row and false
	 * otherwise.
	 * 
	 * @return If the user has answered correctly on enough problems
	 */
	public boolean isComplete() {
		return completedProblemsInARow == PROBLEMS_TO_COMPLETE;
	}

	/**
	 * Returns how many problems the user has solved in a row.
	 * 
	 * @return Numbers of problems solved in a row
	 */
	public int getCompletedProblems() {
		return completedProblemsInARow;
	}

}
