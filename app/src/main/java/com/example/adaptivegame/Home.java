package com.example.adaptivegame;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adaptivegame.MainActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;


public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button button = findViewById(R.id.button_start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, MainActivity.class);
                // Replace 'TargetActivity' with the name of your target activity
                MainActivity main = new MainActivity();
                //main.handleSwitch(0, 0, 0);
                startActivity(intent);
            }

    });

    }
}