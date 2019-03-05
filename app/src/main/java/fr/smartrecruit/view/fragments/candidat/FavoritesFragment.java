package fr.smartrecruit.view.fragments.candidat;

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

import java.util.List;

import fr.smartrecruit.R;
import fr.smartrecruit.controller.candidat.FavoritesAdapter;
import fr.smartrecruit.controller.candidat.FavoritesController;
import fr.smartrecruit.controller.candidat.OffersDetailController;
import fr.smartrecruit.data.JobOffer;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class FavoritesFragment extends Fragment {
    private RecyclerView favoriteOffersRecycler;
    private FavoritesAdapter favoriteOffersAdapter;
    private List<JobOffer> favoriteOffers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        findViews(view);
        initAdapter();
        refresh(view);
        return view;
    }

    public void findViews(View view){
        favoriteOffersRecycler = view.findViewById(R.id.listFavoriteOffers);
    }

    public void initAdapter(){
        final Context context = getContext();
        favoriteOffers = FavoritesController.getFavoritesController().getApiFavorites(context);
        favoriteOffersAdapter = new FavoritesAdapter(favoriteOffers, context);
        favoriteOffersRecycler.setAdapter(favoriteOffersAdapter);
        favoriteOffersRecycler.setHasFixedSize(true);
        FavoritesController.getFavoritesController().setApiAdapter(favoriteOffersAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        favoriteOffersRecycler.setLayoutManager(llm);
        favoriteOffersRecycler.setItemAnimator(new DefaultItemAnimator());
        favoriteOffersRecycler.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                if(direction == ItemTouchHelper.LEFT){
                    FavoritesController.getFavoritesController().removeFavorite(favoriteOffers.get(position).getId());
                    favoriteOffersAdapter.removeItem(position);
                }else if(direction == ItemTouchHelper.RIGHT){
                    OffersDetailController.getOffersDetailController().apply(context, favoriteOffers.get(position).getId());
                    favoriteOffersAdapter.removeItem(position);
                }
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                new RecyclerViewSwipeDecorator.Builder(context, c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(ContextCompat.getColor(context, R.color.deleteColor))
                        .addSwipeLeftActionIcon(R.drawable.ic_favorite_border_white_36dp)
                        .addSwipeRightBackgroundColor(ContextCompat.getColor(context, R.color.favoriteColor))
                        .addSwipeRightActionIcon(R.drawable.ic_check_white_36dp)
                        .create()
                        .decorate();
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(favoriteOffersRecycler);
    }

    private void refresh(View view){
        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipe_to_refresh_favorites);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                FavoritesController.getFavoritesController().refreshFavorites();
                favoriteOffersAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
