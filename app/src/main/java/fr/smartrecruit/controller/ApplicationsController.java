package fr.smartrecruit.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.smartrecruit.data.Applicant;
import fr.smartrecruit.data.JobOffer;

public class ApplicationsController {

    public List<JobOffer> getAppliedOffers(){
        Set<JobOffer> appliedOffers = new HashSet<>(Applicant.getApplicant().getAppliedOffers());
        Applicant.getApplicant().getAppliedOffers().clear();
        Applicant.getApplicant().getAppliedOffers().addAll(appliedOffers);
        return Applicant.getApplicant().getAppliedOffers();
    }
}
