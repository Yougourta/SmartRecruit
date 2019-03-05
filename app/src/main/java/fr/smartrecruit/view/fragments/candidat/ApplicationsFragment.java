package fr.smartrecruit.view.fragments.candidat;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.smartrecruit.R;
import fr.smartrecruit.api.SmarRecruitApi;
import fr.smartrecruit.controller.candidat.ApplicationsAdapter;
import fr.smartrecruit.controller.candidat.ApplicationsController;
import fr.smartrecruit.data.DataConstants;
import fr.smartrecruit.data.JobOffer;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

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
        applicationsRecycler.setItemAnimator(new DefaultItemAnimator());
        applicationsRecycler.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                builder.setTitle("Cancel application");
                builder.setMessage("Do you really want to cancel your application ?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new SmarRecruitApi(context).updateStatus(applications.get(position).getId(), DataConstants.DELETED);
                                applicationsAdapter.removeItem(position);
                                dialog.dismiss();
                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                applicationsAdapter.notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                new RecyclerViewSwipeDecorator.Builder(context, c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(ContextCompat.getColor(context, R.color.deleteColor))
                        .addSwipeLeftActionIcon(R.drawable.ic_delete_white_36dp)
                        .create()
                        .decorate();
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(applicationsRecycler);
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

