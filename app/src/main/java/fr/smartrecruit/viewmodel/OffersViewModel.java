package fr.smartrecruit.viewmodel;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.smartrecruit.R;
import fr.smartrecruit.data.JobOffer;
import fr.smartrecruit.view.OffersActivity;

public class OffersViewModel {

    private List<JobOffer> getOffers(){
        List<JobOffer> offers = new ArrayList();
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

        return offers;
    }

    public void setView(OffersActivity view){
        ListView listOffers = view.findViewById(R.id.listOffers);
        OffersAdapter offersAdapter = new OffersAdapter(view, getOffers());
        listOffers.setAdapter(offersAdapter);
    }
}
