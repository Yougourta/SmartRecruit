package fr.smartrecruit.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import fr.smartrecruit.R;
import fr.smartrecruit.view.MainActivity;
import fr.smartrecruit.view.OffersActivity;

public class MainActivityViewModel {

    private Context context;
    public void setViews(MainActivity view, Context context){
        this.context = context;
    }

    public void login(View v){
        Intent intentLogin = new Intent(context, OffersActivity.class);
        context.startActivity(intentLogin);
    }
}
