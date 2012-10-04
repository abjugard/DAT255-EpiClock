/*
 * Copyright (C) 2012 Joakim Persson, Daniel Augurell, Adrian Bjugård, Andreas Rolén
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
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathProblemType;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.constants.Difficulty;

/**
 * 
 * @author Joakim Persson
 *
 */
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
		MathProblemType operator = currentProblem.getOperator();
		int[] nbrs = currentProblem.getNumbers();

		boolean correctAnswer = answer == operator.getResult(nbrs);

		if (correctAnswer) {
			completedProblemsInARow++;
		} else {
			completedProblemsInARow = 0;
		}

		return correctAnswer;
	}

	// TODO add logic to increase difficulty
	public MathProblem generateNewProblem() {
		Difficulty difficulty = Difficulty.HARD;
		currentProblem = problemGenerator.generateProblem(difficulty);
		return currentProblem;
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
