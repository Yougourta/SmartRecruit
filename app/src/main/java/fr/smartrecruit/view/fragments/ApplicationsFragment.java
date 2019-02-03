package fr.smartrecruit.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.smartrecruit.R;
import fr.smartrecruit.viewmodel.ApplicationsAdapter;
import fr.smartrecruit.viewmodel.OffersViewModel;

/**
 * Code du fragment c'est ici qu'on effectue les traitements concernant chaque fragment sur son affichage
 */

public class ApplicationsFragment extends Fragment {
    private OffersViewModel offersViewModel = new OffersViewModel();
    private RecyclerView offersRecycler;

    public ApplicationsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_applications, container, false);
        findViews(view);
        initAdapter();
        return view;

    }

    public void findViews(View view) {
        offersRecycler = view.findViewById(R.id.listOffers);
    }

    public void initAdapter() {
        ApplicationsAdapter offersAdapter = new ApplicationsAdapter(offersViewModel.getRandomOffers(), getContext());
        offersRecycler.setAdapter(offersAdapter);
        offersRecycler.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        offersRecycler.setLayoutManager(llm);
    }
}

