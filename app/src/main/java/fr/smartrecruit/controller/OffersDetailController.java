package fr.smartrecruit.controller;

import android.content.Context;

import fr.smartrecruit.api.SmarRecruitApi;
import fr.smartrecruit.data.DataConstants;
import fr.smartrecruit.data.JobOffer;

public class OffersDetailController {
    private Context context;

    public OffersDetailController(Context context) { this.context = context; }

    public void apply(JobOffer offer){
        new SmarRecruitApi(context).apply(offer, DataConstants.APP_ATT_RDV);
    }
}
