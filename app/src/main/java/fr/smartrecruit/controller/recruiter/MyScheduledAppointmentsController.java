package fr.smartrecruit.controller.recruiter;

import android.content.Context;

import java.util.List;

import fr.smartrecruit.api.SmarRecruitApi;
import fr.smartrecruit.data.RecAppointment;

public class MyScheduledAppointmentsController {
    private static MyScheduledAppointmentsController myScheduledAppointmentsController;
    private SmarRecruitApi api;

    private MyScheduledAppointmentsController() { }

    public static MyScheduledAppointmentsController getMyScheduledAppointmentsController(){
        if ( myScheduledAppointmentsController == null)
            myScheduledAppointmentsController = new MyScheduledAppointmentsController();
        return myScheduledAppointmentsController;
    }

    public List<RecAppointment> getRecruiterAppointments(Context context){
        api = new SmarRecruitApi(context);
        api.requestRecruiterScheduledAppointments();
        return api.getRecruiterScheduledAppointments();
    }

    public void refreshMyScheduledAppointments(){
        api.requestRecruiterScheduledAppointments();
    }

    public void setApiAdapter(MyScheduledAppointmentsAdapter adapter) { api.setApiAdapter(adapter); }
}
