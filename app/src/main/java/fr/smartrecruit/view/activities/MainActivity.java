package fr.smartrecruit.view.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import fr.smartrecruit.R;
import fr.smartrecruit.view.fragments.ApplicationsFragment;
import fr.smartrecruit.view.fragments.RecruiterFragment;

public class MainActivity extends AppCompatActivity {

    private final Fragment candidatFm = new ApplicationsFragment();
    private final Fragment recruiterFm = new RecruiterFragment();
    private final FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        Button button = findViewById(R.id.navigation_candidat);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CandidatActivity.class);
                startActivity(i);
            }
        });
    }

}
