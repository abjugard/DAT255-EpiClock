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
package edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes;

import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.constants.Difficulty;

/**
 * 
 * @author Joakim Persson
 * 
 */
public interface MathProblemType {

	/**
	 * Evalutate the entered numbers using the decided operator
	 * 
	 * @param numbers
	 *            An array containing the numbers to evaluate
	 * @return The evaluate result of the two numbers
	 */
	public int getResult(int[] numbers);

	/**
	 * Generate numbers customized for the problem based on the difficulty
	 * 
	 * @param difficulty
	 *            The difficulty of the problem
	 * @return An array of numbers
	 */
	public int[] generateNumbers(Difficulty difficulty);

	/**
	 * Get The Header/Title for the problem
	 * 
	 * @return The Title of the problem
	 */
	public String getProblemHeader();

	/**
	 * Get a formatted output of the current problem
	 * 
	 * @param nbrs
	 *            The numbers in the current problem
	 * @return A formatted String
	 */
	public String getFormattedProblem(int[] nbrs);

}
