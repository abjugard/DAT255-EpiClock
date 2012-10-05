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
package edu.chalmers.dat255.group09.Alarmed.modules.mathModule.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import edu.chalmers.dat255.group09.Alarmed.R;
import edu.chalmers.dat255.group09.Alarmed.activity.AbstractActivity;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.controller.MathController;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathProblemGenerator;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathProblemType;

/**
 * 
 * @author Joakim Persson
 * 
 */
public class MathActivity extends AbstractActivity {

	private MathController controller;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_math);
		controller = new MathController(new MathProblemGenerator());
		generateNewMathProblem();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_math, menu);
		return true;
	}

	public void onSubmitButtonPressed(View view) {
		EditText answerField = (EditText) findViewById(R.id.activity_math_answer_text);
		String text = answerField.getText().toString();

		if (isInputValid(text)) {
			int answer = Integer.parseInt(text);

			if (!controller.checkAnswer(answer)) {
				Toast.makeText(this, "OOOOOOO not good....", Toast.LENGTH_LONG)
						.show();
			}

			if (controller.isComplete()) {
				Toast.makeText(this, "Winner Winner", Toast.LENGTH_LONG).show();
				stopAlarm();
			}

			generateNewMathProblem();

		} else {

			Toast.makeText(this, "Please Enter Something!", Toast.LENGTH_LONG)
					.show();
		}
		answerField.setText("");
	}

	private void generateNewMathProblem() {
		MathProblem problem = controller.generateNewProblem();
		MathProblemType problemType = problem.getProblemType();
		int[] nbrs = problem.getNumbers();

		setProblemHeader(problemType);
		setProblemText(nbrs, problemType);
	}

	private void setProblemText(int[] nbrs, MathProblemType problemType) {
		TextView textView = (TextView) findViewById(R.id.math_activity_problem_text);
		textView.setText(problemType.getFormattedProblem(nbrs));
	}

	private void setProblemHeader(MathProblemType problemType) {
		TextView textHeader = (TextView) findViewById(R.id.math_activity_problem_header);
		textHeader.setText(problemType.getProblemHeader());
	}

	private boolean isInputValid(String text) {
		return text.length() != 0;
	}
}
