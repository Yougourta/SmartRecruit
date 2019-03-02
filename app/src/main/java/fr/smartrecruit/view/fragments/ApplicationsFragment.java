package fr.smartrecruit.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.smartrecruit.R;
import fr.smartrecruit.controller.ApplicationsAdapter;
import fr.smartrecruit.controller.ApplicationsController;
import fr.smartrecruit.data.JobOffer;

/**
 * Code du fragment c'est ici qu'on effectue les traitements concernant chaque fragment sur son affichage
 */

public class ApplicationsFragment extends Fragment {
    private RecyclerView applicationsRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_applications, container, false);
        findViews(view);
        initAdapter();
        return view;
    }

    public void findViews(View view) {
        applicationsRecycler = view.findViewById(R.id.listApplications);
    }

    public void initAdapter() {
        final Context context = getContext();
        List<JobOffer> applications = ApplicationsController.getApplicationsController().getAppliedOffers(context);
        ApplicationsAdapter offersAdapter = new ApplicationsAdapter(applications, context);
        applicationsRecycler.setAdapter(offersAdapter);
        applicationsRecycler.setHasFixedSize(true);

        ApplicationsController.getApplicationsController().setApiAdapter(offersAdapter);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        applicationsRecycler.setLayoutManager(llm);
    }
}

