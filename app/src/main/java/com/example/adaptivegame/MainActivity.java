package com.example.adaptivegame;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.airbnb.lottie.LottieAnimationView;

import android.content.Intent;
import android.graphics.Color;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.Random;
import android.os.CountDownTimer;
import android.view.View;
import android.animation.ValueAnimator;
import android.widget.ProgressBar;

import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    TextView questtxt, textview3, textview, scoreint;
    LottieAnimationView animationView, easylbl, mediumlbl, hardlbl;
    ProgressBar pg;
    Button opt1, opt2, opt3, opt4, button5;
    int stage = 0;
    int score = 0;
    List<Question> questions = new ArrayList<>();
    List<Question2> questions2 = new ArrayList<>();
    List<Question3> questions3 = new ArrayList<>();
    private CountDownTimer countDownTimer;
    private final long startTimeInMillis = 10000; // Timer duration in milliseconds
    private final long interval = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            questtxt = (TextView) findViewById(R.id.questxt);
            textview3 = (TextView) findViewById(R.id.textView3);
            textview = (TextView) findViewById(R.id.score);
            scoreint = (TextView) findViewById(R.id.scorenumber);

            pg = findViewById(R.id.progressBar);
            animationView = findViewById(R.id.kurek);
            easylbl = findViewById(R.id.indicate1);
            mediumlbl = findViewById(R.id.indicate2);
            hardlbl = findViewById(R.id.indicate3);

            opt1 = findViewById(R.id.opt1);
            opt2 = findViewById(R.id.opt2);
            opt3 = findViewById(R.id.opt3);
            opt4 = findViewById(R.id.opt4);
            button5 = findViewById(R.id.button5);

            loadQuestions();
            quizstart();
            easylbl.setVisibility(View.VISIBLE);
            mediumlbl.setVisibility(View.GONE);
            hardlbl.setVisibility(View.GONE);

            button5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        quizstart();
                        backethecolor();

                        opt1.setEnabled(true);
                        opt2.setEnabled(true);
                        opt3.setEnabled(true);
                        opt4.setEnabled(true);
                    if(finish == 10) {
                        handleTimerEnd();
                        Intent intent = new Intent(MainActivity.this, measure.class);
                        intent.putExtra("logicValue", logic);
                        intent.putExtra("adaptiveSkillsValue", adaptivescore);
                        intent.putExtra("analyticsSkillsValue", analyticskills);
                        intent.putExtra("timeManagementValue", timemanagement);
                        intent.putExtra("scoreme", score);
                        startActivity(intent);
                    }

                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "An error occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                }
            });
        }
        catch(Exception e)
        {
            Toast.makeText(MainActivity.this, "An error occurred: " + e.getMessage(), Toast.LENGTH_SHORT);
        }


    }
    int finish  = 0;
    int easylogic = 0;
    int easyadaptive = 0;
    int easyanalytic = 0;
    int easytime = 0;
    int medlogic = 0;
    int medadaptive = 0;
    int medanalytic = 0;
    int medtime = 0;
    int hardlogic = 0;
    int hardadaptive = 0;
    int hardanalytic = 0;
    int hardtime = 0;
    private void loadQuestions() {
        try {
            //QUEST1
            InputStream inputStream = getResources().openRawResource(R.raw.easy);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String strdata;


            if (inputStream != null) {
                try {
                    while ((strdata = bufferedReader.readLine()) != null) {
                        String[] parts = strdata.split("/");
                        String questionText = parts[0];

                        List<String> possibleAnswers = new ArrayList<>();
                        for (int i = 1; i < parts.length-5; i++) {
                            possibleAnswers.add(parts[i]);
                        }

                        String correctAnswer = parts[parts.length - 5];
                        easylogic += Integer.parseInt(parts[parts.length - 4]);
                        easyadaptive += Integer.parseInt(parts[parts.length - 3]);
                        easyanalytic += Integer.parseInt(parts[parts.length - 2]);
                        easytime += Integer.parseInt(parts[parts.length-1]);
                        Question question = new Question(questionText, possibleAnswers,correctAnswer);
                        questions.add(question);



                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "An error occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            //----------------------------QUEST2
            InputStream inputStream2 = getResources().openRawResource(R.raw.medium);
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream2));
            String strdata2;



            if (inputStream2 != null) {
                try {
                    while ((strdata2 = bufferedReader2.readLine()) != null) {
                        String[] parts2 = strdata2.split("/");
                        String questionText2 = parts2[0];
                        List<String> possibleAnswers2 = new ArrayList<>();
                        for (int i = 1; i < parts2.length-5; i++) {
                            possibleAnswers2.add(parts2[i]);
                        }
                        String correctAnswer2 = parts2[parts2.length - 5];
                        medlogic = Integer.parseInt(parts2[parts2.length - 4]);
                        medadaptive = Integer.parseInt(parts2[parts2.length - 3]);
                        medanalytic = Integer.parseInt(parts2[parts2.length - 2]);
                        medtime = Integer.parseInt(parts2[parts2.length - 1]);
                        Question2 question2 = new Question2(questionText2, possibleAnswers2, correctAnswer2);
                        questions2.add(question2);

                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "An error occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            //---------------------------------QUEST3
            InputStream inputStream3 = getResources().openRawResource(R.raw.hard);
            BufferedReader bufferedReader3 = new BufferedReader(new InputStreamReader(inputStream3));
            String strdata3;



            if (inputStream3 != null) {
                try {
                    while ((strdata3 = bufferedReader3.readLine()) != null) {
                        String[] parts3 = strdata3.split("/");
                        String questionText3 = parts3[0];
                        List<String> possibleAnswers3 = new ArrayList<>();
                        for (int i = 1; i < parts3.length-5; i++) {
                            possibleAnswers3.add(parts3[i]);
                        }
                        String correctAnswer3 = parts3[parts3.length - 5];
                        hardlogic = Integer.parseInt(parts3[parts3.length - 4]);
                        hardadaptive = Integer.parseInt(parts3[parts3.length - 3]);
                        hardanalytic = Integer.parseInt(parts3[parts3.length - 2]);
                        hardtime = Integer.parseInt(parts3[parts3.length - 1]);
                        Question3 question3 = new Question3(questionText3, possibleAnswers3, correctAnswer3);
                        questions3.add(question3);

                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "An error occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();

                }

            }
        }
        catch(Exception e)
        {
            Toast.makeText(MainActivity.this, "An error occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }


    public void quizstart()
    {
        button5.setEnabled(false);
        if(indicator < 3) {
            easylbl.setVisibility(View.VISIBLE);
            mediumlbl.setVisibility(View.GONE);
            hardlbl.setVisibility(View.GONE);
            startProgressAnimation();
            Random random = new Random();
            int index = random.nextInt(questions.size());
            Question question = questions.get(index);
            String questionText = question.getQuestionText();
            List<String> possibleAnswers = question.getPossibleAnswers();
            String correctAnswer = question.getCorrectAnswer();

            questtxt.setText(questionText);
            opt1.setText(possibleAnswers.get(0));
            opt2.setText(possibleAnswers.get(1));
            opt3.setText(possibleAnswers.get(2));
            opt4.setText(possibleAnswers.get(3));

            correctanswertemp = correctAnswer;
        } else if (indicator < 6) {
            easylbl.setVisibility(View.GONE);
            mediumlbl.setVisibility(View.VISIBLE);
            hardlbl.setVisibility(View.GONE);
            startProgressAnimation();
            Random random = new Random();
            int index = random.nextInt(questions2.size());
            Question2 question2 = questions2.get(index);
            String questionText = question2.getQuestionText2();
            List<String> possibleAnswers = question2.getPossibleAnswers2();
            String correctAnswer = question2.getCorrectAnswer2();

            questtxt.setText(questionText);
            opt1.setText(possibleAnswers.get(0));
            opt2.setText(possibleAnswers.get(1));
            opt3.setText(possibleAnswers.get(2));
            opt4.setText(possibleAnswers.get(3));

            correctanswertemp = correctAnswer;
        } else
        {
            easylbl.setVisibility(View.GONE);
            mediumlbl.setVisibility(View.GONE);
            hardlbl.setVisibility(View.VISIBLE);
            startProgressAnimation();
            Random random = new Random();
            int index = random.nextInt(questions3.size());
            Question3 question3 = questions3.get(index);
            String questionText = question3.getQuestionText3();
            List<String> possibleAnswers = question3.getPossibleAnswers3();
            String correctAnswer = question3.getCorrectAnswer3();

            questtxt.setText(questionText);
            opt1.setText(possibleAnswers.get(0));
            opt2.setText(possibleAnswers.get(1));
            opt3.setText(possibleAnswers.get(2));
            opt4.setText(possibleAnswers.get(3));

            correctanswertemp = correctAnswer;
        }

    }

    ValueAnimator progressAnimator = ValueAnimator.ofInt(100, 0);
    private void startProgressAnimation() {

        progressAnimator.setDuration(startTimeInMillis);
        progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int progress = (int) animation.getAnimatedValue();
                pg.setProgress(progress); // Update the progress bar
                if (progress == 0) {
                    quizstart();
                }
            }
        });
        progressAnimator.start();
    }

    private void handleTimerEnd() {

        if (progressAnimator != null && progressAnimator.isRunning()) {
            progressAnimator.cancel();
        }
    }
    public void backethecolor()
    {
        opt1.setBackgroundResource(R.drawable.background_btn_choose_color);
        opt2.setBackgroundResource(R.drawable.background_btn_choose_color);
        opt3.setBackgroundResource(R.drawable.background_btn_choose_color);
        opt4.setBackgroundResource(R.drawable.background_btn_choose_color);
        isonclick = false;
    }

    Boolean isonclick = false;
    int  indicator =  0;
    String valuechoose = "";
    int adaptivescore = 0;

    public int logic = 0;
    public int adaptiveskills = 0;
    public int analyticskills = 0;
    public int timemanagement = 0;
    String correctanswertemp = "";

    public int getLogic() {
        return logic;
    }

    public int getAdaptiveSkills() {
        return adaptiveskills;
    }

    public int getAnalyticsSkills() {
        return analyticskills;
    }

    public int getTimeManagement() {
        return timemanagement;
    }
    public void chooseme(View view) {
        try {
            finish++;
            handleTimerEnd();
            opt1.setEnabled(false);
            opt2.setEnabled(false);
            opt3.setEnabled(false);
            opt4.setEnabled(false);
            button5.setEnabled(true);
            if (!isonclick) {
                Button btn_click = (Button) view;
                btn_click.setBackgroundResource(R.drawable.background_btn_choose);
                btn_click.setTextColor(Color.parseColor("#FFFFFF"));


                valuechoose = btn_click.getText().toString();

                if(correctanswertemp.equals(valuechoose))
                {
                    btn_click.setBackgroundResource(R.drawable.correct);
                    animationView.playAnimation();
                    score++;
                    indicator++;
                    adaptivescore++;
                    scoreint.setText(String.valueOf(score));

                    if(indicator < 3)
                    {
                        logic  +=  easylogic;
                        adaptiveskills += easyadaptive;
                        analyticskills  +=  easyanalytic;
                        timemanagement +=  easytime;
                    }
                    else  if(indicator  < 6)
                    {
                        logic  += medlogic;
                        adaptiveskills += medadaptive;
                        analyticskills  +=  medanalytic;
                        timemanagement +=  medtime;
                    }
                    else
                    {
                        logic  += hardlogic;
                        adaptiveskills += hardadaptive;
                        analyticskills  +=  medanalytic;
                        timemanagement +=  medtime;
                    }

                }
                else
                {

                    if(opt1.getText().toString().equals(correctanswertemp))
                    {
                        opt1.setBackgroundResource(R.drawable.correct);
                    }
                    else if(opt2.getText().toString().equals(correctanswertemp))
                    {
                        opt2.setBackgroundResource(R.drawable.correct);
                    }
                    else if(opt3.getText().toString().equals(correctanswertemp))
                    {
                        opt3.setBackgroundResource(R.drawable.correct);
                    }
                    if(opt4.getText().toString().equals(correctanswertemp))
                    {
                        opt4.setBackgroundResource(R.drawable.correct);
                    }
                    indicator--;
                 adaptivescore--;
                }
                isonclick = true;
            }

        }
        catch(Exception e)
        {
            Toast.makeText(MainActivity.this, "An error occurred: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }




    public class Question {
        private String questionText;
        private List<String> possibleAnswers;
        private String correctAnswer;

        public Question(String questionText, List<String> possibleAnswers, String correctAnswer) {
            this.questionText = questionText;
            this.possibleAnswers = possibleAnswers;
            this.correctAnswer = correctAnswer;
        }

        public String getQuestionText() {
            return questionText;
        }

        public List<String> getPossibleAnswers() {
            return possibleAnswers;
        }

        public String getCorrectAnswer() {
            return correctAnswer;

        }
    }

        public class Question2 {
            private String questionText2;
            private List<String> possibleAnswers2;
            private String correctAnswer2;

            public Question2(String questionText2, List<String> possibleAnswers2, String correctAnswer2) {
                this.questionText2 = questionText2;
                this.possibleAnswers2 = possibleAnswers2;
                this.correctAnswer2 = correctAnswer2;
            }

            public String getQuestionText2() {
                return questionText2;
            }

            public List<String> getPossibleAnswers2() {
                return possibleAnswers2;
            }

            public String getCorrectAnswer2() {
                return correctAnswer2;
            }
        }

            public class Question3 {
                private String questionText3;
                private List<String> possibleAnswers3;
                private String correctAnswer3;

                public Question3(String questionText3, List<String> possibleAnswers3, String correctAnswer3) {
                    this.questionText3 = questionText3;
                    this.possibleAnswers3 = possibleAnswers3;
                    this.correctAnswer3 = correctAnswer3;
                }

                public String getQuestionText3() {
                    return questionText3;
                }

                public List<String> getPossibleAnswers3() {
                    return possibleAnswers3;
                }

                public String getCorrectAnswer3() {
                    return correctAnswer3;
                }
            }
}