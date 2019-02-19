package fr.smartrecruit.controller;

import android.content.Context;

import fr.smartrecruit.api.SmarRecruitApi;
import fr.smartrecruit.data.Applicant;
import fr.smartrecruit.data.JobOffer;

public class OffersDetailController {
    private Context context;

    public OffersDetailController(Context context) { this.context = context; }

    public void addAppliedOffer(JobOffer offer){
        new SmarRecruitApi(context).applyToOffer(offer, Applicant.getApplicant());
        Applicant.getApplicant().getAppliedOffers().add(offer);
    }
}
