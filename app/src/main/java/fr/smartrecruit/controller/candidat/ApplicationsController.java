package fr.smartrecruit.controller.candidat;

import android.content.Context;

import java.util.List;

import fr.smartrecruit.api.SmarRecruitApi;
import fr.smartrecruit.data.JobOffer;

public class ApplicationsController {

    private static ApplicationsController applicationsController;
    private SmarRecruitApi api;

    private ApplicationsController() { }

    public static ApplicationsController getApplicationsController(){
        if (applicationsController == null)
            return  applicationsController = new ApplicationsController();
        return applicationsController;
    }

    public List<JobOffer> getAppliedOffers(Context context){
        api = new SmarRecruitApi(context);
        api.requestApplications();
        return api.getApplications();
    }

    public void setApiAdapter(ApplicationsAdapter adapter){
        api.setApiAdapter(adapter);
    }

    public void refreshApplications(){
        api.requestApplications();
    }
}
