package fr.smartrecruit.controller;

import android.content.Context;

import java.util.List;

import fr.smartrecruit.api.SmarRecruitApi;
import fr.smartrecruit.data.JobOffer;

public class FavoritesController {
    private static FavoritesController favoritesController;
    private SmarRecruitApi api;

    private FavoritesController() { }

    public static FavoritesController getFavoritesController(){
        if (favoritesController == null)
            favoritesController = new FavoritesController();
        return favoritesController;
    }

    public List<JobOffer> getApiFavorites(Context context){
        api = new SmarRecruitApi(context);
        api.requestFavorites();
        return api.getFavoriteOffers();
    }

    public void removeFavorite(String idOffer){
        api.removeFavorite(idOffer);
    }

    public void setApiAdapter(FavoritesAdapter adapter){
        api.setApiAdapter(adapter);
    }

    public void refreshFavorites(){
        api.requestFavorites();
    }
}
