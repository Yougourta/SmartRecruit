package fr.smartrecruit.view.fragments.candidat;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.smartrecruit.R;
import fr.smartrecruit.controller.candidat.ApplicationsAdapter;
import fr.smartrecruit.controller.candidat.ApplicationsController;
import fr.smartrecruit.data.JobOffer;

/**
 * Code du fragment c'est ici qu'on effectue les traitements concernant chaque fragment sur son affichage
 */

public class ApplicationsFragment extends Fragment {
    private RecyclerView applicationsRecycler;
    private ApplicationsAdapter applicationsAdapter;
    private List<JobOffer> applications;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_applications, container, false);
        findViews(view);
        initAdapter();
        refresh(view);
        return view;
    }

    public void findViews(View view) {
        applicationsRecycler = view.findViewById(R.id.listApplications);
    }

    public void initAdapter() {
        final Context context = getContext();
        applications = ApplicationsController.getApplicationsController().getAppliedOffers(context);
        applicationsAdapter = new ApplicationsAdapter(applications, context);
        applicationsRecycler.setAdapter(applicationsAdapter);
        applicationsRecycler.setHasFixedSize(true);
        ApplicationsController.getApplicationsController().setApiAdapter(applicationsAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        applicationsRecycler.setLayoutManager(llm);
    }

    private void refresh(View view){
        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipe_to_refresh_applications);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ApplicationsController.getApplicationsController().refreshApplications();
                applicationsAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}

