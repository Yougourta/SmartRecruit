package fr.smartrecruit.viewmodel;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.smartrecruit.R;
import fr.smartrecruit.data.JobOffer;

public class OffersViewModel {

    private Context context;

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

    public void setView(View view, Context context){
        this.context = context;
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

    public void apply(View view){
        Toast.makeText(context, "Hello darkness my old friend", Toast.LENGTH_SHORT).show();
    }

    public void favorite(View view){
        Toast.makeText(context, "Hello darkness my old friend", Toast.LENGTH_SHORT).show();
    }
}
