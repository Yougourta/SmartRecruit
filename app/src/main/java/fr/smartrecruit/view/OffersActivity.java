package fr.smartrecruit.view;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.smartrecruit.R;
import fr.smartrecruit.data.JobOffer;
import fr.smartrecruit.viewmodel.OffersAdapter;

public class OffersActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private List<JobOffer> offers = new ArrayList();

    private ListView listOffers;
    private OffersAdapter offersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTextMessage = (TextView) findViewById(R.id.message);

        JobOffer offer1 = new JobOffer("Burger King", "Restauration", "Cormeilles-en-parisis", "Salaire min 7.5€", R.mipmap.ic_burgerking);
        JobOffer offer2 = new JobOffer("IKEA", "Vente meubles", "Nanterre", "Salaire min 11.5€", R.mipmap.ic_ikea);
        JobOffer offer3 = new JobOffer("Lotus", "Restauration","Clichy", "Salaire min 9€", R.mipmap.ic_burgerking);
        JobOffer offer4 = new JobOffer("Pizza Hut", "Pizza","Alfortville", "Salaire min 8.5€", R.mipmap.ic_pizzahut);

        offers.add(offer1);
        offers.add(offer2);
        offers.add(offer3);
        offers.add(offer4);

        offers.add(offer1);
        offers.add(offer2);
        offers.add(offer3);
        offers.add(offer4);

        listOffers = findViewById(R.id.listOffers);
        offersAdapter = new OffersAdapter(this, offers);
        listOffers.setAdapter(offersAdapter);
    }

}
