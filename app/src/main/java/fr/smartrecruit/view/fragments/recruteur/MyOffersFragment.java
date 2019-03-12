package fr.smartrecruit.view.fragments.recruteur;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import fr.smartrecruit.R;
import fr.smartrecruit.controller.recruiter.MyOffersAdapter;
import fr.smartrecruit.controller.recruiter.MyOffersController;
import fr.smartrecruit.data.JobOffer;

public class MyOffersFragment extends Fragment {

    private RecyclerView offersRecycler;
    private MyOffersAdapter adapter;
    private JobOffer myoffer;
    public MyOffersFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_offers, container, false);
        findViews(view);
        initAdapter();
        refresh(view);
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 final Dialog dialog= new Dialog(getContext());
                 dialog.setContentView(R.layout.fragment_formulaire_add_offre);
                 dialog.show();
                 final EditText position = dialog.findViewById(R.id.position);
                 final EditText company = dialog.findViewById(R.id.company);
                 final EditText localisation = dialog.findViewById(R.id.localisation);
                 final EditText desc = dialog.findViewById(R.id.descriptif);
                 Button ajouter = dialog.findViewById(R.id.ajouterOffre);
                 ajouter.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                        if(!position.getText().toString().isEmpty() && !localisation.getText().toString().isEmpty() && !company.getText().toString().isEmpty() && !desc.getText().toString().isEmpty()){
                            String pattern = "dd/mm/yyyy";
                            DateFormat df = new SimpleDateFormat(pattern);
                            Date today = Calendar.getInstance().getTime();
                            String todayAsString = df.format(today);

                            myoffer = new JobOffer();
                            String uniqueID = UUID.randomUUID().toString();
                            myoffer.setId(uniqueID);
                            myoffer.setPosition(position.getText().toString());
                            myoffer.setCompany(company.getText().toString());
                            myoffer.setLocation(localisation.getText().toString());
                            myoffer.setDatePosted(todayAsString);
                            MyOffersController.getMyOffersController().createOffer(myoffer);
                            dialog.cancel();
                        }else{
                            Toast.makeText(getContext(),
                                    "Verifier les champs vides",
                                    Toast.LENGTH_SHORT).show();
                        }
                     }
                 });
            }
        });
        return view;
    }

    public void findViews(View view){
        offersRecycler = view.findViewById(R.id.listMyOffers);
    }

    public void initAdapter(){
        final Context context = getContext();
        List<JobOffer> offers = MyOffersController.getMyOffersController().getApiMyOffers(context);
        adapter = new MyOffersAdapter(offers, context);
        offersRecycler.setAdapter(adapter);
        offersRecycler.setHasFixedSize(true);
        MyOffersController.getMyOffersController().setApiAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        offersRecycler.setLayoutManager(llm);
        offersRecycler.setItemAnimator(new DefaultItemAnimator());
        offersRecycler.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
    }

    private void refresh(View view){
        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipe_to_refresh_myOffers);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MyOffersController.getMyOffersController().refreshMyOffers();
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
