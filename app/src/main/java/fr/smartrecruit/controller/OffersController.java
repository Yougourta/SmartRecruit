package fr.smartrecruit.controller;

import android.content.Context;
import java.util.List;

import fr.smartrecruit.api.SmarRecruitApi;
import fr.smartrecruit.data.JobOffer;

public class OffersController {

    private static OffersController offersController;

    private OffersController() { }

    public static OffersController getOfferController(){
        if (offersController == null)
            offersController = new OffersController();
        return offersController;
    }

    private SmarRecruitApi api;

    public List<JobOffer> getApiOffers(Context context){
        api = new SmarRecruitApi(context);
        api.requestOffers();
        return api.getOffers();
    }

    public void setApiAdapter(OffersAdapter adapter){
        api.setApiAdapter(adapter);
    }

    public void refreshApiOffers(){
        api.requestOffers();
    }
}
