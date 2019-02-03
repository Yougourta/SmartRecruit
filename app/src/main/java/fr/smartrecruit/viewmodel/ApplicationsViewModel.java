package fr.smartrecruit.viewmodel;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.smartrecruit.data.Applicant;
import fr.smartrecruit.data.JobOffer;

public class ApplicationsViewModel {

    public List<JobOffer> getAppliedOffers(){
        Set<JobOffer> appliedOffers = new HashSet<>(Applicant.getApplicant().getAppliedOffers());
        Applicant.getApplicant().getAppliedOffers().clear();
        Applicant.getApplicant().getAppliedOffers().addAll(appliedOffers);
        return Applicant.getApplicant().getAppliedOffers();
    }

    public void addAppliedOffer(JobOffer offer){
        Applicant.getApplicant().getAppliedOffers().add(offer);
    }
}
