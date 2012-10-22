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
package edu.chalmers.dat255.group09.Alarmed.modules.mathModule.factory;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import edu.chalmers.dat255.group09.Alarmed.constants.Constants;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.constants.Difficulty;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.AdditionProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.BaseSwitchProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.DifferenceOfTwoSquaresProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.FactorialProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.FibonacciProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.MathProblemType;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.ModularProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.MultiplicationProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.problemTypes.PrimeProblem;

/**
 * A class that generates new math problems.
 * 
 * @author Joakim Persson
 * 
 */
public class MathProblemFactory {

	private List<Class<?>> mathProblemTypes;
	private static final String TAG = Constants.PACKAGE + ".MathProblemFactory";

	/**
	 * Create a new MathProblemGenerator.
	 */
	public MathProblemFactory() {
		mathProblemTypes = new ArrayList<Class<?>>();

		mathProblemTypes.add(AdditionProblem.class);
		mathProblemTypes.add(MultiplicationProblem.class);
		mathProblemTypes.add(PrimeProblem.class);
		mathProblemTypes.add(FactorialProblem.class);
		mathProblemTypes.add(FibonacciProblem.class);
		mathProblemTypes.add(ModularProblem.class);
		mathProblemTypes.add(DifferenceOfTwoSquaresProblem.class);
		mathProblemTypes.add(BaseSwitchProblem.class);

	}

	/**
	 * Generate a new math problem with an specified difficulty.
	 * 
	 * @param difficulty
	 *            The difficulty to use in the problem.
	 * @return A new mathproblem.
	 */
	public MathProblem generateProblem(Difficulty difficulty) {
		MathProblemType operator = generateProblemType();
		int[] numbers = operator.generateNumbers(difficulty);

		return new MathProblem(numbers, operator);
	}

	/**
	 * Generate a random math problem type. If a problem arise with the random
	 * selection an AdditionProblem is returned.
	 * 
	 * @return A random math problem type.
	 */
	private MathProblemType generateProblemType() {
		int nbrOfOperators = mathProblemTypes.size();

		int rand = (int) Math.floor((Math.random() * nbrOfOperators));

		try {
			return (MathProblemType) mathProblemTypes.get(rand).newInstance();
		} catch (InstantiationException e) {
			Log.d(TAG, e.getMessage());
		} catch (IllegalAccessException e) {
			Log.d(TAG, e.getMessage());
		}

		// return default problem.
		return new AdditionProblem();
	}
}
