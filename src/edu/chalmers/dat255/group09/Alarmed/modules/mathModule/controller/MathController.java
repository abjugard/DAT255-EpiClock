package edu.chalmers.dat255.group09.Alarmed.modules.mathModule.controller;

import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathProblemGenerator;

public class MathController {
	private MathProblem currentProblem;
	private int completedProblemsInARow;
	private MathProblemGenerator problemGenerator;

	public boolean checkAnswer(int answer) {
		return false;
	}

	private boolean evaluateAnswer(int answer) {
		return false;
	}

	public boolean isComplete() {
		return false;
	}

	public MathProblem getProblem() {
		return currentProblem;
	}
}
