/*
 * Copyright (C) 2012 Joakim Persson, Daniel Augurell, Adrian Bjug�rd, Andreas Rol�n
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
package edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model;

import java.util.Arrays;

import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.MathProblemType;

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
