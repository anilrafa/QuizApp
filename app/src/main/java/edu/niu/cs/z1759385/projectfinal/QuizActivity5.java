package edu.niu.cs.z1759385.projectfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
/**
 * Created by Anil Vishwanath Murahari (z1759385)
 * Date: 5/9/16.
 * Project: CSCI 240 Quiz
 * Allows users to take quiz from random pool of question of each week and displays the results
 */

public class QuizActivity5 extends AppCompatActivity {
    //data members
    private TextView tbQuestion;
    private Button btnNxt;
    private RadioGroup rg;
    private RadioButton rb1,rb2,rb3,rb4;
    //questions from quiz
    String questions[]= {"main() is a function.",
            "A function knows _______ about the calling code.",
            "Every function must return a value.",
            "It is legal and acceptable to have two local variables in two different functions with the same name and the same data type.",
            "If a function takes two integer arguments it"};
    //answers to quiz
    String answers[] = {"true","nothing","false","true","can return any valid data type"};
    //options
    String options[] = {"true","false","it depends on how you define it","All the above",
            "something","nothing","everything","none",
            "true","false","true","false",
            "true","false","only if they always have different values","only if they always have the same value",
             "must return an int","must return a numeric value of some kind","can return any valid data type","nothing"
             };
    int trueorFalse[] = {0,0,1,0,0};
    int flag = 0;
    public static int correct=0,incorrect=0;
    int[] randomArray = {0,1,2,3,4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        //connect to the screen
        tbQuestion = (TextView)findViewById(R.id.textView);
        btnNxt = (Button)findViewById(R.id.btnNextQuestion);
        rg = (RadioGroup)findViewById(R.id.radioGroup);
        rb1 = (RadioButton)findViewById(R.id.radioButton);
        rb2 = (RadioButton)findViewById(R.id.radioButton2);
        rb3 = (RadioButton)findViewById(R.id.radioButton3);
        rb4 = (RadioButton)findViewById(R.id.radioButton4);
        //int[] randomArray = {0,1,2,3,4};
        correct = 0;
        incorrect = 0;
        //shuffleArray(randomArray);
        ////shuffle the array to get random question
        QuizActivity.shuffleArray(randomArray);
        if(trueorFalse[randomArray[flag]] == 0) {
            rb3.setVisibility(View.VISIBLE);
            rb4.setVisibility(View.VISIBLE);
            tbQuestion.setText(questions[randomArray[flag]]);
            rb1.setText(options[(randomArray[flag] * 4)]);
            rb2.setText(options[(randomArray[flag] * 4) + 1]);
            rb3.setText(options[(randomArray[flag] * 4) + 2]);
            rb4.setText(options[(randomArray[flag] * 4) + 3]);
        }
        else
        {
            tbQuestion.setText(questions[randomArray[flag]]);
            rb1.setText(options[(randomArray[flag] * 4)]);
            rb2.setText(options[(randomArray[flag] * 4) + 1]);
            rb3.setVisibility(View.INVISIBLE);
            rb4.setVisibility(View.INVISIBLE);
        }

        //Toast.makeText(QuizActivity.this, "", Toast.LENGTH_SHORT).show();
    }
    public void nextQuestion(View view)
    {
        //throw a toast message if no option is selected
        if(!(rb1.isChecked()||rb2.isChecked()||rb3.isChecked()||rb4.isChecked()))
        {
            Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton ans = (RadioButton)findViewById(rg.getCheckedRadioButtonId());
        //get the selected answer
        String ansText = ans.getText().toString();
        //check the selected answer with correct answer
        if(ansText.equalsIgnoreCase(answers[randomArray[flag]]))
        {
            correct++;  //check the selected answer with correct answer
        }
        else
        {
            incorrect++; //increment the incorrect count
        }
        flag++;
        if(flag < 5)
        {
            if(trueorFalse[randomArray[flag]] == 0) {
                //if the question is not true or false question
                //make the rb3 and rb4 visible
                rb3.setVisibility(View.VISIBLE);
                rb4.setVisibility(View.VISIBLE);
                //clear the previously checked radio button
                rg.clearCheck();
                //set the question and answers
                tbQuestion.setText(questions[randomArray[flag]]);
                rb1.setText(options[(randomArray[flag] * 4)]);
                rb2.setText(options[(randomArray[flag] * 4) + 1]);
                rb3.setText(options[(randomArray[flag] * 4) + 2]);
                rb4.setText(options[(randomArray[flag] * 4) + 3]);
            }
            else{
                //if the question is a true or false question
                rg.clearCheck();
                tbQuestion.setText(questions[randomArray[flag]]);
                rb1.setText(options[(randomArray[flag] * 4)]);
                rb2.setText(options[(randomArray[flag] * 4) + 1]);
                //hide the radiobutton rb3 and rb4
                rb3.setVisibility(View.INVISIBLE);
                rb4.setVisibility(View.INVISIBLE);
            }

        }
        else
        {
            //create bundle to get correct and incorrect answers
            Bundle bundle = new Bundle();
            bundle.putInt("Correct",correct);
            bundle.putInt("Incorrect",incorrect);
            //create and start activity
            Intent in = new Intent(getApplicationContext(),ResultActivity.class);
            in.putExtras(bundle);
            startActivity(in);
        }
    }
}