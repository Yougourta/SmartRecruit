package fr.smartrecruit.viewmodel;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.smartrecruit.R;
import fr.smartrecruit.data.JobOffer;

public class OffersViewModel {
    private List<JobOffer> offers = new ArrayList();
    private List<JobOffer> getOffers(){
        JobOffer offer1 = new JobOffer("Burger King", "Serveur H/F", "Cormeilles-en-parisis", "Salaire min 7.5€", "https://static.actu.fr/uploads/2016/03/DSC_0090-1024x680.jpg");
        JobOffer offer2 = new JobOffer("IKEA", "Vendeur H/F", "Nanterre", "Salaire min 11.5€", "https://www.creads.fr/app/uploads/sites/1/2017/02/2000px-ikea_logo.svg_.png");
        JobOffer offer3 = new JobOffer("Lotus", "Cuisinier H/F","Clichy", "Salaire min 9€", "https://u.tfstatic.com/restaurant_photos/108/20108/169/612/le-lotus-blanc-vue-de-la-mezzanine-b46e7.jpg");
        JobOffer offer4 = new JobOffer("Pizza Hut", "Caissier H/F","Alfortville", "Salaire min 8.5€", "http://www.identilux.fr/lib/images/habillagefacade/33.jpg");
        offers.add(offer1);
        offers.add(offer2);
        offers.add(offer3);
        offers.add(offer4);
        offers.add(offer1);
        offers.add(offer2);
        offers.add(offer3);
        offers.add(offer4);
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

    public void setView(View view, Context ctx){
        final Context context = ctx;
        RecyclerView offersRecycler = view.findViewById(R.id.listOffers);

        OffersAdapter offersAdapter = new OffersAdapter(getOffers(),context);
        offersRecycler.setAdapter(offersAdapter);
        offersRecycler.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(context);
        offersRecycler.setLayoutManager(llm);

        refresh(view);
    }

    private void refresh(View view){

        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipe_to_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                JobOffer offer = new JobOffer("Added row", "Added row","Added row", "Added row", "https://www.arris.com/globalassets/images/other/android_tv_expansion.jpg");
                offers.add(offer);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

}
