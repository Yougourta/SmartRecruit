package fr.smartrecruit.controller.recruiter;

import android.content.Context;

import java.util.List;

import fr.smartrecruit.api.SmarRecruitApi;
import fr.smartrecruit.data.RecAppointment;

public class MyAppointmentsController {
    private static MyAppointmentsController myAppointmentsController;
    private SmarRecruitApi api;

    private MyAppointmentsController() {}

    public static MyAppointmentsController getMyAppointmentsController(){
        if (myAppointmentsController == null)
            myAppointmentsController = new MyAppointmentsController();
        return myAppointmentsController;
    }

    public List<RecAppointment> getAppointments(Context context){
        api = new SmarRecruitApi(context);
        api.requestRecruiterAppointments();
        return api.getRecruiterAppointments();
    }

    public void rejectApplication(String offerId){
        api.rejectApplication(offerId);
    }
    public void setAppointment(String offerId, String date, String time){
        api.setAppointment(offerId, date, time);
    }


    public void setApiAdapter(MyAppointmentsAdapter adapter){
        api.setApiAdapter(adapter);
    }
    public void refreshAppointments(){
        api.requestRecruiterAppointments();
    }
}
