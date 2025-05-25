package com.example.adaptivegame;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ProgressBar;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.widget.Button;
public class measure extends AppCompatActivity {

    ProgressBar progressBar1,progressBar2,progressBar3,progressBar4;
    TextView textscore;
    Button ok;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure);
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        progressBar2 = (ProgressBar)findViewById(R.id.progressBar2);
        progressBar3 = (ProgressBar)findViewById(R.id.progressBar3);
        progressBar4 = (ProgressBar) findViewById(R.id.progressBar4);
        textscore = findViewById(R.id.textView);

        ok = findViewById(R.id.button);
        try {
            Intent intent = getIntent();
            int logicValue = intent.getIntExtra("logicValue", 0);
            int adaptiveSkillsValue = intent.getIntExtra("adaptiveSkillsValue", 0);
            int analyticsSkillsValue = intent.getIntExtra("analyticsSkillsValue", 0);
            int timeManagementValue = intent.getIntExtra("timeManagementValue", 0);
            int score = intent.getIntExtra("scoreme", 0);
            progressBar1.setProgress(logicValue);
            progressBar2.setProgress(adaptiveSkillsValue);
            progressBar3.setProgress(analyticsSkillsValue);
            progressBar4.setProgress(timeManagementValue);
            textscore.setText(String.valueOf(score)+"/10");
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(measure.this, Home.class);
                    startActivity(intent);
                }
            });
        }
        catch(Exception e)
        {
            Toast.makeText(measure.this, "An error occurred: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}