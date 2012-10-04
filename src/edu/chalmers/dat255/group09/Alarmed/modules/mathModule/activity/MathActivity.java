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
package edu.chalmers.dat255.group09.Alarmed.modules.mathModule.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import edu.chalmers.dat255.group09.Alarmed.R;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.controller.MathController;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathProblem;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathProblemGenerator;
import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.model.MathProblemType;

/**
 * 
 * @author Joakim Persson
 * 
 */
public class MathActivity extends Activity {

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
		int[] nbrs = problem.getNumbers();
		MathProblemType problemType = problem.getProblemType();
		String operator = problemType.toString();
		
		
		TextView textView = (TextView) findViewById(R.id.math_activity_problem_text);
		String textViewText = createProblemString(nbrs, operator);
		
		TextView textHeader = (TextView) findViewById(R.id.math_activity_problem_header);
		
		
		textView.setText(textViewText);
	}

	private String createProblemString(int[] nbrs, String operator) {
		StringBuilder builder = new StringBuilder();
		String operatorSign = operator.toString();

		for (int i = 0; i < nbrs.length; i++) {
			builder.append(nbrs[i] + " ");
			if ((i + 1) != nbrs.length) {
				builder.append(operatorSign + " ");
			}
		}

		builder.append(" = ?");
		return builder.toString();
	}

	private boolean isInputValid(String text) {
		return text.length() != 0;
	}
}
