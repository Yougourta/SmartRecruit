package fr.smartrecruit.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.smartrecruit.R;
import fr.smartrecruit.controller.OffersAdapter;
import fr.smartrecruit.controller.OffersController;

/**
 * Code du fragment c'est ici qu'on effectue les traitements concernant chaque fragment sur son affichage
 */

public class OffersFragment extends Fragment {
    private RecyclerView offersRecycler;
    private OffersAdapter offersAdapter;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_offers, container, false);
        findViews(view);
        initAdapter();
        refresh(view);
        return view;
    }

    public void findViews(View view){
        offersRecycler = view.findViewById(R.id.listOffers);
    }

    public void initAdapter(){
        final Context context = getContext();
        offersAdapter = new OffersAdapter(OffersController.getOfferController().getApiOffers(context), context);
        offersRecycler.setAdapter(offersAdapter);
        offersRecycler.setHasFixedSize(true);

        OffersController.getOfferController().setApiAdapter(offersAdapter);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        offersRecycler.setLayoutManager(llm);
    }

    private void refresh(View view){
        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipe_to_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                OffersController.getOfferController().refreshApiOffers();
                offersAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
