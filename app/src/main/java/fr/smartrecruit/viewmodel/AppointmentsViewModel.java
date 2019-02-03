package fr.smartrecruit.viewmodel;


import java.util.ArrayList;
import java.util.List;

import fr.smartrecruit.data.Appointment;
import fr.smartrecruit.data.JobOffer;

public class AppointmentsViewModel {
    private List<Appointment> appointments = new ArrayList();
    private final String APPLICANT_ID = "Random Applicant ID";
    private final String DATE_HOUR = "01/28/2019 : 10PM";
    private final JobOffer jobOffer = new OffersViewModel().createRandomOffer();

    public List<Appointment> getAppointmentsList(){
        Appointment appointment = new Appointment(APPLICANT_ID, DATE_HOUR, jobOffer);
        for(int i=0; i<3; i++){ appointments.add(appointment); }
        return appointments;
    }
}
