package fr.smartrecruit.viewmodel;

import java.util.List;

import fr.smartrecruit.data.Applicant;
import fr.smartrecruit.data.JobOffer;

public class OffersDetailViewModel {

    public List<JobOffer> getAppliedOffers() {
        return Applicant.getApplicant().getAppliedOffers();
    }

    public void addAppliedOffer(JobOffer offer){
        Applicant.getApplicant().getAppliedOffers().add(offer);
    }
}
