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
		String operator = problem.getOperator().toString();
		TextView textView = (TextView) findViewById(R.id.math_activity_problem_text);
		String textViewText = nbrs[0] + " " + operator + " " + nbrs[1];
		textView.setText(textViewText);
	}

	private boolean isInputValid(String text) {
		return text.length() != 0;
	}
}
