package fr.smartrecruit.view.fragments.recruteur;

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
import fr.smartrecruit.controller.recruiter.MyScheduledAppointmentsAdapter;
import fr.smartrecruit.controller.recruiter.MyScheduledAppointmentsController;
import fr.smartrecruit.data.RecAppointment;

public class MyScheduledAppointmentsFragment extends Fragment {
    private RecyclerView appointmentsRecycler;
    private MyScheduledAppointmentsAdapter appointmentsAdapter;

    public MyScheduledAppointmentsFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_scheduled_appointments, container, false);
        findViews(view);
        initAdapter();
        refresh(view);
        return view;
    }

    public void findViews(View view){
        appointmentsRecycler = view.findViewById(R.id.listScheduledAppointments);
    }

    public void initAdapter(){
        final Context context = getContext();
        List<RecAppointment> appointments = MyScheduledAppointmentsController.getMyScheduledAppointmentsController().getRecruiterAppointments(context);
        appointmentsAdapter = new MyScheduledAppointmentsAdapter(appointments);
        appointmentsRecycler.setAdapter(appointmentsAdapter);
        appointmentsRecycler.setHasFixedSize(true);
        MyScheduledAppointmentsController.getMyScheduledAppointmentsController().setApiAdapter(appointmentsAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        appointmentsRecycler.setLayoutManager(llm);
        appointmentsRecycler.setItemAnimator(new DefaultItemAnimator());
        appointmentsRecycler.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
    }

    private void refresh(View view){
        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipe_to_refresh_scheduled_appointments);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MyScheduledAppointmentsController.getMyScheduledAppointmentsController().refreshMyScheduledAppointments();
                appointmentsAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
