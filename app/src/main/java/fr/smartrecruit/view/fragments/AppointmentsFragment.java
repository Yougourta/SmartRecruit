package fr.smartrecruit.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.smartrecruit.R;
import fr.smartrecruit.viewmodel.AppointmentsAdapter;
import fr.smartrecruit.viewmodel.AppointmentsViewModel;

public class AppointmentsFragment extends Fragment {
    private AppointmentsViewModel appointmentsViewModel = new AppointmentsViewModel();
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
        AppointmentsAdapter appointmentsAdapter = new AppointmentsAdapter(appointmentsViewModel.getAppointmentsList());
        applicationsRecycler.setAdapter(appointmentsAdapter);
        applicationsRecycler.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        applicationsRecycler.setLayoutManager(llm);

        
    }
}
