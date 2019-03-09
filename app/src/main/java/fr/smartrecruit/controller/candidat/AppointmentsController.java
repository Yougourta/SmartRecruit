package fr.smartrecruit.controller.candidat;


import android.content.Context;

import java.util.List;

import fr.smartrecruit.api.SmarRecruitApi;
import fr.smartrecruit.data.Appointment;

public class AppointmentsController {
    private static AppointmentsController appointmentsController;
    private SmarRecruitApi api;

    private AppointmentsController() {}

    public static AppointmentsController getAppointmentsController(){
        if (appointmentsController == null)
            appointmentsController = new AppointmentsController();
        return appointmentsController;
    }

    public List<Appointment> getAppointments(Context context){
        api = new SmarRecruitApi(context);
        api.requestApplicantScheduledAppointments();
        return api.getApplicantScheduledAppointments();
    }
    public void setApiAdapter(AppointmentsAdapter adapter){
        api.setApiAdapter(adapter);
    }
    public void refreshAppointments(){
        api.requestApplicantScheduledAppointments();
    }
}
