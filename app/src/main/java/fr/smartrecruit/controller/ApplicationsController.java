package fr.smartrecruit.controller;

import android.content.Context;

import java.util.List;

import fr.smartrecruit.api.SmarRecruitApi;
import fr.smartrecruit.data.JobOffer;

public class ApplicationController {

    private static ApplicationController applicationsController;
    private SmarRecruitApi api;

    private ApplicationController() { }

    public static ApplicationController getApplicationsController(){
        if (applicationsController == null)
            return  applicationsController = new ApplicationController();
        return applicationsController;
    }

    public List<JobOffer> getAppliedOffers(Context context){
        api = new SmarRecruitApi(context);
        api.requestApplications();
        return api.getApplications();
    }

    public void setApiAdapter(ApplicationAdapter adapter){
        api.setApiAdapter(adapter);
    }

    public void refreshApplications(){
        api.requestApplications();
    }
}
