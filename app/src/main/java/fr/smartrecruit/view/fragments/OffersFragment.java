package fr.smartrecruit.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.smartrecruit.R;
import fr.smartrecruit.viewmodel.OffersViewModel;

/**
 * Code du fragment c'est ici qu'on effectue les traitements concernant chaque fragment sur son affichage
 */

public class OffersFragment extends Fragment {
    public OffersFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Ici le traitement specifique a ce qui se passe dans le fragment
        View view = inflater.inflate(R.layout.fragment_offers, container, false);
        new OffersViewModel().setView(view, getActivity().getApplicationContext());
        return view;
    }
}
