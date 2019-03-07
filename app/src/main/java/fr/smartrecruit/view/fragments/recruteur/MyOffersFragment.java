package fr.smartrecruit.view.fragments.recruteur;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fr.smartrecruit.R;
import fr.smartrecruit.controller.candidat.FavoritesAdapter;
import fr.smartrecruit.controller.candidat.FavoritesController;
import fr.smartrecruit.controller.recruiter.MyOffersAdapter;
import fr.smartrecruit.controller.recruiter.MyOffersController;
import fr.smartrecruit.data.JobOffer;

public class MyOffersFragment extends Fragment {

    private RecyclerView offersRecycler;
    private List<JobOffer> offers;
    private MyOffersAdapter adapter;
    public MyOffersFragment() { }

    /*
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_offers, container, false);
    } */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_offers, container, false);
        findViews(view);
        initAdapter();
        return view;
    }

    public void findViews(View view){
        offersRecycler = view.findViewById(R.id.listMyOffers);
    }

    public void initAdapter(){
        final Context context = getContext();
        offers = MyOffersController.getMyOffersController().getApiMyOffers(context);
        adapter = new MyOffersAdapter(offers, context);
        offersRecycler.setAdapter(adapter);
        offersRecycler.setHasFixedSize(true);
        MyOffersController.getMyOffersController().setApiAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        offersRecycler.setLayoutManager(llm);
        offersRecycler.setItemAnimator(new DefaultItemAnimator());
        offersRecycler.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
    }
}
