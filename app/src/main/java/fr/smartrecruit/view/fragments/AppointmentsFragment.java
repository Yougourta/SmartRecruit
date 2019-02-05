package fr.smartrecruit.view.fragments;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.smartrecruit.R;
import fr.smartrecruit.controller.AppointmentsAdapter;
import fr.smartrecruit.controller.AppointmentsController;
import fr.smartrecruit.controller.AppointmentsSwipeController;
import fr.smartrecruit.controller.AppointmentsSwipeControllerActions;

public class AppointmentsFragment extends Fragment {
    private AppointmentsController appointmentsController = new AppointmentsController();
    private RecyclerView appointmentsRecycler;
    private AppointmentsSwipeController swipeController = null;
    private AppointmentsAdapter appointmentsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_applications, container, false);
        findViews(view);
        initAdapter();
        return view;
    }

    public void findViews(View view) {
        appointmentsRecycler = view.findViewById(R.id.listApplications);
    }

    public void initAdapter() {
        appointmentsAdapter = new AppointmentsAdapter(appointmentsController.getAppointmentsList());
        appointmentsRecycler.setAdapter(appointmentsAdapter);
        appointmentsRecycler.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        appointmentsRecycler.setLayoutManager(llm);

        swipeController = new AppointmentsSwipeController(new AppointmentsSwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                appointmentsAdapter.appointments.remove(position);
                appointmentsAdapter.notifyItemRemoved(position);
                appointmentsAdapter.notifyItemRangeChanged(position, appointmentsAdapter.getItemCount());
            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(appointmentsRecycler);

        appointmentsRecycler.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });
        
    }
}
