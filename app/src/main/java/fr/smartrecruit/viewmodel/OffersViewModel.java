package fr.smartrecruit.viewmodel;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

    public void setView(OffersActivity view, Context context){
        RecyclerView offersRecycler = view.findViewById(R.id.listOffers);
        OffersAdapter offersAdapter = new OffersAdapter(getOffers(),context);
        offersRecycler.setAdapter(offersAdapter);
        offersRecycler.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(context);
        offersRecycler.setLayoutManager(llm);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(offersRecycler.getContext(),
                        llm.getOrientation());
        offersRecycler.addItemDecoration(dividerItemDecoration);
    }
}
