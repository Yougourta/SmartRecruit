package fr.smartrecruit.view.fragments.candidat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.smartrecruit.R;
import fr.smartrecruit.controller.candidat.AppointmentsAdapter;
import fr.smartrecruit.controller.candidat.AppointmentsController;

public class AppointmentsFragment extends Fragment {
    private AppointmentsController appointmentsController = new AppointmentsController();
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
        AppointmentsAdapter appointmentsAdapter = new AppointmentsAdapter(null);
        applicationsRecycler.setAdapter(appointmentsAdapter);
        applicationsRecycler.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        applicationsRecycler.setLayoutManager(llm);
    }
}
