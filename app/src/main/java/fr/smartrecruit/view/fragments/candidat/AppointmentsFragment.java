package fr.smartrecruit.view.fragments.candidat;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.smartrecruit.R;
import fr.smartrecruit.controller.candidat.AppointmentsAdapter;
import fr.smartrecruit.controller.candidat.AppointmentsController;
import fr.smartrecruit.data.Appointment;

public class AppointmentsFragment extends Fragment {
    private RecyclerView appointmentsRecycler;
    private AppointmentsAdapter appointmentsAdapter;
    private List<Appointment> appointments;

    public AppointmentsFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_appointments, container, false);
        findViews(view);
        initAdapter();
        refresh(view);
        return view;
    }

    public void findViews(View view){
        appointmentsRecycler = view.findViewById(R.id.listAppointments);
    }

    public void initAdapter(){
        final Context context = getContext();
        appointments = AppointmentsController.getAppointmentsController().getAppointments(context);
        appointmentsAdapter = new AppointmentsAdapter(appointments);
        appointmentsRecycler.setAdapter(appointmentsAdapter);
        appointmentsRecycler.setHasFixedSize(true);
        AppointmentsController.getAppointmentsController().setApiAdapter(appointmentsAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        appointmentsRecycler.setLayoutManager(llm);
        appointmentsRecycler.setItemAnimator(new DefaultItemAnimator());
        appointmentsRecycler.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
    }

    private void refresh(View view){
        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipe_to_refresh_appointments);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                AppointmentsController.getAppointmentsController().refreshAppointments();
                appointmentsAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
