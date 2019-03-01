package fr.smartrecruit.controller;

import android.content.Context;

import java.util.List;

import fr.smartrecruit.api.SmarRecruitApi;
import fr.smartrecruit.data.JobOffer;

public class FavoritesController {
    private static FavoritesController favoritesOffersController;
    private SmarRecruitApi api;

    private FavoritesController() { }

    public static FavoritesController getFavoritesController(){
        if (favoritesOffersController == null)
            favoritesOffersController = new FavoritesController();
        return favoritesOffersController;
    }

    public List<JobOffer> getApiFavorites(Context context){
        api = new SmarRecruitApi(context);
        api.requestFavorites();
        return api.getFavoriteOffers();
    }

    public void setApiAdapter(FavoritesAdapter adapter){
        api.setApiAdapter(adapter);
    }

    public void refreshApiOffers(){
        api.requestOffers();
    }
}
