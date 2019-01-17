package fr.smartrecruit.viewmodel;

import android.content.Context;
import android.content.Intent;

import fr.smartrecruit.view.OffersActivity;

public class MainActivityViewModel {

    public void handle(Context context){
        Intent intentLogin = new Intent(context, OffersActivity.class);
        context.startActivity(intentLogin);
    }
}
