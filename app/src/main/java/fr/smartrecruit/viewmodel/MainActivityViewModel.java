package fr.smartrecruit.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import fr.smartrecruit.R;
import fr.smartrecruit.view.MainActivity;
import fr.smartrecruit.view.OffersActivity;

public class MainActivityViewModel {

    public void setViews(MainActivity view, final Context context){
        Button candidat = view.findViewById(R.id.loginCandidat);
        candidat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(context, OffersActivity.class);
                context.startActivity(intentLogin);
            }
        });
    }
}
