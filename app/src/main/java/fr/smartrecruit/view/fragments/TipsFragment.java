package fr.smartrecruit.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import fr.smartrecruit.R;
import fr.smartrecruit.view.activities.CandidatActivity;
import fr.smartrecruit.view.activities.MainActivity;
import fr.smartrecruit.view.activities.RecruteurActivity;

public class TipsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tips, container, false);
    }

    public void onCreate(Bundle savedInstanceState) //A la   creation de la vue
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_formulaire_add_offre); //Afficher la vue portant le nom "jeu"
    }

    private void setContentView(int fragment_formulaire_add_offre) {
    }

}
