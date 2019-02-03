package fr.smartrecruit.data;

public class Appointment {
    private String applicantId;
    private String dateHour;
    private JobOffer jobOffer;

    public Appointment(String applicantId, String dateHour, JobOffer jobOffer) {
        this.applicantId = applicantId;
        this.dateHour = dateHour;
        this.jobOffer = jobOffer;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public String getDateHour() {
        return dateHour;
    }

    public JobOffer getJobOffer() {
        return jobOffer;
    }
}
