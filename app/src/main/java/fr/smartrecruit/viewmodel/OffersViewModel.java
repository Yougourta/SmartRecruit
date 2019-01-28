package fr.smartrecruit.viewmodel;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import fr.smartrecruit.R;
import fr.smartrecruit.data.JobOffer;

public class OffersViewModel {
    private List<JobOffer> offers = new ArrayList();
    private final String DUMMY_TEXT = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
    private final String DUMMY_IMAGE = "https://cdn.pixabay.com/photo/2016/03/28/00/37/flat-1284770_960_720.png";
    private final String DUMMY_COMPANY = "Company #";
    private final String DUMMY_POSITION = "Offer #";
    private final String DUMMY_LOCATION = "Location #";
    private final String DUMMY_POSTED_DATE = "Posted 1 day ago";

    private List<JobOffer> getOffers(){
        JobOffer offer = new JobOffer(DUMMY_COMPANY, DUMMY_POSITION, DUMMY_LOCATION, DUMMY_TEXT, DUMMY_POSTED_DATE, DUMMY_IMAGE);
        for(int i=0; i<5; i++){ offers.add(offer); }
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
                JobOffer offer = new JobOffer("Added row", "Added row","Added row", DUMMY_TEXT, DUMMY_POSTED_DATE, "http://www.bluthemes.com/themes/calypso/wp-content/uploads/sites/17/2015/01/101.jpg");
                offers.add(offer);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

}
