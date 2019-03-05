package fr.smartrecruit.view.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import fr.smartrecruit.R;
import fr.smartrecruit.view.fragments.recruteur.MyAppointmentsFragment;
import fr.smartrecruit.view.fragments.recruteur.MyOffersFragment;

public class RecruteurActivity extends AppCompatActivity {

    private final Fragment myOffersFragment = new MyOffersFragment();
    private final Fragment myAppointmentsFm = new MyAppointmentsFragment();
    //private final Fragment myNotificationsFm = new MyNotificationsFragment();
    private final FragmentManager fm = getSupportFragmentManager();


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int itemId = item.getItemId();
            switch (itemId) {
                case R.id.navigation_recruiter_offers:
                    fm.beginTransaction().replace(R.id.frame_recruteur, myOffersFragment).commit();
                    return true;
                case R.id.navigation_recruiter_appointments:
                    fm.beginTransaction().replace(R.id.frame_recruteur, myAppointmentsFm).commit();
                    return true;
                /*case R.id.navigation_recruiter_notifications:
                    fm.beginTransaction().replace(R.id.frame_recruteur, myNotificationsFm).commit();
                    return true;*/
                default:
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruteur);
        Toolbar toolbar = findViewById(R.id.toolbar_recruiter);
        setSupportActionBar(toolbar);

        //On simule qu'on a un notre recruteur connecte


        //Sert a afficher le bottom navigation view, celui qui sert a acceder aux fonctionnalites
        BottomNavigationView navigation = findViewById(R.id.btn_recruiter);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //Charger le fragment de la liste des offres comme principal
        fm.beginTransaction().add(R.id.frame_recruteur, myOffersFragment).show(myOffersFragment).commit();
    }
}