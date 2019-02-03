package fr.smartrecruit.data;

import java.util.ArrayList;
import java.util.List;

public class Applicant {
    private String name;
    private List<JobOffer> appliedOffers;
    private static Applicant applicant;

    private Applicant (String name,  List<JobOffer> appliedOffers){
        this.name = name;
        this.appliedOffers = appliedOffers;
    }

    public static Applicant getApplicant(){
        if(applicant==null)
            applicant = new Applicant("SmartRecruit", new ArrayList<JobOffer>());
        return  applicant;
    }

    public String getName() {
        return name;
    }

    public List<JobOffer> getAppliedOffers() {
        return appliedOffers;
    }
}