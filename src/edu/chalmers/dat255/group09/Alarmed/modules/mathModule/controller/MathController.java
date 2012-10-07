/*
 * Copyright (C) 2012 Joakim Persson, Daniel Augurell, Adrian BjugŒrd, Andreas RolŽn
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
 * 
 * @author Joakim Persson
 * 
 */
public class MathController {

	private MathProblem currentProblem;
	private int completedProblemsInARow;
	private MathProblemGenerator problemGenerator;
	private Difficulty currentDifficulty;
	private final static int problemsThatMustBeCompleted = 5;
	private final static int MEDIUM_DIFFICULTY_LEVEL = 3;
	private final static int HARD_DIFFICULTY_LEVEL = 4;

	/**
	 * Create a new MathController Object
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
	 * Checks if the user has answered the current problem correctly
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

	public MathProblem generateNewProblem() {

		currentProblem = problemGenerator.generateProblem(currentDifficulty);
		return currentProblem;
	}

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
	 * Get the current difficulty of the active problem
	 * 
	 * @return The current difficulty
	 */
	public Difficulty getDifficulty() {
		return currentDifficulty;
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
