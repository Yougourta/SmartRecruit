package fr.smartrecruit.controller.recruiter;

import android.content.Context;
import java.util.List;
import fr.smartrecruit.api.SmarRecruitApi;
import fr.smartrecruit.data.JobOffer;

public class MyOffersController {

    private static MyOffersController myOffersController;
    private SmarRecruitApi api;

    private MyOffersController() { }

    public static MyOffersController getMyOffersController(){
        if ( myOffersController == null)
            myOffersController = new MyOffersController();
        return myOffersController;
    }

    public List<JobOffer> getApiMyOffers(Context context){
        api = new SmarRecruitApi(context);
        api.requestOffersRecruiter();
        return api.getMyOffers();
    }

    public void setApiAdapter(MyOffersAdapter adapter){
        api.setApiAdapter(adapter);
    }


}
