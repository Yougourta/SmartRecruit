package fr.smartrecruit.view.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import fr.smartrecruit.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button candidat = findViewById(R.id.btn_candidat);
        Button recruiter = findViewById(R.id.btn_recruiter);

        candidat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CandidatActivity.class);
                startActivity(i);
            }
        });

        recruiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RecruteurActivity.class);
                startActivity(i);
            }
        });
    }

}
