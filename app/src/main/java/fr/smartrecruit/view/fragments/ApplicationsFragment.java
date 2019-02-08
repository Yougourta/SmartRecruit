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
import fr.smartrecruit.controller.ApplicationsAdapter;
import fr.smartrecruit.controller. ApplicationsController;
import fr.smartrecruit.controller.ApplicationsSwipeController;
import fr.smartrecruit.controller.ApplicationsSwipeControllerActions;

/**
 * Code du fragment c'est ici qu'on effectue les traitements concernant chaque fragment sur son affichage
 */

public class ApplicationsFragment extends Fragment {
    private ApplicationsController applicationsController = new ApplicationsController();
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
        ApplicationsAdapter = new ApplicationsAdapter (ApplicationsController.getAppointmentsList());

        applicationsRecycler.setAdapter(ApplicationsAdapter);
        applicationsRecycler.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        applicationsRecycler.setLayoutManager(llm);

        swipeController = new ApplicationsSwipeController(new ApplicationsSwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                applicationsController.applications.remove(position);
                applicationsAdapter.notifyItemRemoved(position);
                applicationsAdapter.notifyItemRangeChanged(position, applicationsAdapter.getItemCount());
            }

            @Override
            public void onLeftClicked(int position) {

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

