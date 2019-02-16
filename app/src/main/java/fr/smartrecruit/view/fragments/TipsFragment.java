package fr.smartrecruit.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.smartrecruit.R;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import fr.smartrecruit.viewmodel.TipsAdapter;
import fr.smartrecruit.viewmodel.TipsViewModel;



public class TipsFragment extends Fragment {




        private TipsViewModel tipsViewModel = new TipsViewModel();
        private RecyclerView tipsRecycler;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_tips, container, false);
            findViews(view);
            initAdapter();
            refresh(view);
            return view;
        }

        public void findViews(View view) {
            tipsRecycler = view.findViewById(R.id.listTips);
        }

        public void initAdapter() {
            TipsAdapter tipsAdapter = new TipsAdapter(tipsViewModel.getTips(), getContext());
            tipsRecycler.setAdapter(tipsAdapter);
            tipsRecycler.setHasFixedSize(true);

            LinearLayoutManager llm = new LinearLayoutManager(getContext());
            tipsRecycler.setLayoutManager(llm);
        }

        private void refresh(View view) {
            final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipe_to_refresh);
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    tipsViewModel.getTips().add(tipsViewModel.createRandomTips());
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
        }


    }
