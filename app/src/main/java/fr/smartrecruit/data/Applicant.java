package fr.smartrecruit.data;

import java.util.List;

public class Applicant {
    private String name;
    private List<JobOffer> appliedOffers;

    public Applicant(String name, List<JobOffer> appliedOffers){
        this.name = name;
        this.appliedOffers = appliedOffers;
    }

    public String getName() {
        return name;
    }

    public List<JobOffer> getAppliedOffers() {
        return appliedOffers;
    }
}
