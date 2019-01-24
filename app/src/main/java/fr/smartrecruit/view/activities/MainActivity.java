package fr.smartrecruit.view.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import fr.smartrecruit.R;
import fr.smartrecruit.view.fragments.ApplicationsFragment;
import fr.smartrecruit.view.fragments.NotificationsFragment;
import fr.smartrecruit.view.fragments.OffersFragment;
import fr.smartrecruit.view.fragments.TipsFragment;

public class MainActivity extends AppCompatActivity {

    final Fragment offersFm = new OffersFragment();
    final Fragment applicationsFragmentFm = new ApplicationsFragment();
    final Fragment tipsFm = new TipsFragment();
    final Fragment notificationsFm = new NotificationsFragment();
    final FragmentManager fm = getSupportFragmentManager();


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fm.beginTransaction().replace(R.id.frame, offersFm).commit();
                    return true;
                case R.id.navigation_applications:
                    fm.beginTransaction().replace(R.id.frame, applicationsFragmentFm).commit();
                    return true;
                case R.id.navigation_tips:
                    fm.beginTransaction().replace(R.id.frame, tipsFm).commit();
                    return true;
                case R.id.navigation_notifications:
                    fm.beginTransaction().replace(R.id.frame, notificationsFm).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Sert a afficher le bottom navigation view, celui qui sert a acceder aux fonctionnalites
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //Charger le fragment de la liste des offres comme principal
        fm.beginTransaction().add(R.id.frame, offersFm).show(offersFm).commit();
    }

}
