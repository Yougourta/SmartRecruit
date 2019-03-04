package fr.smartrecruit.view.fragments.recruteur;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import fr.smartrecruit.R;
import fr.smartrecruit.controller.recruiter.MyAppointmentsAdapter;
import fr.smartrecruit.controller.recruiter.MyAppointmentsController;
import fr.smartrecruit.data.RecAppointment;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class MyAppointmentsFragment extends Fragment {
    private RecyclerView appointmentsRecycler;
    private MyAppointmentsAdapter appointmentsAdapter;
    private List<RecAppointment> appointments;

    public MyAppointmentsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_appointments, container, false);
        findViews(view);
        initAdapter();
        refresh(view);
        return view;
    }

    public void findViews(View view){
        appointmentsRecycler = view.findViewById(R.id.listRecAppointments);
    }

    public void initAdapter(){
        final Context context = getContext();
        appointments = MyAppointmentsController.getMyAppointmentsController().getAppointments(context);
        appointmentsAdapter = new MyAppointmentsAdapter(appointments);
        appointmentsRecycler.setAdapter(appointmentsAdapter);
        appointmentsRecycler.setHasFixedSize(true);
        MyAppointmentsController.getMyAppointmentsController().setApiAdapter(appointmentsAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        appointmentsRecycler.setLayoutManager(llm);
        appointmentsRecycler.setItemAnimator(new DefaultItemAnimator());
        appointmentsRecycler.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                if (direction == ItemTouchHelper.LEFT)
                    // Mark application rejected
                    Toast.makeText(getContext(), "Application rejected", Toast.LENGTH_SHORT).show();
                else if (direction == ItemTouchHelper.RIGHT)
                    // Launch date and time picker
                    Toast.makeText(getContext(), "Pick date and time", Toast.LENGTH_SHORT).show();
                appointmentsAdapter.removeItem(position);
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                new RecyclerViewSwipeDecorator.Builder(context, c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(ContextCompat.getColor(context, R.color.deleteColor))
                        .addSwipeLeftActionIcon(R.drawable.ic_delete_white_36dp)
                        .addSwipeRightBackgroundColor(ContextCompat.getColor(context, R.color.favoriteColor))
                        .addSwipeRightActionIcon(R.drawable.ic_check_white_36dp)
                        .create()
                        .decorate();
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(appointmentsRecycler);
    }

    private void refresh(View view){
        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipe_to_refresh_recappointments);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MyAppointmentsController.getMyAppointmentsController().refreshAppointments();
                appointmentsAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
