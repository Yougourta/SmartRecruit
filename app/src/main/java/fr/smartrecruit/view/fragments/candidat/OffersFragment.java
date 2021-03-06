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
import fr.smartrecruit.controller.candidat.OffersAdapter;
import fr.smartrecruit.controller.candidat.OffersController;
import fr.smartrecruit.data.DataConstants;
import fr.smartrecruit.data.JobOffer;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

/**
 * Code du fragment c'est ici qu'on effectue les traitements concernant chaque fragment sur son affichage
 */

public class OffersFragment extends Fragment {
    private RecyclerView offersRecycler;
    private OffersAdapter offersAdapter;
    private List<JobOffer> offers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offers, container, false);
        findViews(view);
        initAdapter();
        refresh(view);
        return view;
    }

    public void findViews(View view){
        offersRecycler = view.findViewById(R.id.listOffers);
    }

    public void initAdapter(){
        final Context context = getContext();
        offers = OffersController.getOfferController().getApiOffers(context);
        offersAdapter = new OffersAdapter(offers, context);
        offersRecycler.setAdapter(offersAdapter);
        offersRecycler.setHasFixedSize(true);
        OffersController.getOfferController().setApiAdapter(offersAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        offersRecycler.setLayoutManager(llm);
        offersRecycler.setItemAnimator(new DefaultItemAnimator());
        offersRecycler.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                if (direction == ItemTouchHelper.LEFT) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(true);
                    builder.setTitle("Remove offer");
                    builder.setMessage("Do you really want to permanently remove this offer ?");
                    builder.setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new SmarRecruitApi(context).updateStatus(offers.get(position).getId(), DataConstants.DELETED);
                                    offersAdapter.removeItem(position);
                                    dialog.dismiss();
                                }
                            });
                    builder.setNegativeButton("No",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    offersAdapter.notifyDataSetChanged();
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else if(direction == ItemTouchHelper.RIGHT) {
                    new SmarRecruitApi(context).addFavorite(offers.get(position).getId());
                    offersAdapter.removeItem(position);
                }
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                new RecyclerViewSwipeDecorator.Builder(context, c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(ContextCompat.getColor(context, R.color.deleteColor))
                        .addSwipeLeftActionIcon(R.drawable.ic_delete_white_36dp)
                        .addSwipeRightBackgroundColor(ContextCompat.getColor(context, R.color.favoriteColor))
                        .addSwipeRightActionIcon(R.drawable.ic_favorite_white_36dp)
                        .create()
                        .decorate();
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(offersRecycler);
    }

    private void refresh(View view){
        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipe_to_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                OffersController.getOfferController().refreshApiOffers();
                offersAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
